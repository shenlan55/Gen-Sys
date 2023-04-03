package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义积木报表鉴权(如果不进行自定义，则所有请求不做权限控制)
 *
 * @Author lasu
 * @Date 2023/4/3 14:40
 */
@Component
public class JimuReportTokenService implements JmReportTokenServiceI {

    @Autowired
    private TokenService tokenService;

    @Override
    public String getToken(HttpServletRequest request) {
        //先从url参数中取，若没有，再从header中取
        String paramToken = request.getParameter("token");
        String headerToken = request.getHeader("token");
        if (StringUtils.isEmpty(paramToken)) {
            paramToken = headerToken;
        }
        LoginUser loginUser = tokenService.getLoginUser(paramToken);
        if (loginUser != null) {
            return paramToken;
        }
        return "";
    }

    @Override
    public String getUsername(String token) {
        LoginUser loginUser = tokenService.getLoginUser(token);
        return loginUser.getUsername();
    }

    /**
     * 校验设计器权限
     * @param token
     * @return
     */
    @Override
    public Boolean verifyToken(String token) {
        LoginUser loginUser = tokenService.getLoginUser(token);
        if (StringUtils.isNotNull(loginUser)) {
            //刷新Token有效期
            tokenService.refreshToken(loginUser);
            //超管不需要鉴权
            if(loginUser.getUser() != null && loginUser.getUser().isAdmin()){
                return true;
            }else{
                //校验菜单权限
                Set<String> permissions = loginUser.getPermissions();
                if(permissions != null && permissions.contains("report:jimu:list")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 系统上下文变量(http://report.jeecg.com/2159712)
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String token) {
        Map<String, Object> map = new HashMap(5);
        LoginUser loginUser = tokenService.getLoginUser(token);
        //设置用户名
        map.put(SYS_USER_CODE, loginUser.getUsername());
        //设置部门编码
        map.put(SYS_ORG_CODE, loginUser.getDeptId());
        // 将所有信息存放至map 解析sql/api会根据map的键值解析
        return map;
    }

    @Override
    public HttpHeaders customApiHeader() {

        HttpHeaders header = new HttpHeaders();

        //主要用于API数据源。默认给API数据源的header中携带上Token。
        //如使用当前项目的API，则需要在header中携带Authorization头
        header.add(tokenService.getHeader(), getToken());

        header.add("token", getToken());
        header.add("X-Access-Token", getToken());
        return header;
    }
}

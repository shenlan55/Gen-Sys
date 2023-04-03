package com.ruoyi.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 拦截积木报表查看访问路由，校验权限
 *
 * @Author lasu
 * @Date 2023/4/3 14:40
 */
@Component
public class JmReportInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");
        LoginUser loginUser = tokenService.getLoginUser(token);
        if (loginUser != null) {
            //超管不需要鉴权
            if(loginUser.getUser() != null && loginUser.getUser().isAdmin()){
                return true;
            }else{
                //获取权限集合
                Set<String> permissions = loginUser.getPermissions();
                //如果拥有设计器的权限，则无需view权限，也可以通过校验
                if(permissions != null && permissions.contains("report:jimu:list")){
                    return true;
                }
                //其余情况，一般是通过报表菜单点击进来的，校验是否有对应报表的权限：report:jimu:view:{reportId}
                //http../jmreport/view/717968580806651904，则reportId = 717968580806651904
                String reportId = StringUtils.substringAfterLast(request.getRequestURI(), "/");
                String viewPerm = "report:jimu:view:" + reportId;
                if(permissions != null && permissions.contains(viewPerm)){
                    return true;
                }
            }
        }
        AjaxResult ajaxResult = AjaxResult.error("参数错误或没有该报表的访问权限！");
        ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
        return false;
    }
}


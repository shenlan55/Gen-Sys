package com.ruoyi.portal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门户应用对象 portal_app
 * 
 * @author lasu
 * @date 2023-04-25
 */
public class PortalApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用ID */
    private Long appId;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appName;

    /** 应用分类 */
    @Excel(name = "应用分类")
    private String appClass;

    /** 应用路由 */
    @Excel(name = "应用路由")
    private String router;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long sortnum;

    /** 0表示启用1表示停用 */
    @Excel(name = "0表示启用1表示停用")
    private String status;

    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setAppClass(String appClass) 
    {
        this.appClass = appClass;
    }

    public String getAppClass() 
    {
        return appClass;
    }
    public void setRouter(String router) 
    {
        this.router = router;
    }

    public String getRouter() 
    {
        return router;
    }
    public void setSortnum(Long sortnum) 
    {
        this.sortnum = sortnum;
    }

    public Long getSortnum() 
    {
        return sortnum;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appId", getAppId())
            .append("appName", getAppName())
            .append("appClass", getAppClass())
            .append("router", getRouter())
            .append("sortnum", getSortnum())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

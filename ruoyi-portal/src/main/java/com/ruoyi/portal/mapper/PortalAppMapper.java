package com.ruoyi.portal.mapper;

import java.util.List;
import com.ruoyi.portal.domain.PortalApp;

/**
 * 门户应用Mapper接口
 * 
 * @author lasu
 * @date 2023-04-25
 */
public interface PortalAppMapper 
{
    /**
     * 查询门户应用
     * 
     * @param appId 门户应用主键
     * @return 门户应用
     */
    public PortalApp selectPortalAppByAppId(Long appId);

    /**
     * 查询门户应用列表
     * 
     * @param portalApp 门户应用
     * @return 门户应用集合
     */
    public List<PortalApp> selectPortalAppList(PortalApp portalApp);

    /**
     * 新增门户应用
     * 
     * @param portalApp 门户应用
     * @return 结果
     */
    public int insertPortalApp(PortalApp portalApp);

    /**
     * 修改门户应用
     * 
     * @param portalApp 门户应用
     * @return 结果
     */
    public int updatePortalApp(PortalApp portalApp);

    /**
     * 删除门户应用
     * 
     * @param appId 门户应用主键
     * @return 结果
     */
    public int deletePortalAppByAppId(Long appId);

    /**
     * 批量删除门户应用
     * 
     * @param appIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePortalAppByAppIds(Long[] appIds);
}

package com.ruoyi.portal.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.portal.mapper.PortalAppMapper;
import com.ruoyi.portal.domain.PortalApp;
import com.ruoyi.portal.service.IPortalAppService;

/**
 * 门户应用Service业务层处理
 * 
 * @author lasu
 * @date 2023-04-25
 */
@Service
public class PortalAppServiceImpl implements IPortalAppService 
{
    @Autowired
    private PortalAppMapper portalAppMapper;

    /**
     * 查询门户应用
     * 
     * @param appId 门户应用主键
     * @return 门户应用
     */
    @Override
    public PortalApp selectPortalAppByAppId(Long appId)
    {
        return portalAppMapper.selectPortalAppByAppId(appId);
    }

    /**
     * 查询门户应用列表
     * 
     * @param portalApp 门户应用
     * @return 门户应用
     */
    @Override
    public List<PortalApp> selectPortalAppList(PortalApp portalApp)
    {
        return portalAppMapper.selectPortalAppList(portalApp);
    }

    /**
     * 新增门户应用
     * 
     * @param portalApp 门户应用
     * @return 结果
     */
    @Override
    public int insertPortalApp(PortalApp portalApp)
    {
        portalApp.setCreateTime(DateUtils.getNowDate());
        return portalAppMapper.insertPortalApp(portalApp);
    }

    /**
     * 修改门户应用
     * 
     * @param portalApp 门户应用
     * @return 结果
     */
    @Override
    public int updatePortalApp(PortalApp portalApp)
    {
        portalApp.setUpdateTime(DateUtils.getNowDate());
        return portalAppMapper.updatePortalApp(portalApp);
    }

    /**
     * 批量删除门户应用
     * 
     * @param appIds 需要删除的门户应用主键
     * @return 结果
     */
    @Override
    public int deletePortalAppByAppIds(Long[] appIds)
    {
        return portalAppMapper.deletePortalAppByAppIds(appIds);
    }

    /**
     * 删除门户应用信息
     * 
     * @param appId 门户应用主键
     * @return 结果
     */
    @Override
    public int deletePortalAppByAppId(Long appId)
    {
        return portalAppMapper.deletePortalAppByAppId(appId);
    }
}

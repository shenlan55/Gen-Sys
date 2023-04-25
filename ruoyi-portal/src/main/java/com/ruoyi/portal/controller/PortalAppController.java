package com.ruoyi.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.portal.domain.PortalApp;
import com.ruoyi.portal.service.IPortalAppService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门户应用Controller
 * 
 * @author lasu
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/portal/app")
public class PortalAppController extends BaseController
{
    @Autowired
    private IPortalAppService portalAppService;

    /**
     * 查询门户应用列表
     */
    @PreAuthorize("@ss.hasPermi('portal:app:list')")
    @GetMapping("/list")
    public TableDataInfo list(PortalApp portalApp)
    {
        startPage();
        List<PortalApp> list = portalAppService.selectPortalAppList(portalApp);
        return getDataTable(list);
    }

    /**
     * 导出门户应用列表
     */
    @PreAuthorize("@ss.hasPermi('portal:app:export')")
    @Log(title = "门户应用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PortalApp portalApp)
    {
        List<PortalApp> list = portalAppService.selectPortalAppList(portalApp);
        ExcelUtil<PortalApp> util = new ExcelUtil<PortalApp>(PortalApp.class);
        util.exportExcel(response, list, "门户应用数据");
    }

    /**
     * 获取门户应用详细信息
     */
    @PreAuthorize("@ss.hasPermi('portal:app:query')")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") Long appId)
    {
        return success(portalAppService.selectPortalAppByAppId(appId));
    }

    /**
     * 新增门户应用
     */
    @PreAuthorize("@ss.hasPermi('portal:app:add')")
    @Log(title = "门户应用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PortalApp portalApp)
    {
        return toAjax(portalAppService.insertPortalApp(portalApp));
    }

    /**
     * 修改门户应用
     */
    @PreAuthorize("@ss.hasPermi('portal:app:edit')")
    @Log(title = "门户应用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PortalApp portalApp)
    {
        return toAjax(portalAppService.updatePortalApp(portalApp));
    }

    /**
     * 删除门户应用
     */
    @PreAuthorize("@ss.hasPermi('portal:app:remove')")
    @Log(title = "门户应用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appIds}")
    public AjaxResult remove(@PathVariable Long[] appIds)
    {
        return toAjax(portalAppService.deletePortalAppByAppIds(appIds));
    }
}

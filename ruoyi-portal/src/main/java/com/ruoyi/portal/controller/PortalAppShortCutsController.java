package com.ruoyi.portal.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.portal.domain.PortalApp;
import com.ruoyi.portal.service.IPortalAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("/portal/app/api")
public class PortalAppShortCutsController extends BaseController {

    @Autowired
    private IPortalAppService portalAppService;

    @GetMapping("/applications")
    public List<PortalApp> list(PortalApp portalApp) {
        List<PortalApp> list = portalAppService.selectPortalAppList(portalApp); // Query data from database using Spring Data JPA
        return list;
    }

}

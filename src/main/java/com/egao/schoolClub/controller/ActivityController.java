package com.egao.schoolClub.controller;

import com.egao.common.core.web.BaseController;
import com.egao.schoolClub.service.SchoolActivityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schoolClub/schoolActivity/all")
public class ActivityController extends BaseController {
    @Autowired
    private SchoolActivityService schoolActivityService;

    @RequiresPermissions("schoolClub:schoolActivity:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/schoolActivity.html";
    }
}

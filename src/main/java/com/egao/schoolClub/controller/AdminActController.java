package com.egao.schoolClub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egao.common.core.annotation.OperLog;
import com.egao.common.core.web.BaseController;
import com.egao.common.core.web.JsonResult;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.service.UserService;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.entity.Club;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.service.AuditService;
import com.egao.schoolClub.service.ClubService;
import com.egao.schoolClub.service.PeopleService;
import com.egao.schoolClub.service.SchoolActivityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/schoolClub/schoolActivity/admin")
public class AdminActController extends BaseController {
    @Autowired
    private SchoolActivityService schoolActivityService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private PeopleService peopleService;

    @RequiresPermissions("schoolClub:schoolActivity:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/admin/adminAct.html";
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageAll")
    public PageResult<SchoolActivity> pageAll(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        pageParam.setPageData(map);
        return schoolActivityService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageAudit")
    public PageResult<SchoolActivity> pageAudit(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        pageParam.setPageData(map);
        return schoolActivityService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 审核通过：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:update")
    @OperLog(value = "管理", desc = "审核通过")
    @ResponseBody
    @RequestMapping("/actPass")
    public JsonResult actPass(SchoolActivity schoolActivity) {
        System.out.println(schoolActivity);
        // 1 设置状态为运营
        schoolActivity.setStatus(1);
        // 2 保存审核表单
        // 2.1 先获取该社团申请的id
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<Audit>();
        queryWrapper.eq("applicant_id", schoolActivity.getActId());
        Audit audit = auditService.getOne(queryWrapper);
        // 2.2 设置反馈值
        audit.setAuditorId(getLoginUserId());
        audit.setResult(0);
        audit.setRemark("审核通过");
        auditService.updateById(audit);
        // 3 设置社团人员表
        People people = new People();
        people.setActId(schoolActivity.getActId());
        people.setStatus(0);
        // 3.1 获取用户id
        Club club = clubService.listAllUserId(audit.getCreatorId());
        people.setUserId(club.getLeaderUserId());
        peopleService.save(people);

        if (schoolActivityService.updateById(schoolActivity)) {
            return JsonResult.ok("审核成功");
        }
        return JsonResult.error("审核失败");
    }

    /**
     * 审核驳回：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:update")
    @OperLog(value = "管理", desc = "审核驳回")
    @ResponseBody
    @RequestMapping("/actBack")
    public JsonResult actBack(SchoolActivity schoolActivity) {
        // 设置状态为驳回
        schoolActivity.setStatus(2);
        // 保存审核表单
        // 1 先获取该社团申请的id
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<Audit>();
        queryWrapper.eq("applicant_id", schoolActivity.getActId());
        Audit audit = auditService.getOne(queryWrapper);
        // 2 设置反馈值
        audit.setAuditorId(getLoginUserId());
        audit.setResult(1);
        audit.setRemark(schoolActivity.getRemark());
        auditService.updateById(audit);

        if (schoolActivityService.updateById(schoolActivity)) {
            return JsonResult.ok("审核成功");
        }
        return JsonResult.error("审核失败");
    }
}

package com.egao.schoolClub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egao.common.core.annotation.OperLog;
import com.egao.common.core.web.BaseController;
import com.egao.common.core.web.JsonResult;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.entity.Club;
import com.egao.schoolClub.entity.ClubPeople;
import com.egao.schoolClub.service.AuditService;
import com.egao.schoolClub.service.ClubPeopleService;
import com.egao.schoolClub.service.ClubService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/schoolClub/club/admin")
public class AdminClubController extends BaseController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private ClubPeopleService clubPeopleService;

    @RequiresPermissions("schoolClub:AdminClub:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/admin/adminClub.html";
    }

    /**
     * 分页查询全部状态的社团：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageAudit")
    public PageResult<Club> page(HttpServletRequest request) {
        PageParam<Club> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        pageParam.setPageData(map);
//        return new PageResult<>(clubService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        return clubService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询运营状态的社团：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageRun")
    public PageResult<Club> pageRun(HttpServletRequest request) {
        PageParam<Club> pageParam = new PageParam<>(request);

        return clubService.listPageRun(pageParam);  // 使用关联查询
    }

    /**
     * 审核通过：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:update")
    @OperLog(value = "管理", desc = "审核通过")
    @ResponseBody
    @RequestMapping("/statusPass")
    public JsonResult statusPass(Club club) {
        System.out.println(club);
        // 1 设置状态为运营
        club.setStatus(1);
        // 2 保存审核表单
        // 2.1 先获取该社团申请的id
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<Audit>();
        queryWrapper.eq("applicant_id", club.getClubId());
        Audit audit = auditService.getOne(queryWrapper);
        // 2.2 设置反馈值
        audit.setAuditorId(getLoginUserId());
        audit.setResult(0);
        audit.setRemark("审核通过");
        auditService.updateById(audit);
        // 3 设置社团人员表
        ClubPeople clubPeople = new ClubPeople();
        clubPeople.setClubId(club.getClubId());
        clubPeople.setUserId(audit.getCreatorId());
        clubPeople.setClubPosition(1);
        clubPeopleService.save(clubPeople);

        if (clubService.updateById(club)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 审核驳回：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:update")
    @OperLog(value = "管理", desc = "审核驳回")
    @ResponseBody
    @RequestMapping("/statusBack")
    public JsonResult statusBack(Club club) {
        // 设置状态为驳回
        club.setStatus(2);
        // 保存审核表单
        // 1 先获取该社团申请的id
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<Audit>();
        queryWrapper.eq("applicant_id", club.getClubId());
        Audit audit = auditService.getOne(queryWrapper);
        // 2 设置反馈值
        audit.setAuditorId(getLoginUserId());
        audit.setResult(1);
        audit.setRemark(club.getRemark());
        auditService.updateById(audit);

        if (clubService.updateById(club)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 封禁社团：管理员
     */
    @RequiresPermissions("schoolClub:AdminClub:update")
    @OperLog(value = "管理", desc = "社团运营状态更新")
    @ResponseBody
    @RequestMapping("/statusBlock")
    public JsonResult statusBlock(Integer id, Integer status) {
        Club club = new Club();
        club.setClubId(id);
        club.setStatus(status);
        if (clubService.updateById(club)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}

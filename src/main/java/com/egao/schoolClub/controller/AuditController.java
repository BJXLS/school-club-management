package com.egao.schoolClub.controller;

import com.egao.common.core.web.*;
import com.egao.common.core.annotation.OperLog;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.entity.ClubPeople;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.service.AuditService;
import com.egao.schoolClub.service.ClubPeopleService;
import com.egao.schoolClub.service.ClubService;
import com.egao.schoolClub.service.PeopleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@Controller
@RequestMapping("/schoolClub/audit")
public class AuditController extends BaseController {
    @Autowired
    private AuditService auditService;
    @Autowired
    private ClubPeopleService clubPeopleService;
    @Autowired
    private PeopleService peopleService;

    @RequiresPermissions("schoolClub:audit:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/audit.html";
    }

    /**
     * 分页查询我申请的
     */
    @RequiresPermissions("schoolClub:audit:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Audit> page(HttpServletRequest request) {
        PageParam<Audit> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("creator_id", getLoginUserId());
        pageParam.setPageData(map);
        return new PageResult<>(auditService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return auditService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询我审核的
     */
    @RequiresPermissions("schoolClub:audit:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageMyAudit")
    public PageResult<Audit> pageMyAudit(HttpServletRequest request) {
        PageParam<Audit> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("auditorId", getLoginUserId());
        pageParam.setPageData(map);
//        return new PageResult<>(auditService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        return auditService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @RequiresPermissions("schoolClub:audit:list")
    @OperLog(value = "管理", desc = "查询全部")
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Audit> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(auditService.list(pageParam.getOrderWrapper()));
        //List<Audit> records = auditService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("schoolClub:audit:list")
    @OperLog(value = "管理", desc = "根据id查询")
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(auditService.getById(id));
		// 使用关联查询
        //PageParam<Audit> pageParam = new PageParam<>();
		//pageParam.put("auditId", id);
        //List<Audit> records = auditService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加
     */
    @RequiresPermissions("schoolClub:audit:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Audit audit) {
        if (auditService.save(audit)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 审核成功
     */
    @RequiresPermissions("schoolClub:audit:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/auditPass")
    public JsonResult auditPass(Audit audit) {
        System.out.println(audit);
        if (audit.getResult() != null)
            return JsonResult.error("不能重复审核");
        // 更新审核
        audit.setResult(0);
        audit.setRemark("审核通过，欢迎加入！");
        auditService.updateById(audit);

        if (audit.getType() == 0){
            // 将人员加入社团中
            ClubPeople clubPeople = new ClubPeople();
            clubPeople.setClubId(audit.getApplicantId());
            clubPeople.setUserId(audit.getCreatorId());
            clubPeople.setClubPosition(0);

            if (clubPeopleService.save(clubPeople)) {
                return JsonResult.ok("审核成功");
            }
        } else if (audit.getType() == 2) {
            // 将人员加入活动中
            People people = new People();
            people.setUserId(audit.getCreatorId());
            people.setActId(audit.getApplicantId());
            people.setStatus(1);
            if (peopleService.save(people)) {
                return JsonResult.ok("审核成功");
            }
        }

        return JsonResult.error("审核失败");
    }

    /**
     * 审核成功
     */
    @RequiresPermissions("schoolClub:audit:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/auditBack")
    public JsonResult auditBack(Audit audit) {
        System.out.println(audit);
        if (audit.getResult() != null)
            return JsonResult.error("不能重复审核");
        // 更新审核
        audit.setResult(1);

        if (auditService.updateById(audit)) {
            return JsonResult.ok("审核成功");
        }
        return JsonResult.error("审核失败");
    }

    /**
     * 修改
     */
    @RequiresPermissions("schoolClub:audit:update")
    @OperLog(value = "管理", desc = "修改", param = false, result = true)
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Audit audit) {
        if (auditService.updateById(audit)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("schoolClub:audit:remove")
    @OperLog(value = "管理", desc = "删除", result = true)
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (auditService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加
     */
    @RequiresPermissions("schoolClub:audit:save")
    @OperLog(value = "管理", desc = "批量添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<Audit> list) {
        if (auditService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量修改
     */
    @RequiresPermissions("schoolClub:audit:update")
    @OperLog(value = "管理", desc = "批量修改", result = true)
    @ResponseBody
    @RequestMapping("/updateBatch")
    public JsonResult updateBatch(@RequestBody BatchParam<Audit> batchParam) {
        if (batchParam.update(auditService, "audit_id")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("schoolClub:audit:remove")
    @OperLog(value = "管理", desc = "批量删除", result = true)
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (auditService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

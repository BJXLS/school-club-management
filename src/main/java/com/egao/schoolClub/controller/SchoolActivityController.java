package com.egao.schoolClub.controller;

import com.egao.common.core.web.*;
import com.egao.common.core.annotation.OperLog;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.service.AuditService;
import com.egao.schoolClub.service.SchoolActivityService;
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
 * Created by BJXLS on 2021-02-21 00:39:46
 */
@Controller
@RequestMapping("/schoolClub/schoolActivity")
public class SchoolActivityController extends BaseController {
    @Autowired
    private SchoolActivityService schoolActivityService;
    @Autowired
    private AuditService auditService;

    @RequiresPermissions("schoolClub:schoolActivity:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/table-card.html";
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageMy")
    public PageResult<SchoolActivity> pageMy(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        Integer clubId = Integer.parseInt(request.getParameter("clubId"));
        Map<String, Object> map = new HashMap<>();
        map.put("clubId", clubId);
        map.put("status", 1);
        pageParam.setPageData(map);
        return schoolActivityService.listPage(pageParam);  // 使用关联查询
    }


    /**
     * 活动申请
     */
    @RequiresPermissions("schoolClub:schoolActivity:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/auditAct")
    public JsonResult auditAct(SchoolActivity schoolActivity) {
        System.out.println(schoolActivity);
        // 赋值
        schoolActivity.setNowPeopleNumber(1);
        schoolActivity.setLeaderId(getLoginUserId());
        schoolActivity.setStatus(0);
        schoolActivityService.save(schoolActivity);

        // 创建审核表
        Audit audit = new Audit();
        audit.setCreatorId(schoolActivity.getClubId());
        audit.setAuditName("申请开展\"" + schoolActivity.getActName() + "\"活动");
        audit.setType(1);
        audit.setApplicantId(schoolActivity.getActId());
        if (auditService.save(audit)) {
            return JsonResult.ok("审核提交成功，请耐心等待~");
        }
        return JsonResult.error("审核提交失败，请联系管理员！");
    }

    /**
     * 参加活动申请
     */
    @RequiresPermissions("schoolClub:schoolActivity:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/signUpAct")
    public JsonResult signUpAct(SchoolActivity schoolActivity) {
        System.out.println(schoolActivity);
        // 创建审核表
        Audit audit = new Audit();
        audit.setCreatorId(getLoginUserId());
        audit.setAuditName("申请加入\"" + schoolActivity.getActName() + "\"活动");
        audit.setType(2);
        audit.setApplicantId(schoolActivity.getActId());
        // 设置创建人
        audit.setAuditorId(schoolActivity.getLeaderId());
        if (auditService.save(audit)) {
            return JsonResult.ok("审核提交成功，请耐心等待~");
        }
        return JsonResult.error("审核提交失败，请联系管理员！");
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<SchoolActivity> page(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        pageParam.setPageData(map);
        return schoolActivityService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "查询全部")
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(schoolActivityService.list(pageParam.getOrderWrapper()));
        //List<SchoolActivity> records = schoolActivityService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("schoolClub:schoolActivity:list")
    @OperLog(value = "管理", desc = "根据id查询")
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(schoolActivityService.getById(id));
		// 使用关联查询
        //PageParam<SchoolActivity> pageParam = new PageParam<>();
		//pageParam.put("actId", id);
        //List<SchoolActivity> records = schoolActivityService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加
     */
    @RequiresPermissions("schoolClub:schoolActivity:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(SchoolActivity schoolActivity) {
        if (schoolActivityService.save(schoolActivity)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @RequiresPermissions("schoolClub:schoolActivity:update")
    @OperLog(value = "管理", desc = "修改", param = false, result = true)
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(SchoolActivity schoolActivity) {
        if (schoolActivityService.updateById(schoolActivity)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("schoolClub:schoolActivity:remove")
    @OperLog(value = "管理", desc = "删除", result = true)
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (schoolActivityService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加
     */
    @RequiresPermissions("schoolClub:schoolActivity:save")
    @OperLog(value = "管理", desc = "批量添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<SchoolActivity> list) {
        if (schoolActivityService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量修改
     */
    @RequiresPermissions("schoolClub:schoolActivity:update")
    @OperLog(value = "管理", desc = "批量修改", result = true)
    @ResponseBody
    @RequestMapping("/updateBatch")
    public JsonResult updateBatch(@RequestBody BatchParam<SchoolActivity> batchParam) {
        if (batchParam.update(schoolActivityService, "act_id")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("schoolClub:schoolActivity:remove")
    @OperLog(value = "管理", desc = "批量删除", result = true)
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (schoolActivityService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

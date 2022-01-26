package com.egao.schoolClub.controller;

import com.egao.common.core.web.*;
import com.egao.common.core.annotation.OperLog;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.entity.Club;
import com.egao.schoolClub.service.AuditService;
import com.egao.schoolClub.service.ClubService;
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
@RequestMapping("/schoolClub/club")
public class ClubController extends BaseController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private AuditService auditService;

    @RequiresPermissions("schoolClub:club:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/club.html";
    }


    /**
     * 分页查询运营状态的社团：学生
     */
    @RequiresPermissions("schoolClub:club:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Club> page(HttpServletRequest request) {
        PageParam<Club> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        pageParam.setPageData(map);
        return clubService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询全部状态的社团：管理员
     */
    @RequiresPermissions("schoolClub:club:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageAll")
    public PageResult<Club> pageAll(HttpServletRequest request) {
        PageParam<Club> pageParam = new PageParam<>(request);
        return new PageResult<>(clubService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return clubService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @RequiresPermissions("schoolClub:club:list")
    @OperLog(value = "管理", desc = "查询全部")
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Club> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(clubService.list(pageParam.getOrderWrapper()));
        //List<Club> records = clubService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("schoolClub:club:list")
    @OperLog(value = "管理", desc = "根据id查询")
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(clubService.getById(id));
		// 使用关联查询
        //PageParam<Club> pageParam = new PageParam<>();
		//pageParam.put("clubId", id);
        //List<Club> records = clubService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加
     */
    @RequiresPermissions("schoolClub:club:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Club club) {
        System.out.println(club);
        // 设置club值
        club.setNowPeopleNumber(1);
        club.setStatus(0);
        if (clubService.save(club)) {
            // 创建申请表单
            Audit audit = new Audit();
            audit.setApplicantId(club.getClubId());
            audit.setCreatorId(getLoginUserId());
            audit.setAuditName("申请创建\"" + club.getClubName() + "\"社团");
            audit.setType(0);
            auditService.save(audit);
            return JsonResult.ok("申请已提交，请耐心等候~");
        }
        return JsonResult.error("申请提交失败，请联系管理员！");
    }

    /**
     * 社团报名
     */
    @RequiresPermissions("schoolClub:club:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/signUpClub")
    public JsonResult signUpClub(Club club) {
        System.out.println(club);

        // 创建申请表单
        Audit audit = new Audit();
        audit.setApplicantId(club.getClubId());
        audit.setCreatorId(getLoginUserId());
        audit.setAuditName("申请加入\"" + club.getClubName() + "\"社团");
        // 设置接受者
        audit.setAuditorId(club.getLeaderUserId());
        audit.setType(0);
        if (auditService.save(audit)){
            return JsonResult.ok("申请已提交，请耐心等候~");
        }
        return JsonResult.error("申请提交失败，请联系管理员！");
    }

    /**
     * 修改
     */
    @RequiresPermissions("schoolClub:club:update")
    @OperLog(value = "管理", desc = "修改", param = false, result = true)
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Club club) {
        if (clubService.updateById(club)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("schoolClub:club:remove")
    @OperLog(value = "管理", desc = "删除", result = true)
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (clubService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加
     */
    @RequiresPermissions("schoolClub:club:save")
    @OperLog(value = "管理", desc = "批量添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<Club> list) {
        if (clubService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量修改
     */
    @RequiresPermissions("schoolClub:club:update")
    @OperLog(value = "管理", desc = "批量修改", result = true)
    @ResponseBody
    @RequestMapping("/updateBatch")
    public JsonResult updateBatch(@RequestBody BatchParam<Club> batchParam) {
        if (batchParam.update(clubService, "club_id")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("schoolClub:club:remove")
    @OperLog(value = "管理", desc = "批量删除", result = true)
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (clubService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

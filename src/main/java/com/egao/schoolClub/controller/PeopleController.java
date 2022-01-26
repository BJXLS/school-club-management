package com.egao.schoolClub.controller;

import com.egao.common.core.web.*;
import com.egao.common.core.annotation.OperLog;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.Club;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.service.ClubService;
import com.egao.schoolClub.service.PeopleService;
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
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@Controller
@RequestMapping("/schoolClub/people")
public class PeopleController extends BaseController {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private SchoolActivityService schoolActivityService;

    @RequiresPermissions("schoolClub:people:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/actPeople.html";
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<People> page(HttpServletRequest request) {
        PageParam<People> pageParam = new PageParam<>(request);
        return new PageResult<>(peopleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return peopleService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageJoin")
    public PageResult<SchoolActivity> pageJoin(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
//        return new PageResult<>(peopleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        Map<String, Object> map = new HashMap<>();
        map.put("userId", getLoginUserId());
        pageParam.setPageData(map);
        return peopleService.listPageAct(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询活动人员
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pagePeople")
    public PageResult<User> pagePeople(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        Integer actId = Integer.parseInt(request.getParameter("actId"));
        Map<String, Object> map = new HashMap<>();
        map.put("actId", actId);
        pageParam.setPageData(map);
        return peopleService.listPageActUser(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询我创建的活动
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageMy")
    public PageResult<SchoolActivity> pageMy(HttpServletRequest request) {
        PageParam<SchoolActivity> pageParam = new PageParam<>(request);
        // 获取我创建的活动（审核通过）
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("leaderId", getLoginUserId());
        pageParam.setPageData(map);
        return schoolActivityService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "查询全部")
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<People> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(peopleService.list(pageParam.getOrderWrapper()));
        //List<People> records = peopleService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("schoolClub:people:list")
    @OperLog(value = "管理", desc = "根据id查询")
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(peopleService.getById(id));
		// 使用关联查询
        //PageParam<People> pageParam = new PageParam<>();
		//pageParam.put("actPeopleid", id);
        //List<People> records = peopleService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加
     */
    @RequiresPermissions("schoolClub:people:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(People people) {
        if (peopleService.save(people)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @RequiresPermissions("schoolClub:people:update")
    @OperLog(value = "管理", desc = "修改", param = false, result = true)
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(People people) {
        if (peopleService.updateById(people)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("schoolClub:people:remove")
    @OperLog(value = "管理", desc = "删除", result = true)
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (peopleService.removeById(id)) {
            return JsonResult.ok("剔除成功");
        }
        return JsonResult.error("剔除失败，请联系管理员");
    }

    /**
     * 批量添加
     */
    @RequiresPermissions("schoolClub:people:save")
    @OperLog(value = "管理", desc = "批量添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<People> list) {
        if (peopleService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量修改
     */
    @RequiresPermissions("schoolClub:people:update")
    @OperLog(value = "管理", desc = "批量修改", result = true)
    @ResponseBody
    @RequestMapping("/updateBatch")
    public JsonResult updateBatch(@RequestBody BatchParam<People> batchParam) {
        if (batchParam.update(peopleService, "act_peopleId")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("schoolClub:people:remove")
    @OperLog(value = "管理", desc = "批量删除", result = true)
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (peopleService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

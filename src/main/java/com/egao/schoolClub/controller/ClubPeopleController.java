package com.egao.schoolClub.controller;

import com.egao.common.core.web.*;
import com.egao.common.core.annotation.OperLog;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.ClubPeople;
import com.egao.schoolClub.service.ClubPeopleService;
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
@RequestMapping("/schoolClub/clubPeople")
public class ClubPeopleController extends BaseController {
    @Autowired
    private ClubPeopleService clubPeopleService;

    @RequiresPermissions("schoolClub:clubPeople:view")
    @RequestMapping()
    public String view() {
        return "schoolClub/clubPeople.html";
    }

    /**
     * 分页查询
     */
    @RequiresPermissions("schoolClub:clubPeople:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<ClubPeople> page(HttpServletRequest request) {
        PageParam<ClubPeople> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", getLoginUserId());
        map.put("clubPosition", 0);
        pageParam.setPageData(map);
//        return new PageResult<>(clubPeopleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        return clubPeopleService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 分页查询我创建的社团
     */
    @RequiresPermissions("schoolClub:clubPeople:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageMy")
    public PageResult<ClubPeople> pageMy(HttpServletRequest request) {
        PageParam<ClubPeople> pageParam = new PageParam<>(request);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", getLoginUserId());
        map.put("clubPosition", 1);
        pageParam.setPageData(map);
//        return new PageResult<>(clubPeopleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        return clubPeopleService.listPageMy(pageParam);  // 使用关联查询
    }


    /**
     * 分页查询我创建的社团
     */
    @RequiresPermissions("schoolClub:clubPeople:list")
    @OperLog(value = "管理", desc = "分页查询")
    @ResponseBody
    @RequestMapping("/pageClubPeople")
    public PageResult<User> pageClubPeople(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        Integer clubId = Integer.parseInt(request.getParameter("clubId"));
        System.out.println("clubId: " + clubId);
        Map<String, Object> map = new HashMap<>();
        map.put("clubId", clubId);
        pageParam.setPageData(map);
        return clubPeopleService.listPageClubPeople(pageParam);  // 使用关联查询
    }


    /**
     * 查询全部
     */
    @RequiresPermissions("schoolClub:clubPeople:list")
    @OperLog(value = "管理", desc = "查询全部")
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<ClubPeople> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(clubPeopleService.list(pageParam.getOrderWrapper()));
        //List<ClubPeople> records = clubPeopleService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @RequiresPermissions("schoolClub:clubPeople:list")
    @OperLog(value = "管理", desc = "根据id查询")
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(clubPeopleService.getById(id));
		// 使用关联查询
        //PageParam<ClubPeople> pageParam = new PageParam<>();
		//pageParam.put("clubPeopleid", id);
        //List<ClubPeople> records = clubPeopleService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加
     */
    @RequiresPermissions("schoolClub:clubPeople:save")
    @OperLog(value = "管理", desc = "添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(ClubPeople clubPeople) {
        if (clubPeopleService.save(clubPeople)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @RequiresPermissions("schoolClub:clubPeople:update")
    @OperLog(value = "管理", desc = "修改", param = false, result = true)
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(ClubPeople clubPeople) {
        if (clubPeopleService.updateById(clubPeople)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("schoolClub:clubPeople:remove")
    @OperLog(value = "管理", desc = "删除", result = true)
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id, Integer userId) {
        System.out.println("删除的id: " + id);
        if (clubPeopleService.removeById(id)) {
            return JsonResult.ok("剔除成功");
        }
        return JsonResult.error("剔除失败，请联系管理员");
    }

    /**
     * 批量添加
     */
    @RequiresPermissions("schoolClub:clubPeople:save")
    @OperLog(value = "管理", desc = "批量添加", param = false, result = true)
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<ClubPeople> list) {
        if (clubPeopleService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量修改
     */
    @RequiresPermissions("schoolClub:clubPeople:update")
    @OperLog(value = "管理", desc = "批量修改", result = true)
    @ResponseBody
    @RequestMapping("/updateBatch")
    public JsonResult updateBatch(@RequestBody BatchParam<ClubPeople> batchParam) {
        if (batchParam.update(clubPeopleService, "club_peopleId")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 批量删除
     */
    @RequiresPermissions("schoolClub:clubPeople:remove")
    @OperLog(value = "管理", desc = "批量删除", result = true)
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (clubPeopleService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

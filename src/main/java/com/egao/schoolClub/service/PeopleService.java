package com.egao.schoolClub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.entity.SchoolActivity;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface PeopleService extends IService<People> {

    /**
     * 分页查询
     */
    PageResult<People> listPage(PageParam<People> page);

    PageResult<User> listPageActUser(PageParam<User> page);

    PageResult<SchoolActivity> listPageAct(PageParam<SchoolActivity> page);

    /**
     * 查询所有
     */
    List<People> listAll(Map<String, Object> page);

}

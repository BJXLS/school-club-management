package com.egao.schoolClub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.ClubPeople;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * Created by BJXLS on 2021-02-21 00:39:46
 */
public interface ClubPeopleService extends IService<ClubPeople> {

    /**
     * 分页查询
     */
    PageResult<ClubPeople> listPage(PageParam<ClubPeople> page);

    PageResult<ClubPeople> listPageMy(PageParam<ClubPeople> page);

    PageResult<User> listPageClubPeople(PageParam<User> page);


    /**
     * 查询所有
     */
    List<ClubPeople> listAll(Map<String, Object> page);

}

package com.egao.schoolClub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.entity.Club;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface ClubService extends IService<Club> {

    /**
     * 分页查询
     */
    PageResult<Club> listPage(PageParam<Club> page);

    PageResult<Club> listPageRun(PageParam<Club> page);

    Club listAllUserId (Integer clubId);

    List<Integer> listAllClubIdsByLeaderId (String leaderId);

    /**
     * 查询所有
     */
    List<Club> listAll(Map<String, Object> page);

}

package com.egao.schoolClub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.mapper.ClubMapper;
import com.egao.schoolClub.entity.Club;
import com.egao.schoolClub.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {

    @Override
    public PageResult<Club> listPage(PageParam<Club> page) {
        List<Club> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public PageResult<Club> listPageRun(PageParam<Club> page) {
        List<Club> records = baseMapper.listPageRun(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public Club listAllUserId(Integer clubId) {
        return baseMapper.listAllUserId(clubId);
    }

    @Override
    public List<Integer> listAllClubIdsByLeaderId (String leaderId) {
        return baseMapper.listAllClubIdsByLeaderId(leaderId);
    }


    @Override
    public List<Club> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }


}

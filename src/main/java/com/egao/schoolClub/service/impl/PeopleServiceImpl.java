package com.egao.schoolClub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.mapper.PeopleMapper;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

    @Override
    public PageResult<People> listPage(PageParam<People> page) {
        List<People> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public PageResult<User> listPageActUser(PageParam<User> page) {
        List<User> records = baseMapper.listPageActUser(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public PageResult<SchoolActivity> listPageAct(PageParam<SchoolActivity> page) {
        List<SchoolActivity> records = baseMapper.listPageAct(page);
        return new PageResult<>(records, page.getTotal());
    }


    @Override
    public List<People> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

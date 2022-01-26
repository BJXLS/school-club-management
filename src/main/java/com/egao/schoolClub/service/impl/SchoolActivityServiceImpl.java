package com.egao.schoolClub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.mapper.SchoolActivityMapper;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.service.SchoolActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 * Created by BJXLS on 2021-02-21 00:39:46
 */
@Service
public class SchoolActivityServiceImpl extends ServiceImpl<SchoolActivityMapper, SchoolActivity> implements SchoolActivityService {

    @Override
    public PageResult<SchoolActivity> listPage(PageParam<SchoolActivity> page) {
        List<SchoolActivity> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<SchoolActivity> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

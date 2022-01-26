package com.egao.schoolClub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.entity.SchoolActivity;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * Created by BJXLS on 2021-02-21 00:39:46
 */
public interface SchoolActivityService extends IService<SchoolActivity> {

    /**
     * 分页查询
     */
    PageResult<SchoolActivity> listPage(PageParam<SchoolActivity> page);

    /**
     * 查询所有
     */
    List<SchoolActivity> listAll(Map<String, Object> page);

}

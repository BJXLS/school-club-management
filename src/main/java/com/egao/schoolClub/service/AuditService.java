package com.egao.schoolClub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.schoolClub.entity.Audit;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface AuditService extends IService<Audit> {

    /**
     * 分页查询
     */
    PageResult<Audit> listPage(PageParam<Audit> page);

    /**
     * 查询所有
     */
    List<Audit> listAll(Map<String, Object> page);

}

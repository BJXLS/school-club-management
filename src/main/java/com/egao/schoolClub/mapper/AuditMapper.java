package com.egao.schoolClub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.schoolClub.entity.Audit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface AuditMapper extends BaseMapper<Audit> {

    /**
     * 分页查询
     */
    List<Audit> listPage(@Param("page") PageParam<Audit> page);

    /**
     * 查询全部
     */
    List<Audit> listAll(@Param("page") Map<String, Object> page);

}

package com.egao.schoolClub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.People;
import com.egao.schoolClub.entity.SchoolActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface PeopleMapper extends BaseMapper<People> {

    /**
     * 分页查询
     */
    List<People> listPage(@Param("page") PageParam<People> page);

    List<User> listPageActUser(@Param("page") PageParam<User> page);

    List<SchoolActivity> listPageAct(@Param("page") PageParam<SchoolActivity> page);

    /**
     * 查询全部
     */
    List<People> listAll(@Param("page") Map<String, Object> page);

}

package com.egao.schoolClub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.schoolClub.entity.SchoolActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 * Created by BJXLS on 2021-02-21 00:39:46
 */
public interface SchoolActivityMapper extends BaseMapper<SchoolActivity> {

    /**
     * 分页查询
     */
    List<SchoolActivity> listPage(@Param("page") PageParam<SchoolActivity> page);

    List<SchoolActivity> selectByClubIds(@Param("clubIds") List<Integer> clubIds);



    /**
     * 查询全部
     */
    List<SchoolActivity> listAll(@Param("page") Map<String, Object> page);

}

package com.egao.schoolClub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.common.system.entity.User;
import com.egao.schoolClub.entity.ClubPeople;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 * Created by BJXLS on 2021-02-21 00:39:46
 */
public interface ClubPeopleMapper extends BaseMapper<ClubPeople> {

    /**
     * 分页查询
     */
    List<ClubPeople> listPage(@Param("page") PageParam<ClubPeople> page);

    List<Integer> selectUserIdsByClubIds(@Param("clubIds") List<Integer> clubIds);

    List<User> listPageClubPeople(@Param("page") PageParam<User> page);

    /**
     * 查询全部
     */
    List<ClubPeople> listAll(@Param("page") Map<String, Object> page);

}

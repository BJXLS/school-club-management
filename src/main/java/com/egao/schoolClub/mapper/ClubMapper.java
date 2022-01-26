package com.egao.schoolClub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.schoolClub.entity.Club;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 * Created by BJXLS on 2021-02-21 00:39:45
 */
public interface ClubMapper extends BaseMapper<Club> {

    /**
     * 分页查询
     */
    List<Club> listPage(@Param("page") PageParam<Club> page);

    List<Club> listPageRun(@Param("page") PageParam<Club> page);

    Club listAllUserId(@Param("clubId") Integer clubId);

    List<Integer> listAllClubIdsByLeaderId(@Param("leaderId") String leaderId);

    /**
     * 查询全部
     */
    List<Club> listAll(@Param("page") Map<String, Object> page);

}

package com.egao.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egao.common.core.web.PageParam;
import com.egao.common.system.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单Mapper接口
 * Created by wangfan on 2018-12-24 16:10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 分页查询
     */
    List<Menu> listPage(@Param("page") PageParam<Menu> page);

    /**
     * 根据用户id查询
     */
    List<Menu> listByUserId(@Param("userId") Integer userId, @Param("menuType") Integer menuType);

}

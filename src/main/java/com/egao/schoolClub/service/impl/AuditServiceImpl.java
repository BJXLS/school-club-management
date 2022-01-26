package com.egao.schoolClub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.entity.User;
import com.egao.common.system.mapper.UserMapper;
import com.egao.schoolClub.mapper.AuditMapper;
import com.egao.schoolClub.entity.Audit;
import com.egao.schoolClub.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements AuditService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<Audit> listPage(PageParam<Audit> page) {
        List<Audit> records = baseMapper.listPage(page);

        selectUsers(records);
        return new PageResult<>(records, page.getTotal());
    }

    private void selectUsers(List<Audit> records) {
        List<Integer> userIds = new ArrayList<>();
        for (Audit one : records) {
            userIds.add(one.getCreatorId());
        }
        // 获取user集合
        List<User> users = userMapper.selectBatchIds(userIds);
        // 双循环获取
        for (Audit one : records) {
           for (User user : users) {
               if (one.getCreatorId().equals(user.getUserId())){
                   one.setUser(user);
               }
           }
            System.out.println(one.getUser().getStudentNumber());
        }
    }

    @Override
    public List<Audit> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

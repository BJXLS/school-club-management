package com.egao.schoolClub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.egao.common.core.web.PageParam;
import com.egao.common.core.web.PageResult;
import com.egao.common.system.entity.User;
import com.egao.common.system.mapper.UserMapper;
import com.egao.schoolClub.entity.SchoolActivity;
import com.egao.schoolClub.mapper.ClubPeopleMapper;
import com.egao.schoolClub.entity.ClubPeople;
import com.egao.schoolClub.mapper.SchoolActivityMapper;
import com.egao.schoolClub.service.ClubPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 * Created by BJXLS on 2021-02-21 00:39:46
 */
@Service
public class ClubPeopleServiceImpl extends ServiceImpl<ClubPeopleMapper, ClubPeople> implements ClubPeopleService {
    @Autowired
    private ClubPeopleMapper clubPeopleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SchoolActivityMapper schoolActivityMapper;

    @Override
    public PageResult<ClubPeople> listPage(PageParam<ClubPeople> page) {
        List<ClubPeople> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public PageResult<User> listPageClubPeople(PageParam<User> page) {
        List<User> records = baseMapper.listPageClubPeople(page);
        return new PageResult<>(records, page.getTotal());
    }


    @Override
    public PageResult<ClubPeople> listPageMy(PageParam<ClubPeople> page) {
        // 先获取ClubPeople
        List<ClubPeople> records = baseMapper.listPage(page);

//        selectClubPeopleList(records);
        return new PageResult<>(records, page.getTotal());
    }

    private void selectClubPeopleList(List<ClubPeople> clubPeople) {
        if (clubPeople != null && clubPeople.size() > 0) {
            List<Integer> clubIds = new ArrayList<>();
            // 获取自建社团id
            for (ClubPeople one : clubPeople) {
                clubIds.add(one.getClubId());
            }
            // 获取该社团id下的所有用户id
            List<Integer> userIds = clubPeopleMapper.selectUserIdsByClubIds(clubIds);

            // 查询所有用户
            List<User> users = userMapper.selectBatchIds(userIds);
            // 进行双循环比对
            for (ClubPeople one : clubPeople) {
                List<User> users1 = new ArrayList<>();
                for (User user_temp : users) {
                    if (one.getUserId().equals(user_temp.getUserId())){
                        users1.add(user_temp);
                    }
                }
                one.setUsers(users1);
            }

            // 继续查找活动集合
            List<SchoolActivity> schoolActivities = schoolActivityMapper.selectByClubIds(clubIds);
            // 进行双循环比对
            for (ClubPeople one : clubPeople) {
                List<SchoolActivity> schoolActivities1 = new ArrayList<>();
                for (SchoolActivity schoolActivity_temp : schoolActivities) {
                    if (one.getClubId().equals(schoolActivity_temp.getClubId())){
                        schoolActivities1.add(schoolActivity_temp);
                    }
                }
                one.setSchoolActivities(schoolActivities1);
            }
        }
    }

    @Override
    public List<ClubPeople> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

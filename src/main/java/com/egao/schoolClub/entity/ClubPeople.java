package com.egao.schoolClub.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.egao.common.system.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * Created by BJXLS on 2021-02-21 00:39:46
 */
@TableName("club_people")
public class ClubPeople implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "club_peopleId", type = IdType.AUTO)
    private Integer clubPeopleid;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer clubPosition;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    @TableLogic
    private Integer deleted;

    /**
     * 对应的成员
     */
    @TableField(exist = false)
    private List<User> users = new ArrayList<>();

    /**
     * 对应的社团名称
     */
    @TableField(exist = false)
    private String  clubName;

    /**
     * 对应的社团状态
     */
    @TableField(exist = false)
    private Integer clubStatus;

    /**
     * 对应的社团活动
     */
    @TableField(exist = false)
    private List<SchoolActivity> schoolActivities = new ArrayList<>();

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getClubStatus() {
        return clubStatus;
    }

    public void setClubStatus(Integer clubStatus) {
        this.clubStatus = clubStatus;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<SchoolActivity> getSchoolActivities() {
        return schoolActivities;
    }

    public void setSchoolActivities(List<SchoolActivity> schoolActivities) {
        this.schoolActivities = schoolActivities;
    }

    public Integer getClubPeopleid() {
        return clubPeopleid;
    }

    public void setClubPeopleid(Integer clubPeopleid) {
        this.clubPeopleid = clubPeopleid;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClubPosition() {
        return clubPosition;
    }

    public void setClubPosition(Integer clubPosition) {
        this.clubPosition = clubPosition;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ClubPeople{" +
        ", clubPeopleid=" + clubPeopleid +
        ", clubId=" + clubId +
        ", userId=" + userId +
        ", clubPosition=" + clubPosition +
        ", status=" + status +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}

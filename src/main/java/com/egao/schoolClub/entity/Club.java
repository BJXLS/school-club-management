package com.egao.schoolClub.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@TableName("club")
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "club_id", type = IdType.AUTO)
    private Integer clubId;

    /**
     * 
     */
    private String clubName;

    /**
     * 
     */
    private String clubIcon;

    /**
     * 
     */
    private String clubIntroduce;

    /**
     * 
     */
    private Integer clubType;

    /**
     * 
     */
    private Integer maxPeopleNumber;

    /**
     * 社长id
     */
    private String leaderId;

    /**
     * 社长名字
     */
    @TableField(exist = false)
    private String trueName;

    @TableField(exist = false)
    private Integer leaderUserId;

    /**
     * 审核回复
     */
    @TableField(exist = false)
    private String remark;

    /**
     * 
     */
    private Integer nowPeopleNumber;

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
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    public Integer getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(Integer leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubIcon() {
        return clubIcon;
    }

    public void setClubIcon(String clubIcon) {
        this.clubIcon = clubIcon;
    }

    public String getClubIntroduce() {
        return clubIntroduce;
    }

    public void setClubIntroduce(String clubIntroduce) {
        this.clubIntroduce = clubIntroduce;
    }

    public Integer getClubType() {
        return clubType;
    }

    public void setClubType(Integer clubType) {
        this.clubType = clubType;
    }

    public Integer getMaxPeopleNumber() {
        return maxPeopleNumber;
    }

    public void setMaxPeopleNumber(Integer maxPeopleNumber) {
        this.maxPeopleNumber = maxPeopleNumber;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getNowPeopleNumber() {
        return nowPeopleNumber;
    }

    public void setNowPeopleNumber(Integer nowPeopleNumber) {
        this.nowPeopleNumber = nowPeopleNumber;
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
        return "Club{" +
        ", clubId=" + clubId +
        ", clubName=" + clubName +
        ", clubIcon=" + clubIcon +
        ", clubIntroduce=" + clubIntroduce +
        ", clubType=" + clubType +
        ", maxPeopleNumber=" + maxPeopleNumber +
        ", leaderId=" + leaderId +
        ", nowPeopleNumber=" + nowPeopleNumber +
        ", status=" + status +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}

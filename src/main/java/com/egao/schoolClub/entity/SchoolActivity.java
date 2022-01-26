package com.egao.schoolClub.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 * Created by BJXLS on 2021-02-21 00:39:46
 */
@TableName("school_activity")
public class SchoolActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "act_id", type = IdType.AUTO)
    private Integer actId;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private String actName;

    /**
     * 
     */
    private String actIcon;

    /**
     * 
     */
    private String actIntroduce;

    /**
     * 
     */
    private Integer actType;

    /**
     * 
     */
    private Integer maxPeopleNumber;

    /**
     * 
     */
    private Integer nowPeopleNumber;

    /**
     * 
     */
    private Integer leaderId;

    @TableField(exist = false)
    private String clubName;

    @TableField(exist = false)
    private String remark;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private String endTime;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActIcon() {
        return actIcon;
    }

    public void setActIcon(String actIcon) {
        this.actIcon = actIcon;
    }

    public String getActIntroduce() {
        return actIntroduce;
    }

    public void setActIntroduce(String actIntroduce) {
        this.actIntroduce = actIntroduce;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public Integer getMaxPeopleNumber() {
        return maxPeopleNumber;
    }

    public void setMaxPeopleNumber(Integer maxPeopleNumber) {
        this.maxPeopleNumber = maxPeopleNumber;
    }

    public Integer getNowPeopleNumber() {
        return nowPeopleNumber;
    }

    public void setNowPeopleNumber(Integer nowPeopleNumber) {
        this.nowPeopleNumber = nowPeopleNumber;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        return "SchoolActivity{" +
        ", actId=" + actId +
        ", clubId=" + clubId +
        ", actName=" + actName +
        ", actIcon=" + actIcon +
        ", actIntroduce=" + actIntroduce +
        ", actType=" + actType +
        ", maxPeopleNumber=" + maxPeopleNumber +
        ", nowPeopleNumber=" + nowPeopleNumber +
        ", leaderId=" + leaderId +
        ", status=" + status +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}

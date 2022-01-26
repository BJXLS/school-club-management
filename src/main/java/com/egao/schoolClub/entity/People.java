package com.egao.schoolClub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

/**
 * 
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@TableName("activity_people")
public class People implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "act_peopleId", type = IdType.AUTO)
    private Integer actPeopleid;

    /**
     * 
     */
    private Integer actId;

    /**
     * 
     */
    private Integer userId;

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

    public Integer getActPeopleid() {
        return actPeopleid;
    }

    public void setActPeopleid(Integer actPeopleid) {
        this.actPeopleid = actPeopleid;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "People{" +
        ", actPeopleid=" + actPeopleid +
        ", actId=" + actId +
        ", userId=" + userId +
        ", status=" + status +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}

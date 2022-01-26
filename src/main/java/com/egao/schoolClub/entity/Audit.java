package com.egao.schoolClub.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.egao.common.system.entity.User;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 * Created by BJXLS on 2021-02-21 00:39:45
 */
@TableName("audit")
public class Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "audit_id", type = IdType.AUTO)
    private Integer auditId;

    private String auditName;

    private Integer creatorId;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Integer result;

    /**
     * 
     */
    private Integer applicantId;

    /**
     * 
     */
    private Integer auditorId;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    @TableLogic
    private Integer deleted;

    /**
     * 申请人信息
     */
    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private String auditorStudentnumber;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    public String getAuditorStudentnumber() {
        return auditorStudentnumber;
    }

    public void setAuditorStudentnumber(String auditorStudentnumber) {
        this.auditorStudentnumber = auditorStudentnumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Audit{" +
        ", auditId=" + auditId +
        ", type=" + type +
        ", result=" + result +
        ", applicantId=" + applicantId +
        ", auditorId=" + auditorId +
        ", remark=" + remark +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

}

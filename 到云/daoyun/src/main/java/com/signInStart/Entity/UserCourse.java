package com.signInStart.Entity;

import com.signInStart.Entity.BaseClass.BaseSetting;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "userCourse", schema = "dbo", catalog = "et")
public class UserCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @Column()
    private Integer score; //签到积分

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date modifyDate;

    @Column()
    private String createBy;

    @Column()
    private String modifyBy;

    @Column()
    private Integer isCharge;

    @Column()
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserCourse(UserInfo userInfo, Course course) {
        this.userInfo = userInfo;
        this.course = course;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Integer isCharge) {
        this.isCharge = isCharge;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course= course;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }


    public String getAccount(){
        if (userInfo != null) {
            return getUserInfo().getAccount();
        }
        return null;
    }
    public Long getUserId(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getId();
    }
    public String getCourseName(){
        if (course == null) {
            return null;
        }
        return course.getCourseName();
    }
    public Long getCourseId(){
        if (course == null) {
            return null;
        }
        return course.getId();
    }
    public Date getLastLogin(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getLastLogin();
    }
    public String getUserStatus(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getStatus();
    }
    public String getPhone(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getPhone();
    }
    public String getEmail(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getEmail();
    }
    public String getSex(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getSex();
    }
    public String getUserName(){
        if (userInfo == null) {
            return null;
        }
        return userInfo.getUserName();
    }
    // 构造无参构造方法
    public UserCourse() {

    }
}
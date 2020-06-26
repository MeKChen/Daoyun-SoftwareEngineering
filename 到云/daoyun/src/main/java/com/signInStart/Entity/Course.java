package com.signInStart.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course", schema = "dbo", catalog = "et")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @Column()
    private String courseName; //课程名称

    @Column()
    private String location; //上课地点

    @Column()
    private String startWeek; //起始周

    @Column()
    private String endWeek; //终止周

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Column()
    private String startSection; //起始节

    @Column()
    private String endSection; //终止节

    @Column()
    private String semester; //学期

    @Column()
    private Integer status; //签到状态

    @Column()
    private Double lng;//经度

    @Column()
    private Double lat;//纬度

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createDate;

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date modifyDate;

    @Column()
    private String createBy;

    @Column()
    private String modifyBy;

    public Long getId() {
        return courseId;
    }

    public void setId(Long id) {
        this.courseId = id;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getStartSection() {
        return startSection;
    }

    public void setStartSection(String startSection) {
        this.startSection = startSection;
    }

    public String getEndSection() {
        return endSection;
    }

    public void setEndSection(String endSection) {
        this.endSection = endSection;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
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
}

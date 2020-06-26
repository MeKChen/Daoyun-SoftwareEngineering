package com.signInStart.Service.Impl;

import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.Course;
import com.signInStart.Repository.CourseRepository;
import com.signInStart.Service.LoginInfoService;
import com.signInStart.Service.CourseService;
import com.signInStart.Utils.DataUtils;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LoginInfoService loginInfoService;

    @Override
    public Course findCourseById(Long id){
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        }
        return null;
    }

    @Override
    public List<Course> findByCourseNames(String[] names) {
        return courseRepository.findByCourseNames(names);
    }


    @Override
    public Course findByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }

    @Override
    public Integer Insert(Course course) throws FriendlyException {
        if (course == null) {
            throw new FriendlyException("课程不能为空", DataUtils.CurrentMethodName());
        }
        if (DataUtils.isEmptyString(course.getCourseName())) {
            throw new FriendlyException("课程名称不能为空", 1);
        }
        if (courseRepository.findByCourseName(course.getCourseName()) != null) {
            throw new FriendlyException("课程名称重复", DataUtils.CurrentMethodName());
        }
        course.setCreateDate(new Date());
        course.setStatus(0);
        course.setLng(0.0);
        course.setLat(0.0);
        course.setCreateBy(loginInfoService.getAccount());
        courseRepository.save(course);
        return 0;
    }

    @Override
    public Integer Delete(Long courseId) throws FriendlyException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new FriendlyException("删除对象不存在", DataUtils.CurrentMethodName());
        }
        courseRepository.delete(course);
        return 0;
    }


    @Override
    public Integer modify(Course course) throws FriendlyException {
        if (course == null) {
            throw new FriendlyException("传入参数不能为空", DataUtils.CurrentMethodName());
        }

        Course oldCourse = findCourseById(course.getId());
        if (oldCourse == null) {
            throw new FriendlyException("修改对象不存在", DataUtils.CurrentMethodName());
        }
        DataUtils.copyProperty(course, oldCourse);
        oldCourse.setModifyBy(loginInfoService.getAccount());
        oldCourse.setModifyDate(new Date());
        courseRepository.save(oldCourse);
        return 0;
    }

    @Override
    public List<Course> getAll() throws FriendlyException {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }


    @Override
    public Integer isstatus(Course course){
        return course.getStatus();
    }

}

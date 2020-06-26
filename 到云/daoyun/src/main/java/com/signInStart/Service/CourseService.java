package com.signInStart.Service;

import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.Course;
import net.minidev.json.JSONObject;

import java.util.List;
public interface CourseService {
    Course findCourseById(Long id);

    Course findByCourseName(String name);
    List<Course> findByCourseNames(String[] names);

    Integer Insert(Course course) throws FriendlyException;

    Integer Delete(Long roleId) throws FriendlyException;

    Integer modify(Course course) throws FriendlyException;

    List<Course> getAll() throws FriendlyException;

    List<Course> findAll()throws FriendlyException ;

    Integer isstatus(Course course)throws FriendlyException ;
}

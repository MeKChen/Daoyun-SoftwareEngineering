package com.signInStart.Repository;

import com.signInStart.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
//    @Query("select dc from Course dc where dc.Course.id = ?1")
//    List<Course> findCourseByCourseId(Long CourseId);
//    @Query("select dc from Course dc where dc.Course.name = ?1")
//    List<Course> findCourseByCoursename(String Coursename);

    Course findByCourseId(Long courseId);

    Course findByCourseName(String courseName);
    @Query("select r from Course r where r.courseName in (?1)")
    List<Course> findByCourseNames(String[] CourseName);
}

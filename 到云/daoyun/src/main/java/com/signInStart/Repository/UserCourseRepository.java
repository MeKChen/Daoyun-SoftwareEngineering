package com.signInStart.Repository;

import com.signInStart.Entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    @Modifying
    @Query(value = "delete from user_course where user_id = ?1", nativeQuery = true)
    Integer deleteAllByUserId(Long id);

    @Query(value = "select u from UserCourse u where u.userInfo.id = ?1")
    List<UserCourse> findAllByUserId(Long userId);

    @Query(value = "select u from UserCourse u order by u.score")
    List<UserCourse> findAll();

    @Query(value = "select u from UserCourse u where u.userInfo.id = ?1 and u.course.id = ?2")
    UserCourse findByUserIdCourseId(Long userId, Long courseId);

    @Query(value = "select u from UserCourse u where u.userInfo.id = ?1")
    List<UserCourse> findByUserId(Long id);

    @Query(value = "select u from UserCourse u where u.course.id = ?1")
    List<UserCourse> findByCourseId(Long id);

    @Transactional
    @Modifying
    @Query(value = "update user_role set course_id = ?3 where user_id = ?1 and course_id = ?2", nativeQuery = true)
    void modifyCourse(Long userId, Long courseId, Long newcourseId);


}

package com.signInStart.Service;

import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.UserCourse;
import com.signInStart.Entity.Course;
import com.signInStart.Entity.UserInfo;

import java.util.List;
public interface UserCourseService {
    UserCourse findById(Long id)throws FriendlyException;

    UserCourse findByUserIdCourseId(UserInfo userInfo,Course course)throws FriendlyException;

    List<UserCourse> findAll()throws FriendlyException ;



    Integer addUserCourse(UserCourse userCourse)throws FriendlyException ;

    Integer removeUserCourse(Long id)throws FriendlyException ;

    Integer removeAllUserCourseById(Long userCourseId)throws FriendlyException ;

    Integer modifyUserCourse(UserCourse userCourse)throws FriendlyException ;

    List<UserCourse> findByUserID(Long id)throws FriendlyException ;
    List<UserCourse> findByCourseID(Long id)throws FriendlyException ;

    Integer isCharge(Course course, UserInfo userInfo)throws FriendlyException ;

    Integer distance(Course course, Double lng, Double lat)throws FriendlyException ;

}

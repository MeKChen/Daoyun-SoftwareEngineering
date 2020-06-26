package com.signInStart.Service.Impl;

import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.Course;
import com.signInStart.Entity.UserCourse;
import com.signInStart.Entity.UserInfo;
import com.signInStart.Repository.UserCourseRepository;
import com.signInStart.Service.LoginInfoService;
import com.signInStart.Service.UserCourseService;
import com.signInStart.Utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userCourseService")
public class UserCourseServiceImpl implements UserCourseService {
    @Autowired
    UserCourseRepository userCourseRepository;
    @Autowired
    LoginInfoService loginInfoService;

    @Override
    public UserCourse findById(Long id) {
        if (id == null) {
            return null;
        }
        Optional<UserCourse> byId = userCourseRepository.findById(id);
        if (byId.get() == null) {
            return null;
        }
        return byId.get();
    }

    @Override
    public UserCourse findByUserIdCourseId(UserInfo userInfo,Course course){
        return userCourseRepository.findByUserIdCourseId(userInfo.getId(),course.getId());
    }

    @Override
    public List<UserCourse> findAll() {
        return userCourseRepository.findAll();
    }

    @Override
    public List<UserCourse> findByCourseID(Long id){
        return userCourseRepository.findByCourseId(id);
    }

//    @Override
//    public Integer addUserCourse(UserCourse userCourse)throws FriendlyException {
//        if (userCourse == null) {
//            throw new FriendlyException("对象不存在");
//        }
//        if (userCourse.getUserInfo() == null) {
//            throw new FriendlyException("用户不存在");
//        }
//        if (userCourse.getCourse() == null) {
//            throw new FriendlyException("课程不存在");
//        }
//        if (userCourseRepository.findByUserIdCourseId(userCourse.getUserId(), userCourse.getCourseId())!=null) {
//            userCourse.setIsCharge(0);
////            throw new FriendlyException("该课程已被创建");
//        }
//        else
//            userCourse.setIsCharge(1);
//        userCourse.setScore(0);
//        userCourse.setCreateDate(new Date());
//        userCourse.setCreateBy(userCourse.getAccount());
//        userCourseRepository.save(userCourse);
//        return 0;
//    }
    @Override
    public Integer addUserCourse(UserCourse userCourse)throws FriendlyException {
        if (userCourse == null) {
            throw new FriendlyException("对象不存在");
        }
        if (userCourse.getUserInfo() == null) {
            throw new FriendlyException("用户不存在");
        }
        if (userCourse.getCourse() == null) {
            throw new FriendlyException("课程不存在");
        }
        if (userCourseRepository.findByUserIdCourseId(userCourse.getUserId(),userCourse.getCourseId())!=null)
            throw new FriendlyException("已加入该班课");
        if (userCourse.getCourse().getCreateBy().equals(userCourse.getUserInfo().getAccount())) {
            userCourse.setIsCharge(1);
        }
        else
            userCourse.setIsCharge(0);
        userCourse.setScore(0);
        userCourse.setCreateDate(new Date());
        userCourse.setCreateBy(userCourse.getAccount());
        userCourseRepository.save(userCourse);
        return 0;
    }

    @Override
    public Integer removeUserCourse(Long id) {
        UserCourse userCourse = findById(id);
        if (id == null || userCourse == null) {
            return 1; // 对象不存在
        }
        userCourseRepository.delete(userCourse);
        return 0;
    }

    @Override
    @Transactional
    public Integer removeAllUserCourseById(Long userId) {
        if (userId == null) {
            return 1; // 对象不存在
        }
        userCourseRepository.deleteAllByUserId(userId);
        return 0;
    }


    @Override
    public Integer modifyUserCourse(UserCourse userCourse) {
        if (userCourse == null || findById(userCourse.getId()) == null) {
            return 1; // 对象不存在
        }
        userCourseRepository.save(userCourse);
        return 0;
    }

    @Override
    public List<UserCourse> findByUserID(Long id) {
        return userCourseRepository.findByUserId(id);
    }

    @Override
    public Integer isCharge(Course course, UserInfo userInfo)throws FriendlyException{
        System.out.println("%%%%%%%%%"+ course.getId());
        System.out.println("***********"+ userInfo.getId());
        UserCourse userCourse= userCourseRepository.findByUserIdCourseId(userInfo.getId(),course.getId());
        if (userCourse == null) {
            throw new FriendlyException("对象不存在");
        }
        if (userCourse.getUserInfo() == null) {
            throw new FriendlyException("用户不存在");
        }
        if (userCourse.getCourse() == null) {
            throw new FriendlyException("课程不存在");
        }
        System.out.println("###########"+ userCourse.getIsCharge());
        return userCourse.getIsCharge();
    }

    @Override
    public Integer distance(Course course, Double lng, Double lat){
        double radiansAX = Math.toRadians(course.getLng()); // A经弧度
        double radiansAY = Math.toRadians(course.getLat()); // A纬弧度
        double radiansBX = Math.toRadians(lng); // B经弧度
        double radiansBY = Math.toRadians(lat);
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos)*6371393;
        if (acos<50)
            return 1;  //<50M
        else
            return 0;
    }
}

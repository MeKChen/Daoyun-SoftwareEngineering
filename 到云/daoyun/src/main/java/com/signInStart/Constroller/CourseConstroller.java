package com.signInStart.Constroller;

import com.signInStart.Entity.BaseClass.Auth;
import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Entity.BaseClass.DataResult;
import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.Course;
import com.signInStart.Entity.UserInfo;
import com.signInStart.Entity.UserCourse;
import com.signInStart.Service.*;
import com.signInStart.Utils.DataUtils;
import com.signInStart.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/course")
public class CourseConstroller {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    LoginInfoService loginInfoService;
    @Autowired
    UserCourseService userCourseService;
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public DataResult findAll(Course course) throws FriendlyException {
        Course c= courseService.findByCourseName(course.getCourseName());
        List<UserCourse> all = userCourseService.findByCourseID(c.getId());
        return ResultUtils.success(all, all.size());
    }

    @RequestMapping(value = "/allcourse", method = RequestMethod.GET)
        public DataResult findAllCourse() throws FriendlyException {
            List<Course> all = courseService.findAll();
            return ResultUtils.success(all, all.size());
        }


    @RequestMapping(value = "/allcoursebyaccount", method = RequestMethod.GET)
        public DataResult findAllCourseByAccount(UserInfo userInfo) throws FriendlyException {
            UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
            List<UserCourse> all = userCourseService.findByUserID(u.getId());
            return ResultUtils.success(all, all.size());
        }

//    @RequestMapping(value = "/creatcourse", method = RequestMethod.POST)
//    public DataResult creatCourse(Course course,UserInfo userInfo) throws FriendlyException {
//        courseService.Insert(course);
//        UserCourse usercourse=new UserCourse(userInfo,course);
//        userCourseService.addUserCourse(usercourse);
//        return ResultUtils.success();
//    }
    @RequestMapping(value = "/creatcourse", method = RequestMethod.POST)
    public DataResult creatCourse(Course course,UserInfo userInfo) throws FriendlyException {
        courseService.Insert(course);
        UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
        Course c= courseService.findByCourseName(course.getCourseName());
        UserCourse usercourse=new UserCourse(u,c);
        userCourseService.addUserCourse(usercourse);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
    public DataResult addCourse(Course course,UserInfo userInfo) throws FriendlyException {
//        UserCourse usercourse=new UserCourse(userInfo,course);
        UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
        Course c= courseService.findByCourseName(course.getCourseName());
        UserCourse usercourse=new UserCourse(u,c);
        userCourseService.addUserCourse(usercourse);
        return ResultUtils.success();
    }

//    @RequestMapping(value = "/launch", method = RequestMethod.POST)
//    @Auth(BaseSetting.NOUSER)
//    public DataResult Launch(Course course,UserInfo userInfo) throws FriendlyException {
//        if(!userCourseService.isCharge(course,userInfo))
//            return ResultUtils.error(3, "没有权限");
//        else
//            course.setStatus(true);
//        return ResultUtils.success();
//    }

    @RequestMapping(value = "/launch", method = RequestMethod.POST)
    public DataResult Launch(Course course,UserInfo userInfo,
                             @RequestParam("lng") Double lng,
                             @RequestParam("lat") Double lat) throws FriendlyException {
//        Double lng1=new Double(lng);
//        Double lat1=new Double(lat);
        UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
        Course c= courseService.findByCourseName(course.getCourseName());
        if(userCourseService.isCharge(c,u)==0)
            return ResultUtils.error(3, "没有权限");
        else
        {
            List<UserCourse> alluser = userCourseService.findByCourseID(c.getId());
            for(UserCourse uc : alluser)
            {
                uc.setStatus(1);
                userCourseService.modifyUserCourse(uc);
            }
            c.setStatus(1);
            c.setLng(lng);
            c.setLat(lat);
            courseService.modify(c);
        }
        return ResultUtils.success();
    }

//    @RequestMapping(value = "/signin", method = RequestMethod.POST)
//    @Auth(BaseSetting.NOUSER)
//    public DataResult Signin(Course course,UserInfo userInfo) throws FriendlyException {
//        if(!courseService.isstatus(course))
//            return ResultUtils.error(3, "未到签到时刻");
//        else{
//            UserCourse userCourse=userCourseService.findByUserIdCourseId(userInfo,course);
//            userCourse.setScore(userCourse.getScore()+2);
//            userCourseService.modifyUserCourse(userCourse);
//        }
//        return ResultUtils.success();
//    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public DataResult Signin(Course course,UserInfo userInfo,
                             @RequestParam("lng") Double lng,
                             @RequestParam("lat") Double lat) throws FriendlyException {
        UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
        Course c= courseService.findByCourseName(course.getCourseName());
        UserCourse userCourse=userCourseService.findByUserIdCourseId(u,c);
        if(userCourse==null) return ResultUtils.error(2, "未加入该课程");
        else if(courseService.isstatus(c)==0)
            return ResultUtils.error(3, "未到签到时刻");
        else if(userCourse.getStatus()==0)
            return ResultUtils.error(4, "重复签到");
        else if(userCourseService.distance(c,lng,lat)==0)
            return ResultUtils.error(5, "未在签到范围内");
        else{
            // UserCourse userCourse=userCourseService.findByUserIdCourseId(u,c);
            userCourse.setStatus(0);
            userCourse.setScore(userCourse.getScore()+2);
            userCourseService.modifyUserCourse(userCourse);
        }
        return ResultUtils.success();
    }

//    @RequestMapping(value = "/endlaunch", method = RequestMethod.POST)
//    @Auth(BaseSetting.NOUSER)
//    public DataResult endLaunch(Course course,UserInfo userInfo) throws FriendlyException {
//        if(!userCourseService.isCharge(course,userInfo))
//            return ResultUtils.error(3, "没有权限");
//        else
//            course.setStatus(false);
//        return ResultUtils.success();
//    }

    @RequestMapping(value = "/endlaunch", method = RequestMethod.POST)
    public DataResult endLaunch(Course course,UserInfo userInfo) throws FriendlyException {
        UserInfo u= userInfoService.findUserByAccount(userInfo.getAccount());
        Course c= courseService.findByCourseName(course.getCourseName());
        if(userCourseService.isCharge(c,u)==0)
            return ResultUtils.error(3, "没有权限");
        else{
            c.setStatus(0);
            courseService.modify(c);
        }
        return ResultUtils.success();
    }



}

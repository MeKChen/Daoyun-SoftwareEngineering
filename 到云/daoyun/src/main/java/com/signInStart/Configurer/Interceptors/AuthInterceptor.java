package com.signInStart.Configurer.Interceptors;

import com.signInStart.Entity.BaseClass.Auth;
import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Service.LoginInfoService;
import com.signInStart.Utils.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description //TODO  权限拦截器,验证方法所需要的权限
 * @Param
 * @return
 **/
@Controller
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private LoginInfoService loginInfoService;

    Log log = LogFactory.getLog(AuthInterceptor.class);

    public AuthInterceptor() {
    }

    public AuthInterceptor(LoginInfoService loginInfoService) {
        this.loginInfoService = loginInfoService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String methodName = handlerMethod.getMethod().getName();
            Auth auth = handlerMethod.getMethod().getAnnotation(Auth.class);// 获取方法上的注解
            if (auth == null) {// 如果方法上的注解为空 则获取类的注解
                auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
            }
            if (auth != null) {
                if (BaseSetting.USRTYPE.Admin_SYS.toString().equals(auth.value()) && loginInfoService.checkAdmin()) {
                    return true;
                }
                if (BaseSetting.USRTYPE.SupperAdmin_SYS.toString().equals(auth.value()) && loginInfoService.checkSupperAdimn()) {
                    return true;
                }
                if (BaseSetting.USRTYPE.User_SYS.toString().equals(auth.value()) && loginInfoService.checkUser()) {
                    return true;
                }
                if (BaseSetting.NOUSER.equals(auth.value()) && !loginInfoService.checkUser()) {
                    return true;
                }
                log.warn("用户 " + loginInfoService.getAccount() + " 试图调用 " + methodName + " 方法：没有权限");
                ServletOutputStream writer = response.getOutputStream();
                writer.print(ResultUtils.error(100, "NOT AUTH").toString());
                writer.flush();
                writer.close();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
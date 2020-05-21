import service from "@/utils/request";
import qs from 'qs';
/**
 * 获取验证码
 */
export function GetSms(data) {
  return service.request({
    method: "post",
    url: "http://8gtwat.natappfree.cc/login_sendEmail/",
    data: qs.stringify(data)
  });
}
/**
 * 登录
 */
export function Login(data) {
    
  return service.request({
    method: "post",
    url: "http://esw3d7.natappfree.cc/loginCheck",
    data: qs.stringify(data)
  });
}
/**
 * 注册
 */
export function Register(data) {
  return service.request({
    method: "post",
    url: "/login_signup/",
    data
  });
}

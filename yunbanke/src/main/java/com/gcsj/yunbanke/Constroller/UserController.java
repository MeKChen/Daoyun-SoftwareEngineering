/**
 * created by
 * Date:2020/4/9
 **/
package com.gcsj.yunbanke.Constroller;

import com.gcsj.yunbanke.Entity.BaseClass.BaseRole;
import com.gcsj.yunbanke.Entity.BaseClass.DataResult;
import com.gcsj.yunbanke.Entity.BaseClass.LoginInfor;
import com.gcsj.yunbanke.Entity.UserInfo;
import com.gcsj.yunbanke.Entity.UserRole;
import com.gcsj.yunbanke.Service.LoginInfoService;
import com.gcsj.yunbanke.Service.UserInfoService;
import com.gcsj.yunbanke.Service.UserRoleService;
import com.gcsj.yunbanke.Utils.ResultUtils;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    LoginInfoService loginInfoService;
    @Autowired
    UserRoleService userRoleService;

    /**
     * 新增用户，由管理员操作
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/insertUserInfo", method = RequestMethod.POST)
    public DataResult insertUserInfo(UserInfo userInfo) {
        LoginInfor logiInfo = loginInfoService.getLogiInfo();
        if (logiInfo == null || !logiInfo.getRoleId().equals(BaseRole.AdminId)) {
            return ResultUtils.error(9, "没有权限");
        }
        Integer res = userInfoService.Insert(userInfo);
        String[] msg = {"创建成功", "添加账户不能为空", "账户不能为空", "该账户已被注册", "该邮箱已被注册", "密码不能为空", "邮箱不能为空","账号长度要大于4小于10"};
        if (res == 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error(res, msg[res]);
    }

    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
    public DataResult deleteUserInfo(@RequestParam("uid") Long uid) {
        LoginInfor logiInfo = loginInfoService.getLogiInfo();
        if (logiInfo == null || !Objects.equals(logiInfo.getRoleId(), BaseRole.AdminId)) {
            return ResultUtils.error(2, "没有权限");
        }
        Integer res = userInfoService.Delete(uid);
        if (res == 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error(res, "删除对象不存在");
    }

    @RequestMapping("/modifyUserInfo")
    public DataResult modifyUserInfo(UserInfo userInfo) {
        Integer res = userInfoService.modify(userInfo);
        if (res == 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error(res, "修改对象不存在");
    }

    @RequestMapping("/findAll")
    public DataResult findAll() {
        List<UserRole> allUserRole = userRoleService.findAll();
        Map<String, Integer> map = new HashMap<>();
        List<JSONObject> list = new ArrayList<>();
        int i = 0;
        for (UserRole ur : allUserRole) {
            JSONObject jsonObject = new JSONObject();
            if (map.containsKey(ur.getAccount())) {
                if (ur.getIsDefault().equals(UserRole.ISDEFAULT.isDefault.toString())) {
                    allUserRole.set(map.get(ur.getAccount()), ur);
                    jsonObject.put("account", ur.getAccount());
                    jsonObject.put("userName", ur.getUserInfo().getUserName());
                    jsonObject.put("sex", ur.getUserInfo().getSex());
                    jsonObject.put("email", ur.getUserInfo().getEmail());
                    jsonObject.put("phone", ur.getUserInfo().getPhone());
                    jsonObject.put("status", ur.getUserInfo().getStatus());
                    jsonObject.put("roleName", ur.getRole().getRoleName());
                }
            } else {
                map.put(ur.getAccount(), i++);
                jsonObject.put("account", ur.getAccount());
                jsonObject.put("userName", ur.getUserInfo().getUserName());
                jsonObject.put("sex", ur.getUserInfo().getSex());
                jsonObject.put("email", ur.getUserInfo().getEmail());
                jsonObject.put("phone", ur.getUserInfo().getPhone());
                jsonObject.put("UserStatus", ur.getUserInfo().getStatus());
                jsonObject.put("roleName", ur.getRole().getRoleName());
            }
            list.add(jsonObject);
        }
//        List<UserInfo> all = userInfoService.findAll();
        return ResultUtils.success(list, list.size());
    }

    @RequestMapping("/resetPwd")
    public DataResult resetPwd(@RequestParam("id") Long id) {
        UserInfo userById = userInfoService.findUserById(id);
        if (userById == null) {
            return ResultUtils.error(1, "用户不存在");
        }
        userById.setInitPassword();
        userInfoService.modify(userById);
        return ResultUtils.success();
    }

    @RequestMapping("/allResetPwd")
    public DataResult allResetPwd(@RequestParam("idList") Long[] idList) {
        if (idList == null || idList.length < 1) {
            return ResultUtils.error(1, "重置密码名单为空");
        }
        userInfoService.allResetPwd(idList);
        return ResultUtils.success();
    }
}
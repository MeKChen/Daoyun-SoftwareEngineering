package com.signInStart.Service.Impl;

import com.signInStart.Entity.BaseClass.Auth;
import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.DTO.SearchUserDTO;
import com.signInStart.Entity.Role;
import com.signInStart.Entity.UserInfo;
import com.signInStart.Entity.UserRole;
import com.signInStart.Repository.RoleRepository;
import com.signInStart.Repository.UserInfoRepository;
import com.signInStart.Service.RoleService;
import com.signInStart.Service.UserInfoService;
import com.signInStart.Service.UserRoleService;
import com.signInStart.Utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LoginInfoServiceImpl loginInfoService;


    @Override
    public UserInfo findUserByAccount(String account,String receiver)throws FriendlyException {
        UserInfo userByAccount = userInfoRepository.findUserInfoByAccount(account);
        if (userByAccount == null) {
            throw new FriendlyException("账号不存在", DataUtils.CurrentMethodName());
        }
        if (userByAccount.getEmail() == null || !userByAccount.getEmail().equals(receiver)) {
            throw new FriendlyException("该邮箱不是注册邮箱", DataUtils.CurrentMethodName());
        }
        return userByAccount;
    }
    @Override
    public UserInfo findUserByAccount(String account)throws FriendlyException {
        return userInfoRepository.findUserInfoByAccount(account);
    }

    @Override
    public UserInfo findUserById(Long id) throws FriendlyException {
        Optional<UserInfo> byId = userInfoRepository.findById(id);
        if (!byId.isPresent()) {
            return null;
        }
        return byId.get();
    }

    @Override
    public List<UserInfo> findUserByEmail(String email) throws FriendlyException {
        List<UserInfo> user = userInfoRepository.findUserByEmail(email);
        if (user.size() < 1 || user == null) {
            return null;
        }
        return user;
    }

    @Override
    public Integer Insert(UserInfo user) throws FriendlyException {
        if (user == null) {
            throw new FriendlyException("不能为空", DataUtils.CurrentMethodName());
        }
        if (user.getAccount() == null) {
            throw new FriendlyException("账户不能为空", DataUtils.CurrentMethodName());
        }
        if (user.getPwd() == null) {
            throw new FriendlyException("密码不能空", DataUtils.CurrentMethodName());
        }
        if (user.getEmail() == null) {
            throw new FriendlyException("邮箱不能空", DataUtils.CurrentMethodName());
        }
        if (findUserByAccount(user.getAccount()) != null) {
            throw new FriendlyException("该账户已被注册", DataUtils.CurrentMethodName());
        }
        if (findUserByEmail(user.getEmail()) != null) {
            throw new FriendlyException("该邮箱已被注册", DataUtils.CurrentMethodName());
        }
        if (user.getAccount().length() < 4 || user.getAccount().length() > 10) {
            throw new FriendlyException("请输入长度为4-9位的账号", DataUtils.CurrentMethodName());
        }
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$");
        if (!pattern.matcher(user.getPwd()).matches()) {
            throw new FriendlyException("请输入长度为6-20位包含数字和字母的密码");
        }

        user.setCreateDate(new Date());
        user.setCreateBy(user.getAccount());
        user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes())); //md5加密

        Role byRoleName = roleRepository.findByRoleName("common_user");
        if (byRoleName == null) {//不存在则创建
            byRoleName = new Role();
            roleService.Insert(byRoleName);
        }
        userInfoRepository.save(user);
        UserRole userRole = new UserRole(user, byRoleName);
        userRole.setIsDefault(BaseSetting.ISDEFAULT.isDefault_SYS.toString());//设置默认角色
        userRoleService.addUserRole(userRole);
        return 0;
    }

    @Override
    public Integer Delete(Long userId) throws FriendlyException {
        UserInfo userById = findUserById(userId);
        if (userById == null) {
            throw new FriendlyException("删除对象不存在", DataUtils.CurrentMethodName());
        }
        if (loginInfoService.checkAdmin() && userRoleService.isAdmin(userById.getId())) {
            throw new FriendlyException("管理员不能相互删除", DataUtils.CurrentMethodName());
        }
        UserInfo userInfo = findUserById(userId);
        if (userInfo != null) {
            userInfoRepository.delete(userInfo);
        }
        return 0;
    }

    /**
     * 修改用户信息，不能修改用户账户信息
     * 管理员界面调用
     *
     * @param userInfo
     * @return
     */
    @Override
    public Integer modify(UserInfo userInfo) throws FriendlyException {
        UserInfo userById = findUserById(userInfo.getId());
        if (null == userById) {
            throw new FriendlyException("修改对象不存在", DataUtils.CurrentMethodName());
        }
        if (loginInfoService.checkAdmin() && userRoleService.isSupperAdmin(userById.getId())) {
            throw new FriendlyException("权限不够", DataUtils.CurrentMethodName());
        }

        if (!"".equals(userInfo.getEmail()) && userInfo.getEmail() != null) {
            if (findUserByEmail(userInfo.getEmail()) != null && !userInfo.getEmail().equals(userById.getEmail())) {
                throw new FriendlyException("邮箱已被占用", DataUtils.CurrentMethodName());
            }
            userById.setEmail(userInfo.getEmail());
        } else {
            throw new FriendlyException("邮箱不能为空", DataUtils.CurrentMethodName());
        }
        if (!"".equals(userInfo.getUserName()) && userInfo.getUserName() != null) {
            userById.setUserName(userInfo.getUserName());
        }
        if (!"".equals(userInfo.getStatus()) && userInfo.getStatus() != null) {
            userById.setStatus(userInfo.getStatus());
        }
        if (!"".equals(userInfo.getPhone()) && userInfo.getPhone() != null) {
            userById.setPhone(userInfo.getPhone());
        }
        if (!"".equals(userInfo.getSex()) && userInfo.getSex() != null) {
            userById.setSex(userInfo.getSex());
        }
        userById.setModifyDate(new Date());
        userById.setModifyBy(loginInfoService.getAccount());
        userInfoRepository.save(userById);
        return 0;//修改成功
    }

    @Override
    public Integer modifyPer(UserInfo userInfo) throws FriendlyException {
        UserInfo userById = findUserById(userInfo.getId());
        if (null == userById) {
            throw new FriendlyException("修改对象不存在", DataUtils.CurrentMethodName());
        }
        if (loginInfoService.getUserInfoID()!= userById.getId()) {
            throw new FriendlyException("不能修改他人信息", DataUtils.CurrentMethodName());
        }

        if (!"".equals(userInfo.getEmail()) && userInfo.getEmail() != null) {
            if (findUserByEmail(userInfo.getEmail()) != null && !userInfo.getEmail().equals(userById.getEmail())) {
                throw new FriendlyException("邮箱已被使用", DataUtils.CurrentMethodName());
            }
            userById.setEmail(userInfo.getEmail());
        } else {
            throw new FriendlyException("邮箱不能为空", DataUtils.CurrentMethodName());
        }
        if (!"".equals(userInfo.getUserName()) && userInfo.getUserName() != null) {
            userById.setUserName(userInfo.getUserName());
        }
        if (!"".equals(userInfo.getStatus()) && userInfo.getStatus() != null) {
            userById.setStatus(userInfo.getStatus());
        }
        if (!"".equals(userInfo.getPhone()) && userInfo.getPhone() != null) {
            userById.setPhone(userInfo.getPhone());
        }
        if (!"".equals(userInfo.getSex()) && userInfo.getSex() != null) {
            userById.setSex(userInfo.getSex());
        }
        userById.setModifyDate(new Date());
        userById.setModifyBy(loginInfoService.getAccount());
        userInfoRepository.save(userById);
        return 0;//修改成功
    }

    @Override
    public List<UserInfo> findAll() throws FriendlyException {
        List<UserInfo> all = userInfoRepository.findAll();
        return all;
    }

    @Override
    @Transactional
    public void allResetPwd(Long[] list) throws FriendlyException {
        userInfoRepository.allResetPwd(DigestUtils.md5DigestAsHex("8888".getBytes()), new Date(), loginInfoService.getAccount(), list);
    }
    /**
     * @Author ypp
     * @Description //TODO  搜索用户（模糊搜索）
     * @Date 9:43 2020/5/21
     * @Params [searchUserDTO]
     * @return java.util.List<com.signInStart.Entity.UserInfo>
     **/
    @Override
    public List<UserInfo> search(SearchUserDTO searchUserDTO) {
       return null;
    }

}

package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.UserInfo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface UserInfoService {
    UserInfo findUserById(Long id);

    UserInfo findUserByAccount(String account);

    Integer Insert(UserInfo user);

    Integer Delete(Long userId);

    Integer modify(UserInfo user);

    List<UserInfo> findUserByEmail(String email);

    List<UserInfo> findAll();

    void allResetPwd(Long[] list);

}

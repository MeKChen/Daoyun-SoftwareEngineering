package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.UserInfo;
import com.gcsj.yunbanke.Entity.UserRole;

import java.util.List;


public interface UserRoleService {

    UserRole findById(Long id);

    UserRole findDefaultRoleByUserId(Long userId);

    List<UserRole> findAll();

    Boolean isAdmin(Long userId);

    Integer addUserRole(UserRole userRole);

    Integer removeUserRole(Long id);

    Integer removeAllUserRoleByUserId(Long userId);

    Integer modifyUserRole(UserRole userRole);
}
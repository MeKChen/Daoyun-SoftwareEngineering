package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleById(Long id);

    List<Role> findAll();

    Integer Insert(Role role);

    Integer Delete(Long roleId);

    Integer modify(Role role);
}
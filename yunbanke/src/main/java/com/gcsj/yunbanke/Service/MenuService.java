package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.Menu;

import java.util.List;

public interface MenuService {

    Menu findById(Long id);

    List<Menu> findAll();

    Integer add(Menu menu);

    Integer remove(Long id);

    Integer modify(Menu menu);

}
package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.Dictionary;

import java.util.List;

public interface DictionaryService {

    Dictionary findById(Long id);

    List<Dictionary> findAll();

    Integer insert(Dictionary dictionary);

    Integer Delete(Long id);

    Integer modify(Dictionary dictionary);
}
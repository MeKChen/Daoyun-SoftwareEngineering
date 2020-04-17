package com.gcsj.yunbanke.Service.Impl;

import com.gcsj.yunbanke.Entity.BaseClass.HttpContent;
import com.gcsj.yunbanke.Entity.Dictionary;
import com.gcsj.yunbanke.Repository.DictionaryRepository;
import com.gcsj.yunbanke.Service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryRepository dictionaryRepository;


    @Override
    public Dictionary findById(Long id) {
        Optional<Dictionary> byId = dictionaryRepository.findById(id);
        if (!byId.isPresent()) {
            return null;
        }
        return byId.get();
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryRepository.findAll();

    }

    @Override
    public Integer insert(Dictionary dictionary) {
        if (dictionary == null) {
            return 1;//用户不能为空
        }
        dictionary.setCreateDate(new Date());
        dictionaryRepository.save(dictionary);
        return 0;
    }

    @Override
    public Integer Delete(Long id) {
        Dictionary dictionary = findById(id);
        if (dictionary == null) {
            return 1;//删除对象不存在
        }
        if (HttpContent.removeIngoreSet.contains(dictionary.getDataKey())) {
            return 2; //不可删除
        }
        dictionaryRepository.delete(dictionary);
        return 0;
    }

    @Override
    public Integer modify(Dictionary dictionary) {
        if (dictionary == null){
            return 1; //对象不能为空
        }
        dictionaryRepository.save(dictionary);
        return 0;
    }
}
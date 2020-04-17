package com.gcsj.yunbanke.Service.Impl;

import com.gcsj.yunbanke.Entity.BaseClass.HttpContent;
import com.gcsj.yunbanke.Entity.DictionaryContent;
import com.gcsj.yunbanke.Repository.DictionaryContentRepository;
import com.gcsj.yunbanke.Service.DictionaryContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("dictionaryContentService")
public class DictionaryContentServiceImpl implements DictionaryContentService{
    @Autowired
    DictionaryContentRepository dictionaryContentRepository;

    @Override
    public DictionaryContent findById(Long id) {
        Optional<DictionaryContent> byId = dictionaryContentRepository.findById(id);
        if (!byId.isPresent()) {
            return null;
        }
        return byId.get();
    }

    @Override
    public Integer insert(DictionaryContent dictionaryContent) {
        if (dictionaryContent == null) {
            return 1; //不能为空
        }
        dictionaryContent.setCreateDate(new Date());
        dictionaryContentRepository.save(dictionaryContent);
        return 0;
    }

    @Override
    public Integer Delete(Long id) {
        DictionaryContent byId = findById(id);
        if (byId == null) {
            return 1;//对像不存在
        }
        if (HttpContent.removeIngoreSet.contains(byId.getContentKey())) {
            return 2; //不可删除
        }
        dictionaryContentRepository.delete(byId);
        return 0;
    }

    @Override
    public Integer modify(DictionaryContent dictionaryContent) {
        if (dictionaryContent == null) {
            return 1;//对象不能为空
        }
        dictionaryContentRepository.save(dictionaryContent);
        return 0;
    }

    @Override
    public List<DictionaryContent> findDicContentByDicId(Long dicId) {
        List<DictionaryContent> dicContentByDicId = dictionaryContentRepository.findDicContentByDicId(dicId);
        return dicContentByDicId;
    }
}
package com.signInStart.Service.Impl;

import com.signInStart.Entity.BaseClass.BaseSetting;
import com.signInStart.Entity.BaseClass.FriendlyException;
import com.signInStart.Entity.Dictionary;
import com.signInStart.Repository.DictionaryContentRepository;
import com.signInStart.Repository.DictionaryRepository;
import com.signInStart.Service.DictionaryService;
import com.signInStart.Service.LoginInfoService;
import com.signInStart.Utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    DictionaryContentRepository dicContentRepository;
    @Autowired
    LoginInfoService loginInfoService;

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
        if (dictionaryRepository.findByDataKey(dictionary.getDataKey()) != null) {
            return 2; //键名已存在
        }
        dictionaryRepository.save(dictionary);
        return 0;
    }

    @Override
    public Integer Delete(Long id) {
        Dictionary dictionary = findById(id);
        if (dictionary == null) {
            return 1;//删除对象不存在
        }
        if (dictionary.getDataTypeKey()!=null && dictionary.getDataTypeKey().equals(BaseSetting.DATATYPE.System_SYS.toString())) {
            return 2; //不可删除
        }
        dictionaryRepository.delete(dictionary);
        return 0;
    }

    @Override
    public Integer modify(Dictionary dictionary)throws FriendlyException {
        if (dictionary == null){
            return 1; //对象不能为空
        }
        if (dictionaryRepository.findMultiDataKey(dictionary.getDataKey(), dictionary.getId()) != null) {
            throw new FriendlyException("不能添加重复字典键", DataUtils.CurrentMethodName());
        }
        Dictionary byId = findById(dictionary.getId());
        byId.setModifyDate(new Date());
        byId.setModifyBy(loginInfoService.getAccount());
        DataUtils.copyProperty(dictionary, byId);
        if (dictionary.getDataDesc() == null || dictionary.getDataDesc() == "") {
            byId.setDataDesc("");
        }
        dictionaryRepository.save(byId);
        return 0;
    }

    @Override
    public List<Map<String, Object>> getDicIdValue() {
        return  dictionaryRepository.getDicIdValue();
    }

    @Override
    public List<Map<String, String>> getDicCntKeyValueByDicKey(String dicKey) {
        return dicContentRepository.findDicCntKeyValueByDicKey(dicKey);
    }
}
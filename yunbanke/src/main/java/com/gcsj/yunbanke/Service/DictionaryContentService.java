package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.DictionaryContent;

import java.util.List;

public interface DictionaryContentService {
    DictionaryContent findById(Long id);

    Integer insert(DictionaryContent dictionaryContent);

    Integer Delete(Long id);

    Integer modify(DictionaryContent dictionaryContent);

    List<DictionaryContent>  findDicContentByDicId(Long dicId);
}
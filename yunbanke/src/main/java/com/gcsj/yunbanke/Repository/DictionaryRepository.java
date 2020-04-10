package com.gcsj.yunbanke.Repository;

import com.gcsj.yunbanke.Entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @Query("select d from Dictionary d order by d.createDate desc ")
    List<Dictionary> findAll();
}
package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}

package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import com.web.dictionaryservice.dictionaryservicewebimplementation.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, String> {
}

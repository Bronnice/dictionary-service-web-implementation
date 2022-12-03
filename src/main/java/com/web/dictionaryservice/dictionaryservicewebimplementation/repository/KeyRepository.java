package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, Long> {
}

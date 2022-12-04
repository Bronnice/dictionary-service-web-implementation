package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
    Optional<Value> findByValue(String value);
    Boolean existsByValue(String value);
}

package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.ERole;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}


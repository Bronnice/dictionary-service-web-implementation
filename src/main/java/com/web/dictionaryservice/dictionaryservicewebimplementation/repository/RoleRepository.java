package com.web.dictionaryservice.dictionaryservicewebimplementation.repository;

import java.util.Optional;

import com.web.dictionaryservice.dictionaryservicewebimplementation.models.ERole;
import com.web.dictionaryservice.dictionaryservicewebimplementation.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}

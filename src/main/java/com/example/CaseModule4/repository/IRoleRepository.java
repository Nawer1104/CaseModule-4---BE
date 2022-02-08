package com.example.CaseModule4.repository;

import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}

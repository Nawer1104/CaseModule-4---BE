package com.example.CaseModule4.repository;

import com.example.CaseModule4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepositoty extends JpaRepository<Users, Long> {
    List<Users> findUsersByIdIsNotLike(Long id);
    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

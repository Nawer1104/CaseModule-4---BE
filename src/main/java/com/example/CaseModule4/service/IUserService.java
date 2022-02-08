package com.example.CaseModule4.service;



import com.example.CaseModule4.model.Users;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    void deleteById(Long id);
    Optional<Users> findById(Long id);
    List<Users> findAll();
    List<Users> findUsersByIdIsNotLike(Long id);
}

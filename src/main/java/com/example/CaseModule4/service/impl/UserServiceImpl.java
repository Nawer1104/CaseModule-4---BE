package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.repository.IUserRepositoty;
import com.example.CaseModule4.security.userprincal.UserPrinciple;
import com.example.CaseModule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepositoty userRepositoty;

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepositoty.findByUsername(username);
    }

    @Override
    public Boolean existsByUserName(String username) {
        return userRepositoty.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepositoty.existsByEmail(email);
    }

    @Override
    @Transactional
    public Users save(Users users) {
        em.joinTransaction();
        return userRepositoty.save(users);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.joinTransaction();
        userRepositoty.deleteById(id);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepositoty.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepositoty.findAll();
    }

    @Override
    public List<Users> findUsersByIdIsNotLike(Long id) {
        return userRepositoty.findUsersByIdIsNotLike(id);
    }

}

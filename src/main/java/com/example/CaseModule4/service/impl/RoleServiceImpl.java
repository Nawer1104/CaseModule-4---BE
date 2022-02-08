package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.RoleName;
import com.example.CaseModule4.repository.IRoleRepository;
import com.example.CaseModule4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}

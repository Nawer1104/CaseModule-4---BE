package com.example.CaseModule4.service;

import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.RoleName;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findByName(RoleName name);

}

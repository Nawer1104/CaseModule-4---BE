package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.response.ResponseMessage;
import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("home")
public class MainController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/roles")
    public List<Role> roles (){
        return roleService.findAll();
    }

}

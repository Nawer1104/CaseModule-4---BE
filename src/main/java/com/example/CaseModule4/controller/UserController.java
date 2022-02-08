package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.request.EditForm;
import com.example.CaseModule4.dto.response.ResponseMessage;
import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.RoleName;
import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.security.userprincal.UserPrinciple;
import com.example.CaseModule4.service.IRoleService;
import com.example.CaseModule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RequestMapping("/storemanager")
@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IRoleService roleService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<Users> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(user.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<?> checkAuthority() {
        return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Users users, @AuthenticationPrincipal UserPrinciple userPrinciple){
        Users userOld = userService.findByUserName(userPrinciple.getUsername()).get();
        if (users.getPassword() == null) {
            users.setPassword(userOld.getPassword());
        }
        userService.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/show")
    public ResponseEntity<?> show(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        List<Users> list = userService.findUsersByIdIsNotLike(users.getId());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<Users> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/getTitle")
    public ResponseEntity<?> getTitle(){
        List<Role> list = roleService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/editPartner")
    public ResponseEntity<?> edit(@RequestBody EditForm editForm) {
        Users oldUser = userService.findById(editForm.getId()).get();
        Users users = new Users(
                editForm.getId(),
                editForm.getName(),
                editForm.getUsername(),
                editForm.getEmail(),
                editForm.getAvatar());
        if (editForm.getPassword().equals("")) {
            users.setPassword(oldUser.getPassword());
        } else {
            users.setPassword(passwordEncoder.encode(editForm.getPassword()));
        }
        Set<String> strRoles = editForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "STOREMANAGER" :
                    Role smRole = roleService.findByName(RoleName.STOREMANAGER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(smRole);
                    break;
                case "INCHARGE":
                    Role icRole = roleService.findByName(RoleName.INCHARGE).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(icRole);
                    break;
                default:
                    Role pnRole = roleService.findByName(RoleName.PARTNER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pnRole);
            }
        });
        users.setRoles(roles);
        userService.save(users);
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
    }

}

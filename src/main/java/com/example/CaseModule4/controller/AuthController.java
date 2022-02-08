package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.request.SignInForm;
import com.example.CaseModule4.dto.request.SignUpForm;
import com.example.CaseModule4.dto.response.JwtResponse;
import com.example.CaseModule4.dto.response.ResponseMessage;
import com.example.CaseModule4.model.Role;
import com.example.CaseModule4.model.RoleName;
import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.security.jwt.JwtProvider;
import com.example.CaseModule4.security.userprincal.UserPrinciple;
import com.example.CaseModule4.service.IRoleService;
import com.example.CaseModule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register (@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUserName(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("username_existed"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/myproject-58f26.appspot.com/o/images%2Fdefaultavatar.png?alt=media&token=a441bd23-f1dc-4a5f-bfe4-9bcb8dee339d");
        }
        Users users = new Users(
          signUpForm.getName(),
          signUpForm.getUsername(),
          signUpForm.getEmail(),
          signUpForm.getAvatar(),
          passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
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
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(token, users));
    }
}

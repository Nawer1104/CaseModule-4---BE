package com.example.CaseModule4.security.userprincal;

import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.repository.IUserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepositoty userRepositoty;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepositoty.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserPrinciple.build(users);
    }
}

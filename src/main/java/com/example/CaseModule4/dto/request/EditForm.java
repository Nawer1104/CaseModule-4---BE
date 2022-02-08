package com.example.CaseModule4.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class EditForm {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Set<String> roles;
}

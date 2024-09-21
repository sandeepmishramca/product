package com.example.demo.dtos;

import com.example.demo.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}

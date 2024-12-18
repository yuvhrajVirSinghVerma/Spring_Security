package com.example.demo.Dto;

import com.example.demo.entities.enums.Permissions;
import com.example.demo.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    String name;
    String email;
    Set<Role>Roles;
    Set<Permissions>permissions;

}

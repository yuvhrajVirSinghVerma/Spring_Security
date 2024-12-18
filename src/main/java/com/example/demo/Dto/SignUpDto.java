package com.example.demo.Dto;

import com.example.demo.entities.enums.Permissions;
import com.example.demo.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {
    String name;
    String email;
    String password;
    Set<Role>Roles;
    Set<Permissions>permissions;

}

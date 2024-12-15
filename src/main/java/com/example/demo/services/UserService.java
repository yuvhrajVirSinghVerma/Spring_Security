package com.example.demo.services;

import com.example.demo.Dto.LogInDto;
import com.example.demo.Dto.SignUpDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userrepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    JwtService jwtServ;

    public UserDto signUp(SignUpDto data) {
        Optional<User> user=userrepo.findByEmail(data.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User Already Exists with this Email "+data.getEmail());
        }
        User createUser=modelMapper.map(data,User.class);

        createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
        User newUser=userrepo.save(createUser);

        return modelMapper.map(newUser,UserDto.class);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loaduser "+username);
        return userrepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    public User getUserById(Long id){
        return userrepo.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}

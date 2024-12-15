package com.example.demo.controller;


import com.example.demo.Dto.LogInDto;
import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.Dto.SignUpDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationNotSupportedException;
import java.util.Arrays;

@RestController
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    @GetMapping("/public")
    public String PublicData(){
        return "public Data";
    }

    @GetMapping("/Admin")
    public String AdminData(){
        return "Admin Data";
    }

    @PostMapping("/auth/SignUp")
    public ResponseEntity<UserDto> SignUp(@RequestBody SignUpDto data){
        System.out.println("SignUpDto "+data);
        UserDto user=userService.signUp(data);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LogInDto loginDt, HttpServletResponse resp){
        LoginResponseDto loginResp=authService.Login(loginDt);
        Cookie cookie=new Cookie("RefreshToken",loginResp.getRefreshToken());
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        return  ResponseEntity.ok(loginResp);
    }

    @GetMapping("/getData")
    public String getData(){
        return "You Are Authenticated";
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        String refreshToken= Arrays.stream(request.getCookies()).
                filter(cookie->"RefreshToken".equals(cookie.getName())).
                findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()->new AuthenticationServiceException(""));


    }
}

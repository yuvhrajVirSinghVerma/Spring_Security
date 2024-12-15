package com.example.demo.controller;


import com.example.demo.Dto.LogInDto;
import com.example.demo.Dto.SignUpDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(@RequestBody LogInDto loginDt, HttpServletResponse resp){
        String token=authService.Login(loginDt);
        Cookie cookie=new Cookie("token",token);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        return token;
    }

    @GetMapping("/getData")
    public String getData(){
        return "You Are Authenticated";
    }
}

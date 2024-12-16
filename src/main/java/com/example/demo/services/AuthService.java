package com.example.demo.services;

import com.example.demo.Dto.LogInDto;
import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager; //created its bean in config
    @Autowired
    JwtService jwtServ;

    @Autowired
    UserService userService;
    public LoginResponseDto Login(LogInDto loginDt) {
        //invokes daoAuthenticationprovider which uses our custom userdetailservice loadbyusername to verify user from our repo
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDt.getEmail(),loginDt.getPassword()));

        User user= (User) authentication.getPrincipal();
        System.out.println("user "+user);
        String Accesstoken=jwtServ.generateAccessToken(user);
        String RefreshToken= jwtServ.generateRefreshToken(user);
        return new LoginResponseDto(user.getId(),Accesstoken,RefreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long Id= jwtServ.getUserId(refreshToken);//this validates that our refresh token is valid;
        User user=userService.getUserById(Id);

        String AccessToken= jwtServ.generateAccessToken(user);

        return new LoginResponseDto(user.getId(),AccessToken,refreshToken);
    }
}

package com.example.demo;

import com.example.demo.entities.User;
import com.example.demo.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JwtService jwtservice;
	@Test
	void contextLoads() {
//		User user=new User(1L,"Yuvi","YuviEmail","YuviPass");
//
//		String token=jwtservice.generateToken(user);
//
//		System.out.println(token);
//
//		Long id=jwtservice.getUserId(token);
//
//		System.out.println(id);
	}

}

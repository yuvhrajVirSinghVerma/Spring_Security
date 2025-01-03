package com.example.demo.config;

import com.example.demo.advice.AuthemticationEntryPointClass;
import com.example.demo.entities.enums.Permissions;
import com.example.demo.entities.enums.Role;
import com.example.demo.filters.JwtAuthFilter;
import com.example.demo.services.JwtService;
import com.example.demo.services.SessionService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity()
public class WebSecurityConfig {

    @Autowired
    JwtAuthFilter jwtauthfilter;

    @Autowired
    AuthemticationEntryPointClass entryPoint;

    @Autowired
    JwtService jwtservice;

    @Autowired
    UserService userservice;

    @Autowired
    SessionService sessionService;


    private final HandlerExceptionResolver handlerExceptionResolver;
    public WebSecurityConfig(HandlerExceptionResolver handlerExceptionResolver){
        this.handlerExceptionResolver=handlerExceptionResolver;
    }
    @Bean
    SecurityFilterChain customFilter(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.
//               httpBasic(basic-> basic.authenticationEntryPoint(entryPoint)).
        authorizeHttpRequests(auth ->
        auth.requestMatchers("/public", "/auth/**").permitAll().//making this route public no authentication needed
                requestMatchers("/Admin").hasAnyRole("ADMIN").
//                requestMatchers("/Post/**").hasRole(Role.ADMIN.name()).
                requestMatchers(HttpMethod.POST,"/Post/**").hasAnyRole(Role.ADMIN.name(), Permissions.POST_CREATE.name()).
                requestMatchers(HttpMethod.GET,"/Post/**").hasAnyRole(Role.USER.name(), Permissions.POST_VIEW.name()).

                anyRequest().authenticated()).
                csrf(csrfcnfg -> csrfcnfg.disable()).
                sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                .addFilterBefore(new JwtAuthFilter(handlerExceptionResolver, jwtservice, userservice,sessionService), UsernamePasswordAuthenticationFilter.class);
//                addFilterAfter(testErrorHandlerFilter, JwtAuthFilter.class);
//                formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService customUserDetailService(){
//        UserDetails user= User.withUsername("Yuvhraj").
//                password(customPasswordEncoder().
//                        encode("YuvhrajPass")).
//                        roles("ADMIN").
//                        build();
//
//        UserDetails user2= User.withUsername("Yuvi").
//                password(customPasswordEncoder().
//                        encode("YuviPass")).
//                roles("USER").
//                build();
//
//        return new InMemoryUserDetailsManager(Arrays.asList(user,user2));//creates a user in internal memory see internal implementation
//    }



    @Bean
    AuthenticationManager customAuthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}

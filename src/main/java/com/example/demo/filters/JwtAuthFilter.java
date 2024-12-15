package com.example.demo.filters;


import com.example.demo.entities.User;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.security.SignatureException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtservice;

    private final UserService userservice;

    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthFilter(HandlerExceptionResolver handlerExceptionResolver, JwtService jwtservice, UserService userservice) {
        this.handlerExceptionResolver=handlerExceptionResolver;
        this.jwtservice=jwtservice;
        this.userservice=userservice;
    }


//    @Autowired
//    @Qualifier("handlerExceptionresolver")
//    private HandlerExceptionResolver handlerExceptionresolver;//giving two beans created error so used @Qualifier

    @Override
    //custom filterchain where we will authenticate logined users for future requests
    //register this filter in webconfig
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          try{
              String Reqtoken=request.getHeader("Authorization");
              System.out.println("reqtoken++++ "+Reqtoken);
              if(Reqtoken==null || !Reqtoken.startsWith("Bearer")){
                  filterChain.doFilter(request,response);
                  return;
              }

              String token=Reqtoken.split("Bearer")[1];
              System.out.println("token++++ "+token);
              Long id=jwtservice.getUserId(token);
              System.out.println("user token id++++ "+id);

              if(id!=null ){
                  User user=userservice.getUserById(id);
                  UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,null,null);
                  authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//contain users device info , ip can be useful for rate limiting
                  SecurityContextHolder.getContext().setAuthentication(authentication);
                  System.out.println("securtiycontext "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

              }
              filterChain.doFilter(request,response);

          } catch (Exception e) {
              System.out.println("AuthenticationException thrown"+e.getLocalizedMessage());
              handlerExceptionResolver.resolveException(request, response, null, e);
//              response.getWriter().write("{\"error\": \"" + e.getMessage() + "\", \"message\": \"" + e.getLocalizedMessage() + "\"}");
          }




    }
}

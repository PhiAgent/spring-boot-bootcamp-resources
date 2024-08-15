package com.ltp.gradesubmission.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.security.SecurityConstants;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

  CustomAuthenticationManager customAuthenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    // if user puts invalid fields in request,
    // like passwordd instead of password, it
    // will lead to exception and must catch these exceptions
    try{
      User user = new ObjectMapper()
      // reads the value into binary results
      .readValue(
      // convert the binary output into value of class User
      request.getInputStream(), User.class);
      // this is wehre we pass the credentials to the authentication manager
      // you can create the authentication object here using the user credentials
      Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
      return customAuthenticationManager.authenticate(authentication);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  // @Override
  // protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
  //     Authentication authResult) throws IOException, ServletException {
  //   System.out.println("boohoo, authentication failed");
  // }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String token = JWT.create()
    .withSubject(authResult.getName())
    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
    .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
    response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
  }
}

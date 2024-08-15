package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

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
      System.out.println(user.getUsername());
      System.out.println(user.getPassword());
    } catch (IOException e) {
      throw new RuntimeException();
    }

    return super.attemptAuthentication(request, response);
  }
}

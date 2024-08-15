package com.ltp.gradesubmission.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{

  private UserService userService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // Here, we try to fetch the user in the record with this username
    // if no user with the username exists, a
    // NoEntityFoundException will be thrown and
    // this exception can be caught in the
    // ExceptionHandlerFilter
    User user = userService.getUser(authentication.getName());

    // this is where we check to see if the encrypted password user provides matches
    // the encrypted password that's stored in record
    if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()))
      throw new BadCredentialsException("Wrong Password");

    // if the correct credentials are supplied, we won't make it to this line as the
    // BadCredentialsException will be thrown above so at this point, its safe to say
    // usercredentials are correct so we'll go ahead and return the authentication object
    return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString());
  }
}

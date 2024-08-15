package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ltp.gradesubmission.security.SecurityConstants;

// In this class, we take the token and the secret key and generate a signature using
// the algorithm, token header and payload and
// compare the signature generated to the signature in the token
// if the signature is valid, then we set the authentication object on the security context
// holder. the security context holder stores details of who is authenticated
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  // Request will have a header like so: Authorization: Bearer JWT
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader("Authorization"); //Bearer JWT
    if(header == null || !header.startsWith(SecurityConstants.BEARER)) {
      chain.doFilter(request, response);
      return;
    }

    String token = header.replace(SecurityConstants.BEARER, "");
    String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
      .build()
      .verify(token)
      .getSubject();

      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
  }
}

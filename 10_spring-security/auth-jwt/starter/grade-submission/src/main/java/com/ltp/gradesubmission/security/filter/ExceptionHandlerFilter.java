package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ltp.gradesubmission.exception.EntityNotFoundException;

// Inherit from OncePerRequestFilter instead of general filter to ensure the filter is invoked
// once per Request
// This ExceptionHandlerFilter will be placed before any other filter. and then we'll wrap the
// doFilter method in a try block. this way if any
// exceptions are triggered in the downstream filters, they'll be caught in
// ExceptionHandlerFilter and we can handle it accordingly
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    try {
      chain.doFilter(request, response);
    }
    // here you can catch any type of exception that'll be triggered downstream here
    catch (EntityNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("Username does not exist");
      response.getWriter().flush();
    } catch (RuntimeException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      // If you want to write a string in response
      response.getWriter().write("BAD_REQUEST");
      response.getWriter().flush();
    }
  }
}

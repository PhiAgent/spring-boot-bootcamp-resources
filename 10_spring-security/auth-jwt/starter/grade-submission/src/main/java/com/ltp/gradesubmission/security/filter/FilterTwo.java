// package com.ltp.gradesubmission.security.filter;

// import java.io.IOException;

// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ltp.gradesubmission.entity.User;

// public class FilterTwo implements Filter {

//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//       throws IOException, ServletException {

//     // this filter will be used right after the authentication filter
//     // just to play around, we're gonna use the ObjectMapper to grab the payload of the
//     // request that'll be passed to this filter
//     User user = new ObjectMapper()
//       .readValue(
//       request.getInputStream(), User.class);
//     System.out.println(user.getPassword());
//     System.out.println(user.getUsername());
//     // ((HttpServletRequest) request).getRequestURI();

//     // chain.doFilter(request, response);
//   }
// }

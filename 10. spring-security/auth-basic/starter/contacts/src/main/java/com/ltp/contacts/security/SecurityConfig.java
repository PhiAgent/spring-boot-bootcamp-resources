package com.ltp.contacts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

// You use the configuration annotation to mark a class as a source for a bean annotation
@Configuration
@AllArgsConstructor
public class SecurityConfig {

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  // by defining this bean, we remove the default login ui that spring security
  // applies once we add the spring security dependency
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // authorize request
      .authorizeRequests()
      // authenticate request
      .anyRequest().authenticated()
      // using basic authentication
      .and()
      .httpBasic()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }

  @Bean
  public UserDetailsService users(){
    UserDetails admin = User.builder()
        .username("admin")
        // never store passwords in plain text
        // the one way bcrypt hashing algorithm has no way of regenerating input from encrypted output
        // on the backend we dont care what the password is, all we care about is that
        // the user knows the password
        // how do we determine this? we pass the given password to
        // the same encryption and if the result is the same as what
        // we have, then the input is the same
        .password(bCryptPasswordEncoder.encode("admin-pass"))
        .roles("ADMIN")
        .build();
        // after building the userdetails, the userdetails is going to be stored in memory
    UserDetails user = User.builder()
        .username("user")
        .password(bCryptPasswordEncoder.encode("user-pass"))
        .roles("USER")
        .build();

    // You can access the userdetail stored in memory using InMem
    return new InMemoryUserDetailsManager(admin, user);
  }
}

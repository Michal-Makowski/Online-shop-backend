package com.makowski.shop.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.makowski.shop.security.filter.AuthenticationFilter;
import com.makowski.shop.security.filter.ExceptionHandlerFilter;
import com.makowski.shop.security.filter.JWTAuthorizationFilter;
import com.makowski.shop.security.manager.CustomAuthenticationManager;
import com.makowski.shop.service.user.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager customAuthenticationManager;
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .headers().frameOptions().disable()// only for h2
                .and()
                .csrf().disable()
                /*
                 * Disabled for test
                 * .authorizeHttpRequests()
                 * .requestMatchers(toH2Console()).permitAll() //only for h2
                 * TODO Authority manager
                 * 
                 * .requestMatchers(HttpMethod.POST, "/user/registerUser").permitAll()
                 * .requestMatchers(HttpMethod.POST, "/user/registerAdmin").permitAll()
                 * .requestMatchers(HttpMethod.GET, "/user/all").hasAuthority("ADMIN")
                 * .requestMatchers(HttpMethod.POST, "/login").permitAll()
                 * .anyRequest().authenticated()
                 * .and()
                 * .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                 * .addFilter(new AuthenticationFilter(customAuthenticationManager))
                 * .addFilterAfter(new JWTAuthorizationFilter(userService),
                 * AuthenticationFilter.class)
                 */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}

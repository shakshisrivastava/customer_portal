package com.DemoProject.cmp.Config;

import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.DemoProject.cmp.Entity.Role.ADMIN;

@RequiredArgsConstructor
@Configuration

public class SecurityFilterChainConfig {


    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
         {
            //Disable csrf
          httpSecurity.csrf(AbstractHttpConfigurer::disable);
            httpSecurity.authorizeHttpRequests(
                    requestMatcher ->
                            requestMatcher.requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                                    .requestMatchers("/customer/**").hasAnyAuthority("USER")
                                    .requestMatchers("/auth/**").permitAll()
                                    //.requestMatchers("/auth/admin/**").permitAll()
                                    .anyRequest().authenticated()

            );
            //Authentication Entry point ->exception handling
          httpSecurity.exceptionHandling(
                    httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint)

            );
            //Session Policy
            httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            //Add a jwt Authentication filter
            httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }

    }
}


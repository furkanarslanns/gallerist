package com.furkanarslan.gallerist.config;

import com.furkanarslan.gallerist.jwt.JwtAutheticetionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider ;
    @Autowired
    private JwtAutheticetionFilter jwtAuthenticationFilter;

    public static final String authenticate = "/authenticate";
    public static final String register = "/register";
    public static final String REFRESH_TOKEN = "/refreshToken";

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests
            (requests ->requests
                    .requestMatchers(authenticate,register,REFRESH_TOKEN).permitAll()
                    .anyRequest()
                    .authenticated())
            .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();


}

}

package com.furkanarslan.gallerist.config;

import com.furkanarslan.gallerist.entitiy.User;
import com.furkanarslan.gallerist.excaption.BaseExcaption;
import com.furkanarslan.gallerist.excaption.ErrorMessage;
import com.furkanarslan.gallerist.excaption.MessageType;
import com.furkanarslan.gallerist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
@Configuration
public class AppConfig {

    @Autowired
     UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> optional=  userRepository.findByUsername(username);
                if(optional.isEmpty()) {
                   throw  new BaseExcaption(new ErrorMessage(MessageType.USERNAME_NOT_FOUND,username));
                }
                return optional.get();
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());



        return authenticationProvider;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}

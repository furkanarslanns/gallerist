package com.furkanarslan.gallerist.jwt;

import com.furkanarslan.gallerist.excaption.BaseExcaption;
import com.furkanarslan.gallerist.excaption.ErrorMessage;
import com.furkanarslan.gallerist.excaption.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAutheticetionFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
if (header==null) {
    filterChain.doFilter(request, response);
    return;
}
String token;
String username;
token= header.substring(7);

try {
    username = jwtService.getUsernameByToken(token);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null && jwtService.isTokenExpired(token)) {
            // kişiyi security contexte koyabilirim
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

            authentication.setDetails(userDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }
}
catch (ExpiredJwtException e){
throw  new BaseExcaption(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED,token));
}
catch (Exception e) {
throw new BaseExcaption(new ErrorMessage(MessageType.GENERAL_EXCAPTION,e.getMessage()));
}

    }
}

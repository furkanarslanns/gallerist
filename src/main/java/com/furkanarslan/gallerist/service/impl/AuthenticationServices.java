package com.furkanarslan.gallerist.service.impl;

import com.furkanarslan.gallerist.dto.AuthRequest;
import com.furkanarslan.gallerist.dto.AuthResponse;
import com.furkanarslan.gallerist.dto.DtoUser;
import com.furkanarslan.gallerist.entitiy.RefreshToken;
import com.furkanarslan.gallerist.entitiy.User;
import com.furkanarslan.gallerist.excaption.BaseExcaption;
import com.furkanarslan.gallerist.excaption.ErrorMessage;
import com.furkanarslan.gallerist.excaption.MessageType;
import com.furkanarslan.gallerist.jwt.JwtService;
import com.furkanarslan.gallerist.repository.RefreshTokenRepository;
import com.furkanarslan.gallerist.repository.UserRepository;
import com.furkanarslan.gallerist.service.IAuthenticationServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServices implements IAuthenticationServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtService jwtService;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;


    public User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        user.setCreateTime(new Date());
        return user;
    }
    // refresh token generate ettim
public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken1 = new RefreshToken();
        refreshToken1.setUser(user);
        refreshToken1.setCreateTime(new Date());
        refreshToken1.setExpiredDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken1.setRefreshToken(UUID.randomUUID().toString());

        return refreshToken1;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        DtoUser dtoUser = new DtoUser();

        User user = userRepository.save(createUser(request));
        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }
    // burada kullanıcıya accses ve refresh token dönüşü yapıyoruz.
    @Override
    public AuthResponse authenticate(AuthRequest request) {

        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Optional<User> optinal = userRepository.findByUsername(request.getUsername());

            String accessToken = jwtService.generateToken(optinal.get());
RefreshToken refreshToken = generateRefreshToken(optinal.get());
            RefreshToken saved = refreshTokenRepository.save(refreshToken);
return new AuthResponse(accessToken, refreshToken.getRefreshToken());
        }
        catch (Exception e) {
   throw new BaseExcaption(new ErrorMessage(MessageType.USERNAME_OR_INVALİD,e.getMessage()));
        }




    }
}
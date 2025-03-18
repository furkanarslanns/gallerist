package com.furkanarslan.gallerist.service;

import com.furkanarslan.gallerist.dto.AuthRequest;
import com.furkanarslan.gallerist.dto.AuthResponse;
import com.furkanarslan.gallerist.dto.DtoUser;

public interface IAuthenticationServices {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);


}

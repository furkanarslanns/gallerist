package com.furkanarslan.gallerist.controller;

import com.furkanarslan.gallerist.dto.AuthRequest;
import com.furkanarslan.gallerist.dto.AuthResponse;
import com.furkanarslan.gallerist.dto.DtoUser;

public interface IRestAuthenticationController {

    public  RootEntity<DtoUser> register(AuthRequest request);
    public RootEntity<AuthResponse> authenticate(AuthRequest request);
}

package com.furkanarslan.gallerist.controller.impl;

import com.furkanarslan.gallerist.controller.IRestAuthenticationController;
import com.furkanarslan.gallerist.controller.RestBaseController;
import com.furkanarslan.gallerist.controller.RootEntity;
import com.furkanarslan.gallerist.dto.AuthRequest;
import com.furkanarslan.gallerist.dto.AuthResponse;
import com.furkanarslan.gallerist.dto.DtoUser;
import com.furkanarslan.gallerist.service.impl.AuthenticationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticaitonController extends RestBaseController implements IRestAuthenticationController {
   @Autowired
    AuthenticationServices authenticationServices;

  @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
        return ok(authenticationServices.register(request));
    }
  @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        return ok(authenticationServices.authenticate(request));
    }
}

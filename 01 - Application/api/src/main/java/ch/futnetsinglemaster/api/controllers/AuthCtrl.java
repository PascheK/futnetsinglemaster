package ch.futnetsinglemaster.api.controllers;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.service.impl.TokenServiceIMPL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthCtrl {
    private static final Logger LOG = LoggerFactory.getLogger(AuthCtrl.class);

    private final TokenServiceIMPL tokenService;

    private final AuthenticationManager authenticationManager;

    public AuthCtrl(TokenServiceIMPL tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager; 
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest login ) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()));
        return tokenService.generateToken(authentication);
    }
}

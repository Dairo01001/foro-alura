package com.dairodev.api_foro.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailsService appUserDetailsService;


    @Override
    public JwtResponse authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                                authRequest.email(),
                                authRequest.password()
                        )
                );

        if (authentication.isAuthenticated()) {
            var userDetails = appUserDetailsService.loadUserByUsername(authRequest.email());
            return new JwtResponse(jwtService.generateToken(userDetails));
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
}

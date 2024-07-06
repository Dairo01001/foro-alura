package com.dairodev.api_foro.Auth;

public interface AuthService {
    JwtResponse authenticate(AuthRequest authRequest);
}

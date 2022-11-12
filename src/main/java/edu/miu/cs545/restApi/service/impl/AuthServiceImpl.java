package edu.miu.cs545.restApi.service.impl;

import edu.miu.cs545.restApi.dto.request.LoginRequest;
import edu.miu.cs545.restApi.dto.request.RefreshTokenRequest;
import edu.miu.cs545.restApi.dto.response.LoginResponse;
import edu.miu.cs545.restApi.service.AuthService;
import edu.miu.cs545.restApi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
        }catch (BadCredentialsException e){
            log.info("Bad Credentials");
        }

        UserDetails UserDetails = myUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        String accessToken = jwtUtil.generateToken(UserDetails);
        String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getUsername());

        return new LoginResponse(accessToken,refreshToken);

    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }
}

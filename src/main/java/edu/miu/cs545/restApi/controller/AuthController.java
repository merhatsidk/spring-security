package edu.miu.cs545.restApi.controller;


import edu.miu.cs545.restApi.dto.request.LoginRequest;
import edu.miu.cs545.restApi.dto.request.RefreshTokenRequest;
import edu.miu.cs545.restApi.dto.response.LoginResponse;
import edu.miu.cs545.restApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        var response = authService.login(loginRequest);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok().body(authService.refreshToken(request));
    }
}

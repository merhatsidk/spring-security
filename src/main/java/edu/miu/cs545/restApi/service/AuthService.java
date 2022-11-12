package edu.miu.cs545.restApi.service;

import edu.miu.cs545.restApi.dto.request.LoginRequest;
import edu.miu.cs545.restApi.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}

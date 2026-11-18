package com.longld.commerceflow.service.auth;

import com.longld.commerceflow.dto.auth.request.LoginRequestDTO;
import com.longld.commerceflow.dto.auth.request.RefreshTokenRequestDTO;
import com.longld.commerceflow.dto.auth.request.SignUpRequestDTO;
import com.longld.commerceflow.dto.auth.response.AuthResponseDTO;
import com.longld.commerceflow.dto.user.response.UserResponseDTO;

public interface AuthService {

    UserResponseDTO signUp(SignUpRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);

    AuthResponseDTO refreshToken(RefreshTokenRequestDTO request);

    void logout(String refreshToken);
}

package com.longld.commerceflow.controller.auth;

import com.longld.commerceflow.common.ApiResponse;
import com.longld.commerceflow.dto.auth.request.LoginRequestDTO;
import com.longld.commerceflow.dto.auth.request.RefreshTokenRequestDTO;
import com.longld.commerceflow.dto.auth.request.SendOtpRequestDTO;
import com.longld.commerceflow.dto.auth.request.SignUpRequestDTO;
import com.longld.commerceflow.dto.auth.request.VerifyOtpRequestDTO;
import com.longld.commerceflow.dto.auth.response.AuthResponseDTO;
import com.longld.commerceflow.dto.auth.response.VerifyOtpResponseDTO;
import com.longld.commerceflow.dto.user.response.UserResponseDTO;
import com.longld.commerceflow.service.auth.AuthService;
import com.longld.commerceflow.service.auth.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<Void>> sendRegistrationOtp(@Valid @RequestBody SendOtpRequestDTO request) {
        otpService.sendRegistrationOtp(request);
        return ResponseEntity.ok(ApiResponse.success("OTP sent to your email successfully", null));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<VerifyOtpResponseDTO>> verifyRegistrationOtp(@Valid @RequestBody VerifyOtpRequestDTO request) {
        VerifyOtpResponseDTO response = otpService.verifyRegistrationOtp(request);
        return ResponseEntity.ok(ApiResponse.success("OTP verified successfully", response));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDTO>> signUp(@Valid @RequestBody SignUpRequestDTO request) {
        UserResponseDTO userResponse = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", userResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        AuthResponseDTO authResponse = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO request) {
        AuthResponseDTO authResponse = authService.refreshToken(request);
        return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", authResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody(required = false) RefreshTokenRequestDTO request) {
        if (request != null) {
            authService.logout(request.getRefreshToken());
        }
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
}

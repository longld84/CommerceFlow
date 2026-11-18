package com.longld.commerceflow.service.auth.impl;

import com.longld.commerceflow.dto.auth.request.LoginRequestDTO;
import com.longld.commerceflow.dto.auth.request.RefreshTokenRequestDTO;
import com.longld.commerceflow.dto.auth.request.SignUpRequestDTO;
import com.longld.commerceflow.dto.auth.response.AuthResponseDTO;
import com.longld.commerceflow.dto.user.response.UserResponseDTO;
import com.longld.commerceflow.entity.auth.OtpVerification;
import com.longld.commerceflow.entity.auth.RefreshToken;
import com.longld.commerceflow.entity.user.Role;
import com.longld.commerceflow.entity.user.User;
import com.longld.commerceflow.exception.AppException;
import com.longld.commerceflow.exception.ErrorCode;
import com.longld.commerceflow.repository.auth.OtpVerificationRepository;
import com.longld.commerceflow.repository.auth.RefreshTokenRepository;
import com.longld.commerceflow.repository.user.UserRepository;
import com.longld.commerceflow.security.CustomUserDetails;
import com.longld.commerceflow.security.JwtTokenProvider;
import com.longld.commerceflow.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OtpVerificationRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserResponseDTO signUp(SignUpRequestDTO request) {
        String email = request.getEmail().trim().toLowerCase();

        // 1. Xác thực OTP Token
        OtpVerification otp = otpRepository.findByOtpTokenAndVerifiedTrue(request.getOtpToken())
                .orElseThrow(() -> new AppException(ErrorCode.OTP_TOKEN_INVALID));

        if (!otp.getEmail().equalsIgnoreCase(email)) {
            throw new AppException(ErrorCode.OTP_TOKEN_INVALID, "OTP token does not match the provided email");
        }

        if (otp.getExpiryDate().plusMinutes(15).isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_TOKEN_INVALID, "OTP token has expired. Please verify OTP again.");
        }

        // 2. Kiểm tra email trùng
        if (userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 3. Tạo User
        User user = User.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName().trim())
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);
        log.info("Registered new user with email: {}", savedUser.getEmail());

        // 4. Xóa OTP sau khi đăng ký thành công
        otpRepository.deleteAllByEmail(email);

        return mapToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public AuthResponseDTO login(LoginRequestDTO request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String refreshTokenString = jwtTokenProvider.generateRefreshToken(user.getEmail());

        // Save refresh token to database
        long refreshExpirationMs = jwtTokenProvider.getRefreshTokenExpiration();
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(refreshTokenString)
                .expiryDate(LocalDateTime.now().plusSeconds(refreshExpirationMs / 1000))
                .revoked(false)
                .build();
        refreshTokenRepository.save(refreshToken);

        log.info("User {} logged in successfully", user.getEmail());

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenString)
                .tokenType("Bearer")
                .expiresInSeconds(jwtTokenProvider.getAccessTokenExpiration() / 1000)
                .emailVerified(true)
                .user(mapToUserResponse(user))
                .build();
    }

    @Override
    @Transactional
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO request) {
        String tokenStr = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(tokenStr)) {
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }

        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndRevokedFalse(tokenStr)
                .orElseThrow(() -> new AppException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }

        User user = refreshToken.getUser();
        CustomUserDetails userDetails = new CustomUserDetails(user);

        String newAccessToken = jwtTokenProvider.generateAccessToken(userDetails);

        return AuthResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(tokenStr)
                .tokenType("Bearer")
                .expiresInSeconds(jwtTokenProvider.getAccessTokenExpiration() / 1000)
                .emailVerified(true)
                .user(mapToUserResponse(user))
                .build();
    }

    @Override
    @Transactional
    public void logout(String refreshTokenStr) {
        if (refreshTokenStr != null && !refreshTokenStr.isBlank()) {
            refreshTokenRepository.findByToken(refreshTokenStr).ifPresent(token -> {
                token.setRevoked(true);
                refreshTokenRepository.save(token);
                log.info("Revoked refresh token for user: {}", token.getUser().getEmail());
            });
        }
    }

    private UserResponseDTO mapToUserResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole().name())
                .isActive(Boolean.TRUE.equals(user.getIsActive()))
                .addresses(Collections.emptyList())
                .build();
    }
}


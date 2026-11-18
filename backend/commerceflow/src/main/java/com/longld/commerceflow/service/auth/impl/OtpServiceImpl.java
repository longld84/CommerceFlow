package com.longld.commerceflow.service.auth.impl;

import com.longld.commerceflow.dto.auth.request.SendOtpRequestDTO;
import com.longld.commerceflow.dto.auth.request.VerifyOtpRequestDTO;
import com.longld.commerceflow.dto.auth.response.VerifyOtpResponseDTO;
import com.longld.commerceflow.entity.auth.OtpVerification;
import com.longld.commerceflow.exception.AppException;
import com.longld.commerceflow.exception.ErrorCode;
import com.longld.commerceflow.repository.auth.OtpVerificationRepository;
import com.longld.commerceflow.repository.user.UserRepository;
import com.longld.commerceflow.service.auth.EmailService;
import com.longld.commerceflow.service.auth.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpVerificationRepository otpRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SecureRandom secureRandom = new SecureRandom();

    private static final int OTP_EXPIRATION_MINUTES = 5;
    private static final int COOLDOWN_SECONDS = 60;
    private static final int MAX_ATTEMPTS = 5;

    @Override
    @Transactional
    public void sendRegistrationOtp(SendOtpRequestDTO request) {
        String email = request.getEmail().trim().toLowerCase();

        // 1. Kiểm tra email đã có người dùng đăng ký chưa
        if (userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 2. Kiểm tra cooldown (tránh spam gửi OTP liên tục)
        otpRepository.findTopByEmailOrderByCreatedAtDesc(email).ifPresent(lastOtp -> {
            if (lastOtp.getCreatedAt() != null &&
                    lastOtp.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(COOLDOWN_SECONDS))) {
                throw new AppException(ErrorCode.OTP_COOLDOWN);
            }
        });

        // 3. Sinh OTP 6 chữ số ngẫu nhiên
        String otpCode = String.format("%06d", secureRandom.nextInt(1_000_000));

        // 4. Lưu bản ghi OTP mới
        OtpVerification otp = OtpVerification.builder()
                .email(email)
                .otpCode(otpCode)
                .expiryDate(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES))
                .verified(false)
                .attempts(0)
                .build();

        otpRepository.save(otp);
        System.out.println("==================================================================");
        System.out.println(">>> [OTP GENERATED] Email: " + email + " | Code: " + otpCode);
        System.out.println("==================================================================");
        log.info("Generated OTP for registration: email={}, code={}", email, otpCode);

        // 5. Gửi email
        emailService.sendOtpEmail(email, otpCode);

    }

    @Override
    @Transactional
    public VerifyOtpResponseDTO verifyRegistrationOtp(VerifyOtpRequestDTO request) {
        String email = request.getEmail().trim().toLowerCase();
        String inputOtp = request.getOtp().trim();

        // 1. Tìm bản ghi OTP mới nhất của email
        OtpVerification otp = otpRepository.findTopByEmailOrderByCreatedAtDesc(email)
                .orElseThrow(() -> new AppException(ErrorCode.OTP_INVALID));

        // 2. Kiểm tra hết hạn
        if (otp.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }

        // 3. Kiểm tra số lần thử
        if (otp.getAttempts() >= MAX_ATTEMPTS) {
            throw new AppException(ErrorCode.OTP_MAX_ATTEMPTS);
        }

        // 4. Kiểm tra mã OTP
        if (!otp.getOtpCode().equals(inputOtp)) {
            otp.setAttempts(otp.getAttempts() + 1);
            otpRepository.save(otp);
            throw new AppException(ErrorCode.OTP_INVALID,
                    String.format("Invalid OTP. %d attempts remaining.", MAX_ATTEMPTS - otp.getAttempts()));
        }

        // 5. Khớp OTP: Sinh token xác thực ngắn hạn (otpToken)
        String otpToken = UUID.randomUUID().toString();
        otp.setOtpToken(otpToken);
        otp.setVerified(true);
        otpRepository.save(otp);

        log.info("OTP verified successfully for email={}", email);

        return VerifyOtpResponseDTO.builder()
                .otpToken(otpToken)
                .message("OTP verified successfully. You may now complete registration.")
                .build();
    }
}


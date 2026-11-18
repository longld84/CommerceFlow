package com.longld.commerceflow.service.auth;

public interface EmailService {

    void sendOtpEmail(String toEmail, String otpCode);
}

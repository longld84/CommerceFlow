package com.longld.commerceflow.service.auth;

import com.longld.commerceflow.dto.auth.request.SendOtpRequestDTO;
import com.longld.commerceflow.dto.auth.request.VerifyOtpRequestDTO;
import com.longld.commerceflow.dto.auth.response.VerifyOtpResponseDTO;

public interface OtpService {

    void sendRegistrationOtp(SendOtpRequestDTO request);

    VerifyOtpResponseDTO verifyRegistrationOtp(VerifyOtpRequestDTO request);
}

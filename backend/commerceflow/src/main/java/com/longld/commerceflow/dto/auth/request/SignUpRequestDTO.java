package com.longld.commerceflow.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    @Email(message = "Email invalid format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;
    @NotBlank(message = "Full name is required")
    @Size(max = 100)
    private String fullName;

    @Size(max = 15)
    private String phone;

    @NotBlank(message = "OTP verification token is required")
    private String otpToken;
}

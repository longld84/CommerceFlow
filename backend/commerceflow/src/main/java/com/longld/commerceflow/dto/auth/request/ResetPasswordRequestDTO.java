package com.longld.commerceflow.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordRequestDTO {
    @NotBlank
    private String token;
    @NotBlank
    @Size(min=8, max=30)
    private String newPassword;
}

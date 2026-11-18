package com.longld.commerceflow.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDTO {
    @NotBlank
    @Size(min=8, max=30)
    private String currentPassword;
    @NotBlank
    @Size(min=8, max=30)
    private String newPassword;
    @NotBlank
    @Size(min=8, max=30)
    private String confirmPassword;
}

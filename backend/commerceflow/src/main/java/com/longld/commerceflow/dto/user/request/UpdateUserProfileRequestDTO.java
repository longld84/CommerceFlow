package com.longld.commerceflow.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequestDTO {
    @NotBlank(message = "Full name cannot be blank")
    @Size(max = 150)
    private String fullName;
    @Size(max = 20)
    private String phone;
    private String avatarUrl;
}

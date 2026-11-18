package com.longld.commerceflow.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponseDTO {
    private final Long id;
    private final String email;
    private final String fullName;
    private final String phone;
    private final String avatarUrl;
    private final String role;
    private final boolean isActive;
    private final List<UserAddressResponseDTO> addresses;
}

package com.longld.commerceflow.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAddressResponseDTO {
    private final Long id;
    private final String recipientName;
    private final String phone;
    private final String province;
    private final String district;
    private final String ward;
    private final String streetAddress;
    private final Boolean isDefault;
}

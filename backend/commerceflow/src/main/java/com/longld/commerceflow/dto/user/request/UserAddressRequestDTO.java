package com.longld.commerceflow.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressRequestDTO {
    @NotBlank (message = "Recipient name is required")
    private String recipientName;
    @NotBlank (message = "Phone number is required")
    @Pattern(regexp = "(0|\\\\+84)[3|5|7|8|9][0-9]{8}$", message = "Invalid Vietnamese phone number")
    private String phone;
    @NotBlank (message = "Province is required")
    private String province;
    @NotBlank(message = "District is required")
    private String district;
    @NotBlank(message = "Ward is required")
    private String ward;
    @NotBlank(message = "Street address is required")
    private String streetAddress;

    @Builder.Default
    private Boolean isDefault = false;
}

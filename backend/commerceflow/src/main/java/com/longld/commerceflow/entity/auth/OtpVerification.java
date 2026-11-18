package com.longld.commerceflow.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Lưu mã OTP gửi về email khi đăng ký tài khoản.
 * Mỗi email chỉ có 1 OTP hợp lệ tại một thời điểm (upsert theo email).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "otp_verifications", indexes = {
        @Index(name = "idx_otp_email", columnList = "email")
})
public class OtpVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Email đang chờ xác thực — không liên kết với bảng users vì user chưa được tạo
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "otp_code", nullable = false, length = 10)
    private String otpCode;

    // Sau khi verify đúng, lưu token ngắn hạn này để dùng ở bước signup
    @Column(name = "otp_token", length = 64)
    private String otpToken;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Builder.Default
    @Column(name = "verified", nullable = false)
    private Boolean verified = false;

    @Builder.Default
    @Column(name = "attempts", nullable = false)
    private Integer attempts = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}


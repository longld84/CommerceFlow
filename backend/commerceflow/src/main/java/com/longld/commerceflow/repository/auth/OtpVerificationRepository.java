package com.longld.commerceflow.repository.auth;

import com.longld.commerceflow.entity.auth.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByEmailOrderByCreatedAtDesc(String email);

    Optional<OtpVerification> findByOtpTokenAndVerifiedTrue(String otpToken);

    @Modifying
    @Query("DELETE FROM OtpVerification o WHERE o.email = :email")
    void deleteAllByEmail(String email);
}

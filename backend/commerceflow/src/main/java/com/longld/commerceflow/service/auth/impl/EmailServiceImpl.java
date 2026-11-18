package com.longld.commerceflow.service.auth.impl;

import com.longld.commerceflow.service.auth.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    @Override
    @Async
    public void sendOtpEmail(String toEmail, String otpCode) {
        // In ra màn hình console để dev/test luôn luôn nhìn thấy
        System.out.println("==================================================================");
        System.out.println("📧 [COMMERCEFLOW OTP EMAIL DISPATCH]");
        System.out.println("From      : " + fromEmail);
        System.out.println("Recipient : " + toEmail);
        System.out.println("OTP Code  : " + otpCode);
        System.out.println("Expires in: 5 minutes");
        System.out.println("==================================================================");

        log.info("Sending OTP email to: {}", toEmail);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Bắt buộc phải có setFrom để Gmail SMTP không từ chối kết nối
            if (fromEmail != null && !fromEmail.isBlank()) {
                helper.setFrom(fromEmail, "CommerceFlow");
            }

            helper.setFrom(fromEmail, "CommerceFlow");
            helper.setTo(toEmail);
            helper.setSubject("[CommerceFlow] Mã xác nhận đăng ký tài khoản");

            String htmlContent = """
                    <div style="font-family: Arial, sans-serif; max-width: 500px; margin: 0 auto; padding: 20px; border: 1px solid #e4e4e7; border-radius: 12px;">
                        <h2 style="color: #18181b; text-align: center;">Xác thực tài khoản CommerceFlow</h2>
                        <p style="color: #52525b; font-size: 14px;">Xin chào,</p>
                        <p style="color: #52525b; font-size: 14px;">Bạn đang thực hiện đăng ký tài khoản tại CommerceFlow. Đây là mã OTP xác thực của bạn (hiệu lực trong 5 phút):</p>
                        <div style="text-align: center; margin: 24px 0;">
                            <span style="font-size: 32px; font-weight: bold; letter-spacing: 8px; color: #18181b; background-color: #f4f4f5; padding: 12px 24px; border-radius: 8px; display: inline-block;">
                                %s
                            </span>
                        </div>
                        <p style="color: #71717a; font-size: 12px; text-align: center;">Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email.</p>
                    </div>
                    """.formatted(otpCode);

            helper.setText(htmlContent, true);
            mailSender.send(message);

            System.out.println("✅ [SUCCESS] Email sent successfully to: " + toEmail);
            log.info("Successfully sent OTP email to: {}", toEmail);
        } catch (Exception ex) {
            System.err.println("❌ [FAILED] Cannot send email via Gmail SMTP: " + ex.getMessage());
            ex.printStackTrace();
            log.error("Failed to dispatch email to {} via SMTP: {}", toEmail, ex.getMessage(), ex);
        }
    }
}

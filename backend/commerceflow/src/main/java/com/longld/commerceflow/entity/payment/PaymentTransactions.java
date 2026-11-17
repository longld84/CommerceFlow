package com.longld.commerceflow.entity.payment;

import com.longld.commerceflow.entity.BaseEntity;
import com.longld.commerceflow.entity.order.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment_transactions", indexes = {
        @Index(name = "idx_payos_order_code", columnList = "payos_order_code")
})
public class PaymentTransactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @Column(name = "payos_order_code", unique = true, nullable = false)
    private Long payosOrderCode;

    @Column(name = "payment_link_id", length = 255)
    private String paymentLinkId;

    @Column(name = "checkout_url", columnDefinition = "TEXT")
    private String checkoutUrl;

    @Column(name = "amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    @Builder.Default
    private PaymentTransactionStatus status = PaymentTransactionStatus.PENDING;

    @Column(name = "transaction_reference", length = 255)
    private String transactionReference;

    @Column(name = "webhook_payload", columnDefinition = "TEXT")
    private String webhookPayload;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}

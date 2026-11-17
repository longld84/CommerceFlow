package com.longld.commerceflow.entity.cart;

import com.longld.commerceflow.entity.product.ProductVariants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cart_product_variant", columnNames = {"cart_id", "product_variant_id"})
})
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Carts cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariants productVariant;

    @Builder.Default
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;
}

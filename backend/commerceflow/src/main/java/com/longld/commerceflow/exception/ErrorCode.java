package com.longld.commerceflow.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // 9xxx: System & Common Errors
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized system error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR(1002, "Validation failed", HttpStatus.BAD_REQUEST),

    // 1xxx: Auth & Security Errors
    UNAUTHENTICATED(1003, "Unauthenticated, please log in", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1004, "You do not have permission to access this resource", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1005, "Incorrect email or password", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(1006, "Token has expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(1007, "Invalid token", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_NOT_FOUND(1008, "Refresh token not found or revoked", HttpStatus.NOT_FOUND),
    OTP_INVALID(1014, "Invalid OTP code", HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(1015, "OTP code has expired", HttpStatus.BAD_REQUEST),
    OTP_MAX_ATTEMPTS(1016, "Maximum OTP verification attempts exceeded", HttpStatus.BAD_REQUEST),
    OTP_TOKEN_INVALID(1017, "OTP verification token is invalid or expired", HttpStatus.BAD_REQUEST),
    OTP_COOLDOWN(1018, "Please wait at least 60 seconds before requesting a new OTP", HttpStatus.TOO_MANY_REQUESTS),

    // 2xxx: User Errors
    USER_NOT_FOUND(2001, "User not found", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS(2002, "Email already registered", HttpStatus.CONFLICT),
    PHONE_ALREADY_EXISTS(2003, "Phone number already registered", HttpStatus.CONFLICT),
    USER_ADDRESS_NOT_FOUND(2004, "Address not found", HttpStatus.NOT_FOUND),

    // 3xxx: Product & Category Errors
    CATEGORY_NOT_FOUND(3001, "Category not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(3002, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_VARIANT_NOT_FOUND(3003, "Product variant not found", HttpStatus.NOT_FOUND),
    OUT_OF_STOCK(3004, "Product is out of stock or insufficient quantity", HttpStatus.BAD_REQUEST),

    // 4xxx: Cart & Order Errors
    CART_NOT_FOUND(4001, "Cart not found", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_FOUND(4002, "Cart item not found", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(4003, "Order not found", HttpStatus.NOT_FOUND),
    ORDER_CANNOT_BE_CANCELLED(4004, "Order cannot be cancelled in its current state", HttpStatus.BAD_REQUEST),
    COUPON_NOT_FOUND(4005, "Coupon not found", HttpStatus.NOT_FOUND),
    COUPON_EXPIRED(4006, "Coupon has expired", HttpStatus.BAD_REQUEST),
    COUPON_LIMIT_REACHED(4007, "Coupon usage limit reached", HttpStatus.BAD_REQUEST),
    COUPON_MIN_ORDER_NOT_MET(4008, "Minimum order amount for coupon not met", HttpStatus.BAD_REQUEST),

    // Resource generic
    RESOURCE_NOT_FOUND(1020, "Requested resource not found", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}

package com.paymentService.PaymentService.exceptions;


import org.springframework.web.bind.annotation.ExceptionHandler;

public class PaymentFailureException extends Exception {
    public PaymentFailureException(String message) {
        super(message);
    }
}

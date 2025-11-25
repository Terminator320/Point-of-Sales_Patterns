package com.example.posapp.PaymentFactory;

import com.example.posapp.models.Payment;

public class PaymentFactoryClass {
    public static PaymentProcessing  createPayment(Payment.PaymentMethod method) {
        return switch (method) {
            case CASH -> new CashPayment();
            case CREDIT -> new CreditPayment();
            case DEBIT -> new DebitPayment();
            //default -> throw new IllegalArgumentException("Unknown payment type: " + method);
        };
    }
}

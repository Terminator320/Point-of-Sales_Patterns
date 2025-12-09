package com.example.posapp.PaymentFactory;

import com.example.posapp.models.Payment;

public class PaymentFactoryClass {
    //Factory Method
    public static PaymentProcessing showPaymentMSG(Payment.PaymentMethod method) {
        return switch (method) {
            case CASH -> new CashPayment();
            case CREDIT -> new CreditPayment();
            case DEBIT -> new DebitPayment();
        };
    }
}

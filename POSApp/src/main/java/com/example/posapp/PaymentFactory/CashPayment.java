package com.example.posapp.PaymentFactory;

public class CashPayment implements PaymentProcessing{
    @Override
    public String processPayment() {
        return "Processing Cash Payment...";
    }
}

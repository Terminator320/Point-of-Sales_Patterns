package com.example.posapp.PaymentFactory;

public class DebitPayment implements PaymentProcessing{
    @Override
    public String processPayment() {
        return "Processing Debit Payment...";
    }
}

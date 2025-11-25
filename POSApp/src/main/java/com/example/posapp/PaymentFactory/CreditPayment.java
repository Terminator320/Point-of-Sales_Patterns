package com.example.posapp.PaymentFactory;

public class CreditPayment implements PaymentProcessing {
    @Override
    public String processPayment() {
        return "Processing Credit Payment...";
    }


}

package com.example.posapp.PaymentFactory;

public class CashPayment implements PaymentProcessing{
    @Override
    public String printProcessPaymentMSG() {
        return "Processing Cash Payment...";
    }
}

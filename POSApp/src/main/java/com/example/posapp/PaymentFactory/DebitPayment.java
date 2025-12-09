package com.example.posapp.PaymentFactory;

public class DebitPayment implements PaymentProcessing{
    @Override
    public String printProcessPaymentMSG() {
        return "Processing Debit Payment...";
    }
}

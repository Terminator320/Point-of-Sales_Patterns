package com.example.posapp.PaymentFactory;

public class CreditPayment implements PaymentProcessing {
    @Override
    public String printProcessPaymentMSG() {
        return "Processing Credit Payment...";
    }


}

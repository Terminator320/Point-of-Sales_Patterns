package com.example.posapp.models;

import java.util.Date;
import java.util.Objects;

public class Refund {
    private int refundID;
    private int orderID;
    private String methodPayment;//cash, credit, debit
    private double tips;
    private Date refundDate;

//    CREATE TABLE refund (
//            refund_id     INTEGER PRIMARY KEY,
//            order_id      INTEGER NOT NULL REFERENCES sale_order(order_id) ON DELETE CASCADE,
//    method        TEXT NOT NULL CHECK (method IN ('ORIGINAL','CASH','CARD','DEBIT','MOBILE','STORE_CREDIT')),
//    amount_cents  INTEGER NOT NULL CHECK (amount_cents > 0),
//    reason        TEXT,
//    created_at    TEXT NOT NULL DEFAULT (datetime('now')),
//);

    public Refund(Date refundDate, double tips, String methodPayment, int orderID, int refundID) {
        this.refundDate = refundDate;
        this.tips = tips;
        this.methodPayment = methodPayment;
        this.orderID = orderID;
        this.refundID = refundID;
    }

    public int getRefundID() {
        return refundID;
    }

    public void setRefundID(int refundID) {
        this.refundID = refundID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;

        Refund refund = (Refund) o;
        return refundID == refund.refundID
                && orderID == refund.orderID
                && Double.compare(tips, refund.tips) == 0
                && Objects.equals(methodPayment, refund.methodPayment)
                && Objects.equals(refundDate, refund.refundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refundID, orderID, methodPayment, tips, refundDate);
    }

    @Override
    public String toString() {
        return "Refund{" +
                "refundID=" + refundID +
                ", orderID=" + orderID +
                ", methodPayment='" + methodPayment + '\'' +
                ", tips=" + tips +
                ", refundDate=" + refundDate +
                '}';
    }
}

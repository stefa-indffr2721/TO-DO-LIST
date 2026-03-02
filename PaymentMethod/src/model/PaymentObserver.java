package model;

public interface PaymentObserver {
    void onPaymentProcessed(String paymentType, double amount);
}
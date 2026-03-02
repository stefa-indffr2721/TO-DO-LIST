package model;

public abstract class PaymentMethod {

    public abstract void processPayment(double amount);

    public boolean isAmountValid(double amount) {
        return amount > 0;
    }
}
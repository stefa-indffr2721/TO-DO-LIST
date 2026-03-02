package model;

public class PayPalPayment extends PaymentMethod {

    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public boolean isEmailValid() {
        return email != null && email.contains("@");
    }

    @Override
    public void processPayment(double amount) {
        if (!isAmountValid(amount)) {
            System.out.println("PayPal: неверная сумма");
            return;
        }
        if (!isEmailValid()) {
            System.out.println("PayPal: неверный email");
            return;
        }
        System.out.println("PayPal: оплата " + amount + " руб. успешно");
    }
}
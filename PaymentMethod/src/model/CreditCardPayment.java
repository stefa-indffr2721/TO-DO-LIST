package model;

public class CreditCardPayment extends PaymentMethod {

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isCardValid() {
        return cardNumber != null && cardNumber.length() == 16;
    }

    @Override
    public void processPayment(double amount) {
        if (!isAmountValid(amount)) {
            System.out.println("CreditCard: неверная сумма");
            return;
        }
        if (!isCardValid()) {
            System.out.println("CreditCard: неверный номер карты");
            return;
        }
        System.out.println("CreditCard: оплата " + amount + " руб. успешно");
    }
}
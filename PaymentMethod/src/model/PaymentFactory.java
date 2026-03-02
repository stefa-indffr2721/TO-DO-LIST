package model;

public class PaymentFactory {

    public static PaymentMethod create(String type, String details) {
        return switch (type.toLowerCase()) {
            case "creditcard" -> new CreditCardPayment(details);
            case "paypal" -> new PayPalPayment(details);
            case "crypto" -> new CryptoPayment(details);
            default -> {
                System.out.println("Factory: неизвестный тип оплаты - " + type);
                yield null;
            }
        };
    }
}
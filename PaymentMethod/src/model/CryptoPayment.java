package model;

public class CryptoPayment extends PaymentMethod {

    private final String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public boolean isWalletValid() {
        return walletAddress != null && walletAddress.length() >= 10;
    }

    @Override
    public void processPayment(double amount) {
        if (!isAmountValid(amount)) {
            System.out.println("Crypto: неверная сумма");
            return;
        }
        if (!isWalletValid()) {
            System.out.println("Crypto: неверный адрес кошелька");
            return;
        }
        System.out.println("Crypto: оплата " + amount + " руб. успешно");
    }
}
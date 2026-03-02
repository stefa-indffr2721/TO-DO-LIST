package view;

public class PaymentView {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTransactionHistory(java.util.List<String> log) {
        System.out.println("\nИстория транзакций:");
        if (log.isEmpty()) {
            System.out.println("Транзакций нет");
        } else {
            for (String entry : log) {
                System.out.println(entry);
            }
        }
    }
}
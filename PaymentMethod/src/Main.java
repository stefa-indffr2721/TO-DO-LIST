import controller.PaymentController;
import model.TransactionLogger;
import view.PaymentView;

public class Main {

    public static void main(String[] args) {
        PaymentView view = new PaymentView();
        PaymentController controller = new PaymentController(view);
        TransactionLogger logger = new TransactionLogger();
        controller.addObserver(logger);

        controller.processPayment("creditcard", "1234567812345678", 1500.0);
        controller.processPayment("paypal", "user@example.com", 800.0);
        controller.processPayment("crypto", "0xABCDEF123456", 300.0);
        controller.processPayment("creditcard", "567", 500.0);
        controller.processPayment("paypal", "notanemail", 200.0);
        controller.processPayment("bitcoin", "someaddress", 100.0);

        //view.showTransactionHistory(logger.getLog());
    }
}
package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionLogger implements PaymentObserver {

    private final List<String> log = new ArrayList<>();

    @Override
    public void onPaymentProcessed(String paymentType, double amount) {
        String message = "Лог: " + paymentType + " — " + amount + " руб.";
        log.add(message);
        System.out.println(message);
    }

    public List<String> getLog() {
        return log;
    }
}
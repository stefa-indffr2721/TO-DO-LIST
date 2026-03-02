package controller;

import model.PaymentFactory;
import model.PaymentMethod;
import model.PaymentObserver;
import view.PaymentView;

import java.util.ArrayList;
import java.util.List;

public class PaymentController {

    private final PaymentView view;
    private final List<PaymentObserver> observers = new ArrayList<>();

    public PaymentController(PaymentView view) {
        this.view = view;
    }

    public void addObserver(PaymentObserver observer) {
        observers.add(observer);
    }

    public void processPayment(String type, String details, double amount) {
        PaymentMethod payment = PaymentFactory.create(type, details);

        if (payment == null) {
            view.showMessage("Оплата отменена");
            return;
        }

        payment.processPayment(amount);

        for (PaymentObserver observer : observers) {
            observer.onPaymentProcessed(type, amount);
        }
    }
}
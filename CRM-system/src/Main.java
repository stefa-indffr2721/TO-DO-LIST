
import java.util.ArrayList;

interface Notifiable {
    void sendNotification(String message);
}

class Client implements Notifiable {

    private final String name;
    private final String email;
    private final String phone;

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Уведомление клиенту " + name + " (" + email + "): " + message);
    }

    public String getName()  { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return name;
    }
}

class Employee implements Notifiable {

    private final String name;
    private final String position;
    private final double commissionPercent;

    public Employee(String name, String position, double commissionPercent) {
        this.name = name;
        this.position = position;
        this.commissionPercent = commissionPercent;
    }

    public double calculateCommission(double dealAmount) {
        return dealAmount * commissionPercent / 100;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Уведомление сотруднику " + name + ": " + message);
    }

    public String getName()     { return name; }
    public String getPosition() { return position; }

    @Override
    public String toString() {
        return name + " (" + position + ")";
    }
}

class Deal {

    public static final String STATUS_NEW         = "Новая";
    public static final String STATUS_IN_PROGRESS = "В работе";
    public static final String STATUS_DONE        = "Завершена";
    public static final String STATUS_CANCELLED   = "Отменена";

    private final String title;
    private final double amount;
    private String status;

    private final Client client;
    private final Employee employee;

    public Deal(String title, double amount, Client client, Employee employee) {
        this.title    = title;
        this.amount   = amount;
        this.client   = client;
        this.employee = employee;
        this.status   = STATUS_NEW;
    }

    public void setStatus(String status) { this.status = status; }

    public String   getTitle()    { return title; }
    public double   getAmount()   { return amount; }
    public String   getStatus()   { return status; }
    public Client   getClient()   { return client; }
    public Employee getEmployee() { return employee; }

    @Override
    public String toString() {
        double commission = employee.calculateCommission(amount);
        return "(" + status + ") " + title + ";" +
                " Сумма: " + amount + " руб." + ";" +
                " Клиент: " + client.getName() + ";" +
                " Менеджер: " + employee.getName() + ";" +
                " Комиссия: " + commission + " руб.";
    }
}

class CrmModel {

    private final ArrayList<Deal> deals = new ArrayList<>();

    public void addDeal(Deal deal) {
        deals.add(deal);
    }

    public void updateDealStatus(Deal deal, String newStatus) {
        deal.setStatus(newStatus);
    }

    public void notifyAboutDeal(Deal deal) {
        String message = "Статус сделки \"" + deal.getTitle() + "\" изменён на: " + deal.getStatus();
        deal.getClient().sendNotification(message);
        deal.getEmployee().sendNotification(message);
    }

    public double getCommissionForDeal(Deal deal) {
        return deal.getEmployee().calculateCommission(deal.getAmount());
    }

    public ArrayList<Deal> getDealsSortedByStatus() {
        ArrayList<Deal> sorted = new ArrayList<>(deals);

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                if (sorted.get(j).getStatus().compareTo(sorted.get(j + 1).getStatus()) > 0) {
                    Deal temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }

        return sorted;
    }

    public ArrayList<Deal> getAllDeals() {
        return deals;
    }
}

class CrmView {

    public void showDeals(String title, ArrayList<Deal> deals) {
        System.out.println("\n " + title );
        for (int i = 0; i < deals.size(); i++) {
            System.out.println((i + 1) + ". " + deals.get(i));
        }
    }

    public void showCommission(Deal deal, double commission) {
        System.out.println("Комиссия по сделке \"" + deal.getTitle() + "\": " + commission + " руб.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}

class CrmController {

    private final CrmModel model;
    private final CrmView  view;

    public CrmController(CrmModel model, CrmView view) {
        this.model = model;
        this.view  = view;
    }

    public void addDeal(Deal deal) {
        model.addDeal(deal);
        view.showMessage("Сделка \"" + deal.getTitle() + "\" добавлена");
    }

    public void changeDealStatus(Deal deal, String newStatus) {
        model.updateDealStatus(deal, newStatus);
    }

    public void notifyDeal(Deal deal) {
        model.notifyAboutDeal(deal);
    }

    public void showAllDeals() {
        view.showDeals("Все сделки", model.getAllDeals());
    }

    public void showDealsSortedByStatus() {
        view.showDeals("Сделки по статусу", model.getDealsSortedByStatus());
    }

    public void showCommission(Deal deal) {
        double commission = model.getCommissionForDeal(deal);
        view.showCommission(deal, commission);
    }
}

public class Main {

    public static void main(String[] args) {

        CrmModel model = new CrmModel();
        CrmView view = new CrmView();
        CrmController controller = new CrmController(model, view);

        Client client1 = new Client("Иван Иванов",   "ivanov@mail.ru",   "+7-912-121-72-73");
        Client client2 = new Client("Анна Ромашевская", "romashusova@mail.ru", "+7-902-564-15-96");
        Client client3 = new Client("ООО Сабай Тай",   "sabaytay@mail.ru",  "+7-495-010-19-32");

        Employee emp1 = new Employee("Алексей Чудинов", "Менеджер",         5.0);
        Employee emp2 = new Employee("Мария Курмачева",  "Старший менеджер", 7.5);

        Deal deal1 = new Deal("Поставка материалов", 150000, client1, emp1);
        Deal deal2 = new Deal("Разработка сайта",       80000, client2, emp2);
        Deal deal3 = new Deal("Годовое обслуживание(все целиком)",  200000, client3, emp1);
        Deal deal4 = new Deal("Консультация",           15000, client1, emp2);

        controller.addDeal(deal1);
        controller.addDeal(deal2);
        controller.addDeal(deal3);
        controller.addDeal(deal4);

        controller.showAllDeals();

        controller.changeDealStatus(deal1, Deal.STATUS_IN_PROGRESS);
        controller.changeDealStatus(deal2, Deal.STATUS_DONE);
        controller.changeDealStatus(deal3, Deal.STATUS_DONE);
        controller.changeDealStatus(deal4, Deal.STATUS_CANCELLED);

        controller.showDealsSortedByStatus();

        System.out.println("\nКомиссии");
        controller.showCommission(deal1);
        controller.showCommission(deal2);
        controller.showCommission(deal3);
        controller.showCommission(deal4);

        System.out.println("\nУведомления");
        controller.notifyDeal(deal1);
        controller.notifyDeal(deal2);
        controller.notifyDeal(deal3);
        controller.notifyDeal(deal4);
    }
}
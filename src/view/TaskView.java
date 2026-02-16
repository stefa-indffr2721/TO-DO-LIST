package view;

import model.Task;
import java.util.ArrayList;

public class TaskView {

    public void showMenu() {
        System.out.println("=== TO-DO LIST ===");
        System.out.println("1. Показать все задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Отметить как выполненную");
        System.out.println("5. Показать активные задачи");
        System.out.println("6. Показать выполненные задачи");
        System.out.println("7. Редактировать задачу");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    public void showTasks(ArrayList<Task> tasks) {

        if (tasks.size() == 0) {
            System.out.println("Список задач пуст.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

            String status;
            if (task.completed) {
                status = " [Выполнено]";
            } else {
                status = " [Активно]";
            }

            System.out.println(i + ". " + task.description + status);
        }
    }
}

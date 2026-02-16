package controller;

import model.TaskManager;
import model.Task;
import view.TaskView;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskController {

    private TaskManager manager;
    private TaskView view;
    private Scanner scanner;

    public TaskController(TaskManager manager, TaskView view) {
        this.manager = manager;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        int choice = -1;

        while (choice != 0) {

            view.showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                view.showTasks(manager.tasks);
            }

            else if (choice == 2) {
                System.out.print("Введите описание задачи: ");
                String text = scanner.nextLine();
                manager.addTask(text);
                System.out.println("Задача добавлена.");
            }

            else if (choice == 3) {
                view.showTasks(manager.tasks);
                System.out.print("Введите номер задачи: ");
                int index = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Вы уверены? (y/n): ");
                String answer = scanner.nextLine();

                if (answer.equals("y")) {
                    manager.removeTask(index);
                    System.out.println("Задача удалена.");
                } else {
                    System.out.println("Удаление отменено.");
                }
            }

            else if (choice == 4) {
                view.showTasks(manager.tasks);
                System.out.print("Введите номер задачи: ");
                int index = scanner.nextInt();
                manager.completeTask(index);
                System.out.println("Задача отмечена как выполненная.");
            }

            else if (choice == 5) {
                ArrayList<Task> active = new ArrayList<>();

                for (Task t : manager.tasks) {
                    if (!t.completed) {
                        active.add(t);
                    }
                }

                view.showTasks(active);
            }

            else if (choice == 6) {
                ArrayList<Task> done = new ArrayList<>();

                for (Task t : manager.tasks) {
                    if (t.completed) {
                        done.add(t);
                    }
                }

                view.showTasks(done);
            }

            else if (choice == 7) {
                view.showTasks(manager.tasks);

                System.out.print("Введите номер задачи: ");
                int index = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Введите новый текст: ");
                String newText = scanner.nextLine();

                manager.editTask(index, newText);
                System.out.println("Задача изменена.");
            }

            else if (choice == 0) {
                System.out.println("Выход...");
            }

            else {
                System.out.println("Неверный выбор.");
            }

            System.out.println();
        }
    }
}

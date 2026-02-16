package app;

import model.TaskManager;
import view.TaskView;
import controller.TaskController;

public class Main {

    static void main(String[] args) {

        TaskManager manager = new TaskManager();
        TaskView view = new TaskView();
        TaskController controller = new TaskController(manager, view);

        controller.start();
    }
}

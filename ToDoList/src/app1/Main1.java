package app1;

import model.TaskManager;
import view.TaskView;
import controller.TaskController;

public class Main1 {

    static void main(String[] args) {

        TaskManager manager = new TaskManager();
        TaskView view = new TaskView();
        TaskController controller = new TaskController(manager, view);

        controller.start();
    }
}

package model;

import java.util.ArrayList;

public class TaskManager {

    public ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).completed = true;
        }
    }

    public void editTask(int index, String newText) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).description = newText;
        }
    }
}

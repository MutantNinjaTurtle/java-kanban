package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description);
        this.subtasks = new ArrayList<>();
    }

    public Epic(String name, String description, int id) {
        super(name, description, Status.NEW, id);
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public void removeById(Subtask subtask) {
        subtasks.remove(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateStatus() {  // Управление изменением статуса эпика
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }
        int countDone = 0;
        int countNew = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == Status.DONE) {
                countDone++;
            } else if (subtask.getStatus() == Status.NEW) {
                countNew++;
            }
        }
        if (countDone == subtasks.size()) {
            setStatus(Status.DONE);
        } else if (countNew == subtasks.size()) {
            setStatus(Status.NEW);
        } else {
            setStatus(Status.IN_PROGRESS);
        }
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }
}

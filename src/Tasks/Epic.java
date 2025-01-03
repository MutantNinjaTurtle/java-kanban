package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description);
        this.subtasks = new ArrayList<>();
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

    public void updateStatus() {  // Управление изменением статуса
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }
        boolean AllDone = false;
        boolean Progress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == Status.DONE) {
                AllDone = true;
            } else if (subtask.getStatus() == Status.IN_PROGRESS) {
                Progress = true;
                AllDone = false;
            }
        }

        if (AllDone) {
            setStatus(Status.DONE);
        } else if (Progress) {
            setStatus(Status.IN_PROGRESS);
        } else {
            setStatus(Status.NEW);
        }

    }

    public void addSubtask (Subtask subtask) { // обновление под задачи
        subtasks.add(subtask);
        updateStatus();
    }

}

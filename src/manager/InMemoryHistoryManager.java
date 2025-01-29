package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_SIZE = 10;
    private final List<Task> historyStorage = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        if (Objects.isNull(task)) {
            return;
        }
        historyStorage.add(new Task(task.getName(), task.getDescription(), task.getStatus(), task.getId()));
        if (historyStorage.size() > HISTORY_SIZE) {
            historyStorage.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(historyStorage);
    }
}


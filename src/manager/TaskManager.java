package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void addTask(tasks.Task task);

    void addSubtask(Subtask subtask);

    void addEpic(Epic epic);

    ArrayList<tasks.Task> getAllTasks();

    ArrayList<Subtask> getAllSubTask();

    ArrayList<Epic> getAllEpic();

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    tasks.Task getByIdTask(int id);

    Subtask getByIdSubTask(int id);

    Epic getByIdEpic(int id);

    void updateTask(tasks.Task task);

    void updateSubTask(Subtask subtask);

    void updateEpic(Epic epic);

    void deleteByIdTasks(int id);

    void deleteByIdSubtasks(int id);

    void deleteByIdEpic(int id);

    ArrayList<Subtask> getSubtaskByEpic(int id);

    List<Task> getHistory();
}

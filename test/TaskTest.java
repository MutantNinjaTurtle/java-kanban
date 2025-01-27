import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.Status.DONE;
import static tasks.Status.NEW;

public class TaskTest {

    private TaskManager taskManager;
    private Task task;
    private Task task2;
    private Task taskNew;

    @BeforeEach
    public void createTask() {
        taskManager = Managers.getDefaultTaskManager();
        task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        task2 = new Task("Test addNewTask", "Test addNewTask description", NEW);
        taskNew = new Task("Test addNewTask", "Test addNewTask description", DONE, 1);
        taskManager.addTask(task);
    }

    @Test
    void addNewTask() {
        final Task savedTask = taskManager.getByIdTask(task.getId());
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");
        final ArrayList<Task> tasks = taskManager.getAllTasks();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void deleteAllTasks() {
        taskManager.addTask(task2);
        taskManager.deleteAllTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    void updateTask() {
        taskManager.updateTask(taskNew);
        final ArrayList<Task> tasks = taskManager.getAllTasks();
        assertEquals(taskNew, tasks.getFirst());
    }

    @Test
    void removeByIDTask() {
        taskManager.addTask(task2);
        taskManager.deleteByIdTasks(2);
        assertFalse(taskManager.getAllTasks().contains(task2));
        taskManager.deleteByIdTasks(1);
        assertTrue(taskManager.getAllTasks().isEmpty());
    }
}

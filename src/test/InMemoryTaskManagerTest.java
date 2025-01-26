package test;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.Status.NEW;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;
    private Task task2;

    @BeforeEach
    void createManager() {
        taskManager = Managers.getDefaultTaskManager();
    }

    @Test
        //Тест создания задачи
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        taskManager.addTask(task);

        final Task savedTask = taskManager.getByIdTask(task.getId());

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void removeTaskTest() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        taskManager.addTask(task);
        taskManager.addTask(task);
        taskManager.deleteAllTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    void addNewEpicAndSubTask() {

        Epic epic = new Epic("Test epic1", "Test description epic1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Test addNewTask", "Test addNewTask description", NEW, epic.getId());
        taskManager.addSubtask(subtask);

        final Task savedSubtask = taskManager.getByIdSubTask(subtask.getId());

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        final ArrayList<Subtask> tasks = taskManager.getAllSubTask();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(subtask, tasks.getFirst(), "Задачи не совпадают.");

        final Task savedEpic = taskManager.getByIdEpic(epic.getId());

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final ArrayList<Epic> tasksEpic = taskManager.getAllEpic();

        assertNotNull(tasksEpic, "Задачи не возвращаются.");
        assertEquals(1, tasksEpic.size(), "Неверное количество задач.");
        assertEquals(epic, tasksEpic.getFirst(), "Задачи не совпадают.");

    }

    void createTwoTask() {
        Task task1 = new Task("task1", "description task1", NEW);
        Task task2 = new Task("task2", "description task2", NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
    }

    @Test
    void removeByIDTask() {
        createTwoTask();
        taskManager.deleteByIdTasks(2);
        assertFalse(taskManager.getAllTasks().contains(task2));
    }

    @Test
    void removeAllTask() {
        createTwoTask();
        taskManager.deleteAllTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());

    }
}







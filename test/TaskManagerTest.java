import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tasks.Status.DONE;
import static tasks.Status.NEW;

public class TaskManagerTest {
    private TaskManager taskManager;
    private Subtask subtask;
    private Subtask subtask2;
    private Epic epic;
    private Epic epic2;
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
        epic = new Epic("Test epic1", "Test description epic1");
        epic2 = new Epic("Test epic2", "Test description epic2");
        taskManager.addEpic(epic);
        taskManager.addEpic(epic2);
        subtask = new Subtask("Test addNewTask", "Test addNewTask description", NEW, epic.getId());
        subtask2 = new Subtask("Test addNewTask", "Test addNewTask description", DONE, epic.getId());
        taskManager.addSubtask(subtask);
    }

    @Test
    void addEpic() {
        final ArrayList<Epic> tasksEpic = taskManager.getAllEpic();
        assertEquals(2, tasksEpic.size(), "Неверное количество эпиков.");
    }

    @Test
    void deleteAllEpics() {
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getAllEpic().isEmpty());
    }

    @Test
    void deleteByIdEpic() {
        taskManager.deleteByIdEpic(epic.getId());
        assertFalse(taskManager.getAllEpic().contains(epic), "Эпик не удален.");
        taskManager.deleteByIdEpic(epic2.getId());
        assertTrue(taskManager.getAllEpic().isEmpty());
        assertTrue(taskManager.getAllSubTask().isEmpty());
    }

    @Test
    void getByIdEpic() {
        Epic epicNew = taskManager.getByIdEpic(epic2.getId());
        assertEquals(epicNew, epic2, "Значения не равны.");
    }

    @Test
    void updateEpic() {
        Epic epicNew = new Epic("Test epicNew", "Test description epic1", epic.getId());
        taskManager.updateEpic(epicNew);
        Epic epic3 = taskManager.getByIdEpic(epicNew.getId());
        assertEquals(epic3, epicNew, "Значения не равны.");
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
        System.out.println(task);
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
        taskManager.deleteByIdTasks(task2.getId());
        assertFalse(taskManager.getAllTasks().contains(task2));
        taskManager.deleteByIdTasks(task.getId());
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    void addNewEpicAndSubTask() {
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
        assertEquals(2, tasksEpic.size(), "Неверное количество задач.");
        assertEquals(epic, tasksEpic.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void deleteAllSubTasks() {
        taskManager.addSubtask(subtask2);
        taskManager.deleteAllSubTasks();
        assertTrue(taskManager.getAllSubTask().isEmpty());
    }

    @Test
    void updateSubTask() {
        Epic epicBeforeUpdateSubtask = taskManager.getByIdEpic(epic.getId());
        assertEquals(epicBeforeUpdateSubtask, epic, "Эпики не совпадают.");
        taskManager.updateSubTask(subtask2);
        final ArrayList<Subtask> subtasksNew = taskManager.getAllSubTask();
        assertEquals(subtask2, subtasksNew.getFirst());
        Epic epicAfterUpdateSubtask = taskManager.getByIdEpic(epic.getId());
        assertEquals(epicAfterUpdateSubtask, epic, "Эпики не совпадают.");
        assertEquals(epic.getStatus(), DONE, "Статус эпика не изменился.");
    }

    @Test
    void deleteByIdSubtasks() {
        taskManager.addSubtask(subtask2);
        taskManager.deleteByIdSubtasks(subtask.getId());
        assertFalse(taskManager.getAllSubTask().contains(subtask), "Задача не удалена.");
        taskManager.deleteByIdSubtasks(subtask2.getId());
        assertTrue(taskManager.getAllSubTask().isEmpty());
    }

    @Test
    void getSubtaskByEpicId() {
        taskManager.addSubtask(subtask2);
        ArrayList<Subtask> subtaskArrayList = taskManager.getSubtaskByEpic(epic.getId());
        assertEquals(2, subtaskArrayList.size(), "Значения не совпадают.");
    }

    @Test
    void subtaskGetByIdSubTask() {
        Subtask subtaskNew = taskManager.getByIdSubTask(subtask.getId());
        assertEquals(subtaskNew, subtask, "Задачи не совпадают.");
    }
}

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.Status.DONE;
import static tasks.Status.NEW;

public class SubtaskTest {
    private TaskManager taskManager;
    private Subtask subtask;
    private Subtask subtask2;
    private Epic epic;

    @BeforeEach
    public void createSubtask() {
        taskManager = Managers.getDefaultTaskManager();
        epic = new Epic("Test epic1", "Test description epic1");
        taskManager.addEpic(epic);
        subtask = new Subtask("Test addNewTask", "Test addNewTask description", NEW, epic.getId());
        subtask2 = new Subtask("Test addNewTask", "Test addNewTask description", DONE, epic.getId());
        taskManager.addSubtask(subtask);
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
        assertEquals(1, tasksEpic.size(), "Неверное количество задач.");
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
        Epic epicBeforeUpdateSubtask = taskManager.getByIdEpic(1);
        assertEquals(epicBeforeUpdateSubtask, epic, "Эпики не совпадают.");
        taskManager.updateSubTask(subtask2);
        final ArrayList<Subtask> subtasksNew = taskManager.getAllSubTask();
        assertEquals(subtask2, subtasksNew.getFirst());
        Epic epicAfterUpdateSubtask = taskManager.getByIdEpic(1);
        assertEquals(epicAfterUpdateSubtask, epic, "Эпики не совпадают.");
        assertEquals(epic.getStatus(), DONE, "Статус эпика не изменился.");
    }

    @Test
    void deleteByIdSubtasks() {
        taskManager.addSubtask(subtask2);
        taskManager.deleteByIdSubtasks(2);
        assertFalse(taskManager.getAllSubTask().contains(subtask), "Задача не удалена. ");
        taskManager.deleteByIdSubtasks(3);
        assertTrue(taskManager.getAllSubTask().isEmpty());
    }

    @Test
    void getSubtaskByEpicId() {
        taskManager.addSubtask(subtask2);
        ArrayList<Subtask> subtaskArrayList = taskManager.getSubtaskByEpic(1);
        assertEquals(2, subtaskArrayList.size(), "Значения не совпадают.");
    }

    @Test
    void subtaskGetByIdSubTask() {
        Subtask subtaskNew = taskManager.getByIdSubTask(2);
        assertEquals(subtaskNew, subtask, "Задачи не совпадают.");
    }
}

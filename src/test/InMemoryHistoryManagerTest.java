package test;

import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    Task task = new Task("TestingHistory_1", "TestingHistoryDescription_1", Status.NEW);
    Task task2 = new Task("TestingHistory_2", "TestingHistoryDescription_2", Status.NEW);
    Task task3 = new Task("TestingHistory_3", "TestingHistoryDescription_3", Status.NEW);
    Task task4 = new Task("TestingHistory_4", "TestingHistoryDescription_4", Status.NEW);
    Task task5 = new Task("TestingHistory_5", "TestingHistoryDescription_4", Status.NEW);
    Task task6 = new Task("TestingHistory_6", "TestingHistoryDescription_4", Status.NEW);
    Task task7 = new Task("TestingHistory_7", "TestingHistoryDescription_4", Status.NEW);
    Task task8 = new Task("TestingHistory_8", "TestingHistoryDescription_4", Status.NEW);
    Task task9 = new Task("TestingHistory_9", "TestingHistoryDescription_4", Status.NEW);
    Task task10 = new Task("TestingHistory_10", "TestingHistoryDescription_4", Status.NEW);
    Task task11 = new Task("TestingHistory_11", "TestingHistoryDescription_4", Status.NEW);

    @Test
    void addHistoryTest() {
        historyManager.addTask(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void getHistoryTest() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        InMemoryHistoryManager expectedHistory = new InMemoryHistoryManager();
        historyManager.addTask(task);
        historyManager.addTask(task2);
        historyManager.addTask(task3);
        historyManager.addTask(task4);
        historyManager.addTask(task5);
        historyManager.addTask(task6);
        historyManager.addTask(task7);
        historyManager.addTask(task8);
        historyManager.addTask(task9);
        historyManager.addTask(task10);
        historyManager.addTask(task11);
        expectedHistory.addTask(task2);
        expectedHistory.addTask(task3);
        expectedHistory.addTask(task4);
        expectedHistory.addTask(task5);
        expectedHistory.addTask(task6);
        expectedHistory.addTask(task7);
        expectedHistory.addTask(task8);
        expectedHistory.addTask(task9);
        expectedHistory.addTask(task10);
        expectedHistory.addTask(task11);
        List<Task> historyManagerList = historyManager.getHistory();
        List<Task> expectedHistoryList = expectedHistory.getHistory();
        assertNotNull(historyManager, "История не пустая.");
        assertArrayEquals(expectedHistoryList.toArray(), historyManagerList.toArray());
    }
}

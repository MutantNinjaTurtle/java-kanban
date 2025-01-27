

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
        for (int i = 0; i < 11; i++) {
            historyManager.addTask(task);
        }
        InMemoryHistoryManager expectedHistory = new InMemoryHistoryManager();
        for (int i = 0; i < 10; i++) {
            expectedHistory.addTask(task);
        }
        List<Task> historyManagerList = historyManager.getHistory();
        List<Task> expectedHistoryList = expectedHistory.getHistory();
        assertNotNull(historyManager, "История не пустая.");
        assertEquals(10,historyManagerList.size());
        assertArrayEquals(historyManagerList.toArray(),expectedHistoryList.toArray());
    }
}

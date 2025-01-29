import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tasks.Status.NEW;

public class TaskTest {

    @Test
    void taskСompare() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW, 1);
        Task task2 = new Task("Test addNewTask", "Test addNewTask description", NEW, 1);
        assertEquals(task.getName(), task2.getName(), "Имена не совпадают.");
        assertEquals(task.getDescription(), task2.getDescription(), "Description не совпадает.");
        assertEquals(task.getStatus(), task2.getStatus(), "Status не совпадает.");
    }
}

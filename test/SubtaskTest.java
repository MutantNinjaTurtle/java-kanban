import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Subtask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tasks.Status.NEW;


public class SubtaskTest {

    @Test
    void subtaskСompare() {
        Subtask subtask = new Subtask("Test addNewTask", "Test addNewTask description", NEW, 1, 2);
        Subtask subtask2 = new Subtask("Test addNewTask", "Test addNewTask description", NEW, 1, 2);
        assertEquals(subtask.getName(), subtask2.getName(), "Имена не совпадают.");
        assertEquals(subtask.getDescription(), subtask2.getDescription(), "Description не совпадает.");
        assertEquals(subtask.getStatus(), subtask2.getStatus(), "Status не совпадает.");
        assertEquals(subtask.getEpicId(), subtask2.getEpicId(), "EpicId не совпадает.");
    }
}



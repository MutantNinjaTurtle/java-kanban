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

public class EpicTest {
    private TaskManager taskManager;
    private Subtask subtask;
    private Subtask subtask2;
    private Epic epic;
    private Epic epic2;

    @BeforeEach
    public void createEpic() {
        taskManager = Managers.getDefaultTaskManager();
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
        taskManager.deleteByIdEpic(1);
        assertFalse(taskManager.getAllEpic().contains(epic), "Эпик не удален");
        taskManager.deleteByIdEpic(2);
        assertTrue(taskManager.getAllEpic().isEmpty());
    }

    @Test
    void getByIdEpic(){
        Epic epicNew = taskManager.getByIdEpic(2);
        assertEquals(epicNew,epic2,"Значения не равны.");
    }

    @Test
    void updateEpic(){
        Epic epicNew = new Epic("Test epicNew", "Test description epic1",1);
        taskManager.updateEpic(epicNew);
        Epic epic3= taskManager.getByIdEpic(1);
        assertEquals(epic3,epicNew,"Значения не равны.");
    }

}

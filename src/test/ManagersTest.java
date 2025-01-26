package test;

import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagersTest {
//убедились, что утилитарный класс всегда возвращает проинициализированные
// и готовые к работе экземпляры менеджеров;

    @Test
    void getDefaultTaskManager() {
        TaskManager taskManager = Managers.getDefaultTaskManager();
        assertNotNull(taskManager);
    }

    @Test
    void getDefaultHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistoryManager();
        assertNotNull(historyManager);
    }
}

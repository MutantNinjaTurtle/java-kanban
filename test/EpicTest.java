import org.junit.jupiter.api.Test;
import tasks.Epic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    @Test
    void epicСompare() {
        Epic epic = new Epic("Epic 1", "Description 1", 1);
        Epic epic1 = new Epic("Epic 1", "Description 1", 1);
        assertEquals(epic.getName(), epic1.getName(), "Имена не совпадают.");
        assertEquals(epic.getDescription(), epic1.getDescription(), "Description не совпадает.");
        assertEquals(epic.getStatus(), epic1.getStatus(), "Status не совпадает.");
    }
}

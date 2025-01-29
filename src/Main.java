import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefaultTaskManager();
        Task task1 = new Task("task1", "Погладить кота", Status.NEW);
        Task task2 = new Task("task2", "Попить чай", Status.NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        Task task11 = taskManager.getByIdTask(1);
        Task task22 = taskManager.getByIdTask(2);
        Epic epic1 = new Epic("Ремонт квартиры", "Капитальный ремонт");
        Epic epic2 = new Epic("Ремонт комнаты", "Покраска пола");
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.getHistory();
        Subtask subtask1 = new Subtask("Ремонт комнаты 1", "Покраска  потолка", Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Ремонт комнаты 2", "Покраска стен", Status.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Ремонт комнаты 3", "покраска пола", Status.NEW, epic2.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
//        Subtask subtask4 = new Subtask("Ремонт комнаты 1", "Покраска  потолка", Status.DONE, epic1.getId(), 5);
       // printAll(taskManager);
//        System.out.println(taskManager.getAllSubTask());
//        taskManager.getAllTasks1();
//        System.out.println(taskManager.getSubtaskByEpic(3));
//        taskManager.deleteById(1);   // удаление задачи по Id
//        taskManager.deleteById(5);   // удаление под задачи по Id
//        taskManager.getAllTasks1();
//        taskManager.deleteByIdEpic(3);  // удаление эпика по id
        printAll(taskManager);
//        taskManager.getAllTasks1();
    }

    private static void printAll(TaskManager taskManager) {
        System.out.println("Задачи: ");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : taskManager.getAllEpic()) {
            System.out.println(epic);

            for (Task task : taskManager.getSubtaskByEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : taskManager.getAllSubTask()) {
            System.out.println(subtask);
        }
        System.out.println("История:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}


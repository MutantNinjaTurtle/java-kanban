import manager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("task1", "Погладить кота", Status.NEW);
        Task task2 = new Task("task2", "Попить чай", Status.NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        Epic epic1 = new Epic("Ремонт квартиры", "Капитальный ремонт");
        Epic epic2 = new Epic("Ремонт комнаты", "Покраска пола");
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        Subtask subtask1 = new Subtask("Ремонт комнаты 1", "Покраска  потолка", Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Ремонт комнаты 2", "Покраска стен", Status.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Ремонт комнаты 3", "покраска пола", Status.NEW, epic2.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        Task task3 = new Task("task1", "Погладить кота", Status.DONE, 1);
        //System.out.println(taskManager.getSubtaskByEpic(epic1));
        Subtask subtask4 = new Subtask("Ремонт комнаты 1", "Покраска  потолка", Status.DONE, epic1.getId(), 5);
        Subtask subtask5 = new Subtask("Ремонт комнаты 2", "Покраска стен", Status.IN_PROGRESS, epic1.getId());
        Subtask subtask6 = new Subtask("погладить кота ", "Покраска  потолка", Status.DONE, epic1.getId());
        taskManager.updateTask(task3);             // обновление задач
//        taskManager.updateSubTask(subtask1,subtask4);    // обновление подзадач
        taskManager.updateSubTask(subtask4);
//        System.out.println(taskManager.getAllSubTask());
//        taskManager.getAllTasks1();
//        System.out.println(taskManager.getSubtaskByEpic(3));

//        taskManager.deleteById(1);   // удаление задачи по Id
//        taskManager.deleteById(5);   // удаление под задачи по Id
//        taskManager.getAllTasks1();
//
//        taskManager.deleteById(3);  // удаление эпика по id
//        taskManager.getAllTasks1();

    }
}


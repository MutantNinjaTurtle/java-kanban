package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCounter = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void addTask(Task task) {     // добавление задачи в хеш-мап, увеличение id
        task.setId(idCounter);
        tasks.put(idCounter, task);
        idCounter++;
    }

    public void addSubtask(Subtask subtask) {   // добавление задачи в хеш-мап, увеличение id
        subtask.setId(idCounter);
        subtasks.put(idCounter, subtask);
        idCounter++;
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask);
        epic.updateStatus();
    }

    public void addEpic(Epic epic) {   // добавление задачи в хеш-мап, увеличение id
        epic.setId(idCounter);
        epic.updateStatus();
        epics.put(idCounter, epic);
        idCounter++;
    }

    public void getAllTasks1(){              //  Получение списка всех задач.
        System.out.println(tasks);
        System.out.println(epics);
        System.out.println(subtasks);
    }

    public ArrayList<Task> getAllTasks() {   //  Возращение всех задач
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubTask() {   //  Возращение всех подзадач
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpic() {   //  Возращение всех эпиков
        return new ArrayList<>(epics.values());
    }

    public void deleteAllTasks() {            //Удаление всех задач
        tasks.clear();
    }

    public void deleteAllEpics() {           //Удаление всех эпиков
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubTasks() {       //Удаление всех подзадач
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtasks(); //   удаление из эпиков
            epic.updateStatus(); // обновление статуса эпика
        }
        subtasks.clear();
    }

    public Task getByIdTask(int id) {         //Получение задачи по идентификатору
        return tasks.get(id);
    }

    public Subtask getByIdSubTask(int id) {   //Получение подзадачи по идентификатору
        return subtasks.get(id);
    }

    public Epic getByIdEpic(int id) {    //Получение эпика по идентификатору
        return epics.get(id);
    }

    public void updateTask(Task task) {   // Обновление задачи
        tasks.put(task.getId(), task);
    }

    public void updateSubTask(Subtask subtask) { //обновление подзадачи
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask);
        epic.updateStatus();
    }

    public void updateEpic(Epic epic) {
        Epic epicUpdate = epics.get(epic.getId());
        if (!epicUpdate.getName().equals(epic.getName())) {
            epicUpdate.setName(epic.getName());
        }
        if (!epicUpdate.getDescription().equals(epic.getDescription())) {
            epicUpdate.setDescription(epic.getDescription());
        }
    }

    public void deleteByIdTasks(int id) {   // Удаление таска по идентификатору (Id)
        tasks.remove(id);
    }

    public void deleteByIdSubtasks(int id) {    // Удаление подзадачи по идентификатору (Id)
        Subtask subtask = subtasks.get(id);
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeById(subtask);
        subtasks.remove(id);
        epic.updateStatus();
    }

    public void deleteByIdEpic(int id) {    // Удаление эпика по идентификатору (Id)
        Epic epic = epics.get(id);
        epic.deleteAllSubtasks();
        epics.remove(id);
    }

    public ArrayList<Subtask> getSubtaskByEpic(int id) {  //Получение списка всех подзадач определённого эпика
        Epic epic = epics.get(id);
        return epic.getSubtasks();
    }
}

// Сергей, добрый день. Не понял вашего замечания по методу public void deleteByIdEpic(int id)
// Удаление эпика по идентификатору (Id)
//Нужно пересмотреть статус эпика. Пустой эпик должен быть в статусе NEW
// для чего пересматривать статус удаляемого эпика?

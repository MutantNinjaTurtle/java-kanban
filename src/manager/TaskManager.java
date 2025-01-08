package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int idCounter = 1;
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
        epics.put(subtask.getEpicId(), epic);
    }

    public void addEpic(Epic epic) {   // добавление задачи в хеш-мап, увеличение id
        epic.setId(idCounter);
        epic.updateStatus();
        epics.put(idCounter, epic);
        idCounter++;
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
    }

    public void deleteAllSubTasks() {       //Удаление всех подзадач
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
        epics.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        Epic epic_update = epics.get(epic.getId());
        if (!epic_update.getName().equals(epic.getName())) {
            epic_update.setName(epic.getName());
        }
        if (!epic_update.getDescription().equals(epic.getDescription())) {
            epic_update.setDescription(epic.getDescription());
        }
        epics.put(epic.getId(), epic_update);
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

    public ArrayList<Subtask> getSubtaskByEpic(Epic epic) {  //Получение списка всех подзадач определённого эпика
        return epic.getSubtasks();
    }
}


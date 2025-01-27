package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaultHistoryManager();

    @Override
    public void addTask(Task task) {     // добавление задачи в хеш-мап, увеличение id
        task.setId(idCounter);
        tasks.put(idCounter, task);
        idCounter++;
    }

    @Override
    public void addSubtask(Subtask subtask) {   // добавление задачи в хеш-мап, увеличение id
        subtask.setId(idCounter);
        subtasks.put(idCounter, subtask);
        idCounter++;
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask);
        epic.updateStatus();
    }

    @Override
    public void addEpic(Epic epic) {   // добавление задачи в хеш-мап, увеличение id
        epic.setId(idCounter);
        epic.updateStatus();
        epics.put(idCounter, epic);
        idCounter++;
    }

    @Override
    public ArrayList<Task> getAllTasks() {   //  Возращение всех задач
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubTask() {   //  Возращение всех подзадач
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpic() {   //  Возращение всех эпиков
        return new ArrayList<>(epics.values());
    }

    @Override
    public void deleteAllTasks() {            //Удаление всех задач
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {           //Удаление всех эпиков
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {       //Удаление всех подзадач
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtasks(); //   удаление из эпиков
            epic.updateStatus(); // обновление статуса эпика
        }
        subtasks.clear();
    }

    @Override
    public Task getByIdTask(int id) {//Получение задачи по идентификатору
        Task task = tasks.get(id);
        historyManager.addTask(task);
        return task;
    }

    @Override
    public Subtask getByIdSubTask(int id) {   //Получение подзадачи по идентификатору
        Subtask subtask = subtasks.get(id);
        historyManager.addTask(subtask);
        return subtask;
    }

    @Override
    public Epic getByIdEpic(int id) {    //Получение эпика по идентификатору
        Epic epic = epics.get(id);
        historyManager.addTask(epic);
        return epic;
    }

    @Override
    public void updateTask(Task task) {   // Обновление задачи
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubTask(Subtask subtask) { //обновление подзадачи
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.updateSubtask(subtask);
        epic.updateStatus();
    }

    @Override
    public void updateEpic(Epic epic) {
        Epic epicUpdate = epics.get(epic.getId());
        if (!epicUpdate.getName().equals(epic.getName())) {
            epicUpdate.setName(epic.getName());
        }
        if (!epicUpdate.getDescription().equals(epic.getDescription())) {
            epicUpdate.setDescription(epic.getDescription());
        }
    }

    @Override
    public void deleteByIdTasks(int id) {   // Удаление таска по идентификатору (Id)
        tasks.remove(id);
    }

    @Override
    public void deleteByIdSubtasks(int id) {
        // Удаление подзадачи по идентификатору (Id)
        Subtask subtask = subtasks.get(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeById(subtask);
        subtasks.remove(id);
        epic.updateStatus();
    }

    @Override
    public void deleteByIdEpic(int id) {    // Удаление эпика по идентификатору (Id)
        Epic epic = epics.remove(id);
        if (epic == null) {
            return;
        }
        epic.deleteAllSubtasks();
    }

    @Override
    public ArrayList<Subtask> getSubtaskByEpic(int id) {  //Получение списка всех подзадач определённого эпика
        Epic epic = epics.get(id);
        if (epic == null) {
            return new ArrayList<>();         // если эпик = null, возвращаем пустой список
        }
        return epic.getSubtasks();
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}



package TaskManager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    int idCounter = 1;
    HashMap<Integer,Task> tasks = new HashMap<>();;
    HashMap<Integer, Epic> epics = new HashMap<>();;
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void addTask(Task task){     // добавление задачи в хеш-мап, увеличение id
        task.setId(idCounter);
        tasks.put(idCounter, task);
        idCounter++;
    }

    public void addSubtask(Subtask subtask){   // добавление задачи в хеш-мап, увеличение id
        subtask.setId(idCounter);
        subtasks.put(idCounter, subtask);
        idCounter++;
    }

    public void addEpic(Epic epic){   // добавление задачи в хеш-мап, увеличение id
        epic.setId(idCounter);
        epic.updateStatus();
        epics.put(idCounter, epic);
        idCounter++;
    }



    public void getAllTasks(){              //  Получение списка всех задач.
        System.out.println(tasks);
        System.out.println(epics);
        System.out.println(subtasks);
    }

    public  void deleteAllTasks(){            //Удаление всех задач
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    public Task getById (int id){  //Получение по идентификатору
        if (tasks.containsKey(id)){
            return tasks.get(id);
        } else if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        } else if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

    public void updateTask (Task task, Task newTask){   // Обновление Tasks.Task задачи
        tasks.put(task.getId(),newTask);
    }

    public void updateSubTask(Subtask subtask, Subtask newSubtask){ //обновление подзадачи
        subtasks.put(subtask.getId(),newSubtask);
        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(newSubtask);
        epics.put(epic.getId(), epic);
    }
    
    public void deleteById (int id){                   // Удаление по идентификатору (Id)
        if (tasks.containsKey(id)){
            tasks.remove(id);
        } else if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());
            epic.removeById(subtask);
            subtasks.remove(id);
            epic.updateStatus();

        } else if (epics.containsKey(id)){
            Epic epic = epics.get(id);
            epic.deleteAllSubtasks();
            epics.remove(id);
        }
    }

    public ArrayList<Subtask>  getSubtaskByEpic (Epic epic){  //Получение списка всех подзадач определённого эпика
        return epic.getSubtasks();
    }

}


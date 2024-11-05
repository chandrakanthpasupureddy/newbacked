package com.example.xorobackend.service;
import java.util.*;

import com.example.xorobackend.repository.TaskRepository;
import com.example.xorobackend.task.Task;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskService implements TaskRepository {
    private HashMap<Integer, Task> hmap = new HashMap<>();
    int uniqueTaskId = 3;

    public TaskService(){
    Task b1 = new Task(1,"book1","book number1");
    Task b2 = new Task(2,"book2","book number2");
    hmap.put(b1.getId(), b1);
    hmap.put(b2.getId(), b2);
    }

    @Override
    public void deleteTask(int taskId){
        Task task=hmap.get(taskId);

        if(task == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        else{
            hmap.remove(taskId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
    @Override
    public Task updateTask(int taskId, Task task) {
        Task existingTask=hmap.get(taskId);

        if(existingTask == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (task.getTitle() != null) {
            existingTask.setTitle(task.getTitle());
        }
        
        if (task.getDescription() != null) {
            existingTask.setDescription(task.getDescription());
        }
        return existingTask;
    }
    @Override
    public Task addTask(Task task) {
        task.setId(uniqueTaskId);
        hmap.put(uniqueTaskId, task);
        uniqueTaskId += 1;

        return task;
    }
    @Override
    public ArrayList<Task> getTasks() {
        Collection<Task> taskCollection = hmap.values();
        ArrayList<Task> tasks = new ArrayList<>(taskCollection);
        return tasks;
    }
    @Override
    public Task getTaskById(int taskId) {
        Task task = hmap.get(taskId);
        if(task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return task;
    }

}

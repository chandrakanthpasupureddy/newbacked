package com.example.xorobackend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.xorobackend.service.TaskH2Service;
import com.example.xorobackend.task.Task; 
@RestController
@CrossOrigin(origins = "https://xoroassig.vercel.app/")
public class TaskController {
    @Autowired
    public TaskH2Service taskService;
    @GetMapping("/tasks")
    public ArrayList<Task> getTasks(){
        return taskService.getTasks();
    }
    @GetMapping("/tasks/{taskId}")
    public Task getBookById(@PathVariable("taskId") int taskId) {
        return taskService.getTaskById(taskId);
    }
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable("taskId") int taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable("taskId") int taskId){
        taskService.deleteTask(taskId);
    }
}

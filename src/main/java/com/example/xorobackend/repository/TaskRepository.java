package com.example.xorobackend.repository;
import java.util.*;

import com.example.xorobackend.task.Task;
public interface TaskRepository {
    ArrayList<Task> getTasks(); 
    Task getTaskById(int taskId);
    Task addTask(Task task);
    Task updateTask(int taskId, Task task);
    void deleteTask(int taskId);
}

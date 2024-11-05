package com.example.xorobackend.service;

import com.example.xorobackend.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.xorobackend.task.TaskRowMapper;
import com.example.xorobackend.task.Task;


@Service
public class TaskH2Service implements TaskRepository{
    @Autowired
    private JdbcTemplate db;


    @Override
    public void deleteTask(int taskId) {

        db.update("delete from task where id = ?", taskId);
      
    }
    @Override
    public Task updateTask(int taskId, Task task) {

        if(task.getTitle() != null){
            db.update("update task set title = ? where id = ?", task.getTitle(), taskId);
        }
        if(task.getDescription() != null){
            db.update("update task set description = ? where id = ?", task.getDescription(), taskId);
        }
        return getTaskById(taskId);
    }
    @Override
    public Task addTask(Task task) {

        db.update("insert into task(title, description) values (?, ?)", task.getTitle(), task.getDescription());

        Task savedTask = db.queryForObject("select * from task where title = ? and description = ?", new TaskRowMapper(), task.getTitle(), task.getDescription());


        return savedTask;
    }
    @Override
    public Task getTaskById(int taskId) {

      try{
        
        Task task = db.queryForObject("select * from task where id = ?", new TaskRowMapper(), taskId);
        
        return task;
        }
      catch (Exception e){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      
    }
    @Override
    public ArrayList<Task> getTasks() {

        List<Task> taskList = db.query("select * from task", new TaskRowMapper());
        ArrayList<Task> tasks = new ArrayList<>(taskList);
        return tasks;

    }
}

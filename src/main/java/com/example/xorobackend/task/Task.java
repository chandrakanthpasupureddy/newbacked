package com.example.xorobackend.task;

public class Task{
    private int id;
    private String title;
    private String description;
    public Task(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ToDoItem {


    private int id;

    private String title;
    private boolean completed;


    public ToDoItem(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }


    public ToDoItem(String title) {
        this.title = title;
        this.completed = false;
    }

    public boolean getCompleted() {
        return completed;
    }

}

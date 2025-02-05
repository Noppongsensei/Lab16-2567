package com.example.demo.repository;

import com.example.demo.dto.CreateTodoItem;
import com.example.demo.model.ToDoItem;

import java.util.List;

public interface TodoRepository {
    List<ToDoItem> findAll();
    List<ToDoItem> findByCompleted(boolean completed);
    ToDoItem add(String title);
    void deleteById(int id);


    List<ToDoItem> addAll(List<CreateTodoItem> todoItems); // เพิ่มหลายรายการ
    ToDoItem updateStatus(int id, boolean completed); // อัปเดตสถานะของ to-do
    List<ToDoItem> findByTitleContaining(String title);
}

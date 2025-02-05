package com.example.demo.repository;

import com.example.demo.dto.CreateTodoItem;
import com.example.demo.model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("MyController")
public class MyTodoRepository  implements  TodoRepository{
    private  List<ToDoItem> toDoItems ;
    private int idCounter = 1;

    public MyTodoRepository(){
        this.toDoItems= new ArrayList<>();
    }

    @Override
    public List<ToDoItem> findAll() {
        return toDoItems;
    }

    @Override
    public List<ToDoItem> findByCompleted(boolean completed) {
        List<ToDoItem> result =new ArrayList<>();
        for(ToDoItem toDoItem : toDoItems){
            if(toDoItem.getCompleted() == completed){
                result.add(toDoItem);
            }
        }
        return result;
    }

    @Override
    public ToDoItem add(String title) {
        ToDoItem toDoItem =new ToDoItem(title);
        toDoItems.add(toDoItem);
        return toDoItem;
    }

    @Override
    public void deleteById(int id) {
        for(ToDoItem toDoItem:toDoItems){
            if(toDoItem.getId() == id){
                toDoItems.remove(toDoItem);
                return;
            }
        }
    }

    @Override
    public List<ToDoItem> addAll(List<CreateTodoItem> todoItems) {
        List<ToDoItem> newTodos = new ArrayList<>();
        for (CreateTodoItem item : todoItems) {
            ToDoItem newItem = new ToDoItem(idCounter++, item.getTitle(), false);
            toDoItems.add(newItem);
            newTodos.add(newItem);
        }
        return newTodos; // ✅ คืนค่าเป็น List<ToDoItem>
    }



    @Override
    public ToDoItem updateStatus(int id, boolean completed) {
        for (ToDoItem todo : toDoItems) {
            if (todo.getId() == id) {
                todo.setCompleted(completed);
                return todo;
            }
        }
        return null;
    }


    @Override
    public List<ToDoItem> findByTitleContaining(String title) {
        return toDoItems.stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }


}

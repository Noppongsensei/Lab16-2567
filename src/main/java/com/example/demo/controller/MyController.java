package com.example.demo.controller;

import com.example.demo.dto.CreateTodoItem;
import com.example.demo.model.ToDoItem;
import com.example.demo.repository.MyTodoRepository;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class MyController {
    private  final TodoRepository todoRepository;

    @Autowired
    public MyController(@Qualifier("MyController") TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }

    @GetMapping("/")
    public List<ToDoItem> getTodoList(@RequestParam(name = "status" ,required = false) Boolean completed){
        if(completed != null){
            return todoRepository.findByCompleted(completed);
        }
        return todoRepository.findAll();
    }

    @PostMapping("/")
    public ToDoItem addTodoItem(@RequestBody CreateTodoItem createTodoItem){
        return  todoRepository.add(createTodoItem.getTitle());
    }

    @DeleteMapping("{id}")
    public  void deleteItem(@PathVariable(name = "id") int todoId){
        todoRepository.deleteById(todoId);
    }

    @PostMapping("/upload")
    public List<ToDoItem> uploadTodos(@RequestBody List<CreateTodoItem> todoItems) {
        return todoRepository.addAll(todoItems);
    }

    @PutMapping("/{id}")
    public ToDoItem updateTodoStatus(@PathVariable int id, @RequestParam boolean completed) {
        return todoRepository.updateStatus(id, completed);
    }

    @GetMapping("/search")
    public List<ToDoItem> searchTodos(@RequestParam String title) {
        return todoRepository.findByTitleContaining(title);
    }

}

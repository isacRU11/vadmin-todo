package com.vadmin.todo.controller;

import java.util.List;

import com.vadmin.todo.entity.TodoList;
import com.vadmin.todo.repository.TodoListRepository;
import com.vadmin.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    @Autowired
    private TodoListRepository todoRepo;
    @Autowired
    private TodoService todoService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/todo", "/"})
    public String sample(Model model){
        List<TodoList> tl = todoRepo.findAll();

        model.addAttribute("todolist", tl);
        return "todo";
    }

    @PostMapping("/todo")
    public String createTodo(@RequestParam(required = false) String todo){
        this.todoService.insertRow(todo);
        return "redirect:/todo";
    }

    @GetMapping("todo-{id}/delete")
    public String deleteTodo(@PathVariable Integer id){
        todoRepo.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/speech")
    public String getSpeech() {
        return "speech";
    }
    
}
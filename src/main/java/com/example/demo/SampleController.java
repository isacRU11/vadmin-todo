package com.example.demo;

import java.util.List;

import com.example.demo.entity.TodoList;
import com.example.demo.repository.TodoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {

    @Autowired
    private TodoListRepository todoRepo;
    @Autowired
    private TodoService todoService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/home", "/"})
    public String sample(Model model){
        List<TodoList> tl = todoRepo.findAll();

        model.addAttribute("todolist", tl);
        return "sample";
    }

    @PostMapping("/home")
    public String createTodo(@RequestParam(required = false) String todo){
        this.todoService.insertRow(todo);
        return "redirect:/home";
    }

    @GetMapping("todo-{id}/delete")
    public String deleteTodo(@PathVariable Integer id){
        todoRepo.deleteById(id);
        return "redirect:/home";
    }
}
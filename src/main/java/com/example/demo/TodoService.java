package com.example.demo;

import com.example.demo.entity.TodoList;
import com.example.demo.repository.TodoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoListRepository todoRepo;


    public void insertRow(String todo){
        TodoList todolst = new TodoList();
        todolst.setTodo(todo);
        todolst.setIsDeleted(false);

        todoRepo.save(todolst);
    }

}
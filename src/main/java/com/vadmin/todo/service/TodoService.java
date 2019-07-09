package com.vadmin.todo.service;

import com.vadmin.todo.entity.TodoList;
import com.vadmin.todo.repository.TodoListRepository;

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
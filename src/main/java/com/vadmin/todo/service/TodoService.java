package com.vadmin.todo.service;

import java.text.SimpleDateFormat;
import java.text.ParseException;

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


    public void insertRow(TodoList todolist){
        try {
            todolist.setDeadLine(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(todolist.getTempDeadLine().replace("T", " ")));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        
        todoRepo.save(todolist);
    }

}
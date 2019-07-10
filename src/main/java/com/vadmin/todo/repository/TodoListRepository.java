package com.vadmin.todo.repository;

import java.util.List;

import com.vadmin.todo.entity.TodoList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * TodoListRepository
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer>{

    @Query("SELECT p FROM TodoList p")
    public List<TodoList> findAll();
}
package com.example.demo.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TodoList
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "m_todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String todo;
    @Column(insertable = false, updatable = true)
    private Boolean isDeleted;
    
}
package com.vadmin.todo.entity;

import java.util.Date;

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
    @Column(length = 100)
    private String todo;
    @Column
    private Date deadLine;

    @Transient
    private String tempDeadLine;
}
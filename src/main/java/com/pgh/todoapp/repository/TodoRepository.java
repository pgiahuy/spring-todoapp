package com.pgh.todoapp.repository;

import com.pgh.todoapp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TodoRepository extends JpaRepository<TodoEntity,Long> {
}

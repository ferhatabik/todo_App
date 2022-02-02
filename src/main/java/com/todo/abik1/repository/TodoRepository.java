package com.todo.abik1.repository;

import com.todo.abik1.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> findByUserId(Long userId);
}

package com.todo.abik1.controller;

import com.todo.abik1.entity.Todo;
import com.todo.abik1.entity.User;
import com.todo.abik1.repository.TodoRepository;
import com.todo.abik1.repository.UserRepository;
import com.todo.abik1.request.AddTodoRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    UserRepository userRepository;
    TodoRepository todoRepository;

    public TodoController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        todo.setUser(user);
        todoRepository.save(todo);
    }

    @PostMapping("/{todoId}")
    public void toggleTodoCompleted( @PathVariable Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoRepository.deleteById(todoId);
    }

    @GetMapping("/{userId}")
    public List<Todo> getTodosByUser(@PathVariable Long userId){
        return todoRepository.findByUserId(userId);
    }
}

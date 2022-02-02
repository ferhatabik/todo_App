package com.todo.abik1;

import com.todo.abik1.entity.Todo;
import com.todo.abik1.entity.User;
import com.todo.abik1.repository.TodoRepository;
import com.todo.abik1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Abik1Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Abik1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setPassword("admin");
        user.setUsername("admin");
        userRepository.save(user);

        Todo todo  = new Todo();
        todo.setContent("todo");
        todo.setUser(user);
        todoRepository.save(todo);
    }
}

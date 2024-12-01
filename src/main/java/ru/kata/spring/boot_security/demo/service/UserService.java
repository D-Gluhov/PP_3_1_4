package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    User getUserById(int id);

    void saveOrUpdate(User user);

    void delete(int id);

    User findByUsername(String username);
}
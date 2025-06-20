package com.jit.mail.service;

import com.jit.mail.domain.User;

import java.util.List;

public interface UserService {

    public User findByUsername(String username);

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(Integer id);

    public List<User> getAll();
}

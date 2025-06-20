package com.jit.mail.service.impl;

import com.jit.mail.domain.User;
import com.jit.mail.repository.UserRepository;
import com.jit.mail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User addUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public User updateUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public void deleteUser(Integer id) {


    }

    @Override
    public List<User> getAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }


}

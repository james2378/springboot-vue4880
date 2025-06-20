package com.jit.mail.repository;

import com.jit.mail.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsername(String username);

}

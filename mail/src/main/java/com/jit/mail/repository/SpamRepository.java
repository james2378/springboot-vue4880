package com.jit.mail.repository;

import com.jit.mail.domain.Spam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpamRepository extends JpaRepository<Spam, Integer> {
    public List<Spam> findByToUser(String toUser);

    public Integer countByToUser(String toUser);

    public Spam findByMessageId(String messageId);

}
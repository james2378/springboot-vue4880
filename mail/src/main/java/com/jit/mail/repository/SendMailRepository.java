package com.jit.mail.repository;

import com.jit.mail.domain.SendMail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SendMailRepository extends JpaRepository<SendMail, Integer> {
    public List<SendMail> findByFromUser(String fromUser);

    public List<SendMail> findByCc(String cc);
}

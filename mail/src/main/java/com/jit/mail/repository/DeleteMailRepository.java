package com.jit.mail.repository;

import com.jit.mail.domain.DeleteMail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeleteMailRepository extends JpaRepository<DeleteMail, Integer> {
    public List<DeleteMail> findByToUser(String toUser);

    public DeleteMail findByMessageId(String messageId);

}

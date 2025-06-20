package com.jit.mail.service;

import com.jit.mail.domain.Spam;

import java.util.List;

public interface SpamService {
    public Spam addSpam(Spam spam);

    public void deleteSpam(Spam spam);

    public List<Spam> findByToUser(String toUser);

    public Integer countByToUser(String toUser);

    public Spam getOne(Integer id);

    public Spam findByMessageId(String messageId);

}

package com.jit.mail.service.impl;

import com.jit.mail.domain.Spam;
import com.jit.mail.repository.SpamRepository;
import com.jit.mail.service.SpamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpamServiceImpl implements SpamService {

    @Autowired
    public SpamRepository spamRepository;

    @Override
    public Spam addSpam(Spam spam) {
        Spam spam1 = spamRepository.save(spam);
        return spam1;
    }

    @Override
    public void deleteSpam(Spam spam) {
        spamRepository.delete(spam);
    }

    @Override
    public List<Spam> findByToUser(String toUser) {
        List<Spam> spamList = spamRepository.findByToUser(toUser);
        return spamList;
    }

    @Override
    public Integer countByToUser(String toUser) {
        Integer num = spamRepository.countByToUser(toUser);
        return num;
    }

    @Override
    public Spam getOne(Integer id) {
        Spam spam = spamRepository.getOne(id);
        return spam;
    }

    @Override
    public Spam findByMessageId(String messageId) {
        Spam spam = spamRepository.findByMessageId(messageId);
        return spam;
    }
}

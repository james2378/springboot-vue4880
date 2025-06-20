package com.jit.mail.service.impl;

import com.jit.mail.domain.Mail;
import com.jit.mail.repository.MailRepository;
import com.jit.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    public MailRepository mailRepository;

    @Override
    public Mail addMail(Mail mail) {
        Mail mail1 = mailRepository.save(mail);
        return mail1;
    }

    @Override
    public void deleteMail(Mail mail) {
        mailRepository.delete(mail);
    }

    @Override
    public List<Mail> findByToUser(String toUser) {

        List<Mail> mailList = mailRepository.findByToUser(toUser);
        return mailList;
    }

    @Override
    public List<Mail> findByFromUser(String fromUser) {

        List<Mail> mailList = mailRepository.findByFromUser(fromUser);
        return mailList;
    }

    @Override
    public Mail getOne(Integer id) {
        Mail mail = mailRepository.getOne(id);
        return mail;
    }

    @Override
    public List<Mail> findByCc(String cc) {
        List<Mail> mailList = mailRepository.findByCc(cc);
        return mailList;
    }

    @Override
    public Mail findByMessageId(String messageId) {
        Mail mail = mailRepository.findByMessageId(messageId);
        return mail;
    }
}

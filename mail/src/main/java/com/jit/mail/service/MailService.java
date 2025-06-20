package com.jit.mail.service;

import com.jit.mail.domain.Mail;

import java.util.List;

public interface MailService {

    public Mail addMail(Mail mail);

    public void deleteMail(Mail mail);

    public List<Mail> findByToUser(String to);

    public List<Mail> findByFromUser(String from);

    public Mail getOne(Integer id);

    public List<Mail> findByCc(String cc);

    public Mail findByMessageId(String messageId);


}

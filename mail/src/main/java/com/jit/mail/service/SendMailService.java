package com.jit.mail.service;

//import com.jit.mail.domain.Mail;

import com.jit.mail.domain.SendMail;

import java.util.List;

public interface SendMailService {
    public SendMail addMail(SendMail sendMail);

    public void deleteMail(SendMail sendMail);

//    public List<Mail> findByToUser(String to);

    public List<SendMail> findByFromUser(String from);

    public SendMail getOne(Integer id);

    public List<SendMail> findByCc(String cc);

//    public Mail findByMessageId(String messageId);
}

package com.jit.mail.service.impl;

import com.jit.mail.domain.SendMail;
import com.jit.mail.repository.SendMailRepository;
import com.jit.mail.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    public SendMailRepository sendMailRepository;

    @Override
    public SendMail addMail(SendMail sendMail) {
        SendMail sendMail1 = sendMailRepository.save(sendMail);
        return sendMail1;
    }

    @Override
    public void deleteMail(SendMail sendMail) {
        sendMailRepository.delete(sendMail);
    }

    @Override
    public List<SendMail> findByFromUser(String from) {
        List<SendMail> sendMailList = sendMailRepository.findByFromUser(from);
        return sendMailList;
    }

    @Override
    public SendMail getOne(Integer id) {
        SendMail sendMail = sendMailRepository.getOne(id);
        return sendMail;
    }

    @Override
    public List<SendMail> findByCc(String cc) {
        List<SendMail> sendMailList = sendMailRepository.findByCc(cc);
        return sendMailList;
    }
}

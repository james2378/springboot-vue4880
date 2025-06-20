package com.jit.mail.service.impl;

import com.jit.mail.domain.DeleteMail;
import com.jit.mail.repository.DeleteMailRepository;
import com.jit.mail.service.DeleteMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteMailServiceImpl implements DeleteMailService {
    @Autowired
    public DeleteMailRepository deleteMailRepository;

    @Override
    public List<DeleteMail> findByToUser(String toUser) {
        List<DeleteMail> deleteMails = deleteMailRepository.findByToUser(toUser);
        return deleteMails;
    }

    @Override
    public DeleteMail addDeleteMail(DeleteMail deleteMail) {
        DeleteMail deleteMail1 = deleteMailRepository.save(deleteMail);
        return deleteMail1;
    }

    @Override
    public void deleteDeleteMail(DeleteMail deleteMail) {
        deleteMailRepository.delete(deleteMail);
//        return null;
    }

    @Override
    public DeleteMail getOne(Integer id) {
        DeleteMail deleteMail = deleteMailRepository.getOne(id);
        return deleteMail;
    }

    @Override
    public DeleteMail findByMessageId(String messageId) {
        DeleteMail deleteMail = deleteMailRepository.findByMessageId(messageId);
        return deleteMail;
    }
}

package com.jit.mail.service;

import com.jit.mail.domain.DeleteMail;

import java.util.List;

public interface DeleteMailService {
    public List<DeleteMail> findByToUser(String toUser);

    public DeleteMail addDeleteMail(DeleteMail deleteMail);

    public void deleteDeleteMail(DeleteMail deleteMail);

    public DeleteMail getOne(Integer id);

    public DeleteMail findByMessageId(String messageId);

}

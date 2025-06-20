package com.jit.mail.repository;

import com.jit.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.mail.internet.InternetAddress;
import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {

    public List<Mail> findByToUser(String to);

    public List<Mail> findByFromUser(String from);

    public List<Mail> findByCc(String cc);

    public List<Mail> findByBcc(String bcc);

    public Mail findByMessageId(String messageId);

    public List<Mail> findByToUserAndHasRead(String to, Boolean hasRead);

    public List<Mail> findByCcAndHasRead(String cc, Boolean hasRead);

//    public Integer countByCc(String cc);

    public Integer countByToUserOrCc(String to, String cc);

    public Integer countByToUserOrCcAndHasRead(String to, String cc, Boolean hasRead);

    public Integer countByFromUser(String from);

}

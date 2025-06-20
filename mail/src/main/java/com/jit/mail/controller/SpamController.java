package com.jit.mail.controller;

import com.jit.mail.domain.DeleteMail;
import com.jit.mail.domain.Mail;
import com.jit.mail.domain.Spam;
import com.jit.mail.domain.User;
import com.jit.mail.service.DeleteMailService;
import com.jit.mail.service.MailService;
import com.jit.mail.service.SpamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/spam")
public class SpamController {
    @Autowired
    public SpamService spamService;
    @Autowired
    public MailService mailService;

    @Autowired
    public DeleteMailService deleteMailService;

    @Value("${test.host}")
    public String host;

    @RequestMapping("/listSpamMail")
    public String listSpamMail(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        List<Spam> spamList = spamService.findByToUser(user1.getUsername() + "@" + host);
        model.addAttribute("spamList", spamList);
        return "/mail/spam-list";
    }

    @RequestMapping("/showSpamMail")
    public String showMail(@RequestParam("id") Integer id, Model model) {
        Spam spam = spamService.getOne(id);
        if (spam.getAttachName() != null) {
            String[] arr = spam.getAttachName().split(",");
            HashMap<String, String> hashMap = new HashMap<>();
            for (String s : arr) {
                String[] arr1 = s.split(".");
//                System.out.println(s.substring(s.lastIndexOf(".") + 1));
                hashMap.put(s, s.substring(s.lastIndexOf(".") + 1));
//            list.add(s);
            }
            model.addAttribute("attach", hashMap);
        }

        model.addAttribute("mail", spam);
        model.addAttribute("mail_flag", "showSpam");
        return "/mail/show";
    }

    @RequestMapping("/moveToMail")
    public String moveToMail(@RequestParam("id") Integer spamId, @RequestParam("type") String type, Model model, RedirectAttributes attributes) {
        Spam spam = spamService.getOne(spamId);
        if (type.equals("deleteMail")) {
            DeleteMail deleteMail = new DeleteMail();
            BeanUtils.copyProperties(spam, deleteMail);
            deleteMail.setId(null);
            deleteMailService.addDeleteMail(deleteMail);
        } else if (type.equals("mail")) {
            Mail mail = new Mail();
            BeanUtils.copyProperties(spam, mail);
            mail.setId(null);
            mailService.addMail(mail);
        }
        return "redirect:/spam/listSpamMail";
    }


}

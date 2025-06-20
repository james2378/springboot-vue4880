package com.jit.mail.controller;

import com.jit.mail.domain.*;
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
@RequestMapping("/deleteMail")
public class DeleteMailController {
    @Autowired
    public MailService mailService;

    @Autowired
    public DeleteMailService deleteMailService;

    @Autowired
    public SpamService spamService;
    @Value("${test.host}")
    public String host;

    @RequestMapping("/listDeleteMail")
    public String listDeleteMail(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        List<DeleteMail> deleteMailList = deleteMailService.findByToUser(user1.getUsername() + "@" + host);
        model.addAttribute("deleteMailList", deleteMailList);
        return "/mail/delete-mail-list";
    }

    @RequestMapping("/moveToMail")
    public String moveToMail(@RequestParam("id") Integer deleteMailId, @RequestParam("type") String type, Model model, RedirectAttributes attributes) {
        DeleteMail deleteMail = deleteMailService.getOne(deleteMailId);
        if (type.equals("spamMail")) {
            Spam spam = new Spam();
            BeanUtils.copyProperties(deleteMail, spam);
            spam.setId(null);
            spamService.addSpam(spam);
        } else if (type.equals("mail")) {
            Mail mail = new Mail();
            BeanUtils.copyProperties(deleteMail, mail);
            mail.setId(null);
            mailService.addMail(mail);
        }
        return "redirect:/spam/listSpamMail";
    }

    @RequestMapping("/showDeleteMail")
    public String showSentMail(@RequestParam("id") Integer id, Model model) {
        System.out.println("id is:" + id);
        DeleteMail deleteMail = deleteMailService.getOne(id);
        if (deleteMail.getAttachPath() != null) {
            String[] arr = deleteMail.getAttachPath().split(",");
            HashMap<String, String> hashMap = new HashMap<>();
            for (String s : arr) {
                String[] arr1 = s.split(".");
//                System.out.println(s.substring(s.lastIndexOf(".") + 1));
                hashMap.put(s, s.substring(s.lastIndexOf(".") + 1));
//            list.add(s);
            }
            model.addAttribute("attach", hashMap);
        }

        model.addAttribute("mail", deleteMail);
        model.addAttribute("mail_flag", "showDeleteMail");
        return "/mail/show";
    }
}

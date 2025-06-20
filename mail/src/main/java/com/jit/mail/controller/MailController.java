package com.jit.mail.controller;

import com.alibaba.fastjson.JSON;
import com.jit.mail.Utils.FileUtilss;
import com.jit.mail.Utils.ReceiveMail;
import com.jit.mail.Utils.SendMailUt;
import com.jit.mail.Utils.SpamCollection;
import com.jit.mail.domain.*;
import com.jit.mail.service.DeleteMailService;
import com.jit.mail.service.MailService;
import com.jit.mail.service.SendMailService;
import com.jit.mail.service.SpamService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    public MailService mailService;
    @Autowired
    public SendMailService sendMailService;
    @Autowired
    public SpamService spamService;

    @Autowired
    public DeleteMailService deleteMailService;

    @Value("${test.host}")
    public String host;

    public void receiveMail(Model model) {

    }

    @RequestMapping("/receiveMail")
    public String getMailStoreDB(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Map<String, KeywordCount> keyword = (Map) session.getAttribute("keywordMap");
        for (Map.Entry<String, KeywordCount> entry : keyword.entrySet()) {
            System.out.println("+++++++++++++++++++++++" + entry.getValue());
        }

        List<Mail> mailList = null;
        if (user1 != null && password != null) {
            mailList = ReceiveMail.receiveMail(host, user1.getUsername(), password);
            for (Mail mail : mailList) {
                Mail mail1 = mailService.findByMessageId(mail.getMessageId());
                Spam spam1 = spamService.findByMessageId(mail.getMessageId());
                DeleteMail deleteMail = deleteMailService.findByMessageId(mail.getMessageId());
                if (mail1 == null && spam1 == null && deleteMail == null) {
                    HashMap<String, String> checkMail = new HashMap<>();
                    Mail mail2 = mailService.addMail(mail);
                    checkMail.put(String.valueOf(mail2.getId()), mail2.getContent());

                    List<String> spamList = SpamCollection.checkSpam(checkMail, keyword);
//                   UserPO userPO=userRepository.findById(id);
                    System.out.println("检测垃圾邮件的数目为"+spamList.size());
                    if (spamList.size() != 0) {
                        Spam spam = new Spam();
                        BeanUtils.copyProperties(mail2, spam);
                        spam.setId(null);
                        spamService.addSpam(spam);
                        mailService.deleteMail(mail2);
                    }
                }
            }
            String toUser = user1.getUsername() + "@" + host;
            List<Mail> mailList1 = mailService.findByToUser(toUser);
            System.out.println("从数据库中查询的结果条目数" + mailList1.size());
            model.addAttribute("mailList", mailList1);
        }
        return "/mail/receive";
    }

    @RequestMapping("/showMail")
    public String showMail(@RequestParam("id") Integer id, Model model) {
        System.out.println("id is:" + id);
        Mail mail = mailService.getOne(id);
        if (mail.getAttachName() != null) {
            String[] arr = mail.getAttachName().split(",");
            HashMap<String, String> hashMap = new HashMap<>();
            for (String s : arr) {
                String[] arr1 = s.split(".");
//                System.out.println(s.substring(s.lastIndexOf(".") + 1));
                if(!s.equals("")){
                    hashMap.put(s, s.substring(s.lastIndexOf(".") + 1));
                }
//            list.add(s);
            }
            model.addAttribute("attach", hashMap);
        }

        model.addAttribute("mail", mail);
        model.addAttribute("mail_flag", "show");
        return "/mail/show";
    }

    @RequestMapping("/showSentMail")
    public String showSentMail(@RequestParam("id") Integer id, Model model) {
        System.out.println("id is:" + id);
        SendMail sendMail = sendMailService.getOne(id);
        if (sendMail.getAttachPath() != null) {
            String[] arr = sendMail.getAttachPath().split(",");
            HashMap<String, String> hashMap = new HashMap<>();
            for (String s : arr) {
                String[] arr1 = s.split(".");
//                System.out.println(s.substring(s.lastIndexOf(".") + 1));
                hashMap.put(s, s.substring(s.lastIndexOf(".") + 1));
//            list.add(s);
            }
            model.addAttribute("attach", hashMap);
        }

        model.addAttribute("mail", sendMail);
        model.addAttribute("mail_flag", "showSent");
        return "/mail/show";
    }

    @RequestMapping("/sendMailForm")
    public String sendMailForm(Model model, HttpServletResponse response, HttpServletRequest request) {

        return "/mail/mail-form";
    }
    @RequestMapping("/replyForm")
    public String replyForm(@RequestParam("id")Integer id,Model model){
        Mail mail = mailService.getOne(id);
        mail.setSubject("Re:"+mail.getSubject());
        model.addAttribute("mail",mail);


        return "/mail/reply-mail";
    }

    @PostMapping(value = "/sendMail")
    @ResponseBody
    public String sendMail(String toUser, String subject, String content, HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(value = "attachFile", required = false) MultipartFile files) throws Exception {
//        JSON.toJSONString(sendMail);
        SendMail sendMail = new SendMail();
        sendMail.setToUser(toUser);
        sendMail.setSubject(subject);
        sendMail.setContent(content);
        System.out.println("这是发送邮件网页界面上的测试");
        String[] sendArr = new String[7];
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        String password = (String) session.getAttribute("password");
        sendArr[0] = user1.getUsername() + "@" + host;
        sendArr[1] = password;
        sendArr[2] = host;
        sendArr[3] = sendMail.getToUser();
        sendArr[4] = sendMail.getSubject();
        sendArr[6] = sendMail.getContent();
        if (!files.isEmpty()) {
            String fileName = files.getOriginalFilename();
            String filePath = ClassUtils.getDefaultClassLoader().getResource("static/attachfile/").getPath();
            File dest = new File(filePath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            try {
                FileUtilss.uploadFile(files.getBytes(), filePath, fileName);
            } catch (Exception e) {

            }
            sendArr[5] = filePath + fileName;
//            sendMail.setAttachName(fileName);
            sendMail.setAttachPath(fileName);
        } else {
            sendArr[5] = null;
        }
        Boolean result = SendMailUt.sendMailUtil(sendArr);
        sendMail.setFromUser(sendArr[0]);
        sendMail.setSmtpServer(sendArr[2]);
//        sendMail.setHasRead(false);
        sendMail.setSentDate(new Date());
        if (result == true) {
            sendMailService.addMail(sendMail);
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping("/sendSuccess")
    public String sendSuccess() {
        return "/mail/send-success";
    }

    @RequestMapping("/sendFail")
    public String sendFail() {
        return "/mail/send-fail";
    }

    @RequestMapping("/sentMail")
    public String sentMail(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        System.out.println(user1.getUsername() + "@" + host);
        List<SendMail> sendMailList = sendMailService.findByFromUser(user1.getUsername() + "@" + host);
        model.addAttribute("sentMailList", sendMailList);
        return "/mail/sent-mail";
    }

    @PostMapping("/moveToMail")
    @ResponseBody
    public String moveToMail(@RequestParam("mailIdList[]") Integer[] mailIdList,
                             @RequestParam("fromType") String fromType,
                             @RequestParam("toType") String toType, Model model) {
        System.out.println("move to mail "+fromType+":"+toType);
        List<Object> mailList = new ArrayList<>();
        if(fromType.equals("normal")){
           mailList = new ArrayList<>();
            for (Integer mailId : mailIdList) {
                Mail mail = mailService.getOne(mailId);
                mailList.add(mail);
                mailService.deleteMail(mail);
            }
        }else if(fromType.equals("spam")){
            mailList = new ArrayList<>();
            for (Integer mailId : mailIdList) {
                Spam spam = spamService.getOne(mailId);
                mailList.add(spam);
                spamService.deleteSpam(spam);
            }
        }else if(fromType.equals("delete")){
            mailList = new ArrayList<>();
            for (Integer mailId : mailIdList) {
                DeleteMail deleteMail= deleteMailService.getOne(mailId);
                mailList.add(deleteMail);
                deleteMailService.deleteDeleteMail(deleteMail);
            }
        }


        for (Object o: mailList) {
            if (toType.equals("delete")) {
                DeleteMail deleteMail = new DeleteMail();

                BeanUtils.copyProperties(o, deleteMail);
                deleteMail.setId(null);
                deleteMailService.addDeleteMail(deleteMail);
//                mailService.deleteMail(mail);
            } else if (toType.equals("spam")) {
                Spam spam = new Spam();

                BeanUtils.copyProperties(o, spam);
                spam.setId(null);
                spamService.addSpam(spam);
//                mailService.deleteMail(mail);

            } else if (toType.equals("normal")) {
                Mail mail= new Mail();
                BeanUtils.copyProperties(o, mail);
                mail.setId(null);
                mailService.addMail(mail);
//                mailService.deleteMail(mail);
            }
        }
        return fromType;
    }

    @PostMapping("/deleteMail")
    @ResponseBody
    public String deleteMail(@RequestParam("mailIdList[]") Integer[] mailIdList,
                             @RequestParam("fromType")String fromType, RedirectAttributes attributes) {
        System.out.println("++");
        for (Integer mailId : mailIdList) {
            System.out.println("选中删除的邮件id为：" + mailId);
            Object o = new Object();
            if(fromType.equals("normal")){
                Mail mail = mailService.getOne(mailId);
                mailService.deleteMail(mail);
                o = (Object) mail;
            }else if(fromType.equals("spam")){
                Spam spam = spamService.getOne(mailId);
                spamService.deleteSpam(spam);
                o = (Object)spam;
            }


            DeleteMail deleteMail = new DeleteMail();
            BeanUtils.copyProperties(o, deleteMail);
            deleteMail.setId(null);

            deleteMailService.addDeleteMail(deleteMail);
        }
        return fromType;
    }


}

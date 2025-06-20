package com.jit.mail.controller;

import com.jit.mail.Utils.SpamCollection;
import com.jit.mail.domain.ChosenSkin;
import com.jit.mail.domain.KeywordCount;
import com.jit.mail.domain.Skin;
import com.jit.mail.domain.User;
import com.jit.mail.service.ChosenSkinService;
import com.jit.mail.service.SkinService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Value("${test.host}")
    public String host;
    @Autowired
    public ChosenSkinService chosenSkinService;
    @Autowired
    public SkinService skinService;

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("host",host);
        return "/login";
    }

    @RequestMapping("/mailIndex")
    public String mailIndex(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//        model.addAttribute("host",host);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        session.setAttribute("host",host);
        ChosenSkin chosenSkin = chosenSkinService.findByUsername(user1.getUsername());
        if (chosenSkin != null) {
            Skin skin = skinService.getOne(chosenSkin.getSkinNo());
            session.setAttribute("hasSkin", skin);
        }


//
        File file = new File("src/main/resources/static/TrainingSet/test");

//        Map<String, KeywordCount> collection = new HashMap<>();
        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.length() == 0) {
            Map<String, KeywordCount> collection = SpamCollection.getSpamCollection();
            System.out.println("文件为空！");
            FileOutputStream out;
            try {
                out = new FileOutputStream(file);
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(collection);
                objOut.flush();
                objOut.close();
                System.out.println("write object success!");
            } catch (IOException e) {
                System.out.println("write object failed");
                e.printStackTrace();
            }
            session.setAttribute("keywordMap", collection);
        } else {
            Map<String, KeywordCount> collection = null;
            FileInputStream in;
            try {
                in = new FileInputStream(file);
                ObjectInputStream objIn = new ObjectInputStream(in);
                collection = (Map<String, KeywordCount>) objIn.readObject();
                objIn.close();
                System.out.println("read object success!");
            } catch (IOException e) {
                System.out.println("read object failed");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            session.setAttribute("keywordMap", collection);
        }


        return "/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @RequestMapping("/receive")
    public String receive() {
        return "/mail/receive";
    }
}

package com.jit.mail.controller;

import com.jit.mail.Utils.DigestUtil;
import com.jit.mail.domain.ChosenSkin;
import com.jit.mail.domain.User;
import com.jit.mail.service.ChosenSkinService;
import com.jit.mail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    public ChosenSkinService chosenSkinService;

    @Value("${test.host}")
    public String host;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("host",host);
        return "/register";
    }

    @PostMapping("/isExistUsername")
    @ResponseBody
    public String isExistUsername(@RequestParam("username") String username) {
        System.out.println(username + "&&&&&&&&&&&&&&&");
        User user = userService.findByUsername(username);
        if (user == null) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("register")
    @ResponseBody
    public String addUser(@Valid User user) {
        System.out.println(user.getUsername());
//        user.setType("0");
        String[] password = new String[1];
        password[0] = user.getPwdHash();
        System.out.println(user.getPwdHash());
        user.setPwdHash(DigestUtil.getPassword(password));
        System.out.println(user.getPwdHash());
        user.setPwdAlgorithm("SHA");
        user.setUseForwarding(0);
        user.setUseAlias(0);
        User user1 = userService.addUser(user);
        //添加默认皮肤
        ChosenSkin chosenSkin = new ChosenSkin();
        chosenSkin.setSkinNo(1);
        chosenSkin.setUsername(user.getUsername());
        chosenSkin.setCreateDate(new Date());
        chosenSkin.setModifyDate(new Date());
        ChosenSkin chosenSkin1 = chosenSkinService.addChosenSkin(chosenSkin);
        if (user1 != null) {
            return "success";
        } else {
            return "error";
        }

    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid User user, Model model, HttpServletRequest request, HttpServletResponse response) {

        if (user == null) {
            System.out.println("用户为空");
            return "error";
        } else {
            System.out.println("用户的名字为:" + user.getUsername());
            User user1 = userService.findByUsername(user.getUsername());
            if (user1 == null) {
                System.out.println("查询到的用户为空");
                return "error";
            } else {
                String[] password = new String[1];
                password[0] = user.getPwdHash();

                if (user1.getPwdHash().equals(DigestUtil.getPassword(password))) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html;charset=UTF-8");
                    //使用request对象的getSession()获取session，如果session不存在则创建一个
                    HttpSession session = request.getSession();
                    //将数据存储到session中
                    session.setAttribute("user", user1);
                    session.setAttribute("password", user.getPwdHash());
//                    System.out.println(user1.getUsername());
//                    System.out.println("数据库中的密码是:"+user1.getPwdHash());
//                    System.out.println("计算出来的密码是:"+DigestUtil.getPassword(password));
                    return "success";
                } else {
                    return "error";
                }


            }

        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        HttpSession session1 = request.getSession();
        session1.invalidate();
        return "redirect:/index";
    }
}

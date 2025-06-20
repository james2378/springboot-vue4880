package com.jit.mail.controller;

import com.jit.mail.domain.User;
import com.jit.mail.domain.UserRelation;
import com.jit.mail.service.UserRelationService;
import com.jit.mail.service.UserService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userRelation")
public class UserRelationController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRelationService userRelationService;
    @Value("${test.host}")
    public String host;

    @RequestMapping("/listUserRelation")
    public String listUserRelation(Model model, HttpServletResponse response, HttpServletRequest request) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");

        List<UserRelation> userRelationList = userRelationService.findByUser1(user1.getUsername());
        List<User> userList = new ArrayList<>();
        for (UserRelation userRelation : userRelationList) {
            User user = userService.findByUsername(userRelation.getUser2());
            userList.add(user);
        }
        model.addAttribute("userList", userList);
        List<User> userList1 = userService.getAll();
        model.addAttribute("userAll", userList1);
        return "/user/list-relation";
    }

    @RequestMapping("/addUserRelation")
    public String addUserRelation(Model model, @RequestParam("user-add") String userAdd, HttpServletResponse response, HttpServletRequest request, RedirectAttributes attributes) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        User user2 = userService.findByUsername(userAdd);
        UserRelation userRelation = new UserRelation();
        userRelation.setUser1(user1.getUsername());
        userRelation.setUser2(user2.getUsername());
        userRelation.setCreateDate(new Date());

        UserRelation userRelation1 = userRelationService.findByUser1AndUser2(user1.getUsername(), user2.getUsername());
        if (userRelation1 == null) {
            userRelationService.addUserRelation(userRelation);
        } else {
            System.out.println("已经添加过好友了");
        }

        return "redirect:/userRelation/listUserRelation";
    }

    @RequestMapping("/deleteUserRelation")
    public String deleteUserRelation(Model model, @RequestParam("user-delete") String userDelete, HttpServletResponse response, HttpServletRequest request, RedirectAttributes attributes) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");

        UserRelation userRelation = userRelationService.findByUser1AndUser2(user1.getUsername(), userDelete);
//        UserRelation userRelation = userRelationService.getOne(user);
        userRelationService.deleteUserRelation(userRelation);
        return "redirect:/userRelation/listUserRelation";
    }
}

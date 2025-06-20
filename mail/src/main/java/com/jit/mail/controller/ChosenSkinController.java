package com.jit.mail.controller;

import com.jit.mail.domain.ChosenSkin;
import com.jit.mail.domain.Skin;
import com.jit.mail.domain.User;
import com.jit.mail.service.ChosenSkinService;
import com.jit.mail.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/chosenSkin")
public class ChosenSkinController {
    @Autowired
    public ChosenSkinService chosenSkinService;
    @Autowired
    public SkinService skinService;

    @RequestMapping("addChosenSkin")
    public String addChosenSkin(@RequestParam("skinNo") Integer skinNo, Model model, RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) {
        Skin skin = skinService.getOne(skinNo);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("hasSkin", skin);
        User user1 = (User) session.getAttribute("user");

        ChosenSkin chosenSkin = new ChosenSkin();
        chosenSkin.setUsername(user1.getUsername());
        chosenSkin.setSkinNo(skinNo);
        chosenSkin.setCreateDate(new Date());
        chosenSkin.setModifyDate(new Date());
        chosenSkinService.addChosenSkin(chosenSkin);

        return "redirect:/skin/listSkin";
    }

    @RequestMapping("/updateChosenSkin")
    public String updateChosenSkin(@RequestParam("skinNo") Integer skinNo, Model model, RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) {
        Skin skin = skinService.getOne(skinNo);
        ChosenSkin chosenSkin1 = chosenSkinService.findByUsername("test");
        System.out.println(skin.getId() + skin.getSkinName() + "+++++++++++++++++++++++++++");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("hasSkin", skin);
        User user1 = (User) session.getAttribute("user");

        ChosenSkin chosenSkin = chosenSkinService.findByUsername(user1.getUsername());
        chosenSkin.setSkinNo(skinNo);
        chosenSkin.setModifyDate(new Date());
        chosenSkinService.addChosenSkin(chosenSkin);

        return "redirect:/skin/listSkin";
    }


}

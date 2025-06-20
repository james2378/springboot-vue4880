package com.jit.mail.controller;

import com.jit.mail.Utils.FileUtilss;
import com.jit.mail.domain.ChosenSkin;
import com.jit.mail.domain.Skin;
import com.jit.mail.domain.User;
import com.jit.mail.service.ChosenSkinService;
import com.jit.mail.service.SkinService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.net.FileNameMap;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/skin")
public class SkinController {
    @Autowired
    public SkinService skinService;
    @Autowired
    public ChosenSkinService chosenSkinService;

    @PostMapping("/addSkin")
    public String addSkin(Model model, @Valid Skin skin,
                          @RequestParam(value = "header", required = true) MultipartFile header,
                          @RequestParam(value = "showLogo", required = true) MultipartFile showLogo,
                          @RequestParam(value = "headerBgLeft", required = true) MultipartFile headerBgLeft,
                          @RequestParam(value = "headerBgRight", required = true) MultipartFile headerBgRight,
                          @RequestParam(value = "menuBgx", required = false) MultipartFile menuBgx,
                          @RequestParam(value = "bodyLeft", required = true) MultipartFile bodyLeft,
                          @RequestParam(value = "bodyLeftBottom", required = true) MultipartFile bodyLeftBottom,
                          @RequestParam(value = "logo", required = false) MultipartFile logo) {

        String headerFileName = header.getOriginalFilename();
        String showLogoFileName = showLogo.getOriginalFilename();
        String headerBgLeftFileName = headerBgLeft.getOriginalFilename();
        String heaerBgRightFileName = headerBgRight.getOriginalFilename();
        String bodyLeftFileName = bodyLeft.getOriginalFilename();
        String bodyLeftBottomFileName = bodyLeftBottom.getOriginalFilename();
//            Skin skin = new Skin();
//            skin.setSkinName(skinName);

        String filePath = ClassUtils.getDefaultClassLoader().getResource("static/skinfile/").getPath();
        File dest = new File(filePath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try {
            FileUtilss.uploadFile(header.getBytes(), filePath, headerFileName);
            skin.setSkinHeader(headerFileName);
            FileUtilss.uploadFile(showLogo.getBytes(), filePath, showLogoFileName);
            skin.setSkinShowLogo(showLogoFileName);
            FileUtilss.uploadFile(headerBgLeft.getBytes(), filePath, headerBgLeftFileName);
            skin.setSkinHeaderBgLeft(headerBgLeftFileName);
            FileUtilss.uploadFile(headerBgRight.getBytes(), filePath, heaerBgRightFileName);
            skin.setSkinHeaderBgRight(heaerBgRightFileName);

            if (!menuBgx.isEmpty()) {
                String menuBgxFileName = menuBgx.getOriginalFilename();
                FileUtilss.uploadFile(menuBgx.getBytes(), filePath, menuBgxFileName);
                skin.setSkinMenuBgx(menuBgxFileName);
            }
            FileUtilss.uploadFile(bodyLeft.getBytes(), filePath, bodyLeftFileName);
            skin.setSkinBodyLeft(bodyLeftFileName);

            FileUtilss.uploadFile(bodyLeftBottom.getBytes(), filePath, bodyLeftBottomFileName);
            skin.setSkinBodyLeftBottom(bodyLeftBottomFileName);
            if (!logo.isEmpty()) {
                String logoFileName = logo.getOriginalFilename();
                FileUtilss.uploadFile(logo.getBytes(), filePath, logoFileName);
                skin.setMailLogo(logoFileName);
            }
        } catch (Exception e) {

        }
        skin.setCreateDate(new Date());
        skin.setModifyDate(new Date());
        Skin skin1 = skinService.addSkin(skin);
        if (skin1 != null) {
            return "redirect:/skin/listSkin";
        } else {
            return null;
        }
    }

    @RequestMapping("/delteSkin")
    public String deleteSkin(@RequestParam("id") Integer id, Model model, RedirectAttributes attributes) {
        Skin skin = skinService.getOne(id);
        skinService.deleteSkin(skin);

        return "redirect:/skin/listSkin";
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam("id") Integer id, Model model) {
        Skin skin = skinService.getOne(id);
        model.addAttribute("skin", skin);
        return "null";
    }

    @RequestMapping("/listSkin")
    public String listSkin(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user1 = (User) session.getAttribute("user");
        ChosenSkin chosenSkin = chosenSkinService.findByUsername(user1.getUsername());
        if (chosenSkin != null) {
            model.addAttribute("chosenSkinFlag", "exist");
        } else {
            model.addAttribute("chosenSkinFlag", "no");
        }
        List<Skin> skinList = skinService.findAll();
        model.addAttribute("skinList", skinList);
        return "/skin/list-skin";
    }
}

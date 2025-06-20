<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title></title>
<#--<script type="text/javascript" src="${base}/js/jquery.min.js"></script>-->
    <script src="${base}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/js/Site.js"></script>
    <script type="text/javascript" src="${base}/js/login.js"></script>
    <link href="${base}/css/Site.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/css/common.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var header = document.getElementById("header");
            var headerBgLeft = document.getElementById("header_bg_left");
            var headerBgRight = document.getElementById("header_bg_right");
            var bodyLeft = document.getElementById("body_left");
            var bodyLeftBottom = document.getElementById("body_left_bottom");
            if ('${(hasSkin.skinHeader)!}' != "") {
                header.style.background = "url(${base}/skinfile/${(hasSkin.skinHeader)!})";
            }
            if ('${(hasSkin.skinHeaderBgLeft)!}' != "") {
                headerBgLeft.style.background = "url(${base}/skinfile/${(hasSkin.skinHeaderBgLeft)!})";
            }
            if ('${(hasSkin.skinHeaderBgRight)!}' != "") {
                headerBgRight.style.background = "url(${base}/skinfile/${(hasSkin.skinHeaderBgRight)!})";
            }
            if ('${(hasSkin.skinBodyLeft)!}' != "") {
                bodyLeft.style.background = "url(${base}/skinfile/${(hasSkin.skinBodyLeft)!})";
            }
            if ('${(hasSkin.skinBodyLeftBottom)!}' != "") {
                bodyLeftBottom.src = "${base}/skinfile/${(hasSkin.skinBodyLeftBottom)!}";
            }


            var subject = '${(mail.subject?js_string)!}';
            setBodyHeight();
            setCurrentHeaderMenu(subject);
            sessionStorage.setItem("1", 1);
            setBgHover();
            // addHeaderMenuClick();
        });

        $(window).resize(function () {
            setBodyHeight();
        });
    </script>
</head>
<body>
<div id="header">
    <div class="header_bg_left" id="header_bg_left"></div>
    <div class="header_bg_right" id="header_bg_right"></div>
    <div class="header_logo">
        <img src="${base}/img/mail.png" alt="" style="width: 136px;height: 62px;margin-top: -20px;"/>

    </div>
    <div id="header_login_info">
        <ul class="top_menu_ul">
            <li class="link user" style="color: gray">
            <#if "${(user.username)!}"=="">
                <script>
                    location.href = "/index";
                </script>
            <#else>
                ${(user.username)!}@${(host)!} (0)&nbsp;&nbsp;您好！
            </#if>
            </li>
            <li class="line">|</li>
            <li class="link">设置</li>
            <li class="line">|</li>
            <li class="link"><a href="/skin/listSkin">换肤</a></li>
            <li class="line">|</li>
            <li class="link">帮助</li>
            <li class="line">|</li>
            <li class="link"><a href="/user/logout" style="color: black">退出</a></li>
        </ul>
    </div>
    <div id="header_menu">
        <ul id="menu-ul" class="top_menu_ul">
            <a class="main_menu" href="/mailIndex">
                <li class="common" id="home">首页</li>
            </a>
            <a class="main_menu" href="/userRelation/listUserRelation">
                <li class="common" id="userRelation">通讯录</li>
            </a>
            <a class="main_menu" href="/mail/sentMail">
                <li class="common" id="sentMail">已发送</li>
            </a>
            <a class="main_menu" href="/mail/receiveMail">
                <li class="common" id="receive">收件箱</li>
            </a>
            <a class="main_menu" href="/spam/listSpamMail">
                <li class="common" id="spam">垃圾箱</li>
            </a>
            <a class="main_menu" href="/deleteMail/listDeleteMail">
                <li class="common" id="deleteMail">已删除的</li>
            </a>
        </ul>
    </div>
    <div id="header_line"></div>
</div>

<div id="body" style="width: 100%;">
    <div id="body_left">
        <div class="menu">
            <img src="${base}/img/menu.png" usemap="#mailmap" alt=""/>
            <map name="mailmap" id="mailmap">
                <area shape="rect" coords="0,0,89,31" href="/mail/receiveMail" alt="receiveMail"/>
                <area shape="rect" coords="89,0,177,31" href="/mail/sendMailForm" alt="sentMail"/>
            </map>
        </div>
        <div class="menu_item">
            <ul id="menu_item_home">
                <li id="left_menu_receive" class="common"><a href="/mail/receiveMail">收件箱</a></li>
                <li><a href="">草稿箱</a></li>
                <li id="left_menu_sent" class="common"><a href="/mail/sentMail">已发送</a></li>
            <#--<li>文件中心</li>-->
            <#--<li>未读邮件</li>-->
            <#--<li>已读邮件</li>-->
                <li id="left_menu_spam" class="common"><a href="/spam/listSpamMail">垃圾邮件</a></li>
                <li id="left_menu_delete_mail" class="common"><a href="/deleteMail/listDeleteMail">删除邮件</a></li>
            </ul>
        </div>
        <div class="body_left_bottom">
            <img id="body_left_bottom" src="${base}/img/left_bottom.jpg" alt=""/>
        </div>
    </div>

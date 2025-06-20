<#include "base/header.ftl">
<link href="${base}/css/home.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<#--<link rel="stylesheet" href="${base}/css/bootstrap.min.css">-->

    <div id="body_content">
        <div class="content">
            <div class="up">
                <div class="headimg">
                    <a href="">
                        <img src="${base}/img/head.gif" width="100" height="100" alt="">
                    </a>
                </div>
                <div class="info">
                    <span>${(user.username)!}</span>
                    <span>,你好</span>
                </div>
                <div class="menu1">
                    <ul class="mailInfo-list">
                        <li class="mail_component">
                            <a href="/mail/sentMail">
                                <div class="logo"><i class="fa fa-envelope-o fa-2x"></i></div>
                                <div class="title">已发邮件</div>
                            </a>
                        </li>
                        <li class="mail_component">
                            <a href="/mail/receiveMail">
                                <div class="logo"><i class="fa fa-heart-o fa-2x"></i></div>
                                <div class="title">收到邮件</div>
                            </a>
                        </li>
                        <li class="mail_component">
                            <a href="/userRelation/listUserRelation">
                                <div class="logo"><i class="fa fa-hand-grab-o fa-2x"></i></div>
                                <div class="title">我的好友</div>
                            </a>
                        </li>
                        <li class="mail_component">
                            <a href="/spam/listSpamMail">
                                <div class="logo"><i class="fa fa-clock-o fa-2x"></i></div>
                                <div class="title">垃圾邮件</div>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
            <div class="middle">
                <img id="i" src="${base}/img/home.gif"/>
                <p id="p">
                    种一棵树最好的时间是十年前
                    <br>
                    其次是现在
                </p>
            </div>
        </div>
        <div class="advertisement">
            <div style="background-color: rgb(226,240,227); width: 100%; text-align:center;">
                <img src="${base}/img/140218_yixin_780x96.jpg" alt=""/>
            </div>
        </div>

    </div>
</div>

<#include "base/footer.ftl">
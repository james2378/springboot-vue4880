<html>
<head>
    <link href="${base}/css/Site.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/css/home.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="${base}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base}/css/bootstrap.min.css">
</head>
<body>
<div id="body_content">
<#--<div class="header">-->
<#--<div class="button">删除</div>-->
<#--<div class="button">移动到</div>-->
<#--<div class="button">导出</div>-->
<#--<div class="button">标记为</div>-->
<#--</div>-->
    <div class="content">
        <div class="up">
            <div class="headimg">
                <a href="">
                    <img src="${base}/img/head.gif" width="100" height="100" alt="">
                </a>
            </div>
            <div class="info">
                <#if "${(user.username)!}"=="">
                    <script>
                        location.href = "/index";
                    </script>
                <#else>
                <span>${(user.username)!}</span>
            <span>,你好</span>
                </#if>

            </div>
            <div class="menu">
                <ul class="mailInfo-list">
                    <li id="mail_component">
                        <div class="logo"><i class="fa fa-envelope-o fa-2x"></i></div>
                        <div class="title">未读邮件</div>
                    </li>
                    <li id="mail_component">
                        <div class="logo"><i class="fa fa-heart-o fa-2x"></i></div>
                        <div class="title">标星邮件</div>
                    </li>
                    <li id="mail_component">
                        <div class="logo"><i class="fa fa-hand-grab-o fa-2x"></i></div>
                        <div class="title">协作邮件</div>
                    </li>
                    <li id="mail_component">
                        <div class="logo"><i class="fa fa-clock-o fa-2x"></i></div>
                        <div class="title">代办邮件</div>
                    </li>

                </ul>
            </div>
        </div>
        <div class="middle">
            <img id="i" src="/img/home.gif"/>
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
</body>
</html>
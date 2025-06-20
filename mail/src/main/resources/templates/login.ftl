<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Letter - Simple Sign Up Form</title>
    <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">  Google web font "Open Sans" -->
    <link rel="stylesheet" href="${base}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base}/css/bootstrap.min.css">

    <link rel="stylesheet" href="${base}/css/demo.css"/>
    <link rel="stylesheet" href="${base}/css/templatemo-style.css">

    <script type="text/javascript" src="${base}/js/modernizr.custom.86080.js"></script>
    <script type="text/javascript" src="${base}/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="${base}/js/login.js"></script>

</head>

<body>

<div id="particles-js"></div>

<ul class="cb-slideshow">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
</ul>

<div class="container-fluid">
    <div class="row cb-slideshow-text-container ">
        <div class="tm-content col-xl-6 col-sm-8 col-xs-8 ml-auto section">
            <header class="mb-5"><h1>Mail</h1></header>
            <P class="mb-5">自定义创意邮箱，让彼此的距离更近</P>

            <form id="loginWindowForm" method="post" class="subscribe-form" enctype="multipart/form-data">
                <div class="row form-section">

                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input name="username" id="loginWindowMemberUsername" type="text" class="form-control"
                               id="contact_email" placeholder="Your Email..." required/>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-4">
                        <h2 style="margin-top: 8px;"> @${(host)!}</h2>
                    </div>
                    <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top: 10px;">
                        <input name="pwdHash" id="loginWindowMemberPassword" type="password" class="form-control"
                               id="contact_email" placeholder="Your Password..." required/>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-5" style="margin-top: 10px;">
                        <button type="button" onclick="memberLogin();" class="tm-btn-subscribe">登陆</button>
                        <p>没有邮箱？&nbsp;&nbsp;<a href="/user/register">创建账号</a></p>
                    </div>

                </div>
            </form>

            <div class="tm-social-icons-container text-xs-center">
                <a href="#" class="tm-social-link"><i class="fa fa-facebook"></i></a>
                <a href="#" class="tm-social-link"><i class="fa fa-google-plus"></i></a>
                <a href="#" class="tm-social-link"><i class="fa fa-twitter"></i></a>
                <a href="#" class="tm-social-link"><i class="fa fa-linkedin"></i></a>
            </div>

        </div>
    </div>
    <div class="footer-link">
        <p>Copyright © 2018 Your Company

            - Design: <a rel="nofollow" href="#" target="_parent">jit.edu.cn software engineering</a></p>
    <#--<p>More Templates</p>-->
    </div>
</div>
</body>

<script type="text/javascript" src="js/particles.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</html>
//扩展jquery获取url参数方法
var idd;
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        // alert(unescape(r[2]));

        if (r != null) {
            var id = unescape(r[2]);
            if (id == "home") {
                // document.getElementById("middle_content").src="/home";
            } else if (id == "receive") {
                // $('#middle_content').attr('src', '/mail/receiveMail');
                // document.getElementById("middle_content").src="http://www.baidu.com";
            }
            return unescape(r[2]);
        }
        return id;
    }
})(jQuery);

function setBodyHeight() {
    $("#body").css("height", document.documentElement.clientHeight - 88);
    // $("#body").css("width", document.documentElement.clientWidth);
    // console.log($("#body").width());
    // console.log($("#body_left").width());
    $("#body_content").css("width", $("#body").width() - $("#body_left").width() - 30);
}

function setCurrentHeaderMenu(subject) {
    var url = window.location.href;
    var array = url.split('/');
    var ar = array[array.length - 1];
    idd = location.search.slice(1).split('&')[0].split('=')[1];
    // var subject =${mail.subject?js_string};
    // console.log(subject);
    if (subject.length > 5) {
        subject = subject.substr(0, 5) + "...";
    } else {
        subject = subject + "...";
    }
    var obj = $("#home");
    ;
    if (ar.indexOf("Index") != -1) {
        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#home");
        // $("#home").removeClass("common");

    } else if (ar.indexOf("listUserRelation") != -1) {
        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#userRelation");
        // obj1 = $("#left_menu_receive");
        // obj1.removeClass("common");
        // obj1.addClass("select");
    } else if (ar.indexOf("listSpam") != -1) {
        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#spam");
        obj1 = $("#left_menu_spam");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("listDelete") != -1) {
        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#deleteMail");
        obj1 = $("#left_menu_delete_mail");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("receive") != -1) {
        // obj = $("#receive");
        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#receive");
        obj1 = $("#left_menu_receive");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("sentMail") != -1) {

        var show = sessionStorage.getItem('show');
        if (show != null) {

            var li1 = document.createElement("showMail");
            li1.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#sentMail");

        obj1 = $("#left_menu_sent");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("showMail") != -1) {
        var li = document.createElement("showMail");
        console.log(idd);
        var content = `<div class="show_receive_mail" style="margin-top: 0px;height:0px;"><a style="position: relative" class="main_menu" href="/mail/showMail?id=${idd}"><li class="common" id="showMail">${subject}</li></a>
<a href="javascript:void(0)" onclick="removeSession('show')" style="position:relative;margin-left:-20px;margin-top:10px;z-index: 999;"><i class="fa fa-close" style="color: black;"></i></a></div>`;
        li.innerHTML = content;
        var s = document.getElementById("menu-ul");
        sessionStorage.setItem("show", content);
        s.appendChild(li);


        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {
            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }

        obj = $("#showMail");
        obj1 = $("#left_menu_receive");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("showSentMail") != -1) {
        var li = document.createElement("showSentMail");
        console.log(idd);
        var content = `<div class="show_sent_mail" style="margin-top: 0px;height: 0px;"><a style="position: relative" class="main_menu" href="/mail/showSentMail?id=${idd}"><li class="common" id="showSentMail">${subject}</li></a>
<a href="javascript:void(0)" onclick="removeSession('showSent')" style="position:relative;margin-left:-20px;margin-top:10px;z-index: 999;"><i class="fa fa-close" style="color: black;"></i></a></div>`;
        li.innerHTML = content;
        var s = document.getElementById("menu-ul");
        sessionStorage.setItem("showSent", content);
        s.appendChild(li);

        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }


        obj = $("#showSentMail");
        obj1 = $("#left_menu_sent");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("showSpamMail") != -1) {
        var li = document.createElement("showSpamMail");
        console.log(idd);
        var content = `<div class="show_spam_mail" style="margin-top: 0px;height: 0px;"><a style="position: relative" class="main_menu" href="/spam/showSpamMail?id=${idd}"><li class="common" id="showSpamMail">${subject}</li></a>
<a href="javascript:void(0)" onclick="removeSession('showSpam')" style="position:relative;margin-left:-20px;margin-top:10px;z-index: 999;"><i class="fa fa-close" style="color: black;"></i></a></div>`;
        li.innerHTML = content;
        var s = document.getElementById("menu-ul");
        sessionStorage.setItem("showSpam", content);
        s.appendChild(li);

        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }

        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showDeleteMail = sessionStorage.getItem('showDeleteMail');
        if (showDeleteMail != null) {

            var li1 = document.createElement("showDeleteMail");
            li1.innerHTML = showDeleteMail;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }


        obj = $("#showSpamMail");
        obj1 = $("#left_menu_spam");
        obj1.removeClass("common");
        obj1.addClass("select");
    } else if (ar.indexOf("showDelteMail") != -1) {
        var li = document.createElement("showDelteMail");
        console.log(idd);
        var content = `<div class="show_delete_mail" style="margin-top: 0px;height: 0px;"><a style="position: relative" class="main_menu" href="/deleteMail/showDeleteMail?id=${idd}"><li class="common" id="showDelteMail">${subject}</li></a>
<a href="javascript:void(0)" onclick="removeSession('showDeleteMail')" style="position:relative;margin-left:-20px;margin-top:10px;z-index: 999;"><i class="fa fa-close" style="color: black;"></i></a></div>`;
        li.innerHTML = content;
        var s = document.getElementById("menu-ul");
        sessionStorage.setItem("showDeleteMail", content);
        s.appendChild(li);

        var show = sessionStorage.getItem('show');
        if (show != null) {
            var li = document.createElement("showMail");
            li.innerHTML = show;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }

        var showSent = sessionStorage.getItem('showSent');
        if (showSent != null) {

            var li1 = document.createElement("showSentMail");
            li1.innerHTML = showSent;
            var s = document.getElementById("menu-ul");
            s.appendChild(li1);
        }
        var showSpam = sessionStorage.getItem('showSpam');
        if (showSpam != null) {
            var li = document.createElement("showSpamMail");
            li.innerHTML = showSpam;
            var s = document.getElementById("menu-ul");
            s.appendChild(li);
        }

        obj = $("#showDeleteMail");
        obj1 = $("#left_menu_delete_mail");
        obj1.removeClass("common");
        obj1.addClass("select");
    }
    // var id = $.getUrlParam('type');
    // console.log(id);
    // var obj = $("#" + id);
    // if (obj.length == 0) {
    //     obj = $("#home");
    // }
    // $("#home").removeClass("common");
    obj.removeClass("common");
    obj.addClass("select");

    // $("#body_left ul").css("display", "none");
    // $("#menu_item_" + obj.attr("id")).css("display", "block");
    // $("#menu_item_" + obj.attr("id"))
}

function removeSession(flag) {
    if (flag == "show") {
        sessionStorage.removeItem(flag);
        location.href = "/mail/receiveMail";
    } else if (flag == "sent") {
        sessionStorage.removeItem(flag);
        location.href = "/mailIndex";
    } else if (flag == "showSent") {
        sessionStorage.removeItem(flag);
        location.href = "/mail/sentMail";
    } else if (flag == "showSpam") {
        sessionStorage.removeItem(flag);
        location.href = "/spam/listSpamMail";
    } else if (flag == "showDeleteMail") {
        sessionStorage.removeItem(flag);
        location.href = "/deleteMail/listDeleteMail";
    }
    // sessionStorage.removeItem(flag);


}

function setBgHover() {
    $("#header_login_info .link").mouseover(function () {
        $(this).addClass("li_hover");
    });
    $("#header_login_info .link").mouseout(function () {
        $(this).removeClass("li_hover");
    });

    $("#header_menu .common").mouseover(function () {
        $(this).addClass("common_hover");
    });
    $("#header_menu .common").mouseout(function () {
        $(this).removeClass("common_hover");
    });

    $("#body #body_left li").mouseover(function () {
        $(this).addClass("select");
    });
    $("#body #body_left li").mouseout(function () {
        $(this).removeClass("select");
    });

    $("#body_content .button").mouseover(function () {
        $(this).addClass("button_hover");
    });
    $("#body_content .button").mouseout(function () {
        $(this).removeClass("button_hover");
    });

    $("#body_content .content .row").mouseover(function () {
        $(this).addClass("row_hover");
    });
    $("#body_content .content .row").mouseout(function () {
        $(this).removeClass("row_hover");
    });
}

// function addHeaderMenuClick() {
//     $("#header_menu li").click(function () {
//         var url = window.location.href;
//         var array = url.split('?');
//         var ar = array[0];
//         ar = ar + "?type=" + $(this).attr("id");
//         window.location.href = ar;
//
//     });
// }
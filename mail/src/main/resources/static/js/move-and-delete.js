function deleteMail(fromType) {


        var cond = document.getElementsByName("mailId");
        for (var i = 0; i < cond.length; i++) {
            if (cond[i].checked == "checked" || cond[i].checked == true)
                break;
        }
        if (i == cond.length) {
            alert("请选择一个需要删除的邮件");
            return null;
        } else {
            var msg = "您真的确定要删除吗？\n\n请确认！";
            if (confirm(msg) == true) {

                var mailIdChecked = $("input[type='checkbox']:checked").val([]);
                var mailIdList = [];
                for (var i = 0; i < mailIdChecked.length; i++) {    //循环值
                    // mailIdList[i] = mailIdChecked[i].value;
                    mailIdList.push(mailIdChecked[i].value);
                }
                // alert(fromType);
                $.ajax({
                    url: "/mail/deleteMail",
                    type: "POST",
                    data: {
                        'mailIdList': mailIdList,
                        'fromType': fromType
                    },
                    async: false,
                    dataType: "text",
                    success: function (data) {
                        if (data == "normal") {
                            alert("删除成功！");
                            location.href = "/mail/receiveMail";
                        }else if(data == "spam"){
                            alert("删除成功！");
                            location.href = "/spam/listSpamMail";
                        } else {
                            alert("删除失败");
                            location.href = "/mailIndex";
                        }
                    },
                    error: function (data) {
                        alert(data);
                    }
                });
            }else{
                return null;
            }
    }
}

function moveToSpam(fromType,toType) {
        var cond = document.getElementsByName("mailId");
        for (var i = 0; i < cond.length; i++) {
            if (cond[i].checked == "checked" || cond[i].checked == true)
                break;
        }
        if (i == cond.length) {
            alert("请选择一个需要移动的邮件");
            return null;
        } else {
            var msg = "您真的确定要移动吗？\n\n请确认！";
            if (confirm(msg) == true) {
                var mailIdChecked = $("input[type='checkbox']:checked").val([]);
                var mailIdList = [];
                for (var i = 0; i < mailIdChecked.length; i++) {    //循环值
                    // mailIdList[i] = mailIdChecked[i].value;
                    mailIdList.push(mailIdChecked[i].value);
                }
                // alert(fromType+":"+toType);
                $.ajax({
                    url: "/mail/moveToMail",
                    type: "POST",
                    data: {
                        'mailIdList': mailIdList,
                        'fromType': fromType,
                        'toType': toType
                    },
                    async: false,
                    dataType: "text",
                    success: function (data) {
                        if (data == "normal") {
                            alert("移动成功！");
                            location.href = "/mail/receiveMail";
                        } else if (data == "spam") {
                            alert("移动成功！");
                            location.href = "/spam/listSpamMail";
                        } else if (data == "delete") {
                            alert("移动成功！");
                            location.href = "/deleteMail/listDeleteMail";
                        } else {
                            alert("移动失败！");
                            location.href = "/mailIndex";
                        }
                    },
                    error: function (data) {
                        alert(data);
                    }
                });
            }else{
                return null;
            }
    }
}
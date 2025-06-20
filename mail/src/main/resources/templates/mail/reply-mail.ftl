<link rel="stylesheet" href="${base}/other/bootstrap.min.css">
<#--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">-->
<#--<link rel="stylesheet" href="${base}/other/fileinput.css">-->
<#--<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">-->
<#--<link rel="stylesheet" href="${base}/other/all.css" crossorigin="anonymous">-->
<#--<link rel="stylesheet" href="${base}/other/theme.css">-->
    <link rel="stylesheet" href="${base}/css/mail-form.css">

<#--<script src="${base}/js/jquery-1.11.1.min.js" type="text/javascript"></script>-->
<script type="text/javascript" src="${base}/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="${base}/uedit/ueditor.all.js"></script>
<script type="text/javascript" src="${base}/uedit/lang/zh-cn/zh-cn.js"></script>
<#--<script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>-->

    <script src="${base}/js/common.js" type="text/javascript"></script>
<#--<script src="${base}/other/fileinput.js" type="text/javascript"></script>-->
<#--<script src="${base}/other/theme.js" type="text/javascript"></script>-->
<#--<link rel="stylesheet" href="${base}/css/font-awesome.min.css">-->

<#include "../base/header.ftl">
<#--<link href="${base}/css/Site.css" type="text/css" rel="stylesheet" />-->
<script src="${base}/other/bootstrap.min.js" type="text/javascript"></script>
<div id="body_content">
    <div class="mail_form">
        <form id="form-send" method="post" enctype="multipart/form-data">
        <#--<div class="button">删除</div>-->
        <#--<div class="button">移动到</div>-->
        <#--<div class="button">导出</div>-->
        <#--<div class="button">标记为</div>-->
            <div class="header_page">
                <div class="button_page">
                    <button type="button" class="btn btn-success save">
                        <i class="fa fa-rocket"></i>&nbsp;&nbsp;发送
                    </button>
                    <#--<button type="button" onclick="alert('存草稿箱')" class="btn btn-default">存草稿</button>-->
                    <button type="button" onclick="javascript:history.go(-1)" class="btn btn-default">取消</button>
                </div>

                <div class="mail_header">
                    <div class="desc">
                        <ul id="mail_desc">
                            <li>
                                <div class="item_title">收件人&nbsp;:</div>
                                <div class="item_content">
                                    <input type="text" id="recipient" value="${(mail.fromUser)!}" name="toUser" size="100" style="border:none;"/>
                                </div>
                            </li>
                            <hr style="width: 100%;border:1px solid #D7DFEB;margin-top: 10px;"/>
                            <li>
                                <div class="item_title">主&nbsp;&nbsp;&nbsp;题&nbsp;:</div>
                                <div class="item_content">
                                    <input type="text" id="subject" name="subject" value="${(mail.subject)!}" size="100" style="border:none;"/>
                                </div>
                            </li>
                            <hr style="width: 100%;border:1px solid #D7DFEB;margin-top: 10px;"/>
                            <li>
                            <#--<div class="item_title">时&nbsp;&nbsp;&nbsp;间&nbsp;:</div>-->
                                <div class="item_content_attach">
                                <#--<input type="file" value="添加附件" />-->
                                    <div class="form-group">
                                        <div class="file-loading">
                                            <input id="file" name="attachFile" style="height: 20px;" class="file"
                                                   type="file" multiple data-preview-file-type="any" data-upload-url="#"
                                                   data-theme="fas">
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content">
                <script id="editor" class="editor-form" type="text/plain" name="content">
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <hr style="width:500px;color:red;">
                    ${(mail.content)!}
                </script>

            <#--${(mail.content)!}-->
            </div>
            <div class="foot_page">
                <div class="button_page">
                    <button type="button" class="btn btn-success save">
                        <i class="fa fa-rocket"></i>&nbsp;&nbsp;发送
                    </button>
                    <#--<button type="button" onclick="alert('存草稿箱')" class="btn btn-default">存草稿</button>-->
                    <button type="button" onclick="javascript:history.go(-1)" class="btn btn-default">取消</button>
                </div>
            </div>
        </form>
        <script>
            $(function () {
                //实例化编辑器
                var ue = UE.getEditor('editor');
                ue.ready(function () {
                    // ue.setHeight(100%);
                    // ue.setWidth(100%);
                });
                $('.save').on("click", function () {
                    alert("test");
                    // var recipient = document.getElementById("recipient").value;
                    // var subject = document.getElementById("subject").value;
                    // var files = document.getElementById("file").files;
                    // var content = ue.getContent();
                    var formdata = new FormData($("#form-send")[0]);
                    // formdata.append("attachFile",files[0]);
                    // formdata.append("toUser",recipient);
                    // formdata.append("subject",subject);
                    // formdata.append("content",content);
                    $.ajax({
                        url: "${base}/mail/sendMail",
                        type: "POST",
                        // contentType: "application/json; charset=UTF-8",
                        data: formdata,
                        async: false,
                        cache: false,
                        processData: false,
                        contentType: false,
                        dataType: "text",
                        success: function (data) {
                            console.log(data);
                            console.log("返回结果");
                            if (data == "success") {
                                console.log("成功");
                                location.href = "/mail/sendSuccess";
                                // alert("success");
                            } else {
                                location.href = "/mail/sendFail";
                                // alert("error");
                            }
                        }
                        // complete: function (data) {
                        //     console.log(data);
                        //     console.log("返回结果");
                        //     if(data == "success"){
                        //         console.log("成功");
                        //         // location.href="/mail/sendSuccess";
                        //         // alert("success");
                        //     }else{
                        //         // location.href="/mail/sendFail";
                        //         // alert("error");
                        //     }
                        //
                        // }
                    });
                });
            })
        </script>
    <#--<div class="attach">-->
    <#--<div class="attach_title">-->
    <#--<i class="fa fa-paperclip"></i><span>附件（1）</span>-->
    <#--</div>-->
    <#--<hr width="95%" style="border:0.7px solid #D7DFEB;margin-top: 10px;">-->
    <#--<div class="attach_content">-->
    <#--<#if attach?exists>-->
    <#--<#list attach?keys as key>-->
    <#--<div class="attach_list">-->
    <#--<#if (attach[key] =="png" || attach[key] =="gif" || attach[key] =="PNG")>-->


    <#--<#else>-->

    <#--</#if>-->
    <#--</div>-->
    <#--</#list>-->
    <#--<#else>-->
    <#--这封邮件没有附件！-->
    <#--</#if>-->
    <#--</div>-->
    <#--</div>-->

    </div>
    <div class="advertisement">
        <div style="background-color: rgb(226,240,227); width: 100%; text-align:center;">
            <img src="${base}/img/140218_yixin_780x96.jpg" alt=""/>
        </div>
    </div>
</div>
</div>
<#include "../base/footer.ftl">
<link rel="stylesheet" href="${base}/other/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/css/show.css">
    <script src="${base}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${base}/other/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/common.js" type="text/javascript"></script>
<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<#include "../base/header.ftl">
<#--<link href="${base}/css/Site.css" type="text/css" rel="stylesheet" />-->

<div id="body_content">
    <div class="show_mail">
    <#--<div class="button">删除</div>-->
    <#--<div class="button">移动到</div>-->
    <#--<div class="button">导出</div>-->
    <#--<div class="button">标记为</div>-->
        <div class="header_page">
            <div class="button_page">
                <button type="button"
                        onclick="javascript:window.location.href=<#if '${(mail_flag)!}'=='show'>'/mail/receiveMail'<#else>'/mail/sentMail'</#if>"
                        class="btn btn-default"><<&nbsp;返回
                </button>
                <div class="btn-group">
                    <button type="button" class="btn btn-default" onclick="window.location.href='/mail/replyForm?id=${(mail.id)!}'">回复</button>
                    <#--<button type="button" class="btn btn-default">标记为</button>-->
                    <div class="btn-group">
                        <#--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">-->
                            <#--移动到-->
                            <#--<span class="caret"></span>-->
                        <#--</button>-->
                        <#--<ul class="dropdown-menu">-->
                            <#--<li><a href="#">垃圾邮件</a></li>-->
                            <#--<li><a href="#">订阅邮件</a></li>-->
                        <#--</ul>-->
                    </div>
                </div>
                <div class="btn-group">
                    <#--<button type="button" class="btn btn-default">删除</button>-->
                    <#--<button type="button" class="btn btn-default">举报</button>-->
                    <div class="btn-group">
                        <#--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">-->
                            <#--移动到-->
                            <#--<span class="caret"></span>-->
                        <#--</button>-->
                        <#--<ul class="dropdown-menu">-->
                            <#--<li><a href="#">垃圾邮件</a></li>-->
                            <#--<li><a href="#">订阅邮件</a></li>-->
                        <#--</ul>-->
                    </div>
                </div>
            </div>

            <div class="mail_header">
                <div class="subject">
                    <h1 title="邮件标题" tabindex="0" class="mail_title">【${(mail.subject)!}】</h1>
                </div>
                <div class="desc">
                    <ul id="mail_desc">
                        <li>
                            <div class="item_title">发件人&nbsp;:</div>
                            <div class="item_content">
                                <span class="mail_user" style="margin-left: 10px;">${(mail.fromUser)!}</span>
                                <span class="mail_user_host"
                                      style="margin-right: 10px;">&lt;${(mail.fromUser)!}&gt;</span>
                            </div>
                        </li>
                        <li>
                            <div class="item_title">收件人&nbsp;:</div>
                            <div class="item_content">
                                <span class="mail_user" style="margin-left: 10px;">${(mail.toUser)!}</span>
                                <span class="mail_user_host"
                                      style="margin-right: 10px;">&lt;${(mail.toUser)!}&gt;</span>
                            </div>
                        </li>
                        <li>
                            <div class="item_title">时&nbsp;&nbsp;&nbsp;间&nbsp;:</div>
                            <div class="item_content_date">
                            ${(mail.sentDate?string("yyyy 年 MM 月 dd 日 HH:mm"))!}&nbsp;&nbsp;(星期五)
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="content">
        ${(mail.content)!}
        </div>
        <div class="attach">
            <div class="attach_title">
                <i class="fa fa-paperclip"></i><span>附件（<#if "${(attach?size)!}"=="" >
                0<#else>${(attach?size)!}</#if>）</span>
            </div>
            <hr width="95%" style="border:0.7px solid #D7DFEB;margin-top: 10px;">
            <div class="attach_content">
                <#if attach?exists>
                <#list attach?keys as key>
                <div class="attach_list">
                    <#if (attach[key] =="png" || attach[key] =="gif" || attach[key] =="PNG" || attach[key] =="jpg")>
                        <a target="_blank" href="<#if '${(mail_flag)!}'=='show'>${base}/attachfile/${(key)!}<#else>${base}/attachfile/${(key)!}</#if>">
                            <div class="attach_item" title="${(key)!}">
                                <div class="attach_item_image">
                                    <i class="fa fa-file-image-o fa-5x" style="color: #F8AFAE"></i>
                                </div>
                                <div class="attach_item_title">
                                    <#if (key?length lt 12)>
                                        <span>${(key)!}</span>
                                    <#else>
                                     <span>${(key[0..10])!}...</span>
                                    </#if>
                                </div>
                            </div>
                        </a>
                    <#elseif (attach[key] =="pdf")>
                    <a target="_blank" href="<#if '${(mail_flag)!}'=='show'>${base}/attachfile/${(key)!}<#else>${base}/attachfile/${(key)!}</#if>">
                        <div class="attach_item" title="${(key)!}">
                            <div class="attach_item_image">
                                <i class="fa fa-file-pdf-o fa-5x style=" color: #F8AFAE""></i>
                            </div>
                            <div class="attach_item_title">
                                <#if (key?length lt 12)>                                    <span>${(key)!}</span>

                                <#else>
                                     <span>${(key[0..10])!}...</span>
                                </#if>
                            </div>
                        </div>
                    </a>
                    <#elseif (attach[key] =="xls" || attach[key] =="xlsx")>
                    <a target="_blank" href="<#if '${(mail_flag)!}'=='show'>${base}/attachfile/${(key)!}<#else>${base}/attachfile/${(key)!}</#if>">
                        <div class="attach_item" title="${(key)!}">
                            <div class="attach_item_image">
                                <i class="fa fa-file-excel-o fa-5x style=" color: #F8AFAE""></i>
                            </div>
                            <div class="attach_item_title">
                                <#if (key?length lt 12)>
                                    <span>${(key)!}</span>
                                <#else>
                                     <span>${(key[0..10])!}...</span>
                                </#if>
                            </div>
                        </div>
                    </a>
                    <#elseif (attach[key] =="doc" || attach[key] =="docx")>
                    <a target="_blank" href="<#if '${(mail_flag)!}'=='show'>${base}/attachfile/${(key)!}<#else>${base}/attachfile/${(key)!}</#if>">
                        <div class="attach_item" title="${(key)!}">
                            <div class="attach_item_image">
                                <i class="fa fa-file-word-o fa-5x style=" color: #F8AFAE""></i>
                            </div>
                            <div class="attach_item_title">
                                 <#if (key?length lt 12)>
                                     <span>${(key)!}</span>
                                 <#else>
                                     <span>${(key[0..10])!}...</span>
                                 </#if>
                            <#--<span>${(key)!}</span>-->
                            </div>
                        </div>
                    </a>
                    <#elseif (attach[key] =="zip" || attach[key] =="rar")>
                    <div class="attach_item" title="${(key)!}">
                        <div class="attach_item_image">
                            <i class="fa fa-file-zip-o fa-5x style=" color: #F8AFAE""></i>
                        </div>
                        <div class="attach_item_title">
                            <#if (key?length lt 12)>
                                <span>${(key)!}</span>
                            <#else>
                                 <span>${(key[0..10])!}...</span>
                            </#if>
                        </div>
                    </div>
                    <#else>
                    <div class="attach_item" title="${(key)!}">
                        <div class="attach_item_image">
                            <i class="fa fa-file-o fa-5x style=" color: #F8AFAE""></i>
                        </div>
                        <div class="attach_item_title">
                            <#if (key?length lt 12)>
                                <span>${(key)!}</span>
                            <#else>
                                 <span>${(key[0..10])!}...</span>
                            </#if>
                        </div>
                    </div>
                    </#if>
                </div>
                </#list>
                <#else>
                    这封邮件没有附件！
                </#if>
            </div>
        </div>

    </div>
    <div class="advertisement">
        <div style="background-color: rgb(226,240,227); width: 100%; text-align:center;">
            <img src="${base}/img/140218_yixin_780x96.jpg" alt=""/>
        </div>
    </div>
</div>
</div>
<#include "../base/footer.ftl">
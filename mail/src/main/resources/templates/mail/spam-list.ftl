<link rel="stylesheet" href="${base}/other/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/css/receive.css">
    <script src="${base}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${base}/other/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/common.js" type="text/javascript"></script>

<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<#include "../base/header.ftl">
<script src="${base}/js/move-and-delete.js" type="text/javascript"></script>
<#--<link href="${base}/css/Site.css" type="text/css" rel="stylesheet" />-->
<div id="body_content">
    <div class="header-mail">
    <#--<div class="button">删除</div>-->
    <#--<div class="button">移动到</div>-->
    <#--<div class="button">导出</div>-->
    <#--<div class="button">标记为</div>-->
        <button type="button" class="btn btn-default" onclick="deleteMail('spam')">删除</button>

        <div class="btn-group">
            <#--<button type="button" class="btn btn-default">举报</button>-->
            <#--<button type="button" class="btn btn-default">标记为</button>-->
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    移动到
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" onclick="moveToSpam('spam','normal')">正常邮件</a></li>
                    <li><a href="" onclick="moveToSpam('spam','delete')">删除邮件</a></li>
                </ul>
            </div>
        </div>
        <button type="button" class="btn btn-default" onclick="location.reload()">刷新</button>
    </div>
    <div class="content">
        <div class="row_header">
            当前列表内容：
        </div>
    <#--<div class="row">-->
    <#--<div class="check">-->
    <#--<input type="checkbox" />-->
    <#--</div>-->
    <#--<div class="name">-->
    <#--招商银行信用卡-->
    <#--</div>-->
    <#--<div class="title">-->
    <#--招商银行信用卡电子账单-->
    <#--</div>-->
    <#--<div class="date">-->
    <#--2014-2-22-->
    <#--</div>-->
    <#--</div>-->
        <div class="mail-list">
            <table class="table table-hover">
            <#--<caption>悬停表格布局</caption>-->
            <#--<thead>-->
            <#--<tr>-->
            <#--<th>名称</th>-->
            <#--<th>城市</th>-->
            <#--<th>邮编</th>-->
            <#--</tr>-->
            <#--</thead>-->
                <tbody>
            <#if spamList??>
                <#list spamList as spam>
                 <tr>
                     <td width="5%"><input type="checkbox" name="mailId" value="${(spam.id)!}"/><input type="hidden" id="mailId" value="${(spam.id)!}"/>
                     </td>
                     <td width="15%"><a style="cursor: pointer"
                                        onclick="changeiframe('/spam/showSpamMail',${(spam.id)!})"> ${(spam.fromUser)!}</a>
                     </td>
                     <td width="70%"><a style="cursor: pointer"
                                        onclick="changeiframe('/spam/showSpamMail',${(spam.id)!})"> ${(spam.subject)!}</a>
                     </td>
                     <td width="10%"><a style="cursor: pointer"
                                        onclick="changeiframe('/spam/showSpamMail',${(spam.id)!})">${(spam.sentDate?string("yyyy-MM-dd"))!}</a>
                     </td>

                 </tr>
                </#list>
            </#if>
                </tbody>
            </table>
        </div>

    </div>
    <div class="advertisement" style="float:left;width: 80%">
        <div style="background-color: rgb(226,240,227); width: 100%; text-align:center;">
            <img src="${base}/img/140218_yixin_780x96.jpg" alt=""/>
        </div>
    </div>
</div>
</div>
<#include "../base/footer.ftl">
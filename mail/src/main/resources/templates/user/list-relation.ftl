<link rel="stylesheet" href="${base}/other/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/css/receive.css">
<link rel="stylesheet" href="${base}/css/user-relation.css">
    <script src="${base}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${base}/other/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/common.js" type="text/javascript"></script>
<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<#include "../base/header.ftl">
<#--<link href="${base}/css/Site.css" type="text/css" rel="stylesheet" />-->
<div id="body_content">
    <div class="content">
        <div class="container">
        <#--<div class="row clearfix">-->
            <div class="col-md-9 column">
                <div class="row">
                        <#list userList as user>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" src="${base}/img/head.gif"/>
                                    <div class="caption">
                                        <center><h3>
                                            ${(user.username)}
                                        </h3></center>
                                        <p>
                                            Cras justo odio, dapibus .
                                        </p>
                                        <p>
                                            <a class="btn btn-success" href="#">关注</a> <a class="btn btn-default"
                                                                                          onclick="javascript:return p_del()"
                                                                                          href="/userRelation/deleteUserRelation?user-delete=${(user.username)!}">删除</a>
                                            <script>
                                                function p_del() {
                                                    var msg = "您真的确定要删除吗？\n\n请确认！";
                                                    if (confirm(msg) == true) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                            </script>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#list>
                </div>
            </div>

        <#--</div>-->
            <div class="col-md-2 column" style="margin-left: -100px;width: 260px;">
                <table class="table table-hover">
                    <caption>站内用户</caption>
                    <tbody>
                        <#list userAll as user_all>
                        <tr>
                            <td><h5>${(user_all.username)!}</h5></td>
                            <td>待定参数</td>
                            <td>
                                <#assign count =0>
                                <#assign flag =0>
                                <#list userList as user_relation>
                                    <#assign  count =count +1 >
                                    <#if (user_all.username == user_relation.username)>
                                        <#assign flag =1>
                                        <#break />
                                    </#if>
                                </#list>
                                <#if (user_all.username == user.username)>
                                    <h5>您自己</h5>
                                <#elseif (count <= userList?size && flag ==1)>
                                    <h6>已是您的好友</h6>
                                <#else>
                                <a class="btn btn-danger btn-xs"
                                   href="/userRelation/addUserRelation?user-add=${(user_all.username)!}"
                                   style="font-size: 10px;">
                                    添加好友
                                </a>
                                </#if>

                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
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
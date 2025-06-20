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
        <div class="container pull-left">
            <div class="col-md-12 column">
                <div class="content-header" style="margin-top: 20px;margin-left: 30px;margin-bottom: 20px;">
                    <a href="" class="btn btn-success" data-toggle="modal" data-target="#myModal_add" title="自定义添加皮肤">添加皮肤（自定义）</a>
                </div>
                <!-- 模态框（Modal） -->
                <div style="margin-top: 50px" class="modal fade" id="myModal_add" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                            <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">-->
                            <#--&times;-->
                            <#--</button>-->
                                <h4 class="modal-title" id="myModalLabel">
                                    自定义添加皮肤
                                </h4>
                            </div>
                            <form action="/skin/addSkin" method="post" enctype="multipart/form-data">
                                <div class="modal-body" style="height:400px;width:400px">
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">皮肤名称</label>
                                        <div class="col-sm-7">
                                            <input type="text" style="" class="form-control" required name="skinName"
                                                   placeholder="请输入皮肤名称">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">皮肤展示</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" class="form-control" required name="showLogo"
                                                   placeholder="请输入皮肤展示图片">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">头部</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" required class="form-control" name="header"
                                                   placeholder="请输入头部">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">头部左边</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" required class="form-control"
                                                   name="headerBgLeft"
                                                   placeholder="请输入头部左边">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">头部右边</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" required class="form-control"
                                                   name="headerBgRight"
                                                   placeholder="请输入头部右边">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">左侧菜单</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" required class="form-control" name="bodyLeft"
                                                   placeholder="请输入左侧菜单图片">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">左侧菜单底部</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" required class="form-control"
                                                   name="bodyLeftBottom"
                                                   placeholder="请输入左侧菜单底部图片">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">横向菜单</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" class="form-control" name="menuBgx"
                                                   placeholder="请输入横向菜单">
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <label for="name" class="col-sm-3 control-label">Logo</label>
                                        <div class="col-sm-7">
                                            <input type="file" style="" class="form-control" name="logo"
                                                   placeholder="请输入邮箱Logo">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" value="关闭" class="col-sm-4 btn btn-default"
                                           data-dismiss="modal"/>
                                    <input type="submit" value="提交" class="col-sm-4 btn btn-primary"/>
                                </div>
                            </form>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
            </div>
        <#--<div class="row clearfix">-->
            <div class="col-md-12 column" style="margin-bottom: 20px;">
                <div class="row">
                    <div class="col-md-12">
                        <div class="thumbnail">
                            <img alt="300x200" style="float:left;width:200px;height:235px"
                                 src="${base}/skinfile/${(hasSkin.skinShowLogo)!}"/>
                            <div class="caption" style="float: left;margin-left: 30px;">
                                <h3>
                                    《${(hasSkin.skinName)!}》
                                </h3>
                                <p>
                                    您当前使用的皮肤是：<b>${(hasSkin.skinName)!}</b>，发布于：${(hasSkin.createDate?string("yyyy-MM-dd HH:mm:ss"))!}
                                    .
                                </p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="row">
                        <#list skinList as skin>
                            <div class="col-md-3">
                                <div class="thumbnail">
                                    <img alt="300x200" style="width:200px;height:235px"
                                         src="${base}/skinfile/${skin.skinShowLogo}"/>
                                    <div class="caption">
                                        <center><h3>
                                            ${(skin.skinName)}
                                        </h3></center>
                                    <#--<p>-->
                                    <#--Cras justo odio, dapibus .-->
                                    <#--</p>-->
                                        <p>
                                        <a class="btn btn-success" href=<#if ("${(chosenSkinFlag)!}" == "exist")>"/chosenSkin/updateChosenSkin?skinNo=${(skin.id)!}"<#else>
                                            "/chosenSkin/addChosenSkin?skinNo=${(skin.id)!}"</#if>">选择</a>
                                            <a class="btn btn-default" onclick="javascript:return p_del()"
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
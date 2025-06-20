<link rel="stylesheet" href="${base}/other/bootstrap.min.css">
    <script src="${base}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${base}/other/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/common.js" type="text/javascript"></script>
<link rel="stylesheet" href="${base}/css/font-awesome.min.css">
<#include "../base/header.ftl">
<#--<link href="${base}/css/Site.css" type="text/css" rel="stylesheet" />-->
<div id="body_content">
    <div class="content">
        <h1 style="margin-left: 20px;"><i class="fa fa-check" style="color: green;"></i>&nbsp;&nbsp;<span>发送成功！！</span>
        </h1>
        <button onclick="gotoIndex()" class="btn-success"
                style="margin-left: 100px;margin-top: 30px;margin-bottom: 20px;">点击返回主页
        </button>
    </div>
    <script>
        function gotoIndex() {
            location.href = "/mailIndex";
        }
    </script>
    <div class="advertisement" style="float:left;width: 80%">
        <div style="background-color: rgb(226,240,227); width: 100%; text-align:center;">
            <img src="${base}/img/140218_yixin_780x96.jpg" alt=""/>
        </div>
    </div>
</div>
</div>
<#include "../base/footer.ftl">
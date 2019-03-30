<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    !-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->


    <title>Title</title>


    <script type="text/javascript">
        $(function () {
            var height = document.documentElement.clientHeight;
            document.getElementById('iframe-page-content').style.height = height + 'px';
        });
        var menuClick = function (menuUrl) {
            $("#iframe-page-content").attr('src', menuUrl);
        };
    </script>

</head>
<body>
<div style="width: 100%">
    <!-- 左侧菜单栏 -->
    <div id="main-container">
        <div id="sidebar" class="col-md-2 column">
            <!-- 创建菜单树 -->
            <div class="col-md-12">
                               
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                                       
                    <li>
                                                <a href="#systemSetting" class="nav-header collapsed"
                                                   data-toggle="collapse">
                                                    <i class="glyphicon glyphicon-cog"></i>index
                                                       <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                                                </a>
                                               
                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                                                       
                            <li><a href="#" onclick="menuClick('${base}toViewInfo?id=${s.id}')"><i
                                    class="glyphicon glyphicon-user"></i>aaa</a></li>
                                                       
                            <li><a href="#" onclick="menuClick('${base}toTestList')"><i
                                    class="glyphicon glyphicon-th-list"></i>bbb</a></li>
                                                   
                        </ul>
                                           
                    </li>
                                       
                    <li>
                                                <a href="#systemSetting1" class="nav-header collapsed"
                                                   data-toggle="collapse">
                                                    <i class="glyphicon glyphicon-cog"></i>index1
                                                       <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                                                </a>
                                               
                        <ul id="systemSetting1" class="nav nav-list collapse secondmenu" style="height: 0px;">
                                                       
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>ccc</a></li>
                                                       
                            <li><a href="#"><i class="glyphicon glyphicon-edit"></i>ddd</a></li>
                                                       
                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>eee</a></li>
                                                   
                        </ul>
                                           
                    </li>
                                   
                </ul>
                           
            </div>
        </div>
        <div class="col-md-10 column">
            <div class="breadcrumbs" id="breadcrumbs">
                <!-- 面包屑导航 -->
                <ul class="breadcrumb">
                    <li>
                        <a href="${base}toLoginIndex">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul>
            </div>

            <!-- 内容展示页 -->
            <div>
                <iframe id="iframe-page-content" src="${base}resource/html/myindex.jsp" width="100%"  frameborder="no"
                        border="0" marginwidth="0"

                        marginheight=" 0" scrolling="no" allowtransparency="yes"></iframe>
            </div>
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</div>
</body>
</html>
<!DOCTYPE html>
<!--[if IE 8 ]> <html class="ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]> <html class="ie9" lang="en"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en"> <!--<![endif]-->
<#--<html lang="zh" class="no-js">-->
<head>
	<title>rems</title>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>

	<link rel="apple-touch-icon" sizes="76x76" href="../lib/assets/img/apple-icon.png"/>
	<link rel="icon" type="image/png" sizes="96x96" href="../lib/assets/img/favicon.png"/>
	<!-- css 的引入  -->
	<link rel="stylesheet" href="../lib/assets/css/base.css"/>
    <script src="../lib/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="../js/seajs/sea.js"></script>
	<script src="../js/seajs/seaconfig.js"></script>
    <script>
        seajs.use("main");
    </script>
    <style>
        html {
            position: relative;
            min-height: 100%;
            margin: 0px;!important;
            padding: 0px;!important;
            /*overflow-y: hidden;*/
        }
        body {
            font-family: "Source Sans Pro", sans-serif;
            font-size: 15px;
            min-height: 100%;
            margin: 0px;!important;
            padding: 0px;!important;
        }
        div {
            margin: 0px;!important;
            padding: 0px;!important;
        }
        .navbar-header{
            width: 100%;
            height: 80px;
            max-height: 80px;
        }
        .container-fluid {
            padding-right: 10px;
            padding-left: 10px;
        }

        .row {
            padding-right: 10px;
            padding-left: 10px;
        }

        #row {
            padding-top: 15px;
            padding-left: 25px;
        }
        footer {
            position:relative;!important;
            bottom: 0;!important;
            right: 0;!important;
            padding-top: 0px;!important;
            padding-bottom: 0px;!important;
            float: right;
            padding-right: 16px;
            padding-top: 5PX;
        }
    </style>
</head>

<body>
    <!-- Start Navigation -->
    <div class="container-fluid" style="padding-right:0px; padding-left: 0px;overflow-y: hidden;overflow-x: hidden">
       <!-- header -->
        <nav class="navbar-header" style="background-image: url(../lib/assets/img/bg/brick-wall-dark.png);background-color: #666;">
            <div class="container-fluid">
                <!-- Start Atribute Navigation -->
                <div class="row" id="row">
                    <!-- left tag log -->
                    <div class="col-xs-6" style="width: 14%">
                        <div class="row">
                            <div class="col-xs-6">
                                <a><img src="../lib/assets/img/apple-icon.png" class="logo" alt="" height="43" width="43"></a>
                                <div>
                                    <span>R E M S</span>
                                </div>
                            </div>
                            <div class="col-xs-6" style="padding-top: 10px;padding-left: 3px;">
                                <span style="font-size: 58px;font-family: Consolas;">REMS</span>
                            </div>
                        </div>
                    </div>

                    <!-- center contaier  -->
                    <div class="col-xs-12" style="width: 68%;padding: 0px;padding-right: 0px;">
                        <div class="attr-nav" navbar-collapse  class="navbar-collapse" id="navbar-menu">
                            <ul class="nav navbar-nav navbar-right" data-in="fadeInDown" data-out="fadeOutUp">
                                <!-- 递归  宏定义 -->
                            <#macro menuTree menus>
                                <#if menus?? && menus?size gt 0>
                                    <#list menus as menu>
                                        <#if menu.menuInfos?? && menu.menuInfos?size gt 0>
                                          <li class="dropdown">
                                              <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                                                  <span>${menu.menuName!}</span>
                                              </a>
                                              <ul class="dropdown-menu">
                                                  <@menuTree menus=menu.menuInfos />
                                              </ul>
                                          </li>
                                        <#else>
                                          <li>
                                              <a href="javascript:;" onclick="addnewTab('${menu.menuName!}','${menu.url!}')">${menu.menuName!}</a>
                                          </li>
                                        </#if>
                                    </#list>
                                </#if>
                            </#macro>
                            <!-- 调用宏 生成递归树 -->
                            <@menuTree menus=menuList />
                            </ul>
                        </div>
                        <!-- End Atribute Navigation -->
                    </div>

                    <!-- right menu title -->
                    <div class="col-xs-6" style="float: right;width: 14%">
                        <div class="attr-nav">
                            <ul>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" >
                                        <i class="fa fa-shopping-bag"></i>
                                        <span class="badge">3</span>
                                    </a>
                                <li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
                                <li class="side-menu"><a href="#"><i class="fa fa-bars"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Start Side Menu right 菜单 -->
            <div class="side">
                <a href="#" class="close-side"><i class="fa fa-times"></i></a>
                <ul class="link">
                    <!-- 递归  宏定义 -->
                <#macro menuTree menus>
                    <#if menus?? && menus?size gt 0>
                        <#list menus as menu>
                            <#if menu.menuInfos?? && menu.menuInfos?size gt 0>
                              <li class="treeview"> <!-- class="treeview" -->
                                  <a href="ja vascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" >
                                      <span>${menu.menuName!}</span>
                                  </a>
                                  <ul class="link">
                                      <@menuTree menus=menu.menuInfos />
                                  </ul>
                              </li>
                            <#else>
                              <li>
                                  <a href="javascript:void(0)" target="show">${menu.menuName!}</a>
                              </li>
                            </#if>
                        </#list>
                    </#if>
                </#macro>
                    <!-- 调用宏 生成递归树 -->
                <@menuTree menus=menuList />
                </ul>
            </div>
            <!-- End Side Menu right 菜单 -->
        </nav>

        <!-- bod container -->
        <div id="container" class="row-fluid" style="overflow-y: auto;">
            <div id="tabs" class="tabs" style="overflow:visible; padding-left: 0px; margin-left: 0px;border: 0px; ">
                <div id="tab_title" title="首页！">
                    <iframe id="mainweb" class="mainweb" scrolling="no" frameborder="0"  marginwidth="0" marginheight="0"
                            src="main" style="min-width: 100%;background-color: #F0F3EF; " name="show" aria-live="polite">
                    </iframe>
                </div>
                <#--<a href="#" id="sampleButton">添加</a>-->
            </div>
        </div>

        <!-- end footer -->
        <div id="footer" class="row-fluid">
            <footer>
                <p>Copyright &copy; 2017.Company name All rights reserved.
                    <a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
            </footer>
        </div>
    </div>
</body>
</html>

<!DOCTYPE html>
<html lang="en" class="no-js fixed-layout">
<head>
    <title>rems</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="description" content="REMS">
    <meta name="keywords" content="index">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="apple-touch-icon-precomposed" sizes="76x76" href="../lib/assets/amazeui/i/app-icon72x72@2x.png"/>
    <link rel="icon" type="image/png" sizes="96x96" href="../lib/assets/amazeui/i/favicon.png"/>

    <!-- css的引入 -->
    <link rel="stylesheet" href="../lib/assets/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../lib/assets/amazeui/css/admin.css">
    <link rel="stylesheet" href="../lib/assets/amazeui/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="../lib/assets/amazeui/css/app.css">
    <link rel="stylesheet" href="../lib/assets/layui/css/layui.css"  media="all">


    <!-- js 的引入  -->
    <script src="../lib/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="../lib/assets/amazeui/js/echarts.min.js"></script>
    <script src="../lib/assets/amazeui/js/jquery.min.js"></script>
    <script src="../lib/assets/layui/js/layui.js" charset="utf-8"></script>
    <script src="../js/seajs/sea.js"></script>
    <script src="../js/seajs/seaconfig.js"></script>

    <!--[if lt IE 9]>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="../lib/assets/amazeui/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
    <script src="../lib/assets/amazeui/js/amazeui.min.js"></script>

    <script src="../lib/assets/amazeui/js/amazeui.datatables.min.js"></script>
    <script src="../lib/assets/amazeui/js/dataTables.responsive.min.js"></script>

    <script>
        seajs.use("main");
    </script>
    <style type="text/css">
        .layui-tab {
            transition: all 0.4s ease-in-out;!important;
            position: relative;!important;
            margin-left: 240px;!important;
            z-index: 1101;!important;
            background-color: #f2f2f2;
            border-bottom-left-radius: 3px;!important;
        }
        .layui-tab.xs-active {
            margin-left: 240px;
        }
        .layui-tab.active {
            margin-left: 0;
        }
    </style>
</head>
<body  data-type="index">
<script src="../lib/assets/amazeui/js/theme.js"></script>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<header id="header">
    <!-- logo -->
    <div class="am-fl tpl-header-logo">
        <a href="javascript:;"><img src="../lib/assets/amazeui/i/img/logo.png" alt=""></a>
    </div>
    <!-- 右侧内容 -->
    <div class="tpl-header-fluid">
        <!-- 侧边切换 -->
        <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>
                </span>
        </div>
        <!-- 搜索 -->
        <div class="am-fl tpl-header-search">
            <form class="tpl-header-search-form" action="javascript:;">
                <button class="tpl-header-search-btn am-icon-search"></button>
                <input class="tpl-header-search-box" name="search" type="text" placeholder="搜索内容..." style="width: 528px;">
            </form>
        </div>
        <!-- 其它功能-->
        <div class="am-fr tpl-header-navbar">
            <ul>
                <!-- 欢迎语 -->
                <li class="am-text-sm tpl-header-navbar-welcome">
                    <a href="javascript:;">欢迎你, <span>Amaze UI</span> </a>
                </li>

                <!-- 新邮件 -->
                <li class="am-dropdown tpl-dropdown" data-am-dropdown>
                    <a href="javascript:;" class="am-dropdown-toggle tpl-dropdown-toggle" data-am-dropdown-toggle>
                        <i class="am-icon-envelope"></i>
                        <span class="am-badge am-badge-success am-round item-feed-badge">4</span>
                    </a>
                    <!-- 弹出列表 -->
                    <ul class="am-dropdown-content tpl-dropdown-content">
                        <li class="tpl-dropdown-menu-messages">
                            <a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">
                                <div class="menu-messages-ico">
                                    <img src="../lib/assets/amazeui/i/img/user04.png" alt="">
                                </div>
                                <div class="menu-messages-time">
                                    3小时前
                                </div>
                                <div class="menu-messages-content">
                                    <div class="menu-messages-content-title">
                                        <i class="am-icon-circle-o am-text-success"></i>
                                        <span>夕风色</span>
                                    </div>
                                    <div class="am-text-truncate"> Amaze UI 的诞生，依托于 GitHub 及其他技术社区上一些优秀的资源；Amaze UI 的成长，则离不开用户的支持。 </div>
                                    <div class="menu-messages-content-time">2016-09-21 下午 16:40</div>
                                </div>
                            </a>
                        </li>

                        <li class="tpl-dropdown-menu-messages">
                            <a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">
                                <div class="menu-messages-ico">
                                    <img src="../lib/assets/amazeui/i/img/user02.png" alt="">
                                </div>
                                <div class="menu-messages-time">
                                    5天前
                                </div>
                                <div class="menu-messages-content">
                                    <div class="menu-messages-content-title">
                                        <i class="am-icon-circle-o am-text-warning"></i>
                                        <span>禁言小张</span>
                                    </div>
                                    <div class="am-text-truncate"> 为了能最准确的传达所描述的问题， 建议你在反馈时附上演示，方便我们理解。 </div>
                                    <div class="menu-messages-content-time">2016-09-16 上午 09:23</div>
                                </div>
                            </a>
                        </li>
                        <li class="tpl-dropdown-menu-messages">
                            <a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">
                                <i class="am-icon-circle-o"></i> 进入列表…
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- 新提示 -->
                <li class="am-dropdown" data-am-dropdown>
                    <a href="javascript:;" class="am-dropdown-toggle" data-am-dropdown-toggle>
                        <i class="am-icon-bell"></i>
                        <span class="am-badge am-badge-warning am-round item-feed-badge">5</span>
                    </a>

                    <!-- 弹出列表 -->
                    <ul class="am-dropdown-content tpl-dropdown-content">
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-line-chart"></i>
                                    <span> 有6笔新的销售订单</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    12分钟前
                                </div>
                            </a>
                        </li>
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-star"></i>
                                    <span> 有3个来自人事部的消息</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    30分钟前
                                </div>
                            </a>
                        </li>
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-folder-o"></i>
                                    <span> 上午开会记录存档</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    1天前
                                </div>
                            </a>
                        </li>


                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <i class="am-icon-bell"></i> 进入列表…
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- 退出 -->
                <li class="am-text-sm">
                    <a href="javascript:;">
                        <span class="am-icon-sign-out"></span> 退出
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>

<!-- 侧边导航栏 -->
<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">
            <div class="tpl-user-panel-profile-picture">
                <img src="../lib/assets/amazeui/i/img/user02.png" alt="">
            </div>
            <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              禁言小张
          </span>
            <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>

    <!-- 菜单 -->
    <ul class="sidebar-nav">
        <li class="sidebar-nav-heading">Components <span class="sidebar-nav-heading-info"> 附加组件</span></li>
        <!-- 递归  宏定义 -->
        <#macro menuTree menus>
            <#if menus?? && menus?size gt 0>
                <#list menus as menu>
                    <#if menu.menuInfos?? && menu.menuInfos?size gt 0>
                      <li class="sidebar-nav-link">
                          <a href="javascript:;" class="sidebar-nav-sub-title">
                              <i class="am-icon-table sidebar-nav-link-logo"></i> ${menu.menuName!}
                              <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                          </a>
                          <ul class="sidebar-nav sidebar-nav-sub">
                              <@menuTree menus=menu.menuInfos />
                          </ul>
                      </li>
                    <#else>
                        <li class="sidebar-nav-link">
                            <a href="javascript:;" target="${menu.url!}" class="site-demo-active" data-type="tabAdd">
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span>
                                <span>${menu.menuName!}</span>
                                <span style="display: none;">${menu.url!}</span>
                            </a>
                        </li>
                    </#if>
                </#list>
            </#if>
        </#macro>
        <!-- 调用宏 生成递归树 -->
        <@menuTree menus=menuList />
    </ul>
</div>


<!-- 内容区域 -->
<#--<div class="tpl-content-wrapper" style="overflow-y: auto;height: 80px;">-->
    <div class="layui-tab layui-tab-card" lay-filter="content" lay-allowclose="true" style="overflow-y: auto;">
        <ul class="layui-tab-title">
            <li class="layui-this">网站设置</li>
        </ul>
        <div class="layui-tab-content">
        </div>
    </div>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<#--<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>-->

<script src="../lib/assets/amazeui/js/app.js"></script>
</body>
</html>


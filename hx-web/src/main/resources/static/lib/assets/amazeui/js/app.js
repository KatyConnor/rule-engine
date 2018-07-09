$(function() {
    // 读取body data-type 判断是哪个页面然后执行相应页面方法，方法在下面。
    // var dataType = $('body').attr('data-type');
    // console.log('dataType = '+dataType);
    // for (key in pageData) {
    //     if (key == dataType) {
    //         pageData[key]();
    //     }
    // }
    //     // 判断用户是否已有自己选择的模板风格
    //    if(storageLoad('SelcetColor')){
    //      $('body').attr('class',storageLoad('SelcetColor').Color)
    //    }else{
    //        storageSave(saveSelectColor);
    //        $('body').attr('class','theme-black')
    //    }

    autoLeftNav();
    $(window).resize(function() {
        autoLeftNav();
        console.log('window = '+$(window).width())
    });

    $('.tpl-header-switch-button').on('click', function() {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.layui-tab').removeClass('active');
            }
            $('.left-sidebar').removeClass('active');
        } else {
            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.layui-tab').addClass('active');
            }
        }
    });

    totalHeight = document.documentElement.clientHeight;
    colHeight = totalHeight-$('#header').height();
    $(".layui-tab").height(colHeight);
    $(".left-sidebar").height(colHeight-56);
    $(".left-sidebar").css('margin-top',$('#header').height());
    $(".left-sidebar").css('padding','0');
});


// 风格切换

$('.tpl-skiner-toggle').on('click', function() {
    $('.tpl-skiner').toggleClass('active');
});

$('.tpl-skiner-content-bar').find('span').on('click', function() {
    $('body').attr('class', $(this).attr('data-color'))
    saveSelectColor.Color = $(this).attr('data-color');
    // 保存选择项
    storageSave(saveSelectColor);

});


// 侧边菜单开关


function autoLeftNav() {
    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}

// 侧边菜单
$('.sidebar-nav-sub-title').on('click', function() {
    $(this).siblings('.sidebar-nav-sub').slideToggle(80)
        .end()
        .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
});

//初始將a.html include p#iframe
$.get("rule-ruleManage",function(data){
    $(".layui-tab-content").html('<div class="layui-tab-item layui-show">'+data+'</div>');
});
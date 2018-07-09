define(function(require, exports, module) {
    require("define");
    $(function () {
        total = document.documentElement.clientHeight;
        clientwidth =  document.documentElement.clientWidth;
        colHeight = total-120; //-100-30
        colWidth = clientwidth - 10;
        $("#container").height(colHeight-10);
        $("#tabs").height(colHeight);
        $("#tabs").width(colWidth);
        $("#mainweb").height(colHeight);
    });

    layui.use('element', function(){
        //Tab的切换功能，切换事件监听等，需要依赖element模块
        var $ = layui.jquery,element = layui.element;
        var nextBreak = false;
        //触发事件
        var active = {
            tabAdd: function(othis){
                //新增一个Tab项
                var title = othis.init()[0].childNodes[3].innerHTML;
                var addTab = false;
                $(".layui-tab-title").each(function () {
                    $(this).find('li').each(function() {
                        if ($(this).html().split('<')[0] == title){
                            var layId = $(this).attr('lay-id');
                            console.log(layId);
                            element.tabChange('content', layId); //切换到当前存在的页面
                            addTab = true;
                            return;
                        }
                    });
                });

                if (addTab){
                    return;
                }

                var contents;
                var _clickTab = othis.init()[0].childNodes[5].innerHTML;
                while (_clickTab.indexOf('/') != -1){
                    _clickTab = _clickTab.replace('/','-');
                }
                $.get(_clickTab,function(data){
                    contents = data;
                    while (!nextBreak){
                        if (contents){
                            nextBreak = true;
                        }
                    }
                    var newLayId =  new Date().getTime()+Math.round(6);
                    element.tabAdd('content', {
                        title: title,
                        content: contents,
                        id: newLayId//实际使用一般是规定好的id，这里以时间戳模拟下
                    });
                    element.tabChange('content', newLayId); //切换到当前存在的页面
                });
            }
        };

        $('.site-demo-active').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });
    module.exports = exports;
});
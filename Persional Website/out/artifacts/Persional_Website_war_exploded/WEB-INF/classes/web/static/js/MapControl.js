var __googleAPIOK = false;
function initGoogleMap() {
    __googleAPIOK = true;
}


MapControl = function (options) {
    var me = this;
    me.options = $.extend({
        width:400,
        height:300
    },options);
    me._isdraging = false;
    me._init();
};
MapControl.prototype._init = function () {
    var me = this;
    me.div = $("#"+me.options.div); //div is a string
    me.div.css({
        "position":"relative",
        width:me.options.width+"px",
        height:me.options.height+"px"
    });
    me.olMapID = uuid();
    me.bkMapID = uuid();
    $("<div>").appendTo(me.div).addClass("full").attr({
        id:me.olMapID
    }).css({
        "position":"absolute",
        top:0,
        left:0,
        "z-index":100
    });
    $("<div>").appendTo(me.div).addClass("full").attr({
        id:me.bkMapID
    }).css({
        "position":"absolute",
        top:0,
        left:0,
        "z-index":1
    });


    // var raster = new ol.layer.Tile({
    //     source:new ol.source.OSM(),
    //     opacity:0.5
    // });
    me._sketchSource = new ol.source.Vector();
    me._sketchVector = new ol.layer.Vector({
        source:me._sketchSource
    });
    // var vector = new ol.layer.Vector({
    //     source:me._sketchSource
    // });
    // me.chinamap  = new ol.layer.Image({
    //     source:new ol.source.ImageWMS({
    //         ratio:1,
    //         url:'',
    //         params:{'FORMAT':"image/png",
    //             'VERSION':'1.1.1',
    //             "LAYERS":"china:province",
    //             "exception":"application/vnd.org.se_inimage",
    //         }
    //     })
    // });
    me._chinamap = new ol.layer.Image({
        source: new ol.source.ImageWMS({
            ratio: 1,
            url: 'http://127.0.0.1:8080/geoserver/china/wms',
            params: {
                'service':'WMS',
                'FORMAT': "image/png",
                'VERSION': '1.1.1',
                "LAYERS": 'china:province',
                "exceptions": 'application/vnd.ogc.se_inimage',
            }
        }),
        opacity:0.5
    });

    me.olMap =  new ol.Map({
        layers:[
            me._chinamap,
            me._sketchVector

        ],
        target:me.olMapID,
        view:new ol.View({
            // center:[-1100000,460000],
            zoom:4,
            center:ol.proj.transform([112.3, 28.9], 'EPSG:4326', 'EPSG:3857'),
            constrainResolution:true
        })
    });

    //定时器，返回一个资源号，每间隔100ms执行一次
    me._timer = setInterval(function(){
        me._initBackgroungMap();
    }, 100);


    //高德地图作为底图
    // me.bkMap = new AMap.Map(me.bkMapID,{
    //     center:[112.3,28.9],
    //     zoom:4
    // });
    setTimeout(function () {
        me._hideCopyrights();
    },1000);

    me.olMap.getView().on("change",function (ev) {
        var v = me.olMap.getView();
        if(v.getZoom()>7){
            me._chinamap.setVisible(false);
        }else{
            me._chinamap.setVisible(true);
        }
        me.updateBackgroundMap();
    });
    me.olMap.on("movestart",function (ev) {
        me._isdraging = true;
    });

    me.olMap.on("moveend",function (ev) {
        me._isdraging = false;
    });
    $("#"+me.olMapID).on("mousemove",function (ev) {
        if(me._isdraging){
            me.updateBackgroundMap();
        }
    });
    me._addDrawInteractions();
};

MapControl.prototype._initBackgroungMap = function(){
    var me = this;
    if(!__googleAPIOK){
        return;
    }
    me.bkMap = new google.maps.Map((document.getElementById(me.bkMapID)), {
        center: {
            lng: 112.3,
            lat: 28.9
        },
        zoom: 4,
        mapTypeId: 'satellite'
    });
    clearInterval(me._timer);
    setTimeout(function () {
        $(".gmnoprint").remove();
        $("#__id_2 > div > div > button").remove();
    },1000);
};

MapControl.prototype._addDrawInteractions = function(){
    var me = this;
    var types = ["Point","LineString","Polygon"];
    me._drawInteractions = {};
    for(var i = 0;i<types.length;i++){
        me._drawInteractions[types[i]] = new ol.interaction.Draw({
            source:me._sketchSource,
            type:types[i]
        });
        me.olMap.addInteraction(me._drawInteractions[types[i]]);
        me._drawInteractions[types[i]].on("drawend",function (ev) {
            var wktformat = new ol.format.WKT();
            var wkt = wktformat.writeFeature(ev.feature);
            $.ajax({
                type:'POST',
                url:"/Persional_Website/addTree",
                // url:"../php/addTree.php",
                data:{
                    "layer":"tree",
                    "wkt":wkt
                },
                success:function(res) {
                    res = res.replace(/'/g, '\"')
                    console.log( res)
                    const jsons = JSON.parse(res);
                    console.log(typeof jsons)
                    if(jsons.success == 'true'){
                        console.log("添加树木成功")
                    }else{
                        console.log("运气不佳！");
                    }
                }
            });

        });
    }
};

MapControl.prototype._deactiveAllDrawInteractions = function(){
    var me = this;
    for(var key in me._drawInteractions){
        me.olMap.removeInteraction(me._drawInteractions[key]);
    }
};

MapControl.prototype.setCurrentOperation = function(op){
    console.log(op);
    var me = this;
    me._currentOperation = op;
    me._deactiveAllDrawInteractions();
    me.olMap.addInteraction(me._drawInteractions[op]);
};

MapControl.prototype._hideCopyrights = function () {
    var me = this;
    $(".amap-logo").remove();
    $(".amap-copyright").remove();
};
MapControl.prototype.updateBackgroundMap = function () {
    var me = this;
    var view = me.olMap.getView();
    var center = view.getCenter();
    center = ol.proj.transform(center, 'EPSG:3857', 'EPSG:4326');
    var zoom = view.getZoom();
    // //高德地图变换
    // var proj = new ProjectionUtils();
    // center = proj.wgs84togcj02(center[0],center[1]);
    // me.bkMap.setZoomAndCenter(zoom,center);
    me.bkMap.setCenter({
        lng:center[0],
        lat:center[1]
    });
    me.bkMap.setZoom(zoom);

};
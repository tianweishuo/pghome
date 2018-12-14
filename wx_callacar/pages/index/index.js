var QQMapWX = require('../../utils/qqmap-wx-jssdk.min.js');
const app = getApp();
var qqmapsdk;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    map_width: 0,
    map_height: 0,
    startplace: '起点',
    endplace: '到哪下车',
    specialcarhide: false,
    messagehide: true,
    carmoney:0,
    borderRadius: 5,
    latitude: 0,
    longitude: 0,
    startmarker: {},
    endmarker: {},
    markers: [],
    callout: {
      content: '在这上车',
      bgColor: "#ffffff",
      color: "#736F6E",
      padding: 10,
      display: "ALWAYS",
      borderRadius: 10
    },
    startLocation: { //移动选择位置数据
      longitude: 0,
      latitude: 0,
      address: '起点',
    },
    endLocation: { //移动选择位置数据
      longitude: 0,
      latitude: 0,
      address: '到哪下车',
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this;
    _this.setData({
      map_width: app.globalData.screenWidth,
      map_height: app.globalData.screenHeight * 0.8,
    });
    // _this.setData({
    //   map_width: app.globalData.windowWidth,
    //   map_height: app.globalData.windowHeight * 0.9,
    //   specialheight: app.globalData.windowHeight * 0.5,
    //   topheight: app.globalData.windowHeight * 0.63,
    //   timetopheight: app.globalData.windowHeight * 0.63,
    //   cartopheight: app.globalData.windowHeight * 0.53,
    //   selectcartopheight: app.globalData.windowHeight * 0.63,
    //   icontop: app.globalData.windowHeight * 0.45,
    // })
    // 实例化API核心类
    qqmapsdk = new QQMapWX({
      key: app.globalData.key
    });
    var that = this;
    //获取位置
    wx.getLocation({
      type: 'gcj02', //默认为 wgs84 返回 gps 坐标，gcj02 返回可用于wx.openLocation的坐标
      success: function (res) {
        var startmarker = {
          id: 0,
          latitude: res.latitude,
          longitude: res.longitude,
          iconPath: "../../images/b1.png",
          width: 30,
          height: 30,
          callout: { //窗体
            content: that.data.callout.content,
            bgColor: that.data.callout.bgColor,
            color: that.data.callout.color,
            padding: that.data.callout.padding,
            display: that.data.callout.display,
            borderRadius: that.data.callout.borderRadius,
          },
        };
        that.setData({
          startmarker: startmarker
        })
        var startLocation = {
          latitude: res.latitude,
          longitude: res.longitude,
        };
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude,
          markers: that.data.markers.concat(startmarker)
        });
        //根据坐标获取当前位置名称，显示在顶部:腾讯地图逆地址解析
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success: function (addressRes) {
            var address = addressRes.result.formatted_addresses.recommend;
            startLocation.address = address;
            //当前位置信息
            that.setData({
              startLocation: startLocation
            });
          }
        });
      }
    });
    this.mapCtx = wx.createMapContext('qqMap');
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //移动选点开始
  choosestartplace: function () {
    var _this = this;
    wx.chooseLocation({
      success: function (res) {
        var markers = [];
        let startLocation = {
          longitude: res.longitude,
          latitude: res.latitude,
          address: res.address,
        };
        for (var index in _this.data.markers) {
          console.log(_this.data.markers[index].id);
          if (_this.data.markers[index].id != 0) {
            markers.push(_this.data.markers[index]);
          }
        }
        var marker = {
          id: 0,
          latitude: res.latitude,
          longitude: res.longitude,
          iconPath: "../../images/b1.png",
          width: 30,
          height: 30,
          callout: { //窗体
            content: _this.data.callout.content,
            bgColor: _this.data.callout.bgColor,
            color: _this.data.callout.color,
            padding: _this.data.callout.padding,
            display: _this.data.callout.display,
            borderRadius: _this.data.callout.borderRadius,
          }
        };
        markers.push(marker);
        _this.setData({
          startmarker: marker
        })
        _this.setData({
          markers: markers
        })
        _this.setData({
          startLocation: startLocation,
        });
        //判断是否已选择上车下车点
        for (var index in _this.data.markers) {
          //判断是否存在下车点
          if (_this.data.markers[index].id == 1) {
            _this.setData({
              specialcarhide:true,
              messagehide: false,
            })
            _this.driving();
          }
        }
      },
      fail: function (err) {
        console.log(err)
      }
    });
  },
  //移动选择结束
  chooseendplace: function () {
    var _this = this;
    wx.chooseLocation({
      success: function (res) {
        var marker = {
          id: 1,
          latitude: res.latitude,
          longitude: res.longitude,
          iconPath: "../../images/b2.png",
          width: 30,
          height: 30,
          callout: { //窗体
            content: "在这里下车",
            bgColor: _this.data.callout.bgColor,
            color: _this.data.callout.color,
            padding: _this.data.callout.padding,
            display: _this.data.callout.display,
            borderRadius: _this.data.callout.borderRadius,
          },
        };
        var markers = [];
        for (var index in _this.data.markers) {
          if (_this.data.markers[index].id != 1) {
            markers.push(_this.data.markers[index]);
          }
        }
        markers.push(marker);
        _this.setData({
          endmarker: marker
        })
        _this.setData({
          markers: markers
        })
        let endLocation = {
          longitude: res.longitude,
          latitude: res.latitude,
          address: res.address,
        };
        _this.setData({
          endLocation: endLocation,
        });

        //判断是否已选择上车下车点
        for (var index in _this.data.markers) {
          //判断是否存在上车点
          if (_this.data.markers[index].id == 0) {
            _this.setData({
              specialcarhide: true,
              messagehide:false,
            })
            _this.driving();
          }
        }
      },
      fail: function (err) {
        console.log(err)
      }
    });
  },
  //事件回调函数
  driving: function () {
    var _this = this;
    _this.setData({
      includePoints: []
    });
    var _from = _this.data.startmarker.latitude + ',' + _this.data.startmarker.longitude;
    var _to = _this.data.endLocation.latitude + ',' + _this.data.endLocation.longitude;
    var startincludePoints = {
      longitude: _this.data.startmarker.longitude,
      latitude: _this.data.startmarker.latitude
    }
    var endincludePoints = {
      longitude: _this.data.endLocation.longitude,
      latitude: _this.data.endLocation.latitude
    }
    this.mapCtx.includePoints({
      padding: [10],
      points: [startincludePoints, endincludePoints]
    })

    //网络请求设置
    var opt = {
      //WebService请求地址，from为起点坐标，to为终点坐标，开发key为必填
      url: 'https://apis.map.qq.com/ws/direction/v1/driving/?policy=LEAST_TIME,NAV_POINT_FIRST&from=' + _from + '&to=' + _to + '&key=BZOBZ-7TPKP-J3NDT-L6Z45-VYNCH-XMBNN',
      method: 'GET',
      dataType: 'json',
      //请求成功回调
      success: function (res) {
        console.log(res.data.result.routes[0]);
        var ret = res.data
        if (ret.status != 0) return; //服务异常处理
        var coors = ret.result.routes[0].polyline,
          pl = [];
        //坐标解压（返回的点串坐标，通过前向差分进行压缩）
        var kr = 1000000;
        for (var i = 2; i < coors.length; i++) {
          coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
        }
        //将解压后的坐标放入点串数组pl中
        for (var i = 0; i < coors.length; i += 2) {
          pl.push({
            latitude: coors[i],
            longitude: coors[i + 1]
          })
        }
        //设置polyline属性，将路线显示出来
        _this.setData({
          polyline: [{
            points: pl,
            arrowLine: true,
            color: '#0066FF',
            width: 5
          }]
        })
      }
    };
    wx.request(opt);
  },

  jiaoche:function(){
    console.log("叫车");
  },
  quxiao:function(){
    var _this = this;
    _this.setData({
      specialcarhide: false,
      messagehide: true,
      markers:null,
      polyline: null,
      startLocation: { //移动选择位置数据
        longitude: 0,
        latitude: 0,
        address: '起点',
      },
      endLocation: { //移动选择位置数据
        longitude: 0,
        latitude: 0,
        address: '到哪下车',
      }
    });
  },
  // 标点
  returnback:function(){
    console.log("标点");
  }
})
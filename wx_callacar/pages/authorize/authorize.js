const app = getApp();
Page({
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    openid:'',
    sessionKey:''
  },
  onLoad: function () {
    var that = this;
    wx.login({
      success: function (res) {
        //获取openid接口
        wx.request({
          url: app.globalData.url + '/wechat/openid',
          data: {
            code: res.code
          },
          success: function (res) {
            that.setData({
              openid: res.data.data.openid,
              sessionKey: res.data.data.sessionKey
            })
            // 查看是否授权
            wx.getSetting({
              success: function (res) {
                if (res.authSetting['scope.userInfo']) {
                  wx.getUserInfo({
                    success: function (res) {
                      //从数据库获取用户信息
                      //that.queryUsreInfo();
                      //用户已经授权过
                      wx.switchTab({
                        url: '/pages/index/index'
                      })
                    }
                  });
                }
              }
            })
          }
        })
      }
    })
  },
  bindGetUserInfo: function (e) {
    if (e.detail.userInfo) {
      //用户按了允许授权按钮
      var that = this;
      //插入登录的用户的相关信息到数据库
      console.log(e.detail.userInfo);
      wx.request({
        url: app.globalData.url + '/wechat/add',
        data: {
          openId: that.data.openid,
          avatarUrl: e.detail.userInfo.avatarUrl,
          city: e.detail.userInfo.city,
          country: e.detail.userInfo.country,
          gender: e.detail.userInfo.gender,
          language: e.detail.userInfo.language,
          nickName: e.detail.userInfo.nickName,
          province: e.detail.userInfo.province
        },
        method:'post',
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {
          //从数据库获取用户信息
          that.queryUsreInfo();
          console.log("插入小程序登录用户信息成功！");
        }
      });
      //授权成功后，跳转进入小程序首页
      wx.switchTab({
        url: '/pages/index/index'
      })
    } else {
      //用户按了拒绝按钮
      wx.showModal({
        title: '警告',
        content: '您点击了拒绝授权，将无法进入小程序，请授权之后再进入!!!',
        showCancel: false,
        confirmText: '返回授权',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击了“返回授权”')
          }
        }
      })
    }
  },
  //获取用户信息接口
  queryUsreInfo: function () {
    var _this = this;
    wx.request({
      url: app.globalData.url + '/wechat/userInfo?openid=' + _this.data.openid,
      success: function (res) {
        console.log(res.data);
        getApp().globalData.userInfo = res.data.data;
      }
    });
  },

})
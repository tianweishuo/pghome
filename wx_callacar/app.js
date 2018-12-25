//app.js

App({
  onLaunch: function () {
    var _this = this;
    wx.getSystemInfo({
      success: function(res) {
        _this.globalData.screenWidth = res.screenWidth;
        _this.globalData.screenHeight = res.screenHeight;
      },
    })
  },
  globalData: {
    url:'http://4k5edj.natappfree.cc',
    websocket:'ws://4k5edj.natappfree.cc',
    userInfo: null,
    key: "BZOBZ-7TPKP-J3NDT-L6Z45-VYNCH-XMBNN",
    windowWidth:0,
    windowHeight:0,
  }
})
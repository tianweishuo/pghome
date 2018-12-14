//logs.js
const util = require('../../utils/util.js')
//如：..­/..­/libs/amap-wx.js
const amapFile = require('../../utils/amap-wx.js');
const config = getApp();

Page({
  data: {
    tips: {},
    name:null,
    startus:null,
    inputstart:null,
    inputend:null
  },
  onLoad: function (options) {
    var _this = this;
    if (options.inputstart != null){
      _this.setData({
        inputstart:options.inputstart
      })
    }

    if (options.inputend != null) {
      _this.setData({
        inputend: options.inputend
      })
    }

    this.setData({
      name: options.name,
      startus: options.startus
    })
  },
  bindInput: function (e) {
    var that = this;
    var keywords = e.detail.value;
    var key = config.globalData.key;
    var myAmapFun = new amapFile.AMapWX({ key: key});
    myAmapFun.getInputtips({
      keywords: keywords,
      location: '',
      success: function (data) {
        if (data && data.tips) {
          that.setData({
            tips: data.tips
          });
        }

      }
    })
  },
  bindSearch: function (e) {
    var _this = this;
    var keywords = e.target.dataset.keywords;
    var url = '../index/index?';
    if(this.data.startus == 1){
      url += "inputstart=" + e.target.dataset.keywords;
    }
    if (this.data.startus == 2) {
      url += "inputend=" + e.target.dataset.keywords;
    }
    
    wx.navigateTo({
      url: url
    })

  }
})
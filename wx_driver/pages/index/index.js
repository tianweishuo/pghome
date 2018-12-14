//index.js
//获取应用实例
const app = getApp();
const { $Toast } = require('../../dist/base/index');
Page({
  data: {
    driverName:'',
    number:'',
    phone:'',
    carNumber:'',
    carModel:'',
    carColor:'',
    carName:'',
    drivingDate: '',
    carRegisterDate:'',
    inputdata:'',
    spinShow: false
  },
  //点击日期组件确定事件  
  bindDrivingDate: function (e) {
    console.log(e.detail.value)
    this.setData({
      dates: e.detail.value
    })
  },
  //页面加载完毕
  onLoad: function () {
  },
  //司机名字
  bindDrivername: function (e) {
    console.log(e.detail.detail.value);
    this.setData({
      driverName: e.detail.detail.value
    })
  },
  //手机号
  bindNumber:function(e){
    this.setData({
      number: e.detail.detail.value
    })
  },
  //身份证号
  bindNumber: function (e) {
    this.setData({
      number: e.detail.detail.value
    })
  },
  //手机号
  bindPhone: function (e) {
    this.setData({
      phone: e.detail.detail.value
    })
  },
  //初次领取驾照日期
  bindDrivingDate: function (e) {
    this.setData({
      drivingDate: e.detail.value
    })
  },
  //车牌号
  bindCarNumber:function(e){
    this.setData({
      carNumber: e.detail.detail.value
    })
  },
  //车型
  bindCarModel: function (e) {
    this.setData({
      carModel: e.detail.detail.value
    })
  },
  //车辆颜色
  bindCarColor: function (e) {
    this.setData({
      carColor: e.detail.detail.value
    })
  },
  //车辆所有人
  bindCarName: function (e) {
    this.setData({
      carName: e.detail.detail.value
    })
  },
  //车辆注册日期
  bindCarRegisterDate: function (e) {
    this.setData({
      carRegisterDate: e.detail.value
    })
  },
  //信息提交
  submitdriverinfo: function (){
    var _this = this
    var thisData = this.data;
    if (thisData.driverName == ''){
      $Toast({
        content: '司机姓名不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.number == ''){
      $Toast({
        content: '身份证号不能为空!',
        type: 'warning'
      });
      return;
    }
    //校验身份证
    if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(thisData.number))) {
      $Toast({
        content: '身份证号码有误',
        type: 'warning'
      });
      return;
    }
    if (thisData.phone == '') {
      $Toast({
        content: '手机号不能为空!',
        type: 'warning'
      });
      return;
    }
    //校验手机号
    if (!(/^1[34578]\d{9}$/.test(thisData.phone))) {
      $Toast({
        content: '手机号码有误',
        type: 'warning'
      });
      return;
    }
    if (thisData.carNumber == '') {
      $Toast({
        content: '车牌号不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.carModel == '') {
      $Toast({
        content: '车型不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.carColor == '') {
      $Toast({
        content: '车辆颜色不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.carName == '') {
      $Toast({
        content: '车辆所有人不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.drivingDate == '') {
      $Toast({
        content: '初次领取驾照日期不能为空!',
        type: 'warning'
      });
      return;
    }
    if (thisData.carRegisterDate == '') {
      $Toast({
        content: '车辆注册日期不能为空!',
        type: 'warning'
      });
      return;
    }
    wx.request({
      url: app.globalData.url+'/driver/register',
      method:'POST',
      data:{
        driverName: thisData.driverName,
        phone: thisData.phone,
        number: thisData.number,
        drivingDate: thisData.drivingDate,
        carNumber: thisData.carNumber,
        carModel: thisData.carModel,
        carColor: thisData.carColor,
        carName: thisData.carName,
        carRegisterDate: thisData.carRegisterDate
      },
      success: function (res){
        _this.setData({
          spinShow:true
        })
        //请求成功
        console.log(res.data);
        //代码报错
        if (res.data.code != 200){
          _this.setData({
            spinShow: false
          })
          _this.setData({
            spinShow: false
          })
        }
        wx.reLaunch({
          url: '../submit/submit'
        })
      }
    })
  }
  
})

// pages/callout/callout.js
const app = getApp();
var socketTask;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    callOrderId: null,
    tips: 31,
    taxiCount: 0,
    progress_txt: '正在匹配中...',
    count: 0, // 设置 计数器 初始为0
    countTimer: null // 设置 定时器 初始为null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this;
    this.setData({
      callOrderId: decodeURIComponent(options.callOrderId)
    });
    //建立长连接进行发送叫车信息
    wx.connectSocket({
      url: "ws://127.0.0.1:8088/ws",
      success: function () {
        console.log("websocket连接成功!");
      }
    });
    // 构建对象
    var dataContent = {
      action: '2',
      callMeg: {
        senderId: '15810723212',
        phone: '15810723212',
        callOrderId: _this.data.callOrderId,
      },
      extand: null
    };

    //监听 WebSocket 连接打开事件
    wx.onSocketOpen(function (res) {
      wx.sendSocketMessage({
        data: JSON.stringify(dataContent),
        success: function () {
          console.log("发送成功");
        }
      })
    });

    //监听 WebSocket 接受到服务器的消息事件
    wx.onSocketMessage(function (res) {
      console.log(JSON.stringify(res));
    })
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
  back: function () {

  },
  countInterval: function () {
    // 设置倒计时 定时器 每100毫秒执行一次，计数器count+1 ,耗时6秒绘一圈
    this.countTimer = setInterval(() => {
      if (this.data.count <= 30) {
        /* 绘制彩色圆环进度条  
        注意此处 传参 step 取值范围是0到2，
        所以 计数器 最大值 60 对应 2 做处理，计数器count=60的时候step=2
        */
        this.drawCircle(this.data.count / (30 / 2));
        this.setData({
          count: this.data.count + 1,
          tips: this.data.tips - 1
        })
      } else {
        this.setData({
          progress_txt: "匹配成功"
        });
        clearInterval(this.countTimer);
      }
    }, 1000)
  },
  drawProgressbg: function () {
    // 使用 wx.createContext 获取绘图上下文 context
    var ctx = wx.createCanvasContext('canvasProgressbg')
    ctx.setLineWidth(4);// 设置圆环的宽度
    ctx.setStrokeStyle('#DDDDDD'); // 设置圆环的颜色
    ctx.setLineCap('round') // 设置圆环端点的形状
    ctx.beginPath();//开始一个新的路径
    ctx.arc(110, 110, 100, 0, 2 * Math.PI, false);
    //设置一个原点(100,100)，半径为90的圆的路径到当前路径
    ctx.stroke();//对当前路径进行描边
    ctx.draw();
  },
  drawCircle: function (step) {
    var context = wx.createCanvasContext('canvasProgress');
    // 设置渐变
    var gradient = context.createLinearGradient(200, 100, 100, 200);
    gradient.addColorStop("0", "#fb9126");
    gradient.addColorStop("0.5", "#fb9126");
    gradient.addColorStop("1.0", "#fb9126");
    context.setLineWidth(10);
    context.setStrokeStyle(gradient);
    context.setLineCap('round')
    context.beginPath();
    // 参数step 为绘制的圆环周长，从0到2为一周 。 -Math.PI / 2 将起始角设在12点钟位置 ，结束角 通过改变 step 的值确定
    context.arc(110, 110, 100, -Math.PI / 2, step * Math.PI - Math.PI / 2, false);
    context.stroke();
    context.draw()
  },
  onReady: function () {
    this.drawProgressbg();
    //this.drawCircle(2)
    this.countInterval();
  }

})
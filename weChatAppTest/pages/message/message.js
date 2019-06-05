// pages/message/message.js
var util=require('../../utils/util.js')
var app=getApp()
var date=new Date();
var username='';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index:1,
    weather:{},
    systemMessage:'',
    tempMsg: '',
    windMsg: '',
    seasonContent:[],
    appointment_list:{},
    patientUsername:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("用户名：下面");
    console.log(options.pusername);
    username=options.pusername;
    console.log("我的用户名="+username);
    this.setData({
      patientUsername: options.pusername
    });
    
    this.getWeatherInfo();
    this.getAppointmentList();
    //获取季节
    this.getSeason();
  },
  //获取天气信息
  getWeatherInfo:function(){
    var that=this;
    wx.request({
      url: 'http://www.weather.com.cn/data/cityinfo/101100101.html',
      header: {
        "Content-Type": "applciation/json"
      },
      method:'GET',
      success:(res)=>{
        console.log(res.data);
        that.setData({
          weather:res.data.weatherinfo
        });
        that.compareTemp(res.data.weatherinfo.temp1, res.data.weatherinfo.temp2);
      },
      fail:(err)=>{
        console.log(err);
      }
    })
  },
  //对比最高温度与最低温度
  compareTemp(temp1,temp2){
    var t1 = parseFloat((temp1 + "").replace("℃", ""));
    var t2 = parseFloat((temp2 + "").replace("℃", ""));
    var tip=''
    if (Math.abs(t2 - t1)>=10){
      tip = '温差大，请注意穿衣哦。'
    }
    tip += this.getTheHotOrCool(t2);
    this.setData({
      tempMsg:tip 
    });
    // console.log(tip);
    // this.tempMsg=this.tempMsg+tip;
  },
  //高温，冷判断
  getTheHotOrCool(t){
    var msg='';
    // var t = parseFloat((temp + "").replace("℃",""));
    console.log("温度的数字："+t);
    switch(true){
      case t>=35:
        msg='高温，记得防暑！';
        break;
      case t > 26 && t < 35:
        msg = '气温热';
        break;
      case t>20&&t<26:
        msg='比较热';
        break;
      case t>16&&t<20:
        msg='温度偏凉';
        break;
      default:
        msg = '天气冷';
        break;
    }
    // console.log("msg=" + msg);
    return msg;
  },
  //跳到预约信息栏
  goToAppointment(e){
    this.setData({
      index:e.target.dataset.index
    });
    // console.log("Appointment message的classList："+e.target.dataset.index);
  },
  //跳到系统信息栏
  goToSystem(e){
    this.setData({
      index:e.target.dataset.index
    });
    // console.log("system message的classList：" + e.target.dataset.index);
  },
  //获取appointmentList
  getAppointmentList(){
    var that=this;
    console.log("病人用户名："+username);
    wx.request({
      url: 'http://127.0.0.1:8085/patientAppointment?pusername=' +username,
      header: {
        "Content-Type": "applciation/json"
      },
      method: 'GET',
      success:(res)=>{
        if(res!=null){
          console.log("预约列表："+res.data.length);
          that.setData({
            appointment_list:res.data
          })
        }else{
          console.log("获取不到！");
        }
      },
      fail:(err)=>{
        console.log(err);
      }
    })
  },
  goBack(){
    wx.navigateBack({
      delta:1
    });//返回上一页
  },
  //获取季节
  getSeason(){
    // var mytime=util.formatTime(date);
    // console.log(mytime);
    var that=this;
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const minute = date.getMinutes()
    const second = date.getSeconds()
    console.log("月份："+month);
    var season='';
    
    //判断月份
    switch(true){
      case month>=3&&month<=5:
          season='spring';
          break;
      case month >= 6 && month <= 8:
        season = 'summer';
        break;
      case month >= 9 && month <= 11:
        season = 'autumn';
        break;
        // month >= 12 && month <= 2
      default:
        season = 'winter';
        break;
    }
    console.log("季节：" +season)
    wx.request({
      url: app.globalData.apiurl + '/patient/season',
      data: {
        sign: season
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log("季节信息：" + res);
        console.log(JSON.stringify(res.data));
        var txt=new Array();
        if (res.data != "") {
          txt = JSON.stringify(res.data).split(",")
          // that.setData({
          //   seasonContent: JSON.stringify(res.data).split(",")
          // });
          txt[0] = txt[0].replace('[', "")
          txt[txt.length - 1] = txt[txt.length - 1].replace(']', "")
          for (var index in txt) {
            txt[index] = txt[index].replace('"', "").replace('"', "");
          }
          console.log(txt)
          that.setData({
            seasonContent: txt
          });
        }
      },
      fail: function (res) { },
      complete: function (res) { },
    })
    if(hour==9&&minute==0&&second==0){
     
    }
  },
  //websocket
  getServerSocket(){
    wx.onSocketMessage(function(res){
      console.log("后台消息："+res.data);
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getAppointmentList();
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

  }
})
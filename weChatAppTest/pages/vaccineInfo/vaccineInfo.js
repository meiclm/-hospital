// pages/vaccineInfo/vaccineInfo.js
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    vaccine:{},//疫苗信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var name=options.name;
    console.log("vaccineInfo："+name);

    var that=this;
    //查询该id的疫苗信息
    wx.request({
      url: app.globalData.apiurl +'/one_vaccine',
      data:{
        vName: name
      },
      success:res=>{
        console.log(res)
        if(res.data!=""){
          that.setData({
            vaccine:res.data
          });
        }
      }
    })
  },
  goToVaccine(){
    wx.navigateBack({
      delta:1
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

  }
})
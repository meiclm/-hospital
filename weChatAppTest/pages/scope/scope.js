// pages/scope/scope.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    enpower: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getSetting({
      success: function (res) {
        console.log("授权结果", res)
        if (!res.authSetting.hasOwnProperty('scope.userInfo') || res.authSetting['scope.userInfo'] == false) {
          console.log("没有用户信息")
          that.setData({
            enpower: false
          })
        } else {
          that.setData({
            enpower: true
          })
          wx.switchTab({
            url: '/pages/index/index',
          })
        }
      }
    })
  },
  bindGetUserInfo: function (e) {
    // var that = this;
    // //此处授权得到userInfo
    // this.setData({
    //   flag: true
    // })
    console.log("页面跳转")
    wx.switchTab({
      url: '/pages/index/index',
    })
  }
})
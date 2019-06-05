//app.js
App({
  data:{
    childrenInfo:[]
  },
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs);
    var that=this;

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if(res.code){
          console.log("res.code："+res.code);
          wx.request({
            url: `https://api.weixin.qq.com/sns/jscode2session?appid=${this.globalData.appid}&secret=${this.globalData.secret}&js_code=${res.code}&grant_type=authorization_code`,
            method: 'POST',
            success:response=>{
              console.log("获取open_id"+response.data.openid);
              that.globalData.open_id=response.data.openid;
              wx.request({
                url: that.globalData.apiurl +'/wxLogin',
                data:{
                  openid:that.globalData.open_id
                },
                success:function(res){
                  that.childrenInfo=res.data;//保存孩子列表
                  console.log(that.childrenInfo);
                  console.log("登录成功！");
                },
                fail(error){
                  console.log("登录失败！");
                }
              })
            }
          })
        }
      }
    });
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo:null,
    appid:'wx8ababb2887c9466b',
    secret:'312a0748b934ea38f5cdbbf423d7c10d',
    open_id:null,
    apiurl:'http://127.0.0.1:8085'//本机测试点
    // phoneurl:'59.49.78.124'
  }
})
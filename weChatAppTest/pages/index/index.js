//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    childrenInfo:[],
    index:0,
    childrenName:[],
  },

  onLoad: function () {
    var that = this;
    var kisName = new Array();
    //正确执行；
    // for (var j in app.data.childrenInfo) {
    //   kisName.push(app.data.childrenInfo[j].pName);
    // }
    // this.setData({
    //   childrenInfo: app.data.childrenInfo,
    //   childrenName: kisName
    // })
    console.log("index页面！");
    // console.log(app.data.childrenInfo);
    console.log("open_id=" + app.globalData.open_id);
    wx.request({
      url: app.globalData.apiurl +'/wxLogin',
      data: {
        openid: app.globalData.open_id
      },
      success:res=>{
        if(res.data!=""){
          that.setData({
            childrenInfo:res.data
          });
          // console.log(that.childrenInfo);//undefined
          for (var i in res.data){
            kisName.push(res.data[i].pName);
          }
          // console.log(kisName);
          that.setData({
            childrenName:kisName
          })
          // console.log(that.childrenName);//undefined
        }
      }
    })
  },
  bindPickerChange(e){
    console.log(e.detail.value);
    this.setData({
      index:e.detail.value
    });
  }
})

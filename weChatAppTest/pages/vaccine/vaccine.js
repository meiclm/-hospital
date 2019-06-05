// pages/vaccine/vaccine.js
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchIndex:0,//默认列表，1=》搜索列表
    vaccineList:[],//默认结果
    resultList:{},//搜索结果
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAll();
  },
  //全部疫苗
  getAll(){
    var that=this;
    wx.request({
      url: app.globalData.apiurl + '/vaccines',
      success:res=>{
        that.setData({
          vaccineList: res.data,
          searchIndex: 0
        });

        // that.vaccineList=res.data;
        // console.log("默认列表that.vaccineList[0].name"+that.vaccineList[0].name);
        console.log("默认列表" + res.data.length);
      }
    })
  },
  //搜索疫苗
  searchVaccine:function(e){
    console.log("输入框："+e.detail.value);
    var that=this;
    wx.request({
      url: app.globalData.apiurl+'/vaccine',
      data:{
        vName: e.detail.value
      },
      header: {
        "Content-Type": "applciation/json"
      },
      method: 'GET',
      success:res=>{
        that.setData({
          resultList:res.data,
          searchIndex: 1
        });
        console.log("查询列表" + res.data.length);
      },
      fail:error=>{
        console.log(error);
      }
    })
  },
  getInputValue(e){
    if(e.detail.value==""){
      console.log("空输入框");
      this.getAll();
    }
    console.log("空输入框:" + e.detail.value);
  },
  goToInfo(e){
    console.log("导航："+e.detail);
    // app.json里面的不需要设置参数，在vaccineInfo.js的onload（options）的options.id
    wx.navigateTo({
      url: '/pages/vaccineInfo/vaccineInfo?id='+1,
    })
  },
  goBack(){
    wx.navigateBack({
      delta: 1
    });
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
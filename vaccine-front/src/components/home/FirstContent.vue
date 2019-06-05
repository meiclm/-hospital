<template>
  <div class="layout">
    <!--轮播-->
      <Carousel autoplay v-model="slide" loop>
        <CarouselItem>
          <div class="demo-carousel">
            <img class="myImg" src="../../assets/login_bg1.jpg">
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="demo-carousel"><img class="myImg" src="../../assets/login_bg2.jpg"></div>
        </CarouselItem>
      </Carousel>
    <!--图标-->
    <div class="clearfix" style="position: absolute;left: 10%;top: 20px;">
      <img class="clearfix" src="../../assets/logo_top2.gif">
    </div>
    <div class="clearfix" style="position:fixed;left: 50%;top: 250px; ">
      <img class="clearfix" style="margin-left: -250px;" src="../../assets/login_slogan.gif">
    </div>
    <div class="loginFooter clearfix">
      <div class="loginFooterLeft">
        <span>公众版</span>
        <!--<router-link >进入</router-link>-->
        <a href="javascript: void(0)" @click="publicClick">进入</a>
      </div>
      <div class="loginFooterRight">
        <span>员工通道</span>
        <a href="javascript: void(0)" @click="memberClick">进入</a>
      </div>
    </div>
  </div>
</template>

<script>
  import Store from '../../vuex/store'
  export default {
    name: "FirstContent",
    data(){
      return{
        slide:0
      }
    },
    methods:{
      memberClick(){
        if (Store.state.username!=''){
          if (Store.state.id==9) {//后台的身份
            this.$router.replace({name:"admin"});
          }else if (Store.state.id==2) {//医生的身份
            this.$router.replace({name:'info'});
          }else {
            this.$router.push({name:'login'});
          }
        } else {
          this.$router.push({name:'login'});
        }
      },
      publicClick(){
        this.$router.push({name:'general'});
      }
    }
  }
</script>

<style scoped>

  .loginFooter{
    width: 100%;
    position: fixed;
    left: 0px;
    bottom: 0px;
  }
  .clearfix{
    zoom: 1;
  }
  .loginFooter a {
    padding: 6px 20px;
    font-size: 20px;
    color: #ffffff;
    border-radius: 20px;
    border: solid 2px #ffffff;
    display: initial;
    cursor: pointer;
  }

  .loginFooter .loginFooterLeft {
    width: 50%;
    height: 70px;
    line-height: 70px;
    float: left;
    text-align: center;
    box-sizing: border-box;
    background-color: #87c301;
  }
  .loginFooter .loginFooterRight {
    width: 50%;
    height: 70px;
    line-height: 70px;
    float: left;
    box-sizing: border-box;
    text-align: center;
    background-color: #13adaf;
  }
  .loginFooter span {
    font-size: 26px;
    font-weight: normal;
    font-stretch: normal;
    letter-spacing: 0px;
    color: #ffffff;
    margin-right: 26px;
  }
  .demo-carousel{
    width: 100%;
    height: 100vh;
    overflow: hidden;
  }
  .myImg{
    width: 100%;
    height: 920px;
  }
</style>


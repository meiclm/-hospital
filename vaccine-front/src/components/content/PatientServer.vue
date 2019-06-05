<template>
    <div style="width: 100%">
      <img style="margin: 1px 10%;width: 75%" src="http://127.0.0.1:8085/upload/serverP.jpg">
      <div class="patientServer">
        <div class="server_content">
          <div class="patient_content">
            <ul>
              <li>
                <div class="li_title">
                  <div>
                    <img src="http://127.0.0.1:8085/upload/server1.png"/>
                  </div>
                  <h1>挂号</h1>
                  <div class="hr"></div>
                </div>
                <div class="li_content">
                  <ul>
                    <li @click="showAppointmentBox">网上预约挂号</li>
                    <li @click="showInfo(false)">电话预约挂号</li>
                  </ul>
                </div>
              </li>
              <li>
                <div class="li_title">
                  <div>
                    <img src="http://127.0.0.1:8085/upload/server2.png"/>
                  </div>
                  <h1>门诊</h1>
                  <div class="hr"></div>
                </div>
                <div class="li_content">
                  <span>门诊流程</span>
                  <br>
                  1、挂号&nbsp;-》&nbsp;2、门诊&nbsp;-》&nbsp;3、缴费取药
                </div>
              </li>
              <li>
                <div class="li_title">
                  <div>
                    <img src="http://127.0.0.1:8085/upload/server3.png"/>
                  </div>
                  <h1>信息</h1>
                  <div class="hr"></div>
                </div>
                <div class="li_content">
                  <ul>
                    <li @click="pointVaccine">预约预防针</li>
                    <li @click="modifiedTime">修改预约时间</li>
                  </ul>
                </div>
              </li>
              <li>
                <div class="li_title">
                  <div>
                    <img src="http://127.0.0.1:8085/upload/server4.png"/>
                  </div>
                  <h1>交通</h1>
                  <div class="hr"></div>
                </div>
                <div class="li_content">
                  <span>（主院区）地址：山西太原市尖草坪区学院路3号</span><br>
                  <span>（朔州院区）地址：山西XXXXXXXXXXXXXXXXXXX</span><br>
                  <a href="https://map.baidu.com">百度地图</a>
                </div>
              </li>
            </ul>
          </div>
          <div class="netWorkAppointment" v-if="showAppointment">
            <Appointment :fatherVaccine="appointmentVaccine"></Appointment>
          </div>
        </div>
        <!--病患的预约表-->
        <!--<div class="my_patient_content" v-if="myContent==2">-->
          <!--<div class="contentBar">-->
            <!--<span class="backBtn">< </span><span class="title-to">&nbsp;&nbsp;我的预约列表{{titleTo}}</span>-->
          <!--</div>-->
          <!--<div class="self-content">-->
            <!--<AppointmentList></AppointmentList>-->
          <!--</div>-->
        <!--</div>-->

      </div>
      <FootDiv class="my_footer"></FootDiv>
    </div>
</template>

<script>
  import store from '../../vuex/store'

  import FootDiv from './FootDiv'
  import Appointment from '../patient/Appointment'
  import AppointmentList from '../patient/AppointmentList'
    export default {
        name: "PatientServer",
        components: {
          FootDiv,
          Appointment,
          AppointmentList
          },
        data(){
          return{
            showAppointment:false,
            appointmentVaccine:-1,
            titleTo:'',
            myContent:0,//展示功能图
          }
        },
        mounted(){
          console.log("挂载myContent="+this.myContent);
          this.showWhichDiv();
          console.log("showWhichDiv()之后，挂载myContent="+this.myContent)
        },
        updated(){
          console.log("更新myContent="+this.myContent);
          this.showWhichDiv();
          console.log("showWhichDiv()之后，更新myContent="+this.myContent)
        },
        methods:{
          showAppointmentBox(){
            if(store.state.id==1&&store.state.username!=""){
                this.showAppointment=true;
                this.appointmentVaccine=-1;
            }else{
              this.$Message.info("请先登录");
            }
          },
          showInfo(nodesc){
            this.$Notice.open({
                    title: '电话预约须知',
                    desc: nodesc ? '' : '请拨打电话至xx-01014569'
                });
          },
          pointVaccine(){
            if(store.state.id==1&&store.state.username!=""){
              this.showAppointment=true;
              this.appointmentVaccine=1;
              console.log("预约疫苗！");
            }else{
              this.$Message.info("请先登录");
            }
          },
          modifiedTime(){
            if (store.state.id==1&&store.state.username!=""){
              this.$router.replace({name:'p_list'});
            } else {
              this.$Message.info("请先登录！");
            }
          },
          showWhichDiv(){
            let msg=store.state.msg;
            if (msg==-1){
              this.myContent=0;
            } else if (msg==1){
              this.myContent=1;
            } else {
              this.myContent=2;
            }
          }
        }
    }
</script>

<style scoped>
  ul{
    padding: 0;
    margin: 0;
  }
  li{
    /*list-style: none;*/
  }
  .patient_content>ul{
    /*border: 1px solid blue;*/
    width: 90%;
    margin: 15px auto;
  }
  .patient_content>ul>li{
    display: inline-block;
    list-style: none;
    width: 48%;
    height: 150px;
    /*border: 1px solid red;*/
    overflow: hidden;
  }
  li>.li_title{
    display: flex;
    align-items: center;
    padding: 5px 5%;
  }
  li>.li_title div{
    margin: 0px 1em;
  }
  li>.li_title .hr{
    width: 70%;
    height: 2px;
    background-color: #e8e8e8;
  }
  .li_content{
    width: 100%;
    padding-left: 15%;
    /*border: 1px greenyellow solid;*/
    font-size: 16px;
  }
  .li_content>ul li{
    display: inline-block;
    list-style: disc;
    cursor: pointer;
    padding: 0 25px;
  }
  .li_content>ul li:hover{
    color: cornflowerblue;
  }

  /* 显示网上预约模块 */
  .netWorkAppointment{
    width: 80%;
    /*border: 1px solid red;*/
    margin: 1px 8%;
    /*l*/
  }
  .my_footer{
    margin-top: 15px;
  }
  /*变化的病患的自个信息*/
  .my_patient_content{
    border: 1px solid blue;
    width: 85%;
    margin: 5px auto;
  }
  .contentBar>.backBtn{
    width: 45px;
    text-align: center;
    font-size: 30px;
    display: inline-block;
    cursor: pointer;
    /*border: 1px dotted saddlebrown;*/
  }
  .contentBar>.backBtn:hover{
    color: cornflowerblue;
  }
  .title-to{
    margin-left: 36px;
    font-size: 20px;
    /*border: 1px dotted saddlebrown;*/
  }
  .self-content{
    border: 1px solid coral;
  }
</style>

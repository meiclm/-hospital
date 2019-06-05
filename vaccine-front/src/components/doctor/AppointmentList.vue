<template>
    <div>
      <div class="topBar">
        <h3>未来的预约行程</h3>
        <hr>
      </div>
      <div>
        <ul class="appointment_content">
          <li v-if="appointment_list.length==0" style="margin-left: 25%;list-style: none">今日没有预约！</li>
          <li v-for="(appointment,index) in appointment_list" :key="index">
            病患姓名：{{appointment.aCase.patient.pName}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预约时间：{{appointment.aTime}}&nbsp;
          </li>
        </ul>
      </div>
    </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
  import store from '../../vuex/store'
    export default {
        name: "AppointmentList",
        data(){
          return{
            appointment_list:[],
          }
        },
        methods:{
          getAppointmentList(){
            let that=this;
            axios.get(api.doctorAppointmentList(store.state.username)).then((response)=> {
              if (response.data!=null) {
                that.appointment_list=response.data;
                console.log("查询被预约的时间");
              }
            })
          }
        },
      mounted(){
          //挂载
          this.getAppointmentList();
      }
    }
</script>

<style scoped>
  .topBar{
    margin:0px 0px 15px 0px;
  }
  .topBar>hr{
    margin-top: 5px;
    height: 3px;
    background-color: #e8e8e8;
  }
  .appointment_content{
    /*border: 1px solid red;*/
    margin: 5px 0px;
    padding: 5px 25px;
    font-size: 14px;
  }
  .appointment_content>li{
    list-style-type:circle;
    line-height: 2em;
    padding: 0 25px;
  }
  .appointment_content:nth-of-type(even){
    background-color: silver;
  }
</style>

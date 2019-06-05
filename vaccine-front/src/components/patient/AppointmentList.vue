<template>
  <div class="appointmentList">
    <div class="my_patient_content">
      <div class="contentBar">
        <span class="backBtn" @click="goToBack">< </span><span class="title-to">&nbsp;&nbsp;我的预约列表</span>
      </div>
      <div class="self-content">
        <ul class="content-list">
          <li style="border-bottom: 1px solid black;font-size: 25px;font-weight: bold">
            <span>病历号</span>
            <span>医生</span>
            <span>预约时间</span>
            <span>方式</span>
            <span>操作</span>
          </li>
          <li v-if="aList.length<=0">没有数据！</li>
          <!--<li>{{my_vaccines[5+1].name}}</li>-->
          <!--<li>{{aList[0].aCase.doctor.dName}}</li>-->
          <li v-for="(appointment,i) in aList" :key="">
            <span>{{appointment.cNo}}</span>
            <span>{{appointment.doctorName}}</span>
            <span>{{appointment.appointmentTime}}</span>
            <span>{{appointment.vNo>0?'打'+my_vaccines[(appointment.vNo+1)].name:'普通看诊'}}</span>
            <span>
              <Button class="mybtn" type="success" style="background-color: rgba(9,28,84,0.71)" @click="showAppointmentModel(appointment.aid)">修改</Button>
              <Button class="mybtn" type="error" @click="delThisAppointment(appointment.aid)">删除</Button>
            </span>
          </li>
          <li style="text-align: center;font-weight: bold;">过时的预约</li>
          <li v-for="appointment in timeOutAppointment" :key="">
            <span>{{appointment.cNo}}</span>
            <span>{{appointment.doctorName}}</span>
            <span>{{appointment.appointmentTime}}</span>
            <span>{{appointment.vNo>0?'打'+my_vaccines[(appointment.vNo+1)].name:'普通看诊'}}</span>
            <span>
              失效预约
            </span>
          </li>
        </ul>
        <hr>
      </div>
    </div>

    <Modal v-model="appointmentModel" width="560">
      <p slot="header" style="color:#e8e8e8;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>修改预约时间</span>
      </p>
      <div style="text-align:center">
        <p>原来的时间：{{thisAppointment.aTime}}</p>
        <br>
        <p>请选择新的时间：</p>
        <p>
          <DatePicker v-model="newTime.dayTime"  type="date"  placeholder="日期" style="width: 200px"></DatePicker>
          <TimePicker v-model="newTime.secondTime" :steps="[1,10]" format="HH:mm" placeholder="时间" style="width: 112px"></TimePicker>
        </p>
      </div>
      <div slot="footer">
        <Button type="success" size="large" long  @click="modifiedMyAppointment(thisAppointment.aid)">修改</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
  import Store from '../../vuex/store'
    export default {
        name: "AppointmentList",
        data(){
          return{
            my_appointmentList:[],
            aList:[],
            timeOutAppointment:[],
            my_vaccines:[],
            appointmentModel:false,
            thisAppointment:{},
            newTime:{
              dayTime:'',
              secondTime:''
            }
          }
        },
        created(){
          // this.getPatientAppointments();
          // console.log("创建列表的长度："+this.aList.length);
        },
        mounted(){
          this.getVaccines();
          this.getPAppointments();
          // this.systemTimeAndAppointTime("2019-6-15 09:00");
          // console.log("挂载列表的长度："+this.aList.length);
        },
        updated(){
          // this.getPatientAppointments();
          // console.log("更新列表的长度："+this.aList.length);
          if (this.aList.length==0){
            this.getPAppointments();
          }
        },
        methods:{
          getPAppointments(){
            let that=this;
            if (Store.state.id==1&&Store.state.username!=""){
              // console.log("加载我的列表前用户名："+Store.state.username)
              axios.get(api.getPatientAppointment(Store.state.username))
                .then((response)=>{
                  if (response.data!=null){
                    that.my_appointmentList=response.data;
                    that.aList=[];
                    that.timeOutAppointment=[];
                    for (let i=0;i<response.data.length;i++){
                      var appointmentObj={
                        aid:'',
                        cNo:'',
                        doctorName:'',
                        appointmentTime:'',
                        vNo:-1,
                        lookTime:''};
                      appointmentObj.aid=response.data[i].aid;
                      appointmentObj.cNo=response.data[i].aCase.id;
                      appointmentObj.vNo=response.data[i].vNo;
                      // console.log("疫苗参数："+response.data[i].vNo);
                      appointmentObj.doctorName=response.data[i].aCase.doctor.dName;
                      appointmentObj.appointmentTime=response.data[i].aTime;
                      appointmentObj.lookTime=response.data[i].aCase.lookTime;
                      if (that.systemTimeAndAppointTime(response.data[i].aTime)==1){
                        that.aList.push(appointmentObj);//没过时的
                      } else {
                        // console.log(response.data[i].aTime+"过时时间："+that.systemTimeAndAppointTime(response.data[i].aTime));
                        that.timeOutAppointment.push(appointmentObj);//时间过了的
                      }

                      // console.log("aList的数据："+that.appointmentObj);
                    }
                    // console.log(response.data);
                    // console.log("取得预约时间表！");
                  }
                })
            }
            // console.log("取得预约时间表:"+that.aList);
          },
          //获取疫苗信息
          getVaccines(){
            let that=this;
            axios.get(api.getVaccines()).then((response)=>{
              if (response.data!=null){
                that.my_vaccines=response.data;
                console.log("疫苗："+that.my_vaccines[0].name);
              }
            })
          },
          showAppointmentModel(aid){
            this.appointmentModel=true;
            var appointment={
              aid:'',
              aTime:'',
            }
            console.log("我的预约列表："+this.my_appointmentList.length);
            for (let i=0;i<this.my_appointmentList.length;i++){
              if (aid==this.my_appointmentList[i].aid){
                console.log(aid+"匹配成功"+this.my_appointmentList[i].aid);
                appointment.aid=this.my_appointmentList[i].aid;
                appointment.aTime=this.my_appointmentList[i].aTime;
                this.thisAppointment=appointment;
                console.log(this.thisAppointment);
              }
            }

          },
          modifiedMyAppointment(aId){
            console.log("修改时间！"+this.newTime.dayTime+","+this.newTime.secondTime);
            if (this.newTime.dayTime!=""&&this.newTime.secondTime!=""){
              console.log("类型："+typeof this.newTime)
              var myTime="";
              let that=this;
              var yAndMAndD=this.getTime(this.newTime.dayTime+",");
              myTime = yAndMAndD+" "+this.newTime.secondTime;
              axios.post(api.modifiedTime(myTime,aId))
                .then((response)=>{
                  if (response.data==1){
                    that.getPAppointments();
                    that.appointmentModel=false;
                    that.$Message.info("修改时间成功！");
                  } else {
                    that.$Message.info("预约失败！")
                  }
                })
            }
          },
          //Wed May 22 2019 00:00:00 GMT+0800 (中国标准时间) =>年-月-日
          getTime(timeString){
            // console.log("时间类型："+typeof timeString);
            let myTime='';
            let year;
            let month;
            let day;
            let timeArr=timeString.split(" ");
            // console.log("时间数组"+timeArr);
            // console.log("月："+typeof timeArr[1]);
            switch (timeArr[1]){
              case "Jan":
                month=1;
                break;
              case "Feb":
                month=2;
                break;
              case "Mar":
                month=3;
                break;
              case "Apr":
                month=4;
                break;
              case "May":
                month=5;
                break;
              case "Jun":
                month=6;
                break;
              case "Jul":
                month=7;
                break;
              case "Aug":
                month=8;
                break;
              case "Sep":
                month=9;
                break;
              case "Oct":
                month=10;
                break;
              case "Nov":
                month=11;
                break;
              case "Dec":
                month=12;
                break;
            }
            day=timeArr[2];
            year=timeArr[3];
            myTime=year+"-"+month+"-"+day;
            return myTime
          },
          goToBack(){
            this.$router.push({name:'server'});
          },
          delThisAppointment(index){
            let that=this;
            axios.post(api.delAppointment(index))
              .then((response)=>{
                if (response.data==1){
                  that.getPAppointments();
                  that.$Message.info("删除成功！");
                } else {
                  that.$Message.info("无法删除！");
                }
              });
          },
          //判断时间是否与当前时间，过了，没过
          systemTimeAndAppointTime(aTime){
            var currentTime=new Date();
            // console.log("当前时间"+currentTime);
            // console.log("月："+currentTime.getMonth()+1);
            var appointTime=new Date(aTime);
            // console.log("预约时间"+appointTime);
            // console.log("当前时间的getTime()="+currentTime.getTime());
            // console.log("预约时间的getTime()="+appointTime.getTime());
            if (appointTime.getTime()-currentTime.getTime()>=0){
              return 1;//未来时间
            }
            return -1;//过时时间
          }
        }
    }
</script>

<style scoped>
  *{
    font-size: 14px;
  }
  .contentBar{
    border: 1px solid rebeccapurple;
    margin: 5px 0px;
    /*line-height: 4em;*/
  }
  .title-to{
    font-size: 17px;
    display: inline-block;
    height: 25px;
    text-align: center;
  }
  .backBtn{
    display: inline-block;
    width: 2em;
    font-weight: bold;
    font-size: 25px;
    /*border: 1px dotted black;*/
    margin: 1px 15px;
    cursor: pointer;
    text-align: center;
  }
  .backBtn:hover{
    color: cornflowerblue;
  }
  .appointmentList{
    border: 1px solid orchid;
    margin: 5px 6%;
    width: 80%;

  }
  .content-list>li{
    list-style: none;
    line-height: 2em;
    margin: 0;
  }
  .content-list>li:not(:first-child){
    line-height: 3em;
  }
  .content-list>li:nth-child(even){
    background-color: rgba(201,199,255,0.71);
  }
  .content-list>li>span{
    display: inline-block;
    width: 19%;
    /*border: 1px solid black;*/
    text-align: center;
  }
  .content-list>li>span>.mybtn{
    /*height: 25px;*/
    width: 75px;
  }
  .mybtn:first-child{
    border: none;
  }
  .mybtn:first-child:hover{
    border: 1px solid midnightblue;
  }

</style>

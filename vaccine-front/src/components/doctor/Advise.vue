<template>
    <!--回复建议，接收建议-->
  <div style="width: 100%">
    <Collapse v-model="value1">
      <Panel v-for="(consult,index) in consults" :key="index"  style="font-size: 15px">
        <span v-if="consult.aCase.advice==undefined||consult.aCase.advice==''" style="color: red">*</span>{{consult.aCase.patient.pName}}&nbsp;&nbsp;&nbsp;&nbsp;年龄:{{calculateAge(consult.aCase.patient.pbirthday)}}岁
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<Icon type="md-call" />{{consult.aCase.patient.pparent}}&nbsp;&nbsp;{{consult.aCase.patient.pphone}}
        &nbsp;&nbsp;预约时间:{{consult.aTime}}
        <div slot="content">
          <Card :bordered="false" style="width: 90%;margin-left: 5px;">
            <p slot="title">病情描述</p>
            <p>{{consult.aCase.describe}}</p>
          </Card>
          <br>
          <Card :bordered="false" v-if="consult.aCase.advice!=''" style="width: 90%;margin-left: 5px">
            <p slot="title" style="color: #9959ff">医生建议</p>
            <p>{{consult.advice}}</p>
          </Card>
          <br>
          <div style="text-align: center">
            <Button type="primary" style="width: 150px;font-size: 15px;font-weight: bold" @click="modal1 = true" v-if="consult.aCase.advice!=undefined&&consult.aCase.advice!=''">修改</Button>
            <Button type="primary" style="width: 150px;font-size: 15px;font-weight: bold" @click="modal1 = true" v-if="consult.aCase.advice==undefined||consult.aCase.advice==''">回复</Button>
            <Modal
              v-model="modal1"
              title="回复框"
              @on-ok="ok(index)"
              @on-cancel="cancel">
              <p>病例号：{{consult.aCase.id}}</p>
              <p>
                <Input v-model="consult.aCase.advice" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..." />
              </p>
            </Modal>&nbsp;
            <Button type="error" style="width: 150px;font-size: 15px;font-weight: bold" @click="ignoreAdvice(index)">忽略</Button></div>
        </div>
      </Panel>
    </Collapse>
  </div>

</template>

<script>
  import Axois from 'axios'
  import Api from '../../server/api'
  import Store from '../../vuex/store'
    export default {
        name: "Advise",
      data(){
          return{
            value1:'1',
            modal1:false,
            consults:[],
            currentDate:'',
            thisDoctorAdvice:{
              casesId:'',
              advice:''
            }

          }
      },
      methods:{
          //获取该医生相关的病例表
          getConsults(){
            let that=this;
            // console.log("当前获取病例的医生的username："+Store.state.username)
            Axois.get(Api.currentDayAppointment(Store.state.username))
              .then(function (response) {
                that.consults=response.data;//获取数据
                // console.log("获取病例；"+that.consults)
              })
          },
        //医生回复病例表
        ok (index) {
            let that=this;
            console.log("获取特定的病例的id:"+this.consults[index].aCase.id);
            console.log("获取医生的建立:"+this.consults[index].aCase.advice);
            // if (this.consults[index].advice!='') {
            //   this.consults[index].advice+='-----------------'+new Date();
            // }
            Axois.post(Api.sendAdvise(that.consults[index].aCase.id,that.consults[index].aCase.advice))
              .then(function (response) {
                if (response.data===1) {
                  that.$Message.info('回复成功');
                  console.log("回复成功后，继续执行");
                  that.getConsults();
                }
              })
        },
        //忽略该病例，不处理
        ignoreAdvice(index){
            this.consults[index].aCase.advice='';
        },
        //计算年龄
        calculateAge(birthday){
            let age=0;
            let birthdayArr=birthday.split("-");
            let bYear=birthdayArr[0];
            let bMonth=birthdayArr[1];
            let bDay=birthdayArr[2];

            let current=new Date();
            let y=current.getFullYear();
            let m=current.getMonth()+1;
            let d=current.getDate();

            if (bYear===y){
              return age;//同年
            }else {
              let ageDiff=y-bYear;
              if (m==bMonth) {//同月
                let dayDiff=d-bDay;
                if (dayDiff<0){
                  return ageDiff-1;//还没有过生日
                } else {
                  return ageDiff;
                }
              }else {//不同月
                let monthDiff=m-bMonth;
                if (monthDiff<0){
                  return ageDiff-1;
                } else {
                  return ageDiff;
                }
              }
            }
        }
      },
      mounted() {
          console.log("获取病例+mounted()")
          this.getConsults();

      },
    }
</script>

<style scoped>

</style>

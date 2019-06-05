<template>
  <div>
    <ul>
      <li >
        <Collapse v-model="value1">
          <Panel v-for="(consult,index) in consults" :key="index" style="font-size: 15px">
            {{consult.patient.pName}}&nbsp;&nbsp;&nbsp;&nbsp;年龄:{{calculateAge(consult.patient.pbirthday)}}岁
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<Icon type="md-call" />{{consult.patient.pparent}}&nbsp;&nbsp;{{consult.patient.pphone}}
            &nbsp;<span style="margin-left: 300px">就诊时间：{{consult.lookTime}}</span>

            <div slot="content">
              <Card :bordered="false" style="width: 90%;margin-left: 5px;">
                <p slot="title">病情描述</p>
                <p>{{consult.describe}}</p>
              </Card>
              <br>
              <Card :bordered="false" v-if="consult.advice!=''" style="width: 90%;margin-left: 5px">
                <p slot="title" style="color: #9959ff">医生建议 </p>
                <p>{{consult.advice}}</p>
              </Card>
              <br>

            </div>
          </Panel>
        </Collapse>
      </li>
    </ul>
  </div>

</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
  import store from '../../vuex/store'
    export default {
        name: "HandledCases",
        data(){
          return{
            consults:[],//处理过的病例
            value1:'1',
          }
        },
        mounted(){
          this.getHandledCaseList();
        },
        methods:{
          getHandledCaseList(){
            let that=this;
            axios.get(api.getHandledCases(store.state.username))
              .then((response)=>{
                if (response.data!=""){
                  that.consults=response.data;
                }
              })
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
        }
    }
</script>

<style scoped>

</style>

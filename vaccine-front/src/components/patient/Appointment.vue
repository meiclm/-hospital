<template>
  <!-- 网上预约挂号 -->
  <div class="appointment">
    <div>
        <div class="barColor"></div>
      <h2 style="font-size:25px">网上预约挂号</h2>
      <br>
      <br>
      <div style="">
        <div class="mycontent">
            <span><span style="color: red">*</span>姓名：</span>
          <Input class="myinput" v-model="patient.pName" disabled placeholder="输入姓名" />
        </div>
        <br>
        <div class="mycontent">
            <span><span style="color: red">*</span>电话：</span>
          <Input class="myinput" v-model="patient.pphone" disabled placeholder="电话" />
        </div>
        <br>
        <div class="pick_content mycontent">
            <div>
                <span><span style="color: red">*</span>就诊方式：</span>
                <Select v-model="cases.model_option" style="width:200px">
                    <Option v-for="item in optionLook" :value="item.value" :key="item.value">{{ item.value }}</Option>
                </Select>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span v-if="showDoctor==2"><span style="color: red">*</span>病历号：<Input class="myinput" v-model="cases.cNo" placeholder="请输入你的病历号！"/></span>
            </div>
           <div class="doctor-list-content" v-if="showDoctor==1">
               <p><span style="color: red">*</span>选择一个医生：{{selectOneDoctorName}}</p>
               <ul>
                  <li class="doctor-list" v-for="(doctor,index) in doctor_list" :key="index" @click="selectVaccineOrDoctor(index,'doctor-list')">
                            <div class="doctorInfo" @click="cases.doctorUsername=doctor.dusername">
                                <img class="doctor_img" :src="doctorImg(doctor.durl)" />
                                <div>
                                    <p>{{doctor.dName}}</p>
                                    <p>从医时间:{{calculateAge(doctor.dpositionTime)}}</p>
                                </div>
                            </div>
                   </li>
             </ul>
            </div>
           <div class="vaccineList" v-if="showDoctor===2">
             <p><span style="color: red">*</span>选择疫苗：{{selectOneVaccineName}}</p>
             <ul>
               <li class="row-list" v-for="(vaccine,i) in vaccines_list" :key="vaccine.vid" @click="selectVaccineOrDoctor(i,'row-list')">
                 {{vaccine.name}}
               </li>
             </ul>
           </div>
        <br>
        <div class="timepick">
            <span><span style="color: red">*</span>选择时间：</span>
            <DatePicker v-model="cases.dayTime"  type="date"  placeholder="日期" style="width: 200px"></DatePicker>
            <TimePicker v-model="cases.secondtTime" :steps="[1,10]" format="HH:mm" placeholder="时间" style="width: 112px"></TimePicker>
            <span>&nbsp;&nbsp;&nbsp;医院营业时间为8:00-18:00</span>
        </div>
        </div>
        <div v-if="showDoctor==1">
          <span>简单描述一下病情：</span>
          <p>
            <Input v-model="cases.describe" type="textarea" placeholder="Enter something..." />
          </p>
        </div>
        <br>
        <div class="btnDiv">
            <Button type="primary" @click="addAppointment" style="width:100%;">预约</Button>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
  import store from '../../vuex/store'
  import axios from 'axios'
  import api from '../../server/api'
  export default {
    name: "Appointment",
    props:{
      fatherVaccine:Number,
      fatherModified:String
    },
    data() {
      return {
        doctor_list: [],
        vaccines_list:[],
        selectOneDoctorName:'',
        selectOneVaccineName:'',
        showDoctor:0,
        user_: {},
        patient: {},
        optionLook: [{
            value: '普通看诊'
          },
          {
            value: '打疫苗'
          }
        ],
        cases: {
          pusername: '',
          pphone: '',
          dayTime: '',
          secondtTime: '',
          model_option: '',
          dusername:'',
          cNo:'',
          vNo:-1,
          describe:''
        }
      }
    },
    methods: {
      getPatient() {
          //获取该病患的信息
        if (store.state.id == 1 && store.state.username != "") {
          this.user_ = store.state; //保存身份，用户名和密码
          let that = this;
          axios.get(api.getPatient(that.user_.username, that.user_.password))
            .then((response) => {
              if (response.data != null) {
                that.patient = response.data;
                console.log("病患的信息：" + that.patient);
              }
            })
        }else{
            console.log("未登陆");
        }
      },
      getVaccinesList(){
        let that=this;
        axios.get(api.getVaccines()).then((response)=>{
          if (response.data!=null){
            that.vaccines_list=response.data;
            console.log("获取到所以的疫苗！");
          } else {
            that.$Message.info("无法获取该医院的疫苗信息");
          }
        })
      },
      getDoctorList(){
          //获取所有的医生
          let that=this;
          axios.get(api.doctorList())
          .then((response)=>{
              if(response.data!=null){
                  that.doctor_list=response.data;
                  console.log("获取所有医生");
              }else{
                  that.$Message.info("无法获取该医院的医生基本信息");
              }
          })
      },
      doctorImg(url){
        // console.log("图片："+url);
        if (url==null||url==undefined||url==""){
          return 'http://127.0.0.1:8085'+'/upload/img/20190102.jpg';
        }
        return 'http://127.0.0.1:8085'+'/upload/img/'+url;
      },
      //计算从医时间长短
      calculateAge(birthday) {
        let age = 0;
        // birthday.split("-")
        let birthdayArr = birthday.split("-");
        // console.log("时间分割："+birthdayArr);
        let bYear = birthdayArr[0];
        let bMonth = birthdayArr[1];
        let bDay = birthdayArr[2];

        let current = new Date();
        let y = current.getFullYear();
        let m = current.getMonth() + 1;
        let d = current.getDate();

        if (bYear === y) {
          return age; //同年
        } else {
          let ageDiff = y - bYear;
          if (m == bMonth) { //同月
            let dayDiff = d - bDay;
            if (dayDiff < 0) {
              return ageDiff - 1; //还没有过生日
            } else {
              return ageDiff;
            }
          } else { //不同月
            let monthDiff = m - bMonth;
            if (monthDiff < 0) {
              return ageDiff - 1;
            } else {
              return ageDiff;
            }
          }
        }
      },
      selectVaccineOrDoctor(index,className){
        console.log(typeof className+",被选中的class的名字："+className);
        var divInfos=document.getElementsByClassName(className);
        for (let i=0;i<divInfos.length;i++){
          divInfos[i].classList.remove("infoActive");
        }
        divInfos[index].classList.add("infoActive");
        if (className=='doctor-list') {
          this.cases.dusername=this.doctor_list[index].dusername;
          this.selectOneDoctorName=this.doctor_list[index].dName;
        }else {
          this.cases.vNo=this.vaccines_list[index].vid;
          this.selectOneVaccineName=this.vaccines_list[index].name;
        }
      },

      addAppointment(){
        //病患建立预约表
        this.cases.pusername=this.patient.pusername;
        this.cases.pphone=this.patient.pphone;

        //判断时间是否是营业时间
        if (this.isRightTime(this.cases.secondtTime+"")){
          let that=this;
          let aTime=this.getTime(this.cases.dayTime+",")+" "+this.cases.secondtTime;
          if (this.showDoctor==1){//普通挂号，看病
            this.cases.cNo="";
            console.log("普通看诊！");
            axios.post(api.buildCase(that.cases.pusername,that.cases.dusername,that.cases.describe))
              .then((response)=>{
                if (response.data!=""){
                  that.cases.cNo=response.data;
                  console.log("普通看诊建号成功："+that.cases.cNo);
                  axios.post(api.addPAppointmentToDoctor(that.cases.cNo,aTime))
                    .then((resp)=>{
                      if(resp.data==1){
                        that.$Message.info("预约医生成功！");
                        that.$router.push({name:'p_list'});
                      }else {
                        that.$Message.info("预约医生失败！");
                      }
                    });
                }else {
                  that.$Message.info("挂号失败！请重新操作！");
                }
              })

          } else if (this.showDoctor=2) {
            console.log("打疫苗");
            if (this.cases.cNo==""){
              this.$Message.info("请输入病例号！若无病历号，先挂号，看医生之后再预约疫苗");
              return;
            }
            if (that.cases.cNo!=null&&that.cases.vNo>0){
              axios.post(api.addPAppointmentToVaccine(that.cases.cNo,aTime,that.cases.vNo))
                .then((response)=>{
                  if (response.data==1) {
                    that.$Message.info("预约疫苗成功！");
                    that.$router.push({name:'p_list'});
                  }else {
                    that.$Message.info("预约失败！");
                  }
                })
            }else {
              this.$Message.info("请选择一种疫苗！");
            }
          }else {
            this.$Message.info("没操作！");
          }

        }else {
          this.$Message.info("请重新选择时间！");
        }
      },
      //Wed May 22 2019 00:00:00 GMT+0800 (中国标准时间) =>年-月-日
      getTime(timeString){
        // console.log("时间类型："+typeof timeString);
        var aTime=new Date(timeString);
        let myTime='';
        let year=aTime.getFullYear();
        let month=aTime.getMonth()+1;
        let day=aTime.getDate();
        myTime=year+"-"+month+"-"+day;
        return myTime
      },
      //判断是否是营业时间
      isRightTime(secondTime){
        let secondArr=secondTime.split(":");
        let hours=secondArr[0];
        // let minutes=secondArr[1];
        if (hours>=8&&hours<18){
          return true;
        } else {
          return false;
        }
      }

    },
    mounted() {
      this.getPatient();
      this.getDoctorList();
      this.getVaccinesList();
    },
    updated(){
        if(this.cases.model_option =="普通看诊"){
            this.showDoctor=1;
        }else if (this.cases.model_option =="打疫苗") {
            this.showDoctor=2;
        }else {
          this.showDoctor=0;
        }
        console.log("appointment更新fatherVaccine="+this.fatherVaccine);
        if (this.fatherVaccine==1){
          this.cases.model_option="打疫苗";
          this.showDoctor=2;
        }

    }
  }

</script>

<style scoped>
*{
    font-size: 14px;
}
.barColor{
     width:100%;border:5px solid #e8e8e8;margin:8px 0;background-color: #e8e8e8;
}
  .myinput {
    width: 264px;
  }
  .mycontent {
    width: 90%;
    margin: 3px auto;
  }
  .pick_content {
     /*border: 1px solid blueviolet;*/
    /* display: flex; */
    /* justify-content: center */

  }
  .doctorList>ul>li{
    list-style: none;
  }
  .doctor_img{
      width: 135px;
      height: 145px;
  }
  .doctorInfo{
       width: 155px;
       /*height: 200px;*/
        border: 1px solid steelblue;
       text-align: center
  }
.doctorInfo:hover{
  border: 1px solid rgb(22, 23, 24);
}
.infoActive{
  color:slateblue;
  border: 1px solid slateblue;
}
  .doctorInfo>p:hover{
      color: #e8e8e8;
      text-decoration: underline;
  }
.doctor-list-content,.vaccineList{
    margin: 3px auto;
    /*border: 1px solid orangered;*/
}
.doctor-list-content>ul>li{
  display: inline-block;
  margin: 5px 10px;
}
.vaccineList>ul{
  /*border: 1px solid slateblue;*/
  margin: 3px 5%;
}
.row-list{
  /*border: 1px solid red;*/
  display: inline-block;
  /*width: 150px;*/
  margin-left: 25px;
  cursor: pointer;
}
.row-list:hover{
  color:slateblue;
}
.vaccineInfoActive{
  color:slateblue;
}
.btnDiv{
width:50%;
/* border: 1px solid rgb(22, 23, 24); */
}
</style>

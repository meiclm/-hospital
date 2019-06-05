<template>
    <div>
      <div style="font-size: 20px">
        <span style="margin: 10px 9px;">全部的医生</span>
        <div style="margin-left: 15%">
          <Input suffix="ios-search" v-model="searchName" placeholder="输入姓名或者工号" style="width: 25%;line-height: 2em;" @keyup.enter.native="searchDoctor" />
          <div style="">
            &nbsp;
            <span style="float: right;margin-right:5%;">人数：{{doctor_list.length}}</span>
          </div>
        </div>
      </div>
      <hr><br>
      <div class="dList">
        <ul class="ul_flex">
          <li v-for="(doctor,index) in doctor_list" :key="index" class="myli">
            <div class="doctor_content" @click="setDoctorInfo(doctor)">
              <img class="dImg" :src="doctorImg(doctor.durl)"/>
              <p>{{doctor.dusername}}</p>
              <p>{{doctor.dName}}</p>
            </div>
          </li>
        </ul>
      </div>
      <Modal v-model="doctorInfo" width="560">
        <p slot="header" style="color: rgba(188,173,255,0.71);">
          <Icon type="ios-information-circle"></Icon>
          <span>员工信息</span>
        </p>
        <div class="oneInfoCard" style="margin-left: 25px;">
          <p>工号：{{showThisDoctor.dusername}}</p>
          <p>姓名：<input v-model="showThisDoctor.dName"/></p>
          <p>密码：<input v-model="showThisDoctor.dpassword"/></p>
          <p>
            <RadioGroup class="radio-group" v-model="showThisDoctor.dgender==1?'女':'男'">
              <Radio label="男"></Radio>
              <Radio label="女"></Radio>
            </RadioGroup>
          </p>
          <p>入职时间：<input v-model="showThisDoctor.dpositionTime"/></p>
        </div>
        <div slot="footer">
          <Button type="success" size="large" long :loading="modal_loading" @click="updateDoctorInfo">确定</Button>
          <!--<br><br>-->
          <!--<Button type="error" size="large" long :loading="modal_loading" @click="delDoctor">辞退</Button>-->
        </div>
      </Modal>
    </div>

</template>

<script>
  import axios from 'axios'
  import Api from '../../server/api'
    export default {
        name: "DoctorList",
        data(){
          return{
            doctor_list:[],
            showThisDoctor:{},
            searchName:'',
            doctorInfo:false,
            modal_loading: false,
          }
        },
        mounted(){
          console.log("医生列表，mounted");
          this.getALLDoctors();
        },
        methods:{
          doctorImg(url){
            console.log("图片："+url);
            if (url==null||url==undefined||url==""){
              return 'http://127.0.0.1:8085'+'/upload/img/20190102.jpg';
            }
            return 'http://127.0.0.1:8085'+'/upload/img/'+url;
          },
          getALLDoctors(){
            let that=this;
            axios.get(Api.doctorList()).then((response)=>{
              if (response.data!=null){
                that.doctor_list=response.data;
              }
            });
          },
          searchDoctor(){
            console.log("回车事件执行！");
            let regexNumber=/^[0-9a-zA-Z]*$/g;//纯数字
            let regexChinese=/^[\u4E00-\u9FA5]+$/;//纯汉字
            if (regexNumber.test(this.searchName)){//数字=》工号
              console.log("工号");
              for (let i=0;i<this.doctor_list.length;i++){
                if (this.doctor_list[i].dusername==this.searchName) {
                  console.log("gonghao ");
                  var lis=document.getElementsByClassName("myli");
                  for (let j=0;j<lis.length;j++){
                    lis[j].classList.remove("doctor_contentActive");
                  }
                  lis[i].classList.add("doctor_contentActive");
                  // lis[i].onclick(function () {
                  //   console.log("该工号找到："+this.doctor_list[i].dusername+","+this.searchName);
                  //   lis[i].classList.add("doctor_contentActive");
                  // });
                }
              }
            } else {//名字
              if (regexChinese.test(this.searchName)){
                for (let i=0;i<this.doctor_list.length;i++){
                  if (this.doctor_list[i].dName==this.searchName) {
                    console.log("该名字找到："+this.doctor_list[i].dName+","+this.searchName);
                    var lis=document.getElementsByClassName("myli");
                    for (let j=0;j<lis.length;j++){
                      lis[j].classList.remove("doctor_contentActive");
                    }
                    lis[i].classList.add("doctor_contentActive");
                  }
                }
              }
            }
          },
          setDoctorInfo(doctor){
            this.doctorInfo=true;
            this.showThisDoctor=doctor;
          },
          updateDoctorInfo(){
            this.modal_loading = true;
            let that=this;
            axios.post(Api.updateDoctor(that.showThisDoctor.dusername,that.showThisDoctor.dpassword,that.showThisDoctor.dbirthday,that.showThisDoctor.dName,that.showThisDoctor.durl,that.showThisDoctor.dphone))
              .then((response)=>{
                if (response.data==1) {
                  setTimeout(() => {
                    that.modal_loading = false;
                    that.doctorInfo = false;
                    that.$Message.success('修改成功');
                  }, 2000);
                }else {
                  that.modal_loading = false;
                  that.doctorInfo = false;
                  that.$Message.error('修改失败');
                }
              });

          },
          // delDoctor () {
          //   this.modal_loading = true;
          //
          //   setTimeout(() => {
          //     this.modal_loading = false;
          //     this.doctorInfo = false;
          //     this.$Message.success('成功删除');
          //   }, 2000);
          // }
        }
    }
</script>

<style scoped>
div{
  font-size: 14px;
}
.dList{
  border: 1px solid rgba(175,202,255,0.71);
  margin: 5px 25px;
}
  .ul_flex{
    /*display: flex;*/
    /*flex-wrap: wrap;//换行*/
  }
  .ul_flex>li{
    display: inline-block;
    margin-top: 5px;
    list-style: none;
    margin-left: 15px;
  }
  .doctor_content{
    width: 150px;
    border: 1px solid silver;
    text-align: center;
  }
.doctor_content:hover{
  border: 1px solid yellow;
}
  .doctor_content:active{
    border: 1px solid blue;
  }
.doctor_contentActive{
  border: 1px solid yellow;
}
  .dImg{
    width: 118px;
    height: 154px;
  }
  .oneInfoCard>p{
    margin:10px 0px;
  }
</style>

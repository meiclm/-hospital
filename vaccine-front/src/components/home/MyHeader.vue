<template>
    <div>
      <div class="logoHeader">
        <img class="logo" src="../../assets/logo.gif"/>
        <div class="nav">
          <ul class="mynav">
            <li :class="objClass.general" @click="goToGeneral">首页</li>
            <li :class="objClass.server" @click="goToServer">患者服务</li>
            <li :class="objClass.note" @click="goToNote">健康小知识</li>
          </ul>
        </div>
        <div class="loginRegister" v-if="patient.password==''">
          <div class="login box">
            <span class="a" @click="showLogin=true"><Icon type="ios-contact" style="font-size: 35px"/>{{username}}</span>
          </div>
          <div class="register box">
            <span class="a" @click="goToRegister">注册</span>
          </div>
        </div>
        <div class="loginRegister" v-if="patient.password!=''">
          <Dropdown>
            <a href="javascript:void(0)">
              <span class="a"><Icon type="ios-contact" style="font-size: 35px"/>{{patient.username}}</span>
              <Icon type="ios-arrow-down"></Icon>
            </a>
            <DropdownMenu slot="list">
              <DropdownItem ><span @click="logout">注销</span></DropdownItem>
              <DropdownItem><span @click="patient_info=true">个人信息</span></DropdownItem>
            </DropdownMenu>
          </Dropdown>
          <Dropdown>
            <a href="javascript:void(0)">
              <span class="a" style="line-height: 2em">我的</span>
              <Icon type="ios-arrow-down"></Icon>
            </a>
            <DropdownMenu slot="list">
              <DropdownItem ><span @click="goToAppointmentList">预约信息</span></DropdownItem>
            </DropdownMenu>
          </Dropdown>
          <span class="myBtnList" style=""></span>
        </div>
      </div>
      <!--登录框-->
      <Modal v-model="showLogin" width="500">
        <p slot="header" style="color: #8666ff;text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>登录</span>
        </p>
        <div style="text-align:center">
          <div class="input_content">
            <span>用户名&nbsp;</span><input class="myinput" v-model="patient.username" type="text" placeholder="用户名"/>
          </div>
          <div class="input_content">
            <span>密&nbsp;&nbsp;&nbsp;码&nbsp;</span><input class="myinput" v-model="patient.password" type="password" placeholder="密码"/>
          </div>
          <span @click="goToRegister" class="registerBtn">没有账号？点击这里</span>
        </div>
        <div slot="footer">
          <Button type="success" size="large" long :loading="modal_loading" @click="login">登录</Button>
        </div>
      </Modal>

      <!--个人信息弹框-->
      <Modal v-model="patient_info" width="560">
        <p slot="header" style="color:#e8e8e8;">
          <Icon type="ios-information-circle"></Icon>
          <span>个人信息表</span>
        </p>
        <div style="">
          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;：&nbsp;<Input class="myinput" v-model="myPatientInfo.pName"/></p>
          <p>出&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生&nbsp;&nbsp;&nbsp;：&nbsp;<Input class="myinput" v-model="myPatientInfo.pbirthday" /></p>
          <p>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;&nbsp;：&nbsp;<RadioGroup v-model="sex">
                                            <Radio label="女" ></Radio>
                                            <Radio  label="男"></Radio>
                                          </RadioGroup>
          </p>
          <p>监护人姓名：<Input class="myinput" v-model="myPatientInfo.pparent"/></p>
          <p>监护人电话：<Input class="myinput" v-model="myPatientInfo.pphone"/></p>
        </div>
        <div slot="footer">
          <Button type="success" size="large" long  @click="updateMyInfo">修改个人信息</Button>
        </div>
      </Modal>
    </div>
</template>

<script>
  import Store from '../../vuex/store'
  import axios from 'axios'
  import api from '../../server/api'
    export default {
        name: "MyHeader",
      data(){
          return{
            objClass:{
              general:'li_active',
              server:'',
              note:''
            },
            username:'患者登录',
            patient:{
              id:'1',
              username:'',
              password:''
            },
            myPatientInfo:{},
            showLogin:false,
            modal_loading: false,
            patient_info:false,
            sex:'男'
          }
      },
      methods:{
        goToGeneral(){
          this.objClass.general='li_active';
          this.objClass.server='';
          this.objClass.note='';
          this.$router.replace({name:'general'})
        },
        goToServer(){
          this.objClass.general='';
          this.objClass.server='li_active';
          this.objClass.note='';
          this.$router.replace({name:'server'})
        },
        goToNote(){
          this.objClass.general='';
          this.objClass.server='';
          this.objClass.note='li_active';
          this.$router.replace({name:'note'})
        },
        logout(){
          console.log("清空vuex，路由跳转");
          Store.commit("clearUser");
          this.$router.push({
            name:'index',
          });
        },
        login () {
          this.modal_loading = true;
          if (this.patient.username.length>0&&this.patient.password.length>0){
            console.log("有输入");
            let _this=this;
            axios.get(api.plogin(_this.patient.username,_this.patient.password))
              .then(function (response) {
                if (response.data!=null){
                  Store.commit("initUser",_this.patient);
                  console.log("登录成功；"+Store.state.username);
                  _this.username=_this.patient.username;
                  _this.myPatientInfo=response.data;//得到登录成功的数据对象

                  if (_this.myPatientInfo.pgender==1){
                    _this.sex='女';
                  }else {
                    _this.sex='男';
                  }
                    setTimeout(() => {
                    _this.modal_loading = false;
                    _this.showLogin = false;
                    _this.$Message.success('Successfully delete');
                    _this.$router.push({name:'p_list'});
                  }, 2000);

                } else {
                  _this.patient.password='';
                   _this.modal_loading = false;
                  _this.$Message.info("登录失败,用户名或密码错误");
                }
              });
          }


        },
        goToRegister(){
          this.$router.push({name:'p_register'});
        },
        goToAppointmentList(){
          // this.$emit("patientVue",2);
          this.$router.push({name:'p_list'});
          console.log("预约List：")
        },
        goToMessage(){
          this.$router.push({name:'p_message'});
        },
        //更新个人信息
        updateMyInfo(){
          let that=this;
          if (this.sex=='男'){
            that.myPatientInfo.pgender=0;
          } else {
            that.myPatientInfo.pgender=1;
          }
          axios.post(api.updatePatient(that.myPatientInfo.pusername,that.myPatientInfo.pName,that.myPatientInfo.pgender,
            that.myPatientInfo.pbirthday,that.myPatientInfo.pparent,that.myPatientInfo.pphone))
            .then((response)=>{
              if (response.data!="") {
                that.$Message.info("更新成功！");
                that.myPatientInfo=response.data;
                if (_this.myPatientInfo.pgender==1){
                  that.sex.girl=true;
                }else {
                  that.sex.boy=true;
                }
              }else {
                that.$Message.info('更新失败！');
              }
            })
        }
      },
      mounted(){
        this.goToGeneral();
        if (Store.state.r_username!=""){
          this.patient.username=Store.state.r_username;
          console.log("头部挂载username！")
        }
      },
      updated(){
        // if (Store.state.r_username!=""){
        //   this.patient.username=Store.state.r_username;
        //   console.log("头部更新username！")
        // }
      }
    }
</script>

<style scoped>
  *{
    font-size: 15px;
  }
  .logoHeader{
    /*border: 1px solid blue;*/
    width: 100%;
    height: 90px;
    margin-bottom: 10px;
  }
  .logo{
    float: left;
    width: 30%;
    /*border: 1px solid orange;*/
  }
  .loginRegister{
    float: left;
    margin-top: 10px;
    padding: 10px 0px;
    cursor: pointer;
    /*border: 1px solid red;*/
    display: flex;
    flex-direction: row;
  }
  .loginRegister>div{
    margin: 0px 20px;
    /*border: 1px solid saddlebrown;*/
  }
  .register>span{
    line-height: 2em;
  }
  .a{
    color: silver;
  }
  .a:hover,.myBtnList:hover{
    color: #7c76ff ;
  }
  .registerBtn{
    color: rgba(34,76,240,0.71);
    cursor: pointer;
  }
  .registerBtn:hover{
    text-decoration: underline;
  }
  /*.myBtnList:hover{*/
    /*text-decoration: underline;*/
  /*}*/
  .myBtnList{
    color: silver;line-height: 2em;
  }
  .nav{
    width: 50%;
    height: 45px;
    float: left;
    margin-top: 40px ;
    /*border: 1px solid black;*/
  }
  .mynav{
    margin-left: 25px;

  }
  .mynav li{
    list-style: none;
    cursor: pointer;
    padding: 10px 15px;
    float: left;
    font-size: 20px;
  }
  .mynav li:hover{
    box-shadow: 0px 2.9px 0px #7c76ff ;
    color: #7c76ff;
  }
  /*标题栏被选中*/
  .li_active{
    box-shadow: 0px 2.9px 0px #7c76ff ;
    color: #7c76ff;
  }
  .myinput{
    width: 70%;
    height: 40px;
    border-radius: 5px;
  }
  .input_content{
    line-height: 65px;
  }
</style>

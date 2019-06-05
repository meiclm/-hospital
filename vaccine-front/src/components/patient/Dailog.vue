<template>
  <div class="backgroudColor">
    <div class="register">
      <div class="logo_title">
        <div class="logo">
          <div class="hospital_logo"></div>
           <img class="register_logo1" src="http://127.0.0.1:8085/upload/r_title1.gif"/>
        </div>
        <div class="title">
           <img class="register_logo2" src="http://127.0.0.1:8085/upload/r_title2.gif"/>
        </div>
      </div>
      <div class="register_box">
        <div class="inputBox">
          <input class="myinput" v-model="user_up.username" placeholder="用户名" type="text" @blur="proofUsername"/><span v-if="username_msg!=''" style="color:red;">{{username_msg}}</span><span v-else>以字母开头的6-8位字母或数字的组合</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.password"  placeholder="密码" type="password" @blur="proofPassword"/><span v-if="pwp_msg!=''" style="color:red;">{{pwp_msg}}</span><span v-else>6-12位字母或与数字的组合</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.confirm_pwd" placeholder="确认密码" type="password" @blur="compareOldAndNew"/><span style="color: red;" v-if="msg!=''">{{msg}}</span><span v-else>再次确认密码</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.pName" placeholder="姓名" type="text"/><span>姓名</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.birthday" placeholder="出生日期" type="text"/><span>出生日期：例2019-09-20</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.phone" placeholder="电话号码" type="text"/><span>监护人电话</span>
        </div>
        <div class="inputBox">
          <input class="myinput" v-model="user_up.parent" placeholder="监护人姓名" type="text"/><span>监护人姓名</span>
        </div>
        <div class="inputBox">
          <RadioGroup class="radio-group" style="color: grey;" v-model="user_up.gender">
            <Radio label="男"></Radio>
            <Radio label="女"></Radio>
          </RadioGroup>
          <span>性别</span>
        </div>

        <div class="inputBox">
          <button class="myinput btn" @click="registerPatient">注册</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
  import store from '../../vuex/store'
    export default {
        name: "Dailog",
        data(){
        return{
          user_up:{
            username:'',
            password:'',
            confirm_pwd:'',
            gender:'',
            pName:'',
            birthday: '',
            phone:'',
            parent:'',
            pgender:-1
          },
          username_msg:'',
          pwp_msg:'',
          msg:'',
        }
      },
        methods:{
          //验证该用户名
          proofUsername(){
            var regex = /^[a-z0-9A-Z]+$/;
            if (this.user_up.username.length>=6&&this.user_up.username.length<=8) {
              console.log("用户名是否正确："+regex.test(this.user_up.username));
              if (regex.test(this.user_up.username)){
                let that=this;
                axios.get(api.getPatient(that.user_up.username))
                  .then((response)=>{
                    if (response.data!=""){
                      console.log(response.data+'该用户信息：'+response.data.pusername)
                      that.username_msg='该用户名已经存在，请重新输入';
                    }
                  });
              } else {
                this.username_msg='用户名不符合规格'
              }
            }else{
              this.username_msg='用户名长度太长或者太短'
          }
            console.log("用户名："+this.user_up.username.length);
          },
          proofPassword(){
            var regex = /^[a-z0-9A-Z]+$/;
            if (this.user_up.password.length>6&&this.user_up.username.length<12){
              if (!regex.test(this.user_up.password)){
                this.pwp_msg="密码必须是数字或字母或两者组合"
              } else {
                this.pwp_msg='';
              }
            }
          },
          registerPatient(){
            let that=this;
            if (this.user_up.gender=="男"){
              this.user_up.pgender=0;
            } else {
              this.user_up.pgender=1
            }
            axios.post(api.pregister(that.user_up.username,that.user_up.password,that.user_up.pgender,that.user_up.birthday,
              that.user_up.pName,that.user_up.phone,that.user_up.parent))
              .then((response)=>{
                if (response.data==1){
                  that.$Message.info("注册成功！");
                  store.commit("initRUsername",that.user_up.username);
                  that.$router.push({name:'general'})
                }
              })
          },
          compareOldAndNew(){
            if (this.user_up.confirm_pwd!=this.user_up.password){
              this.msg="两次输入的密码不相同，请重新输入"
            } else {
              this.msg='';
            }
          }
        }
    }
</script>

<style scoped>
  *{
    font-size: 15px;
  }
  .backgroudColor{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100vh;
    /*background-color: rgba(240,226,197,0.71);*/
    background-image: url("http://127.0.0.1:8085/upload/r_background.jpg") ;
    background-repeat: no-repeat;
    background-size: 100%;
  }
  .register{
    width: 95%;
    margin: 0px auto;
    padding: 5px 0px;
    /*border: 1px black solid;*/
  }
  .logo_title{
    display: flex;
    flex-direction: row;
    margin-top: 3%;
    margin-bottom: 2%;
  }
  .logo,.title{
    width: 50%;
    border-bottom: 3px rgba(57,67,110,0.56) solid;
  }
  .register_logo1{
    width: 682px;
    /*border: 1px solid red;*/
  }
  .register_logo2{
    width: 316px;
    margin-left: 35%;
    /*border: 1px solid red;*/
  }
  .register_box{
    margin-top: 2px;
  }
  .inputBox{
    width: 90%;
    margin: 15px 10%;
    /*border: 1px red solid;*/
  }
  .inputBox>span{
    color: rgba(28,29,110,0.9);
    padding-left: 1.5em;
  }
  .radio-group{
    color: grey;
    margin-right: 28.2%;
    /*margin: 0px 5%;*/
    /*border: 1px black solid;*/
  }
  .myinput{
    width: 35%;
    height: 40px;
    border-radius: 5px;
  }
  .btn{
    background-color: rgba(41,39,188,0.73);
    border: none;
    color: white;
  }
  .btn:hover{
    color: black;
    border: 1px seashell solid;
  }
</style>

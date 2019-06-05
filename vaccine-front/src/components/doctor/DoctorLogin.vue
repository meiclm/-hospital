<template>
    <div>
      <!--图片-->
      <div class="IMGC">
        <h1 class="txt hTXT" style="color: #679bff;">中北大学附属医院-小儿复健部</h1>
        <pre class="txt pTXT" style="font-size: 15px;font-family: 'Blackadder ITC'">
            我们心中藏着天使的翅膀，
                    张开翅膀用爱的羽翼呵护每一位病人
        </pre>
        <div class="backIMG"></div>
      </div>
      <!--模块-->
      <div class="LAndRBox">
        <span style="color: red;font-size: 18px;" v-if="msg!=''">{{msg}}</span>
        <div>
          <h1>员工登陆入口</h1>
          <div class="loginBox">
            <br>
            <Input v-model="user_in.username" placeholder="账号" clearable /><br>
            <div v-text="username_error" style="width: 100%;height: 35px;color: red"></div>
            <Input type="password" v-model="user_in.password" placeholder="密码" clearable /><br>
            <div v-text="password_error" style="width: 100%;height: 35px;color: red"></div>
            <Button type="primary" style="width: 98%;margin-left: 1%;font-size: 16px" @click="login">登陆</Button><br>
            <br>
            <div style="cursor: pointer;color: blue;text-decoration: underline" @click="open(false)">忘记密码</div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

  import Axios from 'axios'
  import Api from '../../server/api'
  import Store from '../../vuex/store'
    export default {
        name: "DoctorLogin",
        data(){
          return{
            msg:'',
            username_error:'',
            password_error:'',
            user_in:{
              id:'',
              username:'',
              password:''
            }
          }
        },
      methods:{
        open (nodesc) {
          this.$Notice.open({
            title: '忘记密码',
            desc: nodesc ? '' : '请带上身份证和工作证去管理处修改回原始密码，然后自己修改新的密码 '
          });
        },
        login(){
          let that=this;
          if (this.user_in.username!=""&&this.user_in.password!=""){
            Axios.get(Api.login(that.user_in.username,that.user_in.password))
              .then(function (response) {
                if (response.data==1) {
                  console.log("登陆成功！");
                  that.user_in.id=2;
                  Store.commit('initUser',that.user_in);
                  that.$router.push({name:"info"});
                }else if (response.data==0){
                  console.log("密码错误");
                  that.password_error="密码错误！"
                } else if (response.data==9) {//后台管理者的账号
                  that.user_in.id=9;
                  Store.commit('initUser',that.user_in);
                  that.$router.replace({name:'admin'});
                }
                else {
                  console.log("该用户不存在");
                  that.username_error="账号错误！"
                  that.$Message.info("该用户不存在！")
                }
              })
          }
        },

      },
      created() {
          this.msg=this.$route.params.msg;
      }
    }
</script>

<style scoped>
  body{
    width: 100%;
    padding: 0;
    margin: 0;
  }
  .IMGC{
    width: 709px;
    height: 600px;
    float: left;
    margin-left: 36px;
    margin-top: 20px;
    /*border: 1px solid blueviolet;*/
    display: inline-block;
  }
  .backIMG{
    width: 100%;
    height: 300px;
    background:url("../../assets/login.jpg") no-repeat;
    /*border: 1px solid blue;*/
  }
  .LAndRBox{
    width: 35%;
    height: 370px;
    margin: 100px 25px;
    float: left;
    padding: 15px;
    border: 1px solid slategray;
    z-index: 1;
  }
  .loginBox{
    width: 100%;
    height: 300px;
    padding: 15px;

  }
  .txt{
    margin-left: 150px;
  }
  .rTxT:hover{
    cursor: pointer;
    text-decoration: underline;
  }
</style>

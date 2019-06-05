<template>
    <!--个人信息管理-->
  <Card style="width: 90%;margin: 20px">
    <p slot="title">
      <Icon type="ios-film-outline"></Icon>
      个人信息
    </p>
    <div>
      <ul class="leftList ">
        <li>姓&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;：<Input style="width: 250px" v-model="userInformation.dName"/></li>
        <li>性&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;：<Radio v-model="girl" disabled>女</Radio><Radio v-model="boy" disabled>男 </Radio></li>
        <li>出生日期：
          <DatePicker :value="userInformation.dbirthday" format="yyyy年MM月dd日" type="date" placeholder="Select date" style="width: 200px"></DatePicker>
        </li>
        <li>电&nbsp;&nbsp;&nbsp;话&nbsp;&nbsp;：<Input style="width: 250px" v-model="userInformation.dphone"/></li>
        <li>
          从医年龄：<Input style="width: 250px" v-model="userInformation.dpositionTime" disabled/>
        </li>
        <li>用户名&nbsp;：<Input style="width: 250px" v-model="userInformation.dusername" disabled/></li>
        <li>
          密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;：<Input style="width: 250px" v-model="userInformation.dpassword" disabled type="password"/>&nbsp;
          <Button @click="updatePwd=true" type="primary" ghost>修改密码</Button>
        </li>
      </ul>
      <ul class="rightBox leftList">
        <li style="list-style: none">
          <!--border: 1px red solid-->
          <div style="">
           <img  class="dImg" :src="imgUrl(userInformation.durl)">
          </div>
          <div>
            <input hidden ref="filElem" type="file" class="upload-file" @change="getFile">
            <!--<input type="fine" class="imgChange" value="头像"/>-->
            <span class="imgChange" @click="pointFileInput">头像更换</span>
          </div>
        </li>
      </ul>
      <Button type="success" @click="updateBtn" style="width: 200px;height: 40px;font-size: 16px;margin: 15px 30%;">更新</Button>
    </div>
    <Modal v-model="updatePwd" width="550">
        <p slot="header" style="color:#e8e8e8;text-align:center">
            <Icon type="ios-information-circle"></Icon>
            <span>修改密码</span>
        </p>
        <div class="changePass" style="">
            <p>旧的密码：<input class="myinput" type="password" v-model="pwd.old" @blur="oldPwd"/><span style="color:red;" v-text="msg_old_pwd"></span></p>

            <p>新的密码：<input class="myinput" type="password" v-model="pwd.new_pwd"/></p>

            <p>确认密码：<input class="myinput" type="password" v-model="pwd.confirm_pwd" @enter.native="updatePass" @change="changePwd"/><span style="color:red;" v-text="meg_pwd"></span></p>

        </div>
        <div slot="footer">
            <Button type="success" size="large" long  @click="updatePass">修改</Button>
        </div>
    </Modal>
  </Card>
</template>

<script>
  import Axios from 'axios'
  import Api from '../../server/api'
  import Store from '../../vuex/store'

    export default {
        name: "Information",
        data(){
          return{
            //一个医生的对象
            userInformation:{},
            girl:false,
            boy:false,
            loadingStatus:false,
// 更新密码的弹窗
            updatePwd:false,
            modal_loading:false,
            pwd:{
              old:'',
              new_pwd:'',
              confirm_pwd:''
            },
            //更新密码的输入框输入错误
            msg_old_pwd:'',
            meg_pwd:'',
            imgInfo:{},//上传图片的数据
            uploadList: []
          }
      },
        methods:{
          getInformation(){
            var that=this;

            Axios.get(Api.getDoctor(Store.state.username))
              .then(function (response) {
                if (response.data!=null){
                  that.userInformation=response.data;
                  if (that.userInformation.dgender===1) {
                    that.boy=true;
                  } else {
                    that.girl=true;
                  }
                  console.log("医生对象："+that.userInformation)
                }
              })
          },
          imgUrl(url) {
            if (url==null||url==undefined||url==""){
              return 'http://127.0.0.1:8085'+'/upload/img/20190102.jpg';
            }
          return 'http://127.0.0.1:8085'+'/upload/img/'+url;
        },
          updateBtn(){
            let that=this;
            if (this.userInformation.dphone.length===11&&that.userInformation.dusername!=''){
              Axios.post(Api.updateDoctor(that.userInformation.dusername,that.userInformation.dpassword,
                that.userInformation.dbirthday,that.userInformation.dName,that.userInformation.dphone))
                .then(function (response) {
                  if (response.data==1){
                    that.$Message.info("更新成功！");
                  } else {
                    that.$Message.info("更新失败！请重新提交")
                  }
                });
            }

        },
          oldPwd(){
          if(this.pwd.old!=this.userInformation.dpassword){
            this.msg_old_pwd="旧密码输入错误"
          }else{
            this.msg_old_pwd='';
          }
          console.log("旧密码："+this.pwd.old+",登录密码："+this.userInformation.dpassword);
        },
          changePwd(){
          if(this.pwd.new_pwd!=this.pwd.confirm_pwd){
            this.meg_pwd="两个密码不相同"
          }else{
            this.meg_pwd='';
          }
        },
          updatePass () {
                // this.modal_loading = true;
                let that=this;
                if(this.pwd.old==this.userInformation.dusername){
                  if(this.pwd.new_pwd==this.pwd.confirm_pwd){
                    Axios.post(Api.updateDoctor(that.userInformation.dusername,that.pwd.new_pwd,that.userInformation.dbirthday,
                          that.userInformation.dName,that.userInformation.durl,that.userInformation.dphone))
                          .then(function(response){
                            if(response.data==1){
                              that.$Message.success('Successfully');
                              Store.commit("clearUser");
                              that.$router.replace({name:'login'});
                            }
                          });
                  }
                }
                console.log("修改密码！");
            },
          pointFileInput(){
            this.$refs.filElem.dispatchEvent(new MouseEvent('click'))
          },
          getFile(){
            var that = this;
            const inputFile = this.$refs.filElem.files;
            console.log("文件数据："+inputFile[0].name);
            console.log("文件："+inputFile[0]);
            var fileEndString=inputFile[0].name.split(".")[1];//我的文件名叫：“补补.jpg”=>["补补","jpg"]

            if(inputFile){
              if(fileEndString !== 'jpg' &&  fileEndString!== 'png' && fileEndString !== 'gif'){
                alert('不是有效的图片文件！');
                return;
              }

              var param=new FormData();//创建表单
              param.append("username",this.userInformation.dusername);
              param.append("file",inputFile[0],inputFile.name);//向表单添加数据
              Axios.post('http://localhost:8085/doctor/uploadImg',param,{
                headers:{'Content-Type':'multipart/form-data'}
              }).then((response)=>{
                if (response.data==1){
                  that.$Message.info("上传成功！");
                  that.getInformation();
                }
              })
              // this.imgInfo = Object.assign({}, this.imgInfo, {
              //   name: inputFile.name,
              //   size: inputFile.size,
              //   lastModifiedDate: inputFile.lastModifiedDate.toLocaleString()
              // });//复制对象
              // const reader = new FileReader();
              // reader.readAsDataURL(inputFile);
              // reader.onload = function (e) {
              //   that.imgSrc = this.result;
              // }
            } else {
              return;
            }
          }

      },
        mounted() {
          if (Store.state.username!=''){
            this.getInformation();
          } else {
            this.$router.replace({name:'login'});
          }
          console.log("info挂载："+this.userInformation.durl);
      }
    }
</script>

<style scoped>
  .myinput{
    border-radius: 5px;
    border: 1px solid silver;
    height: 36px;
  }
  .leftList{
    float: left;
    /*border: 1px solid olivedrab;*/
    /*padding: 5px;*/
  }
  .rightBox{
    margin-left: 20%;
    overflow: hidden;
    width: 35%;
    text-align: center;
  }
li{
  padding: 5px 15px;
  font-size: 15px;
  font-weight: bold;
}
  input{
    width: 269px;
  }
  .changePass>p{
    font-size: 15px;
    line-height: 2em;
    margin: 5px 0px;
  }
  .imgChange{
    display: block;
    border-radius: 5px;
    width: 150px;
    text-align: center;
    line-height: 2.5em;
    background-color: rgba(179,158,255,0.71);
    margin-top: 15px;
    margin-left: 30%;
    color: grey;
    cursor: pointer;
  }
  .imgChange:hover{
    background-color: rgba(165,129,255,0.71);
    color: white;
  }
  .dImg{
    width: 118px;
    height: 154px;
    /*border: 1px solid orange;*/
  }
</style>

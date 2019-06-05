<template>
    <!--添加新成员-->
  <div>
    <div style="margin: 5px 15px;">
      <span style="font-size: 19px;">添加新成员</span>
    </div>
    <hr><br>
    <div class="my_content">
      <div>
        <!--表头-->
        <div class="tableTop">
          <ul class="top">
            <li>
              <span class="navTop">工号</span>
              <span class="navTop">密码</span>
              <span class="navTop">姓名</span>
              <span class="navTop">电话</span>
              <span class="navTop">性别(男：0 &nbsp;&nbsp;&nbsp;  女：1)</span>
              <span class="navTop">入职时间(例：2019-12-05)</span>
            </li>
            <li v-for="(d,index) in doctors" :key="index">
              <div>
                <input class="myinput" placeholder="工号" v-model="d.username"/>
                <input class="myinput" placeholder="密码" v-model="d.password"/>
                <input class="myinput" placeholder="姓名" v-model="d.name"/>
                <input class="myinput" placeholder="电话" v-model="d.phone"/>
                <input class="myinput" placeholder="性别(男：0 女：1)" v-model="d.gender"/>
                <input class="myinput" placeholder="入职时间(例：2019-12-05)" v-model="d.positionTime"/>
              </div>
            </li>
          </ul>
        </div>
        <!--输入体-->
      </div>
      <div class="btn">
        <div class="addOneTr" @click="addTableOneTr">
          <Icon type="md-add" size="25" />
        </div>
        <div>
          <Button @click="insertDoctors">提交</Button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
    export default {
        name: "AddMembers",
        template:'<input v-model="${this.doctors[0].password}}"/>{{this.doctors[0].password}}',
        data(){
          return{
            doctors:[
              {username:'',password:'123456',name:'',phone:'',gender:'',positionTime:'2019-09-01'}
            ]
          }
        },
        mounted(){
          console.log("AddMembers挂载！");
        },
        methods:{
          addTableOneTr(){
            this.doctors.push({username:'',password:'123456',name:'',phone:'',gender:'',positionTime:'2019-09-01'});
            /**
             * <input class="myinput" v-model="${this.doctors[this.doctors.length-1].username}"/>
             * <input class="myinput" v-model="${this.doctors[this.doctors.length-1].password}" />
             * <input class="myinput" v-model="${this.doctors[this.doctors.length-1].name}"/>
             * <input class="myinput" v-model="${this.doctors[this.doctors.length-1].gender}"/>
             *  <input class="myinput" v-model="${this.doctors[this.doctors.length-1].positionTime}"/>
             */
          },
          insertDoctors(){
            let that=this;
            let errorDoctor=[];
            let count=0;
            console.log("列表的长度："+this.doctors.length);
            for (let i=0;i<this.doctors.length;i++){
              that.doctors[i].gender=parseInt(that.doctors[i].gender);
              axios.post(api.register(that.doctors[i].username,that.doctors[i].password,that.doctors[i].name,that.doctors[i].gender,that.doctors[i].phone,that.doctors[i].positionTime))
                .then((response)=>{
                  if (response.data==1) {
                    count++;
                  }else {
                    errorDoctor.push(that.doctors[i]);
                  }
                });
            }
            this.doctors=[];
            if (errorDoctor.length>0){
              this.doctors=errorDoctor;
              this.$Message.error("添加成功"+count+"个，还剩"+errorDoctor.length+"个");
            } else {
              this.doctors=[{username:'',password:'123456',name:'',phone:'',gender:'',positionTime:'2019-09-01'}];
              this.$Message.info("全部添加成功");
            }
          }
        }
    }
</script>

<style scoped>
  .addOneTr{
    width: 65px;
    height: 50px;
    border: 2px dashed #e8e8e8;
    text-align: center;
    padding: 10px 0px;
  }
  .addOneTr:hover{
    border: 2px dashed silver;
  }
  .btn>div{
    display: inline-block;
    margin: 5px 15px;
  }
  .top>li{
    list-style: none;
    /*border: 1px solid blue;*/
    margin: 9px 6px;
  }
  .top>li>.navTop{
    font-size: 14px;
    display: inline-block;
    width: 200px;
    padding: 5px 15px;
    border-bottom: 1px solid silver;
    text-align: center;
  }
  .top>li>div>.myinput{
    width: 200px;
    height: 35px;
  }
</style>

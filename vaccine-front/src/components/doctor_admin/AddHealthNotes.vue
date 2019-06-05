<template>
    <div>
      <div class="top">
        <span @click="goToAll" :class="activeclassListName.allName">全部的疫苗信息</span>
        <span @click="goToAdd" :class="activeclassListName.addName">添加疫苗</span>
      </div>
      <div class="all-vaccine" v-if="btn_change==1">
        <div class="search-content">
          <Input style="width: 30%" icon="ios-search-outline" v-model="vaccineName"  @keyup.enter.native="searchByVaccineName"/>
        </div>
        <div class="all-list" v-if="showItem==1">
          <ul>
            <li v-for="(item,index) in all_vaccine_list" :key="" @click="getThisVaccine(item)">
              <div class="list-vaccine-content" >
                <div class="v v-name" >{{item.name}}</div>
                <div class="vaccine-p v">{{item.pathogenesis}}</div>
              </div>
            </li>
          </ul>
        </div>
        <div class="search-vaccine-result" v-else>
          <ul>
            <li v-for="(item,index) in vaccine_result" :key="" @click="getThisVaccine(item)">
              <div class="list-vaccine-content" >
                <div class="v v-name" >{{item.name}}</div>
                <div class="vaccine-p v">{{item.pathogenesis}}</div>
              </div>
            </li>
          </ul>
        </div>
        <!--更新信息-->
        <Modal v-model="vaccineInfo" width="560">
          <p slot="header" style="color: rgba(188,173,255,0.71);">
            <Icon type="ios-information-circle"></Icon>
            <span>疫苗信息</span>
          </p>
          <div class="oneInfoCard" style="margin-left: 25px;font-size: 15px;">
            <div><span style="font-size: 15px;font-weight: bold">中文名：</span><input v-model="showThisVaccine.name" class="inputName" placeholder="疫苗中文名"/></div>
            <div><div class="title-header">症状：</div><textarea v-model="showThisVaccine.pathogenesis"></textarea></div>
            <div><div class="title-header">预防：</div><textarea  v-model="showThisVaccine.precaution"></textarea></div>
            <div><div class="title-header">后期处理：</div><textarea  v-model="showThisVaccine.handling"></textarea></div>
          </div>
          <div slot="footer">
            <Button type="success" size="large" long  @click="updateVaccineInfo">确定</Button>
            <!--<Button type="error" size="large" long  @click="delVaccine">删除</Button>-->
          </div>
        </Modal>
      </div>

      <div class="add-vaccine">
        <div v-if="btn_change==2">
          <ul >
            <li>
              <div class="add-one">
                <div class="one-name">
                  <span style="font-weight: bold"><span class="red">*</span>疫苗名字</span><input v-model="newVaccine.name" class="inputName" placeholder="疫苗中文名"/>
                </div>
                <div class="second second-pathogenesis">
                  <span class="i"><span class="red">*</span>症状</span>
                  <textarea rows="4" placeholder="输入症状" v-model="newVaccine.pathogenesis"></textarea>
                </div>
                <div class="second second-precaution">
                  <span class="i">预防/注意</span>
                  <textarea rows="4" placeholder="输入" v-model="newVaccine.precaution"></textarea>
                </div>
                <div class="second second-handling">
                  <span class="i" >后期处理</span>
                  <textarea rows="4" placeholder="输入" v-model="newVaccine.handling"></textarea>
                </div>
              </div>
            </li>
          </ul>
          <div style="width: 100%;height: 3em;text-align: center">
            <button type="submit" class="myBtn" @click="insertNewVaccine">提交</button>
          </div>

          <!--<div class="footer-space">-->
            <!--<ul >-->
              <!--<li v-for="item in insertVaccineResult" :key="">-->
                <!--<div class="list-vaccine-content" >-->
                  <!--<div class="v v-name" >{{item.name}}</div>-->
                  <!--<div class="vaccine-p v">{{item.pathogenesis}}</div>-->
                <!--</div>-->
              <!--</li>-->
            <!--</ul>-->
          <!--</div>-->
        </div>

      </div>

    </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
    export default {
        name: "AddHealthNotes",
        data(){
          return{
            all_vaccine_list:[],
            btn_change:1,//全部列表，2：添加疫苗
            showItem:1,//1展示全部列表，2：搜索结果
            vaccineName:'',
            vaccine_result:[],
            activeclassListName:{
              allName:'span_active',
              addName:''
            },
            newVaccine:{
              name:'',
              pathogenesis:'',
              precaution:'',
              handling:''
            },
            insertVaccineResult:[],//出入一条记录，并显示出来
            vaccineInfo:false,//修改窗口展示
            showThisVaccine:{},//修改该条记录
          }
        },
        mounted(){
          this.getAllVaccine();
        },
       updated(){

      },
        methods:{
          getAllVaccine(){
            var that=this;
            axios.get(api.getVaccines()).then((res)=>{
              if (res.data!=""){
                that.all_vaccine_list=res.data;
              }
            })
          },
          searchByVaccineName(e){
            console.log(e);
            console.log("输入的信息："+this.vaccineName)
            var that=this;
            if (this.vaccineName!=""){
              axios.get(api.searchVaccine(that.vaccineName)).then((res)=>{
                if (res.data!=""){
                  that.vaccine_result=res.data;
                  that.showItem=2;
                  // console.log("返回数据")
                }
              })
            }
          },
          goBackALL(name){
            if (name==""){
              this.showItem=1;
            }
            console.log("change:=："+name);
          },
          goToAll(){
            this.btn_change=1;
            this.activeclassListName.allName='span_active';
            this.activeclassListName.addName='';
            console.log(this.activeclassListName.addName);
            console.log("获取全部疫苗页面！");
          },
          goToAdd(){
            this.btn_change=2;
            this.activeclassListName.allName='';
            this.activeclassListName.addName='span_active';
            console.log(this.activeclassListName.allName);
            console.log("添加疫苗页面！btn_change="+this.btn_change);
          },
          insertNewVaccine(){
            var that=this;
            if (this.newVaccine.name!=''&&this.newVaccine.pathogenesis!=""){
              if (this.newVaccine.precaution==""){
                this.newVaccine.precaution="暂时没有描述";
              }
              if (this.newVaccine.handling==""){
                this.newVaccine.handling="暂时没有描述";
              }
              console.log(this.newVaccine);
              axios.post(api.insertVaccine(this.newVaccine.name,this.newVaccine.pathogenesis,this.newVaccine.precaution,this.newVaccine.handling))
                .then((res)=>{
                  if (res.data==1){
                    console.log("成功！");
                    that.newVaccine.name='';
                    that.newVaccine.pathogenesis='';
                      that.newVaccine.precaution='';
                      that.newVaccine.handling='';
                    that.$Message.success("插入一个疫苗信息！");
                    that.getAllVaccine();
                    // that.insertVaccineResult.push(that.newVaccine);
                  }
                })
            }else {
              that.$Message.info("前两个输入框不能为空！");
            }
          },
          //获得该疫苗的信息
          getThisVaccine(vaccine){
            this.showThisVaccine=vaccine;
            this.vaccineInfo=true;
          },
          //修改该疫苗信息
          updateVaccineInfo(){
            var that=this;
            if (this.showThisVaccine.name!=""&&this.showThisVaccine.pathogenesis!=""&&
              this.showThisVaccine.precaution!=""&&this.showThisVaccine.handling!="") {
              axios.post(api.modifiedVaccine(this.showThisVaccine.vid,this.showThisVaccine.name,this.showThisVaccine.pathogenesis,
                this.showThisVaccine.precaution,this.showThisVaccine.handling)).then((res)=>{
                if (res.data==1){
                  that.$Message.success("修改成功！");
                  that.getAllVaccine();
                  that.vaccineInfo=false;//关闭对话框
                }
              })
            }
          },
          delVaccine(){

          }
        },
        watch:{
          vaccineName(val){
            this.goBackALL(val);
          },
        }
    }
</script>

<style scoped>
  .red{
    color: red;
  }
  .top{
    /*border-bottom: 1px solid red;*/
    font-size: 15px;
    margin: 15px 15px;
    height: 3em;
    box-shadow: 0px 2px 0px #e8e8e8;
  }
  .top>span{
    display: inline-block;
    width: 150px;
    line-height: 2.5em;
    border: 1px solid silver;
    text-align: center;
    border-radius: 5px;
    background: rgba(66,63,188,0.73);
    color: white;
    cursor: pointer;
    margin: 0 15px;
  }
  .top>span:hover{
    background: rgba(102,98,188,0.73);
  }
  .span_active{
    background: rgba(18,188,45,0.73);
  }
  /*搜索框*/
  .search-content{
    /*border: 1px solid red;*/
    text-align: center;
  }
  /*疫苗列表*/
  .all-list{
    /*border: 1px solid red;*/
    font-size: 15px;
    margin: 10px 15px;
  }
  ul>li{
    list-style: none;
    /*border: 1px solid green;*/
    padding: 0 5px;
    cursor: default;
  }
  .all-list>ul>li:nth-child(even),.search-vaccine-result>ul>li:nth-child(even){
   background-color: rgba(147,188,154,0.73);
  }
  .all-list>ul>li:hover,.search-vaccine-result>ul>li:hover{
    background-color: rgba(128,188,133,0.73);
  }
  .list-vaccine-content{
    /*border: 1px solid seagreen;*/
    margin: 2px;
    width: 90%;
    height: 40px;
    overflow: hidden;
  }
  .v{
    display: inline-block;
    line-height: 2.3em;
    overflow:hidden;/*超出长度的文字隐藏*/
    text-overflow:ellipsis;/*文字隐藏以后添加省略号*/
    white-space:nowrap;/*强制不换行*/
  }
  .v-name{
    width: 15%;
    font-weight: bold;
    /*border: yellow 1px solid;*/
  }
  .vaccine-p{
    display: inline-block;
    width: 70%;
    /*border: 1px solid red;*/
    /*line-height: 2em;*/
  }
  /*搜索结果列表*/
  .search-vaccine-result{
    /*border: 1px solid darkslateblue;*/
    font-size: 15px;
  }
  /*添加疫苗*/
  .add-vaccine{
    /*border: 1px solid orange;*/
    font-size: 15px;
  }
  .add-one>div:first-child{
    margin-top: 5px;
    height: 55px;
  }
  .add-one>div:nth-last-of-type(4){
    /*border-bottom: 1px solid indigo;*/
    box-shadow: 0px 2px 0px silver;
    margin: 3px 0;
  }
  .add-one>div span{
    /*border: 1px solid black;*/
    line-height: 2.5em;
  }
  span.i{
    display: block;
    width: 115px;
    /*border: 5px solid black;*/
    text-align: right;
    margin-right: 15px;
    font-weight: bold;
  }
  .one-name>span{
    display: inline-block;
    width: 115px;
    text-align: right;
    margin-right: 15px;
  }
  .one-name>input{
    width: 35%;
    height: 38px;
    border-radius: 5px;
    border: 1px solid silver;
  }
  .second>textarea{
    margin-left: 10%;
    width: 70%;
  }
  .myBtn{
    width: 262px;
    height: 2.5em;
    background-color: rgba(65,188,89,0.73);
    border: none;
    border-radius: 5px;
    margin-top: 25px;
    margin-left: -262px;
    color: white;
  }
  .myBtn:hover{
    background-color: rgba(18,188,45,0.73);
    font-size: 16px;
  }
  .footer-space{
    width: 100%;
    height: 100px;
    /*border: 1px solid red;*/
    margin-top: 25px;
  }
  /*弹出框*/
  .oneInfoCard>div{
    /*border: 1px solid pink;*/
  }
  .oneInfoCard>div textarea{
    width: 90%;
    height: 85px;
    margin-left: 15px;
  }
  .title-header{
    /*border: 1px solid black;*/
    font-size: 15px;
    font-weight: bold;
  }
</style>

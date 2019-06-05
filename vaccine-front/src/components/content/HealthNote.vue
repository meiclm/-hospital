<template>
  <div style="width: 100%">
    <img style="margin: 1px 10%;width: 75% " src="http://127.0.0.1:8085/upload/health.jpg">
    <div class="myContent">
      <ol>
        <li style="height: 100px">
          <span class="topTitle">天气变化注意</span>
          <!--<hr>-->
          <div class="content" >
            <!--<span class="blockTip">-->
            <!--温度-->
            <!--</span>-->
            <!--<span class="blockTip" @click="">湿度</span>-->
            <!--<span class="blockTip" @click="">干热</span>-->
            <span class="blockTip" @click="getSeason('spring')">春</span>
            <span class="blockTip" @click="getSeason('summer')">夏</span>
            <span class="blockTip" @click="getSeason('autumn')">秋</span>
            <span class="blockTip" @click="getSeason('winter')">冬</span>
          </div>
        </li>
        <li>
          <span class="topTitle">普通小儿疫苗科普</span>
          <!--<hr>-->
          <div class="content">
            <span class="blockTip" v-for="(vaccine,index) in vaccines" :key="index" @click="showTips(index)">
              {{vaccine.name}}
            </span>
          </div>
        </li>
      </ol>
    </div>
    <div v-if="vaccine_info==1" class="showVaccineContent">
      <h3 style="margin-bottom: 10px">{{showThisVaccine.name}}&nbsp;&nbsp;&nbsp;小知识</h3>
      <div style="padding: 5px 15px;" class="vaccineNotes">
        <h5>症状</h5>
        <p>{{showThisVaccine.pathogenesis}}</p>
        <h5>预防</h5>
        <p>{{showThisVaccine.precaution}}</p>
        <h5>处理</h5>
        <p>{{showThisVaccine.handling}}</p>
      </div>
    </div>
    <div v-if="seasonInfo==1" class="showVaccineContent">
      <p v-for="item in season_content">{{item.replace('""',"")}}</p>
    </div>
    <FootDiv></FootDiv>
  </div>
</template>

<script>
  import axios from 'axios'
  import api from '../../server/api'
  import FootDiv from './FootDiv'
    export default {
        name: "HealthNote",
        components: {FootDiv},
        data(){
          return{
            value1:'1',
            vaccines:[],
            // tipModel:true,
            showThisVaccine:{},
            weatherInfo:true,
            season_content:[],
            seasonInfo:0,
            vaccine_info:1
          }
        },
        mounted(){
          this.getVaccines();
          console.log("取得疫苗小知识"+this.vaccines.length);
        },
        updated(){
          console.log("更新："+this.vaccines.length);
        },
        methods:{
          showTips(index){
            this.tipModel=true;
            this.seasonInfo=0;
            this.vaccine_info=1;
            this.showThisVaccine=this.vaccines[index];
        },
          getVaccines(){
            let that=this;
            axios.get(api.getVaccines()).then((response)=>{
               if (response.data!=null){
                that.vaccines=response.data;
                 that.showThisVaccine=that.vaccines[that.vaccines.length-1];
                 that.seasonInfo=0;
                 that.vaccine_info=1;
               }
            })
          },
          getSeason(season){
            this.seasonInfo=1;
            this.vaccine_info=0;
            console.log("季节："+season);
            var that=this;
            axios.get('http://127.0.0.1:8085/patient/season?sign='+season).then((res)=>{
              // console.log(res);
              console.log(JSON.stringify(res.data));
              if (res.data!=""){
                that.season_content=JSON.stringify(res.data).split(",");
                that.season_content[0]=that.season_content[0].replace("[","");
                that.season_content[that.season_content.length-1].replace(']',"");
                for (var i=0;i<that.season_content.length;i++){
                  that.season_content[i]=that.season_content[i].replace('"',"").replace('"',"")
                }
                console.log("Json的长度："+that.season_content[that.season_content.length-1]);
              }
            })
          },
        }
    }
</script>

<style scoped>
  *{
    font-size: 15px;
  }
 span.topTitle{
   font-size: 18px;
   /*font-weight: bolder;*/
   padding-bottom: 5px;
    box-shadow: 0px 2.9px 0px #7c76ff ;
  }
  hr{
    width: 5%;
  }
  .myContent{
    width: 85%;
    margin: 1px auto;
    /*border: 1px solid lightseagreen;*/
  }
  .content{
    margin: 5px 0px;
  }
  ol{
    margin-left: 25px;
    /*border: 1px solid darkslateblue;*/
  }
ol li{
  /*display: inline-block;*/
  width: 85%;
  height: 150px;
  margin: 1px 10px;
  /*border: 1px solid darkred;*/
  overflow: hidden;
}
  ul li{
    font-size: 15px;
  }
  .content>.blockTip:hover{
     color: #7c76ff;
   }
  .blockTip{
    display: inline-block;
    font-size: 14px;
    cursor: pointer;
    width: 155px;
    margin: 9px 15px;
    /*border: 1px solid darkslateblue;*/
  }
  .showVaccineContent{
    margin:9px 9%;
    width: 80%;
    border: 2px solid silver;
    padding: 10px 25px;
  }
  .vaccineNotes>p{
    margin: 5px 0;
    line-height: 1.5em;
    text-indent: 2em;
  }
</style>

<template>
    <div style="width: 100%">
      <img style="margin: 1px 10%;width: 75% " src="http://127.0.0.1:8085/upload/index0.jpg">

      <p class="tips" style="margin: 0px 10%">->&nbsp;&nbsp;概述</p>
      <div class="demo-split myContent">
        <div class="leftBox">
          <div class="topTwo two">
            <div class="floatleft left imgContent">
              <img src="http://127.0.0.1:8085/upload/medical work.jpg"/>
              <div>
                <h1>
                  <p>
                    <img src="http://127.0.0.1:8085/upload/nimg25_1.png"/>医疗工作
                  </p>
                </h1>
                <div class="content">
                  医院以基本医疗质量和创新医疗技术为基石，通过规范医疗行为，
                  健全医疗服务质量管理体系建设，保障医疗质量与安全。（Total Quality Management)。
                </div>
              </div>

            </div>
            <div class="floatleft right imgContent">
              <img src="http://127.0.0.1:8085/upload/education work.jpg"/>
              <div>
                <h1>
                  <p>
                    <img src="http://127.0.0.1:8085/upload/nimg25_2.png"/>教育工作
                  </p>
                </h1>
                <div class="content">
                  医院拥有18个太原市住院医师规范化培训专业基地、
                  11个国家卫生计生委亚专科医师培训基地、2个太原市住院医师规范化培训技能考核中心等。
                </div>
              </div>

            </div>
          </div>
          <div class="bottomTwo two">
            <div class="floatleft left imgContent">
              <img src="http://127.0.0.1:8085/upload/scientific resarch.jpg"/>
              <div>
                <h1>
                  <p>
                    <img src="http://127.0.0.1:8085/upload/nimg25_3.png"/>科研工作
                  </p>
                </h1>
                <div class="content">
                  中北大学儿童医院紧跟国家科技创新政策要求，着眼国家“十三五”重点研究领域，
                  强化创新导向，以优化机制搭建平台为助力，推进人才梯队和创新团队建设。
                </div>

              </div>
            </div>
            <div class="floatleft right imgContent">
              <img src="http://127.0.0.1:8085/upload/hospital managment.jpg"/>
              <div>
                <h1>
                  <p>
                    <img src="http://127.0.0.1:8085/upload/nimg25_4.png"/>医院管理
                  </p>
                </h1>
                <div class="content">
                  医院近年来将努力推进“一二三工程”作为医院发展总体思路，即“一个中心”以医院全面发展为中心，
                  抓住基本医疗质量和创新医疗技术“两个基本点”，实施人才、学科、空间“三大战略”。
                </div>

              </div>
            </div>
          </div>
        </div>
        <div class="rightBox">
          <ul>
            <li>
              <img style="margin: 0px 25%" src="http://127.0.0.1:8085/upload/indexInfo.jpg"/>
              <!--<img src="../../assets/"/>-->
            </li>
            <li>
                <h1>中北大学附属医院</h1>
                <p>位于太原市尖草坪区学院路3号上兰村，始建于1918年，
              是一所集医疗、教学、科研为一体的三级甲等综合医院，是太原市医尖草坪点医院，国家卫生计生委委管医院，
              中北大学附属医院，中北大学第二临床医学院，先后获得“全国卫生系统思想政治工作先进单位”、
              “全国医院（卫生）文化建设优秀成果奖”、“全国医院（卫生）文化建设先进集体”、
              “全国医药卫生系统创先争优活动先进集体”、“太原市文明单位标兵”、
              “太原市医药卫生文化建设先进单位”、“中北大学党务和思想政治工作先进集体”、“中北大学统战工作先进党委”等荣誉。
              </p>

            </li>
          </ul>

        </div>
      </div>
      <FootDiv></FootDiv>
    </div>
</template>

<script>
  import FootDiv from './FootDiv'
    export default {
        name: "GeneralIndex",
        components: {FootDiv},
        data(){
          return{

          }
        }
    }
</script>

<style scoped>
  .floatleft{
    float: left;
  }
  .myContent{
    width: 95%;
    height: 760px;
    margin: 1px 5%;
    /*border: 1px red solid;*/
  }
  .tips{
    font-size: 15px;
    padding: 5px 1px;
  }
  .leftBox {
    float: left;
    width: 65%;
    /*border: 1px solid saddlebrown;*/
  }
  .rightBox{
    float: left;
    width: 25%;
    /*border: 1px solid blue;*/
  }
  .two{
    margin: 5px 0px;
  }
  .imgContent{
    display: block;
    width: 357px;
    height: 355px;
    overflow: hidden;
    margin: 10px 0px;
    background-color: seashell;
  }
  h1{
    padding: 15px 0 10px;
    color: #333;
    font-size: 18px;
    text-align: center;
    font-weight: 400;
  }
  .content{
    width: 357px;
    line-height: 18px;
    text-align: justify;
    padding: 0 25px;
    font-size: 12px;
  }
  .left{
    margin: 10px 25px;
  }
  .right{
    margin-left: 35px;
  }
  li{
    list-style: none;
    font-weight: 150;
    font-size: 15px;
  }
  .rightBox li{
    /*display: inline-block;*/
    padding: 5px 15px;
  }
</style>

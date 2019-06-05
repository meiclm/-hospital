<template>
    <div  class="footDiv">
      <div class="textList">
        <ul class="myUl">
          <li class="myli">
            <ul>
              <li>院长信箱</li>
              <li>隐私安全</li>
              <li>版权声明</li>
              <li>帮助信息</li>
            </ul>
            </li>
          <li class="myli">
            <ul>
              <li>院长信箱</li>
              <li>隐私安全</li>
              <li>版权声明</li>
              <li>帮助信息</li>
            </ul>
          </li>
          <li class="myli">
            <ul>
              <li>院长信箱</li>
              <li>隐私安全</li>
              <li>版权声明</li>
              <li>帮助信息</li>
            </ul>
          </li>
          <li class="myli">
            <ul>
              <li>院长信箱</li>
              <li>隐私安全</li>
              <li>版权声明</li>
              <li>帮助信息</li>
            </ul>
          </li>
          <li class="myli">
            <pre>
            ICP备案信息：晋ICP备10005257
            晋ICP备05082109
            医院地址：山西省太原市尖草坪区学院路3号
            邮编：100044
            医院总机：88326666
          </pre>
          </li>

        </ul>

      </div>

    </div>
</template>

<script>
    export default {
        name: "FootDiv"
    }
</script>

<style scoped>
  *{
    font-size: 15px;
  }
.footDiv{
  background-color: silver;
  color: black;
}
li{
  list-style: none;
  margin: 5px 0;
}
ul .myli{
  display: inline-block;
  margin: 2px 5%;
}
  .textList{
    padding: 10px 50px;
  }
</style>

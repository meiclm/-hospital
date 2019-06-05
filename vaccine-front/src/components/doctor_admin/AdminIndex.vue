<template>
  <div class="layout">
    <div style="height: 5vh">
      &nbsp;
    </div>
    <Layout>
      <Sider style="background-color: white" ref="side1" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed">
        <div style="width: 100%;border: 1px solid red;height: 75px;background-color: rgba(255,191,250,0.71)">logo展示</div>
        <Menu active-name="1-5" theme="light" width="auto" :class="menuitemClasses">
          <MenuItem name="1-4">
            <Icon type="ios-arrow-back" />
            <span @click="reback">退出</span>
          </MenuItem>
          <MenuItem name="1-5">
            <Icon type="ios-navigate"></Icon>
            <span @click="goToDoctorList">全部医生</span>
          </MenuItem>
          <MenuItem name="1-6">
            <Icon type="md-add" />
            <span @click="goToAddDoctor">新成员</span>
          </MenuItem>
          <MenuItem name="1-7">
            <Icon type="ios-book" />
            <span @click="goToAddNotes">健康tips</span>
          </MenuItem>
          <MenuItem name="1-8">
            <Icon type="md-backspace" />
            <span @click="logout">注销</span>
          </MenuItem>
        </Menu>
      </Sider>
      <Layout>
        <Header :style="{padding: 0}" class="layout-header-bar">
          <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
          <span style="margin-left: 30%;font-size: 23px;font-weight: bold">中北大学附属医院小儿复健部后台管理</span>
        </Header>
        <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
          <router-view></router-view>
        </Content>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  import Store from '../../vuex/store'
    export default {
        name: "AdminIndex",
        data(){
          return{
            isCollapsed: false,
            username:''
          }
        },
        methods:{
          menuitemClasses() {
            return [
              'menu-item',
              this.isCollapsed ? 'collapsed-menu' : ''
            ]
          },
          goToDoctorList(){
            this.$router.replace({name:"d_list"});
          },
          goToAddDoctor(){
            this.$router.replace({name:"d_add"});
          },
          goToAddNotes(){
            console.log("修改、添加健康小tip");
            this.$router.replace({name:"d_addNotes"});
          },
          logout(){
            console.log("清空vuex，路由跳转");
            Store.commit("clearUser");
            this.$router.push({
              name:'index',
            });
          },
          reback(){
            this.$router.push({name:'index'});
          },
          collapsedSider () {
            this.$refs.side1.toggleCollapse();
          }
        },
        created(){
          this.username=Store.state.username;
        },
      mounted(){
        this.goToDoctorList();
      },
      computed: {
        rotateIcon () {
          return [
            'menu-icon',
            this.isCollapsed ? 'rotate-icon' : ''
          ];
        },
        menuitemClasses () {
          return [
            'menu-item',
            this.isCollapsed ? 'collapsed-menu' : ''
          ]
        }
      },
    }
</script>

<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-header-bar{
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    width: 90%;
    height: 30px;
    background: white;
    border-radius: 3px;
    margin: 15px auto;
  }
  .menu-icon{
    transition: all .3s;
  }
  .rotate-icon{
    transform: rotate(-90deg);
  }
  .menu-item span{
    display: inline-block;
    overflow: hidden;
    width: 69px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: bottom;
    transition: width .2s ease .2s;
  }
  .menu-item i{
    transform: translateX(0px);
    transition: font-size .2s ease, transform .2s ease;
    vertical-align: middle;
    font-size: 16px;
  }
  .collapsed-menu span{
    width: 0px;
    transition: width .2s ease;
  }
  .collapsed-menu i{
    transform: translateX(5px);
    transition: font-size .2s ease .2s, transform .2s ease .2s;
    vertical-align: middle;
    font-size: 22px;
  }
</style>

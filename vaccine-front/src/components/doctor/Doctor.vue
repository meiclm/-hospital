<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="">
        <div class="layout-logo"></div>
        <div class="layout-nav">
        <MenuItem name="1">
        <Icon type="ios-navigate"></Icon>
        <span @click="goToIndex">首页</span>
        </MenuItem>
        <Submenu name="3">
        <template slot="title">
        <Icon type="ios-stats" />
        {{username}}
        </template>
        <MenuGroup>
        <MenuItem name="3-1"><span @click="goToInfo">个人中心</span></MenuItem>
        <MenuItem name="3-2"><span @click="logout">注销</span></MenuItem>
        <MenuItem name="3-3"><span @click="reback">退出</span></MenuItem>
        </MenuGroup>
        </Submenu>
        </div>
        </Menu>
      </Header>
      <Layout :style="{padding: '0 50px'}">
        <Breadcrumb :style="{margin: '16px 0'}">
          <BreadcrumbItem>Home</BreadcrumbItem>
          <BreadcrumbItem>Components</BreadcrumbItem>
          <BreadcrumbItem>Layout</BreadcrumbItem>
        </Breadcrumb>
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                <MenuItem name="1" >
                  <Icon type="md-alarm" />
                  <span @click="goToAppointment">未来行程</span>
                </MenuItem>
                <MenuItem name="2" >
                  <Icon type="ios-construct" />
                  <span @click="goToAdvise">病例单</span>
                </MenuItem>
                <MenuItem name="3" >
                  <Icon type="md-briefcase" />
                  <span @click="goToHandling">处理过的</span>
                </MenuItem>
              </Menu>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              <router-view></router-view>
            </Content>
          </Layout>
        </Content>
      </Layout>
      <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
    </Layout>
  </div>
</template>

<script>
  import Store from '../../vuex/store'
  import Link from "iview/src/mixins/link";
    export default {
        name: "Doctor",
      components:{
        Link,
      },
      data(){
          return{
            username:''
          }
      },
      methods:{
          goToIndex(){
            this.$router.push({name:'index'});
          },
          goToInfo(){
            this.$router.replace({name:'info'});
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
        goToAdvise(){
          this.$router.replace({name:'advise'});
        },
        goToAppointment(){
          console.log("list");
          this.$router.replace({name:'dappointment'});
        },
        goToHandling(){
            this.$router.replace({name:'handled'})
        }
      },
      mounted() {
          this.username=Store.state.username;
      }

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
  .layout-logo{
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }
  .layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>

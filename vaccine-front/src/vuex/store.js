//公共数据区域
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

var state={
  /**
   * id=9 =>后台管理账号
   * id=2 =>医生
   * id=1 =>病人
   */
  id:'0',
  username:'',
  password:'',
  r_username:''
}

var mutations={
  initUser(state,payload){
     state.id=payload.id,
      state.username=payload.username,
      state.password=payload.password
  },
  clearUser(state){
    state.id='',
      state.username='',
      state.password=''
  },
  initRUsername(state,u){
    state.r_username=u;
  },
  cleanRUsername(state){
    state.r_username='';
  }

}
const store= new Vuex.Store({
  state:state,
  mutations:mutations
});

export default store;

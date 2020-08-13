import Vuex from 'vuex';
import Vue from 'vue';
import router from '../../router';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.sessionStorage;
const pmemberstore = {
  state: {

  },

  actions: {
    //멤버 추가하기 
    addNewMember: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.post('/api/pmember/', {
        pid: payload.pid,
        userId: payload.userId,
        state: payload.state,
        prioirty: '',
      }, config).then(res => {

      }).catch(exp => console.log(exp))
    },

  }
}
export default pmemberstore;

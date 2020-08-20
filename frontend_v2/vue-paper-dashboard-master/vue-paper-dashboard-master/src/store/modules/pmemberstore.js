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
        alert("팀원 추가 성공!")
      }).catch(exp =>{})
    },
    changeLeader: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.put('/api/pmember/' + payload.pid, {
        pid: payload.pid,
        userId: payload.userId,
        state: '',
        prioirty: '',
      }, config).then(res => {
        alert("리더 권한을 위임했습니다.")
        router.go()
      }).catch(exp => {
        
        alert("오류가 있습니다. 올바른 아이디를 입력해주세요")
      })
    },
    leaveTeam: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.localStorage.getItem("jwt-auth-token")
        }
      }
      http.delete('/api/pmember/delete/' + payload.pid + "/" + payload.userId,config).then(res => {
        alert("delete")
        router.go(-1)
      }).catch(exp => {})
    },
  }
}
export default pmemberstore;

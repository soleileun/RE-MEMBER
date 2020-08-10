import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const recruitstore = {
  state: {
    recruits: [],
    recruit: {}
  },

  actions: {


    //구인글 리스트 가져오기
    [Constant.GET_RECRUITLIST]: (store) => {
      http.get('/api/recruit')
        .then(response => {
          // console.log(response)
          store.commit(Constant.GET_RECRUITLIST, {
            recruits: response.data
          })
        })
        .catch(exp => alert('getRecruitList처리에 실패하였습니다!.' + exp));
    },

    //rnum으로 구인글 하나 가져오기
    [Constant.GET_RECRUIT]: (store, payload) => {
      http.get('/api/recruit/' + payload.rnum)
        .then(response => {
          // console.log(response.data);
          store.commit(Constant.GET_RECRUIT, {
            recruit: response.data
          })
        })
        .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

    },

    //구인글 추가
    [Constant.ADD_RECRUIT]: (store, payload) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
    }
      console.log(payload);
      http.post('/api/recruit/', {
          pid: payload.pid,
          title: payload.title,
          contents: payload.contents,
          endDate: payload.endDate,
          makeDay: payload.makeDay,
          changeDay: payload.changeDay,
          makeId: payload.makeId,
          changeId: payload.changeId,

        },config)
        .then(() => {
          console.log('구인글 추가하였습니다.');
        })
        .catch(exp => {
          console.log('추가 실패 확인 로그');
          alert('리쿠르트 추가 처리에 실패하였습니다.' + exp);
        })
    },

    [Constant.ADD_PINTEREST]: (store, payload) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
    }
      console.log(payload);
      http.post('/api/pinterest/', {
          pid: payload.pid,
          interest: payload.interest,
        },config)
        .then(() => {
          console.log('핀인터 추가하였습니다.');
          store.dispatch(Constant.GET_RECRUITLIST);

        })
        .catch(exp => {
          console.log('추가 실패 확인 로그');
          alert('피인터레스트 추가 처리에 실패하였습니다.' + exp);
        })
    },

    //주소, 태그로 풀리스트 반환
    [Constant.SEARCH_RECRUIT_BY_TAG_ADDR]: (store, payload) => {
      console.log(payload);
      http.get('/api/recruit/selectAddrAndTag/tag=' + payload.stacks + '&addr=' + payload.addr)
        .then(response => {
          console.log('풀리스트 반환: ' + response.data);
          store.commit(Constant.GET_RECRUITLIST, {
            recruits: response.data
          })
        })
        .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
    },
    //주소로 풀리스트 반환
    [Constant.SEARCH_RECRUIT_BY_ADDR]: (store, payload) => {
      console.log(payload);
      http.get('/api/recruit/addr/sido=' + payload.sido + '&gugun=' + payload.gugun + '&dong=' + payload.dong)
        .then(response => {
          // console.log('풀리스트 반환: '+response.data);
          store.commit(Constant.GET_RECRUITLIST, {
            recruits: response.data
          })
        })
        .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
    },
    //태그로 풀리스트 반환
    [Constant.SEARCH_RECRUIT_BY_TAG]: (store, payload) => {
      console.log(payload);
      http.get('/api/recruit/selectSame/' + payload.stacks)
        .then(response => {
          // console.log('풀리스트 반환: '+response.data);
          store.commit(Constant.GET_RECRUITLIST, {
            recruits: response.data
          })
        })
        .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
    },
    //댓글 삭제
    [Constant.REMOVE_RECRUIT]: (store, payload) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
    }
        http.delete('/api/recruit/' + payload.rnum,config)
            .then(() => {
                alert('삭제하였습니다.');
                store.dispatch(Constant.GET_RECRUITLIST);

            })
            .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    },


    // //댓글 클리어
    // [Constant.CLEAR_COMMENT]: (store, payload) => {
    //     http.delete('/api//' + 'java')
    //         .then(() => {
    //             // console.log('할일 목록을 삭제 처리하였습니다.');
    //             // store.commit(Constant.CLEAR_TODO, { todoItems: [], todo: {} });
    //         })
    //         .catch(exp => alert('댓글 클리어 실패하였습니다.' + exp));
    // },



  },

  mutations: {
    [Constant.GET_RECRUITLIST]: (state, payload) => {
      // console.log('mutation' + payload.boards);
      state.recruits = payload.recruits;
    },
    [Constant.GET_RECRUIT]: (state, payload) => {
      state.recruit = payload.recruit;
    },
  },

  modules: {}
};

export default recruitstore;

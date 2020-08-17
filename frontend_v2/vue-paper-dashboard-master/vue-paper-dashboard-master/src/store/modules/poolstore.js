import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const poolstore = {
  state: {
    pools: [],
    extendpools:[],
    // pool: {}
  },
 
  actions: {

    // [Constant.GET_POOLLIST]: (store) => {
    //   http.get('/api/userinfo/getCurrList')
    //       .then(response => {
    //         // console.log(response)
    //           store.commit(Constant.GET_POOLLIST, { pools: response.data })
    //     })
    //       .catch(exp => alert('getPoolList처리에 실패하였습니다!.' + exp));
    // },
    [Constant.GET_EXTENDPOOLLIST]: (store, payload) => {
      http.get('/api/userinfo/pools/'+payload.paging + '&cnt=' + payload.cnt)
          .then(response => {
            console.log("지금 온 내용 :" +response.data)
              store.commit(Constant.GET_EXTENDPOOLLIST, { extendpools: response.data })
        })
          .catch(exp => alert('getExtendPoolList처리에 실패하였습니다!.' + exp));
    },
   
    //통합 검색
    [Constant.SEARCH_POOLIST]: (store, payload) => {
      // console.log(payload);
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
}     
      http.get('/api/userinfo/searchAll/tag=' + payload.stacks + '&addr=' + payload.addr+ '&keyword='+payload.keyword,config)
        .then(response => {
          console.log('통합 검색 조건 유저리스트 반환: ' + response.status);
          store.commit(Constant.GET_EXTENDPOOLLIST, {
            extendpools: response.data
          })
          //store.commit(Constant.GET_POOLLIST, {
          //  pools: response.data
          //})
        })
        .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
    },
    // //댓글 추가
    // [Constant.ADD_COMMENT]: (store, payload) => {
    //     console.log(payload.bno);

    //     http.post('/api/comments/', {
    //             cwriter:payload.cwriter,
    //             ccontent:payload.ccontent,
    //             bno:payload.bno,
    //             isSelected:payload.isSelected,
    //             makeDay:payload.makeDay,
    //             makeId:payload.makeId
    //         })
    //         .then(() => {
    //             console.log('추가하였습니다.');
    //             store.dispatch(Constant.GET_COMMENTLIST, {bno : payload.bno});

    //         })
    //         .catch(exp => {
    //             console.log('추가 실패 확인 로그');
    //             alert('추가 처리에 실패하였습니다.' + exp);
    //         })
    // },
   
    // //댓글 삭제
    // [Constant.REMOVE_COMMENT]: (store, payload) => {
    //     http.delete('/api/comments/delete/' + payload.cno)
    //         .then(() => {
    //             alert('삭제하였습니다.');
    //             store.dispatch(Constant.GET_COMMENTLIST, {bno : payload.bno});

    //         })
    //         .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    // },

    //이거 구현하고 게시글 삭제보다 선행시키기, 공지, 질게 복사

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
    // [Constant.GET_POOLLIST]: (state, payload) => {
    //     console.log('mutation' + payload.pools);
    //     state.pools = payload.pools.filter(item=>item.responsibility!=="admin");
    // },
    [Constant.GET_EXTENDPOOLLIST]: (state, payload) => {
      console.log('mutation' + payload.extendpools);
      state.extendpools = payload.extendpools;
  },

  },

  modules: {
  }
};

export default poolstore;

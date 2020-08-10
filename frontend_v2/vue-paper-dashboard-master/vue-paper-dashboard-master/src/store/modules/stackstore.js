import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
// const storage = window.sessionStorage;

const stackstore = {
  state: {
      // pools: [],
      sidolist: [],
      gugunlist: [],
      donglist: [],
      // pool: {}
  },
 
  actions: {
    //게시판

    // //조건에 맞는 인재풀 리스트 가져오기
    // [Constant.GET_FILTERED_POOLLIST]: (store,payload) => {
    //   http.get('/api/board/statesearching/'+ payload.bstate)
    //       .then(response => {
    //         // console.log(response)
    //           store.commit(Constant.GET_FILTERED_POOLLIST, { pools: response.data })
    //     })
    //       .catch(exp => alert('getFilteredPoolList처리에 실패하였습니다!!' + exp));
    // },
    //시도부르기
    [Constant.GET_SIDOLIST]: (store) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
}
      http.get('/api/addr/getSido',config)
          .then(response => {
            // console.log(response)
              store.commit(Constant.GET_SIDOLIST, { sidolist: response.data })
        })
          .catch(exp => alert('getsidoList처리에 실패하였습니다!!' + exp));
    },
    //시도로 구군 부르기
    [Constant.GET_GUGUNLIST]: (store,payload) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
}
      http.get('/api/addr/getGugun/sido='+ payload.sido,config)
          .then(response => {
            // console.log(response)
              store.commit(Constant.GET_GUGUNLIST, { gugunlist: response.data })
        })
          .catch(exp => alert('get구군PoolList처리에 실패하였습니다!!' + exp));
    },
    //구군으로 동 부르기
    [Constant.GET_DONGLIST]: (store,payload) => {
      const config = {
        headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
}
      http.get('/api/addr/getDong/sido='+payload.sido+'&gugun='+ payload.gugun,config)
          .then(response => {
            // console.log('반응:'+response)
              store.commit(Constant.GET_DONGLIST, { donglist: response.data })
        })
          .catch(exp => alert('get동lList처리에 실패하였습니다!!' + exp));
    },

    // //bno으로 게시글 하나 가져오기
    // [Constant.GET_BOARD]: (store, payload) => {
    //     http.get('/api/board/no/' + payload.bno)
    //         .then(response => {
    //             // console.log(response.data);
    //             store.commit(Constant.GET_BOARD, { board: response.data })})
    //         .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

    // },
    // //게시글 추가
    // [Constant.ADD_BOARD]: (store, payload) => {
    //     // console.log(payload.bstate);

    //     http.post('/api/board/', {
    //             // bno : payload.bno,
    //             bwriter : payload.bwriter,
    //             btitle : payload.btitle,
    //             bcontent : payload.bcontent,
    //             bview : payload.bview,
    //             bfile : payload.bfile,
    //             bstate : payload.bstate,
    //             makeDay : payload.makeDay,
    //             // changeDay : payload.changeDay,
    //             makeId : payload.makeId,
    //             // changeId : payload.changeId
    //         })
    //         .then(() => {
    //             console.log('추가하였습니다.');
    //             store.dispatch(Constant.GET_BOARDLIST, {bstate : payload.bstate});

    //         })
    //         .catch(exp => {
    //             console.log('추가 실패 확인 로그');
    //             alert('추가 처리에 실패하였습니다.' + exp);
    //         })
    // },
    // //게시글 수정
    // [Constant.MODIFY_BOARD]: (store, payload) => {
    //     // console.log(payload);

    //     http.put('/api/board/change/' + payload.board.bno, {
    //             bno:payload.board.bno,
    //             bwriter: payload.board.bwriter,
    //             btitle: payload.board.btitle,
    //             bcontent: payload.board.bcontent,
    //             bview : payload.board.bview,
    //             bfile : payload.board.bfile,
    //             bstate : payload.board.bstate,
    //             makeDay : payload.board.makeDay,
    //             changeDay : new Date(),
    //             makeId : payload.board.makeId,
    //             changeId : payload.board.changeId //세션 id
    //         })
    //         .then(() => {
    //             // console.log('수정하였습니다.'+ response.data);
    //             store.dispatch(Constant.GET_BOARD, {bno : payload.board.bno});
    //         })
    //         .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
    // },
    // //게시글 삭제
    // [Constant.REMOVE_BOARD]: (store, payload) => {
    //     http.delete('/api/board/delete/' + payload.bno)
    //         .then(() => {
    //             alert('삭제하였습니다.');
    //             store.dispatch(Constant.GET_BOARDLIST, {bstate : payload.bstate});

    //         })
    //         .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    // },
    //주소, 태그로 풀리스트 반환
    [Constant.SEARCH_POOL_BY_TAG_ADDR]: (store,payload) => {
        // console.log(payload);
        http.get('/api/usertag/selectAddrAndTag/tag='+payload.stacks+'&addr=' + payload.addr)
            .then(response => {
                console.log('풀리스트 반환: '+response.data);
                store.commit(Constant.GET_POOLLIST, { pools : response.data })
          })
            .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
      },
    //주소로 풀리스트 반환
    [Constant.SEARCH_POOL_BY_ADDR]: (store,payload) => {
        // console.log(payload);
        http.get('/api/userinfo/addr/sido='+payload.sido+'&gugun='+payload.gugun+'&dong=' + payload.dong)
            .then(response => {
                // console.log('풀리스트 반환: '+response.data);
                store.commit(Constant.GET_POOLLIST, { pools : response.data })
          })
            .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
      },
    //태그로 풀리스트 반환
    [Constant.SEARCH_POOL_BY_TAG]: (store,payload) => {
        // console.log(payload);
        http.get('/api/usertag/selectSame/'+payload.stacks)
            .then(response => {
                // console.log('풀리스트 반환: '+response.data);
                store.commit(Constant.GET_POOLLIST, { pools : response.data })
          })
            .catch(exp => alert('풀리스트 반환 처리에 실패하였습니다.' + exp));
      },

    
    // //작성자로 찾기
    // [Constant.SEARCH_BOARD_WRITER]: (store,payload) => {
    //     console.log(payload);
    //     http.get('/api/board/typesearch/writer='+ payload.bwriter + '&bstate=' + payload.bstate)
    //         .then(response => {
    //             console.log(response.data);
    //             store.commit(Constant.GET_BOARDLIST, { boards: response.data })
    //       })
    //         .catch(exp => alert('search by title 처리에 실패하였습니다.' + exp));
    //   },

    // [Constant.COMPLETE_TODO]: (store, payload) => {
    //     http.put('/api/qna/' + payload.qna_no)
    //         .then(() => {
    //             console.log('완료 처리하였습니다.');
    //             store.dispatch(Constant.GET_TODOLIST);
    //         })
    //         .catch(exp => alert('완료 처리에 실패하였습니다.' + exp));
    // },
    
  },

  mutations: {
    // [Constant.GET_FILTERED_POOLLIST]: (state, payload) => {
    //     // console.log('mutation' + payload.boards);
    //     state.pools = payload.pools;
    // },
    [Constant.GET_SIDOLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.sidolist = payload.sidolist;
    },
    [Constant.GET_GUGUNLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.gugunlist = payload.gugunlist;
    },
    [Constant.GET_DONGLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.donglist = payload.donglist;
    },
    // [Constant.GET_BOARD]: (state, payload) => {
    //     state.board = payload.board;
    // },
    // [Constant.CLEAR_TODO]: (state, payload) => {
    //     state.board = payload.todo;
    //     state.boards = payload.todoItems;
    // }
  },

  modules: {
  }
};

export default stackstore;

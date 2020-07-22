import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const boardstore = {
  state: {
      boards: [],
      board: {}
  },
 
  actions: {
    //게시판

    //bstate에 맞는 리스트 가져오기
    [Constant.GET_BOARDLIST]: (store,payload) => {
      http.get('/api/board/statesearching/'+ payload.bstate)
          .then(response => store.commit(Constant.GET_BOARDLIST, { boards: response.data }))
          .catch(exp => alert('getBoardList처리에 실패하였습니다.' + exp));
    },
    //bno으로 게시글 하나 가져오기
    [Constant.GET_BOARD]: (store, payload) => {
        http.get('/api/board/no/' + payload.bno)
            .then(response => store.commit(Constant.GET_BOARD, { board: response.data }))
            .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

    },
    //게시글 추가
    [Constant.ADD_BOARD]: (store, payload) => {
        console.log(payload.bstate);

        http.post('/api/board/', {
                // bno : payload.bno,
                bwriter : payload.bwriter,
                btitle : payload.btitle,
                bcontent : payload.bcontent,
                bview : payload.bview,
                bfile : payload.bfile,
                bstate : payload.bstate,
                makeday : payload.makeday,
                // changeday : payload.changeday,
                makeid : payload.makeid,
                // changeid : payload.changeid
            })
            .then(() => {
                console.log('추가하였습니다.');
            })
            .catch(exp => {
                console.log('추가 실패 확인 로그');
                alert('추가 처리에 실패하였습니다.' + exp);
            })
    },
    //게시글 수정
    [Constant.MODIFY_BOARD]: (store, payload) => {
        http.put('/api/board/' + payload.board.bno, {
            qna_content: payload.board.qna_content,
            qna_title: payload.board.qna_title,
            qna_userid: payload.board.qna_userid,
            qna_datetime : payload.board.qna_datetime
            })
            .then(() => {
                console.log('수정하였습니다.');
                store.dispatch(Constant.GET_TODOLIST);
            })
            .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
    },
    //게시글 삭제
    [Constant.REMOVE_BOARD]: (store, payload) => {
        http.delete('/api/board/' + payload.qna_no)
            .then(() => {
                console.log('삭제하였습니다.');
                store.dispatch(Constant.GET_TODOLIST);
            })
            .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    },
    // [Constant.COMPLETE_TODO]: (store, payload) => {
    //     http.put('/api/qna/' + payload.qna_no)
    //         .then(() => {
    //             console.log('완료 처리하였습니다.');
    //             store.dispatch(Constant.GET_TODOLIST);
    //         })
    //         .catch(exp => alert('완료 처리에 실패하였습니다.' + exp));
    // },
    // [Constant.CLEAR_TODO]: (store) => {
    //     http.delete('/api/qna/' + 'java')
    //         .then(() => {
    //             console.log('할일 목록을 삭제 처리하였습니다.');
    //             store.commit(Constant.CLEAR_TODO, { todoItems: [], todo: {} });
    //         })
    //         .catch(exp => alert('할일 목록을 삭제 처리에 실패하였습니다.' + exp));
    // },
  },

  mutations: {
    [Constant.GET_BOARDLIST]: (state, payload) => {
            state.boards = payload.boards;
    },
    [Constant.GET_BOARD]: (state, payload) => {
        state.board = payload.board;
    },
    // [Constant.CLEAR_TODO]: (state, payload) => {
    //     state.board = payload.todo;
    //     state.boards = payload.todoItems;
    // }
  },

  modules: {
  }
};

export default boardstore;

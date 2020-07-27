import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const commentstore = {
  state: {
    comments: [],
    comment: {}
  },
 
  actions: {
    //댓글

    //bno게시글의 댓글 가져오기
    [Constant.GET_COMMENTLIST]: (store,payload) => {
      http.get('/api/board/getcomments/'+ payload.bno)
          .then(response => {
            console.log(response)
              store.commit(Constant.GET_COMMENTLIST, { comments: response.data })
        })
          .catch(exp => alert('getBoardList처리에 실패하였습니다!.' + exp));
    },
   
    //댓글 추가
    [Constant.ADD_COMMENT]: (store, payload) => {
        console.log(payload.bno);

        http.post('/api/comments/', {
                cwriter:payload.cwriter,
                ccontent:payload.ccontent,
                bno:payload.bno,
                isSelected:payload.isSelected,
                makeDay:payload.makeDay,
                makeId:payload.makeId
            })
            .then(() => {
                console.log('추가하였습니다.');
                store.dispatch(Constant.GET_COMMENTLIST, {bno : payload.bno});

            })
            .catch(exp => {
                console.log('추가 실패 확인 로그');
                alert('추가 처리에 실패하였습니다.' + exp);
            })
    },
   
    //댓글 삭제
    [Constant.REMOVE_COMMENT]: (store, payload) => {
        http.delete('/api/comments/delete/' + payload.cno)
            .then(() => {
                alert('삭제하였습니다.');
                store.dispatch(Constant.GET_COMMENTLIST, {bno : payload.bno});

            })
            .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    },

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
    [Constant.GET_COMMENTLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.comments = payload.comments;
    },
    
  },

  modules: {
  }
};

export default commentstore;

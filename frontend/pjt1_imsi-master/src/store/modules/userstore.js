import Vuex from 'vuex';
import Vue from 'vue';

import ConstantUser from '../../ConstantUser.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const userstore = {
    state: {
        user: {
            id: '',
            nick: '',
        },
        followings: [],
        messageList: [],
        news: [],
        logined: true,
    },

    actions: {
        //게시판

        //bstate에 맞는 리스트 가져오기
        [ConstantUser.GET_BOARDLIST]: (store, payload) => {
            http.get('/api/board/statesearching/' + payload.bstate)
                .then(response => store.commit(ConstantUser.GET_BOARDLIST, { boards: response.data }))
                .catch(exp => alert('getBoardList처리에 실패하였습니다.' + exp));
        },
        //게시글 추가
        // [Constant.ADD_BOARD]: (store, payload) => {
        //     http.post('/api/board/', {
        //         // bno : payload.bno,
        //         bwriter: payload.bwriter,
        //         btitle: payload.btitle,
        //         bcontent: payload.bcontent,
        //         bview: payload.bview,
        //         bfile: payload.bfile,
        //         bstate: payload.bstate,
        //         makeday: payload.makeday,
        //         // changeday : payload.changeday,
        //         makeid: payload.makeid,
        //         // changeid : payload.changeid
        //     })
        //         .then(() => {
        //             console.log('추가하였습니다.');
        //         })
        //         .catch(exp => {
        //             console.log('추가 실패 확인 로그');
        //             alert('추가 처리에 실패하였습니다.' + exp);
        //         })
        // },
        // //게시글 수정
        // [Constant.MODIFY_BOARD]: (store, payload) => {
        //     http.put('/api/board/' + payload.board.bno, {
        //         qna_content: payload.board.qna_content,
        //         qna_title: payload.board.qna_title,
        //         qna_userid: payload.board.qna_userid,
        //         qna_datetime: payload.board.qna_datetime
        //     })
        //         .then(() => {
        //             console.log('수정하였습니다.');
        //             store.dispatch(Constant.GET_TODOLIST);
        //         })
        //         .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
        // },
        // //게시글 삭제
        // [Constant.REMOVE_BOARD]: (store, payload) => {
        //     http.delete('/api/board/' + payload.qna_no)
        //         .then(() => {
        //             console.log('삭제하였습니다.');
        //             store.dispatch(Constant.GET_TODOLIST);
        //         })
        //         .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

        // },
    },

    mutations: {
    },

    modules: {
    }
};

export default userstore;

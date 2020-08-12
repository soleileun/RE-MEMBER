import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const issuestore = {
    state: {
        issues: [],
        issues_created: [],
        issues_ongoing: [],
        issues_done: [],
        issue: {}
    },

    actions: {
        //issue 리스트 
        [Constant.GET_ISSUELIST]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.get('/api/issue/pid/' + payload.pid, config)
                .then(response => {
                    // console.log(response)
                    store.commit(Constant.GET_ISSUELIST, { issues: response.data })
                })
                .catch(exp => alert('getISSUEList처리에 실패하였습니다!!' + exp));
        },

        //issue 리스트 (state 별로 들고오기)
        [Constant.GET_ISSUELIST_BY_STATE]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.get('/api/issue/search/pid=' + payload.pid + '&issuestate=' + payload.issuestate, config)
                .then(response => {
                    // console.log(response)
                    store.commit(Constant.GET_ISSUELIST_BY_STATE, { issues: response.data, issuestate: payload.issuestate })
                })
                .catch(exp => alert('getISSUEList처리에 실패하였습니다!!' + exp));
        },
        //issueid로 게시글 하나 가져오기
        [Constant.GET_ISSUE]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.get('/api/issue/no/' + payload.issueid, config)
                .then(response => {
                    // console.log(response.data);
                    store.commit(Constant.GET_ISSUE, { issue: response.data })
                })
                .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

        },
        //이슈 추가
        [Constant.ADD_ISSUE]: (store, payload) => {
            // console.log(payload.bstate);
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.post('/api/issue/', {
                    pid: payload.pid,
                    uid: payload.uid,
                    response: payload.response,
                    issuetype: payload.issuetype,
                    issuestate: payload.issuestate,
                    issuetitle: payload.issuetitle,
                    issuecontent: payload.issuecontent,
                    prework: payload.prework,
                    priority: payload.priority,
                    makeDay: payload.makeDay,
                    startDay: payload.startDay,
                    endDay: payload.endDay,
                    changeDay: payload.changeDay,
                }, config)
                .then(() => {
                    console.log('추가하였습니다.');
                    store.dispatch(Constant.GET_ISSUELIST, { pid: payload.pid });

                })
                .catch(exp => {
                    console.log('추가 실패 확인 로그');
                    alert('추가 처리에 실패하였습니다.' + exp);
                })
        },
        //이슈 수정
        [Constant.MODIFY_ISSUE]: (store, payload) => {
            // console.log(payload);
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.put('/api/issue/change/' + payload.board.bno, {
                    bno: payload.board.bno,
                    bwriter: payload.board.bwriter,
                    btitle: payload.board.btitle,
                    bcontent: payload.board.bcontent,
                    bview: payload.board.bview,
                    bfile: payload.board.bfile,
                    bstate: payload.board.bstate,
                    makeDay: payload.board.makeDay,
                    changeDay: new Date(),
                    makeId: payload.board.makeId,
                    changeId: payload.board.changeId //세션 id
                }, config)
                .then(() => {
                    // console.log('수정하였습니다.'+ response.data);
                    store.dispatch(Constant.GET_BOARD, { bno: payload.board.bno });
                })
                .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
        },


        [Constant.READ_BOARD]: (_store, payload) => {
            // console.log(payload);
            console.log("받아옴? : " + payload.bno);
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.put('/api/board/read/' + payload.bno, config)
                .then(() => {
                    console.log('수정하였습니다.' + response.data);
                    //store.dispatch(Constant.READ_BOARD, {bno : payload.bno});
                })
                .catch(exp => alert('읽음 처리에 실패하였습니다.' + exp));
        },

        // //이슈글 삭제
        // [Constant.REMOVE_BOARD]: (store, payload) => {
        //     const config = {
        //         headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
        //     }
        //     http.delete('/api/board/delete/' + payload.bno, config)
        //         .then(() => {
        //             alert('삭제하였습니다.');
        //             store.dispatch(Constant.GET_BOARDLIST, { bstate: payload.bstate });

        //         })
        //         .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

        // },
        // //제목으로 찾기
        // [Constant.SEARCH_BOARD_TITLE]: (store, payload) => {
        //     console.log(payload);
        //     const config = {
        //         headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
        //     }
        //     http.get('/api/board/typesearch/btitle=' + payload.btitle + '&bstate=' + payload.bstate, config)
        //         .then(response => {
        //             console.log(response.data);
        //             store.commit(Constant.GET_BOARDLIST, { boards: response.data })
        //         })
        //         .catch(exp => alert('search by title 처리에 실패하였습니다.' + exp));
        // },
        // //작성자로 찾기
        // [Constant.SEARCH_BOARD_WRITER]: (store, payload) => {
        //     console.log(payload);
        //     const config = {
        //         headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
        //     }
        //     http.get('/api/board/typesearch/writer=' + payload.bwriter + '&bstate=' + payload.bstate, config)
        //         .then(response => {
        //             console.log(response.data);
        //             store.commit(Constant.GET_BOARDLIST, { boards: response.data })
        //         })
        //         .catch(exp => alert('search by title 처리에 실패하였습니다.' + exp));
        // },



    },

    mutations: {
        [Constant.GET_ISSUELIST]: (state, payload) => {
            // console.log('mutation' + payload.boards);
            state.issues = payload.issues;
        },
        [Constant.GET_ISSUELIST_BY_STATE]: (state, payload) => {
            // console.log('mutation' + payload.boards);
            if (payload.issuestate == 'done') {
                state.issues_done = payload.issues;
            } else if (payload.issuestate == 'ongoing') {
                state.issues_ongoing = payload.issues;
            } else if (payload.issuestate == 'created') {
                state.issues_created = payload.issues;
            }
        },
        [Constant.GET_ISSUE]: (state, payload) => {
            state.issue = payload.issue;
        },
    },

    modules: {}
};

export default issuestore;
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
        issue: {},
        issue_prework: {},
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
            window.localStorage.setItem('issueTarget', payload.pid)
            window.localStorage.setItem('issueLoad', "false")
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
            console.log("확인")
            console.log(payload);
            http.get('/api/issue/no/' + payload.issueid, config)
                .then(response => {
                    console.log("success");
                    console.log(response.data);
                    store.commit(Constant.GET_ISSUE, { issue: response.data })
                })
                .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

        },

        //issueid로 게시글 하나 가져오기
        [Constant.GET_ISSUE_PREWORK]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            console.log("확인")
            console.log(payload);
            http.get('/api/issue/no/' + payload.issueid, config)
                .then(response => {
                    store.commit(Constant.GET_ISSUE_PREWORK, { issue_prework: response.data })
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
            http.put('/api/issue/update/', {
                    issueid: payload.issueid,
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
                    //console.log("성공함");
                })
                .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
        },

        [Constant.UPDATE_ISSUE_BY_STATE]: (_store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.put('/api/issue/updateByState', payload, config)
                .then((response) => {
                    console.log('수정하였습니다.' + response.data);
                    //store.dispatch(Constant.READ_BOARD, {bno : payload.bno});
                })
                .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
        },

        [Constant.CHANGE_RESPONSE]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.put('/api/issue/changeresponse/issueid=' + payload.issueid +
                    '&response=' + payload.response, {
                        issueid: payload.issueid,
                        response: payload.response,
                        //changeid 건너뛰었음 ㅋ
                    }, config)
                .then(response => {
                    // console.log('수정하였습니다.'+ response.data);
                    //store.dispatch(Constant.GET_ISSUELIST, { pid: payload.pid });
                    //alert("변경되었습니다.");
                })
                .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
        },
        //이슈글 삭제
        [Constant.REMOVE_ISSUE]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.delete('/api/issue/delete/' + payload.issueid, config)
                .then(() => {
                    alert('삭제하였습니다.');
                    store.dispatch(Constant.GET_ISSUELIST, { pid: payload.pid });
                })
                .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

        },



    },

    mutations: {
        [Constant.GET_ISSUELIST]: (state, payload) => {
            // console.log('mutation' + payload.boards);
            state.issues = payload.issues;
        },
        [Constant.GET_ISSUE_PREWORK]: (state, payload) => {
            // console.log('mutation' + payload.boards);
            state.issue_prework = payload.issue_prework;
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
            window.localStorage.setItem('issueLoad', 'true')
        },
        [Constant.GET_ISSUE]: (state, payload) => {
            state.issue = payload.issue;
        },
    },

    modules: {}
};

export default issuestore;
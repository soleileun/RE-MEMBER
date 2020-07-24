import Vuex from 'vuex';
import Vue from 'vue';
import router from '../../router';

import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.sessionStorage;

const userstore = {
    state: {
        userNick: '',
        userid: '',
        followings: [],
        messageList: [],
        news: [],
        logined: true,
        logining: false,
        loginError: '',
    },

    actions: {
        // 사이트init

        init: (store) => {
            
            if (storage.getItem("jwt-auth-token")) {
                // 로그인 검증 폼 생기면 바꾸기
                store.dispatch("update")
                store.commit('init', {
                    userNick: storage.getItem("userNick"),
                    userid: storage.getItem("userid")
                })
            } else {
                storage.setItem("jwt-auth-token", "");
                store.commit('init', {
                    userNick: "",
                    userid: ""
                })
            }
        },
        update: () => {
            
        },
        // 유저 관련
        login: (store, payload) => {
            http.post('/api/userinfo/signin', {
                id: payload.id,
                pw: payload.pw
            })
                .then(response => {
                    console.log(response)
                    if (response.data.data) {
                        storage.setItem("jwt-auth-token", response.headers["jwt-auth-token"]);
                        storage.setItem("userNick", response.data.data.nickname)
                        storage.setItem("userid", response.data.data.id)
                        document.querySelector(".login").classList.remove('active')
                        router.push({ name: "mainpage" });
                        store.commit('loginError', { e: '' })
                        store.dispatch("init")
                    }
                    else {
                        storage.setItem("jwt-auth-token", "");
                        storage.setItem("userNick", "")
                        storage.setItem("userid", "")
                        store.commit('loginError', { e: '아이디나 존재하지 않거나 비밀번호가 다릅니다.' })
                    }
                })
                .catch(exp => {
                    console.log(exp)
                    store.commit('loginError', { e: '오류 발생' + exp })
                    storage.setItem("jwt-auth-token", "");
                    storage.setItem("userNick", "")
                    storage.setItem("userid", "")
                });
        },
        logout: (store) => {
            storage.setItem("jwt-auth-token", "");
            storage.setItem("userNick", "")
            storage.setItem("userid", "")
            store.dispatch("init")
            // 로그인폼 구현되면 하기
            // http.get('/api/userinfo/' + payload.id)
            // .then(response => {
            //     store.commit('login', { user: response.data })
            // })
            // .catch(exp => {
            //     store.commit('login', { user: '' })
            //     alert('로그인에 실패하였습니다.' + exp)
            // });
        },
        signup: () => {
            
        },
        leave: (store, payload) => {
            http.post('/api/userinfo/signin', {
                id: storage.getItem("userid"),
                pw: payload.pw,
            })
                .then(response => {
                    if (response.data.data) {
                        http.delete('api/userinfo/' + storage.getItem("userid"), {
                            headers: {
                                "jwt-auth-token": storage.getItem("jwt-auth-token")
                            }
                        })
                            .then(() => {
                                storage.setItem("jwt-auth-token", "");
                                storage.setItem("userNick", "")
                                storage.setItem("userid", "")
                                router.push({ name: "mainpage" });
                            }).catch(exp => { console.log(exp) })
                    }
                    else {
                        alert("비밀번호가 다릅니다.")
                    }
                }).catch(exp => {
                    alert("오류가 발생했습니다." + exp)
                })
        },

        // 메세지
        sendMes: (store, payload) => {
            http.post('/api/message/', {
                content: payload.content,
                fromUser: store.state.user.id,
                toUser: payload.id
            })
                .then(response => {
                    // 자동으로 메세지 창에 추가하기
                    console.log(response.data)
                })
                .catch(exp => {
                    alert('메세지 전송에 실패하였습니다.' + exp)
                });
        }
    },

    mutations: {
        loginError: (state, payload) => {
            state.loginError = payload.e;
            state.logining = false;
        },
        getMesList: (state, payload) => {
            state.messageList = payload.messageList;
        },
        init: (state, payload) => {
            state.userNick = payload.userNick;
            state.userid = payload.userid;
        },
        // update: (state, payload) => {
        // 메신저 버블 업데이트 용
        // },
    },

    modules: {
    }
};

export default userstore;

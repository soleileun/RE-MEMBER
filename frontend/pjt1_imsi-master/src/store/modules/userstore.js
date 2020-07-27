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
        users: [],
        news: [],
        logined: true,
        logining: false,
        loginError: '',
        mesDetail: {},
    },

    actions: {
        // 사이트init

        init: (store) => {

            if (storage.getItem("jwt-auth-token")) {
                // 로그인 검증 폼 생기면 바꾸기
                store.commit('init', {
                    userNick: storage.getItem("userNick"),
                    userid: storage.getItem("userid")
                })
                store.dispatch("update")
            } else {
                storage.setItem("jwt-auth-token", "");
                store.commit('init', {
                    userNick: "",
                    userid: ""
                })
            }
        },
        update: (store) => {
            console.log("up")
            // 메세지
            http.get('/api/message/' + storage.getItem("userid"), {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
            }).then(res => {
                store.commit('loadMesList', { messageList: res.data })
            }).catch(exp => console.log(exp))
            // 팔로잉
            http.get('/api/following/' + storage.getItem("userid"), {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
            }).then(res => {
                store.commit('loadfollowings', { followings: res.data })
            }).catch(exp => console.log(exp))
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
                fromUser: storage.getItem("userid"),
                toUser: payload.other,
                read:false,
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token"),

                },
            })
                .then(response => {
                    console.log(response)
                    store.commit('pushDetailMes', { mes: response.data })
                })
                .catch(exp => {
                    alert('메세지 전송에 실패하였습니다.' + exp)
                });
        },
        detailMes: (store, payload) => {
            http.get(`/api/message/message/${storage.getItem("userid")}/${payload.other}`, {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
            }).then(res => {
                store.commit('loadDetailMes', { list: res.data })
            }).catch(exp => console.log(exp))
        },
    },

    mutations: {
        loginError: (state, payload) => {
            state.loginError = payload.e;
            state.logining = false;
        },
        loadMesList: (state, payload) => {
            state.messageList = payload.messageList;
        },
        loadDetailMes: (state, payload) => {
            state.mesDetail = payload.list;
        },
        pushDetailMes: (state, payload) => {
            state.mesDetail.push(payload.mes);
        },
        loadfollowings: (state, payload) => {
            state.followings = payload.followings;
        },
        loadUsers: (state, payload) => {
            state.users = payload.users;
        },
        init: (state, payload) => {
            if (payload.userid !== state.userid) {
                state.userNick = payload.userNick;
                state.userid = payload.userid;
                state.mesDetail = [];
                state.followings = [];
                state.messageList = [];
                state.users = [];
                state.news = [];
            }
        },
        // loadMes: (state, payload) => {
        //     state
        // },
    },

    modules: {
    }
};

export default userstore;

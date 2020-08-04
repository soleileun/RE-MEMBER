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
        mesDetail: [],
        bubbleS: '1',
        mesdetailid: "",
        mesviewdetail: false,
        bubbleNew: false,
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
                store.dispatch("getFollow");
            } else if (storage.getItem("autologin") === 't') {
                store.dispatch("login", { id: storage.getItem("userid"), pw: storage.getItem("pw") });
            }
            else {
                storage.setItem("jwt-auth-token", "");
                storage.setItem("idvalid","");
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
                // 알림
                http.get(`/api/message/message/${storage.getItem("userid")}/admin`, {
                    headers: {
                        "jwt-auth-token": storage.getItem("jwt-auth-token")
                    }
                }).then(res => {
                    store.commit('loadNews', { list: res.data })
                }).catch(exp => console.log(exp))
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
                        storage.setItem("idvalid","true");
                        document.querySelector(".login").classList.remove('active')
                        store.commit('loginError', { e: '' })
                        store.dispatch("init")
                    }
                    else {
                        storage.setItem("jwt-auth-token", "");
                        storage.setItem("userNick", "")
                        storage.setItem("userid", "")
                        storage.setItem("idvalid","");
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
            storage.setItem("autologin", "f")
            store.dispatch("init")
        },
        signup: () => {

        },
        getFollow: (store) => {
            console.log("팔로우 검색")
            http.get('/api/following/' + storage.getItem("userid"), {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
            }).then(res => {
                store.commit('loadfollowings', { followings: res.data })
            }).catch(exp => console.log(exp))
        },
        leave: (store, payload) => {
            http.delete('/api/userinfo/', {
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
        follow: (store, payload) => {

            http.post('/api/following/', {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                },
                target: payload.target,
                uid: storage.getItem("userid")
            }).then(res => {
                store.commit('newFollow', { followings: res.data })
            }).catch(exp => console.log(exp))
        },
        //this.$store.dispatch("follow", { target: //targetId }); 
        //this.$store.dispatch("sendMes", { toUser//targetId });
        // 메세지
        sendMes: (store, payload) => {
            if (!payload.content) {
                if (!storage.getItem("jwt-auth-token")) {
                    alert("로그인이 필요합니다.")
                    document.querySelector(".login").classList.remove("active");
                    document.querySelector(".login").classList.add("active");
                } else {
                    document.querySelector(".bubble").classList.remove("active")
                    document.querySelector(".bubble").classList.add("active")
                    store.commit('bubbleState', { st: '4' })
                    store.commit("viewMes", { id: payload.toUser, view: true })
                    store.dispatch("detailMes", { other: payload.toUser });
                }
            } else if (payload.other !== '') {
                http.post('/api/message/', {
                    content: payload.content,
                    fromUser: storage.getItem("userid"),
                    toUser: payload.other,
                    read: false,
                    headers: {
                        "jwt-auth-token": storage.getItem("jwt-auth-token"),

                    },
                })
                    .then(response => {
                        console.log(response)
                        store.commit('pushDetailMes', { mes: { fromUser: storage.getItem("userid"), time: "방금", content: payload.content, mnum: 9999999 } })
                    })
                    .catch(exp => {
                        alert('메세지 전송에 실패하였습니다.' + exp)
                    });
            }

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
        delMes: (store, payload) => {
            http.delete(`/api/message/${payload.mnum}`, {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
            }).then(res => {
                alert("메세지가 삭제되었습니다." + res.data)
            }).catch(exp => console.log(exp))
        },
        mesRead: (store, payload) => {
            http.put(`/api/message/${payload.mnum}`, {
                headers: {
                    "jwt-auth-token": storage.getItem("jwt-auth-token")
                },
                read:true,
                mnum:payload.mnum,
                fromUser:"admin",
                toUser:storage.getItem("userid")
            }).then(res => {
                console.log(res)
            }).catch(exp => console.log(exp))
        },
    },

    mutations: {
        loginError: (state, payload) => {
            state.loginError = payload.e;
            state.logining = false;
        },
        loadMesList: (state, payload) => {
            state.messageList = payload.messageList.filter(item => item.fromUser !== "admin");
            state.bubbleNew = false
            if (state.messageList.find(item => item.fromUser !==storage.getItem("userid") && item.read === false)) {
                state.bubbleNew = true
            }
        },
        loadDetailMes: (state, payload) => {
            state.mesDetail = payload.list;
        },
        loadNews: (state, payload) => {
            state.news = payload.list;
            if (!state.bubbleNew){
                if (payload.list.find(item => item.read === false)) {
                    state.bubbleNew = true
                }
            }
        },
        pushDetailMes: (state, payload) => {
            if (typeof (state.mesDetail) == typeof ([])) {
                state.mesDetail.push(payload.mes);
            }
            else {
                state.mesDetail = [payload.mes];
            }
        },
        loadfollowings: (state, payload) => {
            state.followings = payload.followings;
        },
        newFollow: (state, payload) => {
            state.followings.push(payload.followings);
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
        viewMes: (state, payload) => {
            state.mesdetailid = payload.id
            state.mesviewdetail = payload.view
        },
        bubbleState: (state, payload) => {
            state.bubbleS = payload.st
        },
    },

    modules: {
    }
};

export default userstore;

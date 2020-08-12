import Vue from 'vue';
import router from '../../router';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.localStorage;
const userstore = {
  state: {
    userNick: '',
    userid: '',
    usergit: '',
    userintro: '',
    followings: [],
    followers: [],
    messageList: [],
    news: [],
    logined: true,
    logining: false,
    loginError: '',
    bubbleNew: false,
  },


  actions: {
    // 사이트init
    init2: (store) => {
      // 만약 토큰이 있으면
      if (storage.getItem("jwt-auth-token")) {
        store.commit('init', {
          userNick: storage.getItem("userNick"),
          userid: storage.getItem("userid"),
          idvalid: storage.getItem("idvalid"),
          userState:window.localStorage.getItem("userState"),
          intro:window.localStorage.getItem("intro"),
        })
        store.dispatch("update")
        store.dispatch("getFollow");
        store.dispatch("getFollower");
      } else if (storage.getItem("autologin") === 't') {
        store.dispatch("login", {
          id: storage.getItem("id"),
          pw: storage.getItem("pw")
        });
      }
    },
    update2: (store) => {
      console.log("up")
      // 메세지
      const config = {
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token")
        }
      }
      http.get('/api/message/' + storage.getItem("userid"), config).then(res => {
        store.commit('loadMesList', {
          messageList: res.data
        })
        // 알림
        http.get(`/api/message/message/${storage.getItem("userid")}/admin`, config).then(res => {
          if (res.data) {

            store.commit('loadNews', {
              list: res.data
            })
          }
        }).catch(exp => console.log(exp))
      }).catch(exp => console.log(exp))
    },
    // 유저 관련
    login2: (store, payload) => {
      http.post('/api/userinfo/signin', {
        id: payload.id,
        pw: payload.pw
      })
        .then(response => {
          // console.log(response.data.data)

          if (response.data.data) {
            window.localStorage.setItem("jwt-auth-token", response.headers["jwt-auth-token"]);
            window.localStorage.setItem("userNick", response.data.data.nickname)
            window.localStorage.setItem("userid", response.data.data.id)
            window.localStorage.setItem("idvalid", "true"); //response.data.data.valid);
            window.localStorage.setItem("userState", response.data.data.state);
            window.localStorage.setItem("userintro", response.data.data.intro);
            document.querySelector(".login").classList.remove('active')
            store.commit('loginError', {
              e: ''
            })
            store.dispatch("init")
            router.go()
          } else {
            storage.setItem("jwt-auth-token", "");
            storage.setItem("userNick", "")
            storage.setItem("userid", "")
            storage.setItem("idvalid", "");
            storage.setItem("userState", "");
            store.commit('loginError', {
              e: '아이디나 존재하지 않거나 비밀번호가 다릅니다.'
            })
          }
        })
        .catch(exp => {
          console.log(exp)
          store.commit('loginError', {
            e: '오류 발생' + exp
          })
          storage.setItem("jwt-auth-token", "");
          storage.setItem("userNick", "")
          storage.setItem("userid", "")
        });
    },
    logout2: (store) => {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("userNick", "")
      storage.setItem("userid", "")
      window.localStorage.setItem("jwt-auth-token", "");
      window.localStorage.setItem("userNick", "")
      window.localStorage.setItem("userid", "")
      window.localStorage.setItem("autologin", "f")
      setTimeout(() => {
        store.dispatch("init")
        router.go({path:'/main'});
      }, 100)
    },
    getFollow2: (store) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http
        .get("/api/userinfo/", config)
        .then((response) => {

          http.get('/api/following/' + storage.getItem("userid"), {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token")
            }
          }).then(res => {
            store.commit('loadfollowings', {
              followings: res.data,
              users: response.data
            })
            res.data.forEach(item => {
              store.dispatch(Constant.GET_CHATROOMONETOONE, {
                uid1: storage.getItem("userid"),

                uid2: item.target,

              });
            })
          }).catch(exp => console.log(exp))
        })
        .catch((exp) => alert("에러" + exp));
    },
    getFollower2: (store) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http
        .get("/api/userinfo/", config)
        .then((response) => {

          http.get('/api/following/target/' + storage.getItem("userid"), {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token")
            }
          }).then(res => {
            console.log(res);
            store.commit('loadfollowers', {
              followers: res.data,
              users: response.data
            })

            // res.data.forEach(item => {
            //   store.dispatch(Constant.GET_CHATROOMONETOONE, {
            //     uid1: storage.getItem("userid"),
            //     uid2: item.target,
            //   });
            // })
          }).catch(exp => console.log(exp))
        })
        .catch((exp) => alert("에러" + exp));
    },
  },

  mutations: {
    init2: (state, payload) => {
      if (state.userid !== window.localStorage.getItem('userid')) {
        state.userNick = payload.userNick;
        state.userid = payload.userid;
        state.token = payload.token
        state.mesDetail = [];
        state.followings = [];
        state.messageList = [];
        state.users = [];
        state.news = [];
      }
    },
    loginError2: (state, payload) => {
      state.loginError = payload.e;
      state.logining = false;
    },
    loadMesList2: (state, payload) => {
      if (storage.getItem("userid") !== "admin") {
        state.messageList = payload.messageList.filter(item => item.fromUser !== "admin" && item.toUser !== "admin");
      } else {
        state.messageList = payload.messageList;
      }
      state.bubbleNew = false
      if (state.messageList.find(item => item.fromUser !== storage.getItem("userid") && item.read === false)) {
        state.bubbleNew = true
      }
    },
    loadDetailMes2: (state, payload) => {
      state.mesDetail = payload.list;
    },
    loadNews2: (state, payload) => {
      state.news = payload.list.filter(item => item.fromUser == "admin");
      if (!state.bubbleNew) {
        if (state.news.find(item => item.read === false)) {
          state.bubbleNew = true
        }
      }
    },
    loadfollowings2: (state, payload) => {
      let tmp = [];
      payload.followings.forEach(item => {
        tmp.push(item.target)
      })
      state.followings = payload.users.filter(item => item.leaveUser === false).filter(item =>
        (tmp.indexOf(item.id) > -1)
      );
    },
    loadfollowers2: (state, payload) => {
      let tmp = [];
      payload.followers.forEach(item => {
        tmp.push(item.uid)
      })
      state.followers = payload.users.filter(item => item.leaveUser === false).filter(item =>
        (tmp.indexOf(item.id) > -1)
      );
    },
    loadUsers2: (state, payload) => {
      state.users = payload.users.filter(item => item.leaveUser === false);
    },
    
  },

  modules: {}
};

export default userstore;

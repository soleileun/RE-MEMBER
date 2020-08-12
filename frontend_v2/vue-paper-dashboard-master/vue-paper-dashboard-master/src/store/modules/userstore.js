import Vuex from 'vuex';
import Vue from 'vue';
import router from '../../router';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.sessionStorage;
const userstore = {
  state: {
    userNick: '',
    userid: '',
    token: '',
    followings: [],
    followers: [],
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
    interest: [],
    usergit: '',
    userintro: '',
    recommendedPJT: [],
    recommendedUser: []
  },


  actions: {
    // 사이트init
    init: (store) => {
      if (window.localStorage.getItem("jwt-auth-token") !== '') {
        // 로그인 검증 폼 생기면 바꾸기
        store.commit('init', {
          userNick: window.localStorage.getItem("userNick"),
          userid: window.localStorage.getItem("userid"),
          token: window.localStorage.getItem('jwt-auth-token')
        })
        storage.setItem("jwt-auth-token", window.localStorage.getItem("jwt-auth-token"));
        storage.setItem("idvalid", window.localStorage.getItem("idvalid"));
        storage.setItem("userState", window.localStorage.getItem("userState"));
        storage.setItem("userid", window.localStorage.getItem("userid"));
        storage.setItem("userNick", window.localStorage.getItem("userNick"));
        storage.setItem("intro", window.localStorage.getItem("intro"));
        store.dispatch("update")
        store.dispatch("getFollow");
        store.dispatch("getFollower");
      } else if (window.localStorage.getItem("autologin") === 't') {
        store.dispatch("login", {
          id: window.localStorage.getItem("id"),
          pw: window.localStorage.getItem("pw")
        });
      }
    },
    update: (store) => {
      console.log("up")
      // 메세지
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
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
    login: (store, payload) => {
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
    logout: (store) => {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("userNick", "")
      storage.setItem("userid", "")
      window.localStorage.setItem("jwt-auth-token", "");
      window.localStorage.setItem("userNick", "")
      window.localStorage.setItem("userid", "")
      window.localStorage.setItem("autologin", "f")
      setTimeout(() => {
        store.dispatch("init")
        router.go({
          path: '/main'
        });
      }, 100)
    },
    signup: (store, payload) => {
      http
        .post("/api/userinfo/", {
          id: payload.id,
          pw: payload.pw,
          nickname: payload.nickname,
          name: payload.name,
          address1: payload.address1,
          address2: payload.address2,
          phone: payload.phone,
          git: payload.git,
          responsibility: payload.responsibility,
        })
        .then((res) => {
          http.post('/api/userinfo/signin', {
              id: payload.id,
              pw: payload.pw
            })
            .then(response => {
              console.log(response)
              if (response.data.data) {
                const config = {
                  headers: {
                    "jwt-auth-token": response.headers["jwt-auth-token"]
                  }
                }
                window.localStorage.setItem("jwt-auth-token", response.headers["jwt-auth-token"]);
                window.localStorage.setItem("userNick", response.data.data.nickname)
                window.localStorage.setItem("userid", response.data.data.id)
                window.localStorage.setItem("usergit", response.data.data.git)
                window.localStorage.setItem("userintro", response.data.data.intro)
                window.localStorage.setItem("idvalid", "true"); //response.data.data.valid);
                window.localStorage.setItem("userState", response.data.data.state);
                document.querySelector(".login").classList.remove('active')
                store.commit('loginError', {
                  e: ''
                })
                store.dispatch("init")
                store.state.interest.forEach((el) => {
                  http
                    .post("/api/interest/", {
                      id: payload.id,
                      interest: el,
                    }, config).then(res => {

                    }).catch(e => console.log(e))
                })
                router.push({
                  path: "/main"
                })
              } else {
                window.localStorage.setItem("jwt-auth-token", "");
                window.localStorage.setItem("userNick", "")
                window.localStorage.setItem("userid", "")
                window.localStorage.setItem("usergit", "")
                window.localStorage.setItem("userintro", "")
                window.localStorage.setItem("idvalid", "true"); //response.data.data.valid);
                window.localStorage.setItem("userState", "");
                store.commit('loginError', {
                  e: '회원가입에 오류가 있습니다 문의해주세요'
                })
              }
            })
            .catch(exp => {
              store.commit('loginError', {
                e: '오류 발생' + exp
              })
              storage.setItem("jwt-auth-token", "");
              storage.setItem("userNick", "")
              storage.setItem("userid", "")
            });




        })
        .catch((e) => console.log(e));
    },

    getFollow: (store) => {
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

    getFollower: (store) => {
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
    getRecommendedUser: (store) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http
        .get("/api/userinfo/", config)
        .then((response) => {

          http.get('/api/userinfo/getRecommended/User/' + storage.getItem("userid"), {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token")
            }
          }).then(res => {
            console.log("유저추천...");
            console.log(res);
            store.commit('loadRecommendedUser', {
              recommendedUser: res.data,
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
    getRecommendedPJT: (store) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http
        .get("/api/userinfo/", config)
        .then((response) => {

          http.get('/api/userinfo/getRecommended/PJT/' + storage.getItem("userid"), {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token")
            }
          }).then(res => {
            console.log("프로젝트추천...");
            console.log(res);
            store.commit('loadRecommendedPJT', {
              recommendedPJT: res.data,
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
    leave: (store, payload) => {

      let config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }

      http.post('/api/userinfo/signin', {
          id: storage.getItem("userid"),
          pw: payload.pw,
        }, config)
        .then(response => {
          console.log(response);
          if (response.data.data) {
            http.delete('api/userinfo/' + storage.getItem("userid"), config = {
                headers: {
                  "jwt-auth-token": storage.getItem("jwt-auth-token")
                }
              })
              .then(() => {

                store.dispatch("logout");
                router.push({
                  path: "/main"
                });
              }).catch(exp => {
                console.log(exp)
              })
          } else {
            alert("비밀번호가 다릅니다.")
          }
        }).catch(exp => {
          alert("오류가 발생했습니다." + exp)
        })
    },
    follow: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.post('/api/following/', {
        target: payload.target,
        uid: storage.getItem("userid")
      }, config).then(res => {
        store.commit('newFollow', {
          followings: res.data
        })
        alert('팔로우 되었습니다!')
        store.dispatch('getFollow')
      }).catch(exp => console.log(exp))
    },
    delFollow: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.delete('/api/following/', {
        target: payload.target,
        uid: storage.getItem("userid")
      }, config).then(res => {
        store.dispatch('getFollow')
      }).catch(exp => console.log(exp))
    },
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
          store.commit('bubbleState', {
            st: '4'
          })
          store.commit("viewMes", {
            id: payload.toUser,
            view: true
          })
          store.dispatch("detailMes", {
            other: payload.toUser
          });
        }
      } else if (payload.other) {
        const config = {
          headers: {
            "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
          }
        }
        http.post('/api/message/', {
            content: payload.content,
            fromUser: storage.getItem("userid"),
            toUser: payload.other,
            read: false,
          }, config)
          .then(response => {
            console.log(response)
            store.commit('pushDetailMes', {
              mes: {
                fromUser: storage.getItem("userid"),
                time: "방금",
                content: payload.content,
                mnum: 9999999
              }
            })
          })
          .catch(exp => {
            alert('메세지 전송에 실패하였습니다.' + exp)
          });
      }

    },
    detailMes: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.get(`/api/message/message/${storage.getItem("userid")}/${payload.other}`, config).then(res => {
        store.commit('loadDetailMes', {
          list: res.data
        })
      }).catch(exp => console.log(exp))
    },
    delMes: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.delete(`/api/message/${payload.mnum}`, config).then(res => {
        alert("메세지가 삭제되었습니다." + res.data)
      }).catch(exp => console.log(exp))
    },
    mesRead: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.put(`/api/message/${payload.mnum}`, {
        read: true,
        mnum: payload.mnum,
        fromUser: "admin",
        toUser: storage.getItem("userid")
      }, config).then(res => {
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
    loadDetailMes: (state, payload) => {
      state.mesDetail = payload.list;
    },
    loadNews: (state, payload) => {
      state.news = payload.list.filter(item => item.fromUser == "admin");
      if (!state.bubbleNew) {
        if (state.news.find(item => item.read === false)) {
          state.bubbleNew = true
        }
      }
    },
    pushDetailMes: (state, payload) => {
      if (typeof (state.mesDetail) == typeof ([])) {
        state.mesDetail.push(payload.mes);
      } else {
        state.mesDetail = [payload.mes];
      }
    },
    loadfollowings: (state, payload) => {
      let tmp = [];
      payload.followings.forEach(item => {
        tmp.push(item.target)
      })
      state.followings = payload.users.filter(item => item.leaveUser === false).filter(item =>
        (tmp.indexOf(item.id) > -1)
      );
    },
    loadfollowers: (state, payload) => {
      let tmp = [];
      payload.followers.forEach(item => {
        tmp.push(item.uid)
      })
      state.followers = payload.users.filter(item => item.leaveUser === false).filter(item =>
        (tmp.indexOf(item.id) > -1)
      );
    },
    loadRecommendedUser: (state, payload) => {
      let tmp = [];
      payload.recommendedUser.forEach(item => {
        tmp.push(item.id)
      })
      state.recommendedUser = payload.users.filter(item => item.leaveUser === false).filter(item =>
        (tmp.indexOf(item.id) > -1)
      );
    },
    loadRecommendedPJT: (state, payload) => {
      // let tmp = [];
      // payload.recommendedPJT.forEach(item => {
      //   tmp.push(item)
      // })
      state.recommendedPJT = payload.recommendedPJT;
      // state.recommendedUser = payload.users.filter(item => item.leaveUser === false).filter(item =>
      //   (tmp.indexOf(item.id) > -1)
      // );
    },
    newFollow: (state, payload) => {
      state.followings.push(payload.followings);
    },
    loadUsers: (state, payload) => {
      state.users = payload.users.filter(item => item.leaveUser === false);
    },
    init: (state, payload) => {
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
    interest: (state, payload) => {
      state.interest = payload.picks
    },

    viewMes: (state, payload) => {
      state.mesdetailid = payload.id
      state.mesviewdetail = payload.view
    },
    bubbleState: (state, payload) => {
      state.bubbleS = payload.st
    },
  },

  modules: {}
};

export default userstore;

import Vuex from 'vuex';
import Vue from 'vue';

import router from '../../router';
import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.sessionStorage;

const projectstore = {
  state: {
    projects: [],
    project: {},
    pmlist: [],
    pjtcnt: {},
    pinterest: [],
    pjtName : {},
    applys:[],
    applysId:'',
  },

  actions: {
    //userId에 맞는 project 리스트 가져오기
    [Constant.GET_PROJECTLIST_BY_PMEMBER]: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      // console.log('토큰 : '+ storage.getItem("jwt-auth-token"));
      http.get('/api/project/searchByUserId/' + payload.userId, config)
        .then(response => {
          store.commit(Constant.GET_PROJECTLIST_BY_PMEMBER, {
            projects: response.data
          })
          // response.data.forEach(item => {
          //   store.dispatch(Constant.GET_CURRENT_MEMBER_COUNT, {
          //     pid: item.pid
          //   })
          // })
        })
        // .catch(exp => alert('getPmemberList처리에 실패하였습니다.' + exp));
    },
    //pid으로 현재 프로젝트 멤버수 가져오기
    // [Constant.GET_CURRENT_MEMBER_COUNT]: (store, payload) => {
    //   const config = {
    //     headers: {
    //       "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
    //     }
    //   }
    //   http.get('/api/pmember/selectCntByPid/' + payload.pid, config)
    //     .then(response => {
    //       // console.log(response.data);
    //       store.commit(Constant.GET_CURRENT_MEMBER_COUNT, {
    //         pjtcnt: response.data,
    //         pid: payload.pid
    //       })
    //     })
    //     .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));
    // },

    //pid으로 현재 프로젝트 멤버 정보 가져오기
    [Constant.GET_PROJECT_MEMBER_BY_PID]: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.get('/api/pmember/selectByPid/' + payload.pid, config)
        .then(response => {
          //console.log("궁금해 ㅠ");

          store.commit(Constant.GET_PROJECT_MEMBER_BY_PID, {
            pmlist: response.data,
            pid: payload.pid
          })
        })
        // .catch(exp => alert('pid으로 현재 프로젝트 정보 가져오기에 실패하였습니다.' + exp));
    },

    //pid으로 프로젝트 하나 가져오기
    [Constant.GET_PROJECT]: (store, payload) => {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.get('/api/project/' + payload.pid, config)
      .then(response => {
          // console.log("요기"+payload.pid);
          // console.log(response.data);
          store.commit(Constant.GET_PROJECT, {
            project: response.data
          })
        })
        // .catch(exp => alert('getTodo처리에 실패하였습니다.' + exp));

    },

    //project 추가 
    [Constant.ADD_PROJECT]: (store, payload) => {
      // console.log(payload.bstate);
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      }
      http.post('/api/project/', {
        // bno : payload.bno,
        pjtName: payload.pjtName,
        pjtContent: payload.pjtContent,
        pjtState: payload.pjtState,
        pjtMemberCnt: payload.pjtMemberCnt,
        makeDay: payload.makeDay,
        makeId: payload.makeId,
        location:payload.location,
        //changeDay: payload.changeDay,
        
        //changeId: payload.changeId,
        //location: payload.location,
      }, config)
      .then((response) => {
        store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
          userId: payload.makeId
        });
        store.state.pinterest.forEach((el) => {
          http
          .post("/api/pinterest/", {
            pid: response.data,
            interest: el,
          }, config).then(res => {
          }).catch(e => {})
        })
        
        const fileName = response.data;
        const file2 = new File(
          [payload.file],
          `${fileName}.${payload.file.name.split(".")[1]}`,
          {
            type: payload.file.type,
          }
        );
        store.dispatch("upFiledirect", {
          file: file2,
          bno: 0-response.data,
          reload: true,
        });
      })
      // .catch(exp => {
      //   alert('추가 처리에 실패하였습니다.' + exp);
      // })
    },
    // asd
    [Constant.ADD_WAITMEMBER]: (store, payload) => {
      // 지원
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      http
      .post("/api/waitMember/", {
        code: new Date().getTime() +'',
        comment: payload.comment,
        pid: payload.pid,
        state: payload.state,
        type: "Apply",
        userId: window.sessionStorage.getItem('userid'),
      }, config).then(res => {
        alert('지원 되었습니다.');
      })
      // .catch(e => {})
    },
    [Constant.ADD_NEWMEMBER]: (store, payload) => {
      //수락
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      http
      .get("/api/waitMember/invite/"+payload.code, config).then(res => {
        alert('추가 되었습니다.');
        router.go()
      })
      // .catch(e =>{})
    },
    [Constant.ADD_INVITE]: (store, payload) => {
      // 초대
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      
      http
      .post("/api/waitMember/", { 
        comment: payload.comment,
        pid: payload.pid,
        state: payload.state,
        type: "Invite",
        userId: payload.userId,
      }, config).then(res => {
        alert('초대되었습니다.');
        router.go()
      })
      // .catch(e => {})
    },
    [Constant.REMOVE_APPLY]: (store, payload) => {
      // 가입거절
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      http
      .delete(`/api/waitMember/delete/${payload.target}/${payload.pid}`, config).then(res => {
        alert('삭제되었습니다.');
        router.go()
      })
      // .catch(e => {})
    },
    [Constant.MY_APPLY]: (store, payload) => {
      // 내 지원 목록'
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      http
      .get(`/api/waitMember/searchById/${storage.getItem("userid")}`, config).then(res => {
        alert(res.data);
        store.commit(Constant.MY_APPLY,{applys:res.data})
      })
      // .catch(e => {})
    },
    [Constant.PROJECT_APPLY]: (store, payload) => {
      // 프로젝트 지원자 목록
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")
        }
      } 
      http
      .get(`/api/waitMember/searchByPid/${payload.pid}`, config).then(res => {
        store.commit(Constant.PROJECT_APPLY,{applys:res.data,pid:payload.pid})
      })
      // .catch(e => {})
    },

    
    // //게시글 추가
    // [Constant.ADD_BOARD]: (store, payload) => {
      //     // console.log(payload.bstate);
      
      //     http.post('/api/board/', {
        //             // bno : payload.bno,
        //             bwriter : payload.bwriter,
        //             btitle : payload.btitle,
        //             bcontent : payload.bcontent,
        //             bview : payload.bview,
        //             bfile : payload.bfile,
        //             bstate : payload.bstate,
    //             makeDay : payload.makeDay,
    //             // changeDay : payload.changeDay,
    //             makeId : payload.makeId,
    //             // changeId : payload.changeId
    //         })
    //         .then(() => {
    //             console.log('추가하였습니다.');
    //             store.dispatch(Constant.GET_BOARDLIST, {bstate : payload.bstate});

    //         })
    //         .catch(exp => {
    //             console.log('추가 실패 확인 로그');
    //             alert('추가 처리에 실패하였습니다.' + exp);
    //         })
    // },
    // //게시글 수정
    // [Constant.MODIFY_BOARD]: (store, payload) => {
    //     // console.log(payload);

    //     http.put('/api/board/change/' + payload.board.bno, {
    //             bno:payload.board.bno,
    //             bwriter: payload.board.bwriter,
    //             btitle: payload.board.btitle,
    //             bcontent: payload.board.bcontent,
    //             bview : payload.board.bview,
    //             bfile : payload.board.bfile,
    //             bstate : payload.board.bstate,
    //             makeDay : payload.board.makeDay,
    //             changeDay : new Date(),
    //             makeId : payload.board.makeId,
    //             changeId : payload.board.changeId //세션 id
    //         })
    //         .then(() => {
    //             // console.log('수정하였습니다.'+ response.data);
    //             store.dispatch(Constant.GET_BOARD, {bno : payload.board.bno});
    //         })
    //         .catch(exp => alert('수정 처리에 실패하였습니다.' + exp));
    // },
    // //게시글 삭제
    // [Constant.REMOVE_BOARD]: (store, payload) => {
    //     http.delete('/api/board/delete/' + payload.bno)
    //         .then(() => {
    //             alert('삭제하였습니다.');
    //             store.dispatch(Constant.GET_BOARDLIST, {bstate : payload.bstate});

    //         })
    //         .catch(exp => alert('삭제 처리에 실패하였습니다.' + exp));

    // },
    // //제목으로 찾기
    // [Constant.SEARCH_BOARD_TITLE]: (store,payload) => {
    //     console.log(payload);
    //     http.get('/api/board/typesearch/btitle='+ payload.btitle + '&bstate=' + payload.bstate)
    //         .then(response => {
    //             console.log(response.data);
    //             store.commit(Constant.GET_BOARDLIST, { boards: response.data })
    //       })
    //         .catch(exp => alert('search by title 처리에 실패하였습니다.' + exp));
    //   },
    // //작성자로 찾기
    // [Constant.SEARCH_BOARD_WRITER]: (store,payload) => {
    //     console.log(payload);
    //     http.get('/api/board/typesearch/writer='+ payload.bwriter + '&bstate=' + payload.bstate)
    //         .then(response => {
    //             console.log(response.data);
    //             store.commit(Constant.GET_BOARDLIST, { boards: response.data })
    //       })
    //         .catch(exp => alert('search by title 처리에 실패하였습니다.' + exp));
    //   },

    // [Constant.COMPLETE_TODO]: (store, payload) => {
    //     http.put('/api/qna/' + payload.qna_no)
    //         .then(() => {
    //             console.log('완료 처리하였습니다.');
    //             store.dispatch(Constant.GET_TODOLIST);
    //         })
    //         .catch(exp => alert('완료 처리에 실패하였습니다.' + exp));
    // },

  },

  mutations: {
    [Constant.PROJECT_APPLY]:(state, payload) => {
      state.applys = payload.applys;
      state.applysId = payload.pid
    },
    [Constant.MY_APPLY]:(state, payload) => {
      state.applys = payload.applys;
    },
    [Constant.GET_PROJECTLIST_BY_PMEMBER]: (state, payload) => {
      state.projects = payload.projects;
    },
    // [Constant.GET_CURRENT_MEMBER_COUNT]: (state, payload) => {
    //   state.pjtcnt[payload.pid] = payload.pjtcnt;
    // },
    [Constant.GET_PROJECT]: (state, payload) => {
      state.project = payload.project;
      // state.pjtName[payload.project.pid] = payload.project.pjtName;

    },
    [Constant.GET_PROJECT_MEMBER_BY_PID]: (state, payload) => {
      state.pmlist = payload.pmlist;
    },
    pinterest: (state, payload) => {
      state.pinterest = payload.picks
    },
    // [Constant.CLEAR_TODO]: (state, payload) => {
    //     state.board = payload.todo;
    //     state.boards = payload.todoItems;
    // }
  },

  modules: {}
};

export default projectstore;

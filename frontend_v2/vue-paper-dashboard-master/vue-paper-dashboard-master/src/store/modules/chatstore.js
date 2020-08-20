import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);
const storage = window.sessionStorage;
// const storage = window.sessionStorage;

const chatstore = {
  state: {
      chats: [],
      chat: {},
      rooms: [],
      room: {},
      members: [],
      sameroom:{},
  },
 
  actions: {
    //채팅 관련

    //해당 유저가 들어있는 채팅방 리스트 가져오기
    [Constant.GET_CHATROOMLIST]: (store) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
      http.get('/api/chatroom/chat/roomlist='+ storage.getItem("userid"), config)
          .then(response => {
              store.commit(Constant.GET_CHATROOMLIST, { rooms: response.data })
        })
          .catch(exp => alert('getchatroomList처리에 실패하였습니다!!' + exp));
    },
    //채팅방 이름으로 채팅방 멤버리스트 가져오기
    [Constant.GET_CHATROOMMEMBER]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.get('/api/chatroom/chat/name=' + payload.roomName, config)
            .then(response => {
                // console.log(response.data);
                store.commit(Constant.GET_CHATROOMMEMBER, { members: response.data })})
            .catch(exp => alert('getChatroomMember처리에 실패하였습니다.' + exp));

    },
    //채팅방 추가

    
    //둘이 있는 채팅방이 있는지 확인
    [Constant.GET_CHATROOMONETOONE]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.get('/api/chatroom/exists/uid1=' + payload.uid1 + '&uid2=' +payload.uid2, config)
            .then(response => {
                store.commit(Constant.GET_CHATROOMONETOONE, { sameroom: response.data,uid:payload.uid2  })
            })
            .catch(exp => alert('getChatroomOneToOne처리에 실패하였습니다.' + exp));

    },

    
    [Constant.ADD_CHATROOM]: (store, payload) => {
        // console.log(payload.bstate);
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.post('/api/chatroom/', {
                // bno : payload.bno,
                uid : payload.uid,
                roomName : payload.roomName,
            },config)
            .then(response => {
                if(response.data == 'success') {
                    
                    //읽음 표시 해주고
                    http.put('/api/chat/change/roomName=' + payload.roomName + '&id=' + storage.getItem("userid"), {
                        roomName : payload.roomName,
                        id : payload.uid,
                    },config)
                        .then(() => {
                        })
                        .catch(exp => alert('[FAIL] CHAT_READ is not working' + exp));

                    //console.log('추가하였습니다.');
                    http.post('/api/chat/', {   // 입장했다는 메시지
                        roomName : payload.roomName,
                        id : 'system',
                        nickname : 'system',
                        content : payload.uid + "님이 입장하셨습니다.",
                        makedate : payload.makedate,
                    },config)
                    .then(() => {   //채팅창 정보 다시 요청
                        //console.log("참가했습니다 메시지 발송");
                       
                        http.get('/api/chat/chat/name='+ payload.roomName+'&id=' + payload.id, config)
                        .then(response => {
                            store.commit(Constant.GET_CHATLIST, { chats: response.data })
                            //alert(payload.uid + "님을 초대했습니다.");
                        })
                        .catch(exp => alert('getchatList처리에 실패하였습니다!!' + exp));
                    })
                    store.dispatch(Constant.GET_CHATROOMLIST);//, {rooms : payload.bstate});
                }
                else {
                    //console.log("success가 아님" + response.data);
                    alert("이미 참여중이거나 존재하지 않는 ID입니다.");
                };
            })
            .catch(exp => {
                //alert('이미 참가중인 유저입니다.');
            })
    },
    
    //특정 roomName의 모든 채팅 정보를 받아옵니다.
    [Constant.GET_CHATLIST]: (store,payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.get('/api/chat/chat/name='+ payload.roomName+'&id=' + payload.id, config)
            .then(response => {
                store.commit(Constant.GET_CHATLIST, { chats: response.data })
          })
            .catch(exp => alert('getchatList처리에 실패하였습니다!!' + exp));
      },
    //채팅 보내기 dispatch 수정해야함
    [Constant.SEND_CHAT]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.post('/api/chat/', {
                roomName : payload.roomName,
                id : payload.id,
                nickname : payload.nickname,
                content : payload.content,
                makedate : payload.makedate,
            },config)
            .then(() => {
                //store.dispatch(Constant.GET_CHATROOMLIST);//, {rooms : payload.bstate});

            })
            .catch(exp => {
                alert('채팅에 실패하였습니다.' + exp);
            })
    },
    //특정 roomName에서 나간다. dispatch 부분 봐야함
    [Constant.REMOVE_CHATROOM]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.delete('/api/chatroom/delete/roomname=' + payload.roomName + '&uid=' + storage.getItem("userid"),config)
            .then(() => {
                alert('채팅방에서 나갔습니다.');
                store.dispatch(Constant.GET_CHATROOMLIST);   //, {bstate : payload.bstate});

            })
            .catch(exp => alert('채팅방에서 나가는 것에 실패했습니다.' + exp));
    },


    //특정 roomName의 모든 채팅 데이터를 삭제한다. dispatch 부분 봐야함
    [Constant.REMOVE_CHAT]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.delete('/api/chat/delete/' + payload.roomName,config)
            .then(() => {
                alert('채팅 내역 삭제하였습니다.');
                store.dispatch(Constant.GET_CHATROOMLIST);   //, {bstate : payload.bstate});

            })
            .catch(exp => alert('채팅 내역 삭제 처리에 실패하였습니다.' + exp));

    },
    [Constant.CHAT_READ]: (store, payload) => {
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        http.put('/api/chat/change/roomName=' + payload.roomName + '&id=' + storage.getItem("userid"), {
            roomName : payload.roomName,
            id : payload.id,
        },config)
            .then(() => {
            })
            .catch(exp => alert('[FAIL] CHAT_READ is not working' + exp));
    },
  },

  mutations: {
    [Constant.GET_CHATROOMLIST]: (state, payload) => {
        state.rooms = payload.rooms;
    },
    [Constant.GET_CHATROOMMEMBER]: (state, payload) => {
        state.members = payload.members;
    },
    [Constant.GET_CHATLIST]: (state, payload) => {

        state.chats = payload.chats;
    },
    [Constant.GET_CHATROOMONETOONE]: (state, payload) => {
        state.sameroom[payload.uid] = payload.sameroom;
    },
    // [Constant.CLEAR_TODO]: (state, payload) => {
    //     state.board = payload.todo;
    //     state.boards = payload.todoItems;
    // }
  },

  modules: {
  }
};

export default chatstore;

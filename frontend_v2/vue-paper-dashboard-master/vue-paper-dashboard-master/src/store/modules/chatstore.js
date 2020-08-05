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
  },
 
  actions: {
    //채팅 관련

    //해당 유저가 들어있는 채팅방 리스트 가져오기
    [Constant.GET_CHATROOMLIST]: (store) => {
      http.get('/api/chatroom/chat/roomlist='+ storage.getItem("userid"))
          .then(response => {
              store.commit(Constant.GET_CHATROOMLIST, { rooms: response.data })
        })
          .catch(exp => alert('getchatroomList처리에 실패하였습니다!!' + exp));
    },
    //채팅방 이름으로 채팅방 멤버리스트 가져오기
    [Constant.GET_CHATROOMMEMBER]: (store, payload) => {
        http.get('/api/chatroom/chat/name=' + payload.roomName)
            .then(response => {
                // console.log(response.data);
                store.commit(Constant.GET_CHATROOMMEMBER, { members: response.data })})
            .catch(exp => alert('getChatroomMember처리에 실패하였습니다.' + exp));

    },
    //채팅방 추가
    [Constant.ADD_CHATROOM]: (store, payload) => {
        // console.log(payload.bstate);

        http.post('/api/chatroom/', {
                // bno : payload.bno,
                uid : payload.uid,
                roomName : payload.roomName,
            })
            .then(() => {
                //alert(this.uid + '님을 초대했습니다.');
                console.log('추가하였습니다.');
                store.dispatch(Constant.GET_CHATROOMLIST);//, {rooms : payload.bstate});

            })
            .catch(exp => {
                console.log('추가 실패 확인 로그');
                alert('추가 처리에 실패하였습니다.' + exp);
            })
    },
    
    //특정 roomName의 모든 채팅 정보를 받아옵니다.
    [Constant.GET_CHATLIST]: (store,payload) => {
        http.get('/api/chat/chat/name='+ payload.roomName)
            .then(response => {
                store.commit(Constant.GET_CHATLIST, { chats: response.data })
                console.log("불러왔음." + payload.roomName)
          })
            .catch(exp => alert('getchatList처리에 실패하였습니다!!' + exp));
      },
    //채팅 보내기 dispatch 수정해야함
    [Constant.SEND_CHAT]: (store, payload) => {
        // console.log(payload.bstate);

        http.post('/api/chat/', {
                roomName : payload.roomName,
                id : payload.id,
                nickname : payload.nickname,
                content : payload.content,
                makedate : payload.makedate,
            })
            .then(() => {
                console.log('채팅 입력했습니다');
                //store.dispatch(Constant.GET_CHATROOMLIST);//, {rooms : payload.bstate});

            })
            .catch(exp => {
                console.log('채팅 입력 실패 확인 로그');
                alert('채팅에 실패하였습니다.' + exp);
            })
    },
    //특정 roomName에서 나간다. dispatch 부분 봐야함
    [Constant.REMOVE_CHATROOM]: (store, payload) => {
        http.delete('/api/chatroom/delete/roomname=' + payload.roomName + '&uid=' + storage.getItem("userid"))
            .then(() => {
                alert('채팅방에서 나갔습니다.');
                store.dispatch(Constant.GET_CHATROOMLIST);   //, {bstate : payload.bstate});

            })
            .catch(exp => alert('채팅방에서 나가는 것에 실패했습니다.' + exp));
    },


    //특정 roomName의 모든 채팅 데이터를 삭제한다. dispatch 부분 봐야함
    [Constant.REMOVE_CHAT]: (store, payload) => {
        http.delete('/api/chat/delete/' + payload.roomName)
            .then(() => {
                alert('채팅 내역 삭제하였습니다.');
                store.dispatch(Constant.GET_CHATROOMLIST);   //, {bstate : payload.bstate});

            })
            .catch(exp => alert('채팅 내역 삭제 처리에 실패하였습니다.' + exp));

    },
    [Constant.CHAT_READ]: (store, payload) => {
        console.log(payload.roomName);
        console.log(payload.id);
        http.put('/api/chat/change/roomName=' + payload.roomName + '&id=' + storage.getItem("userid"), {
            roomName : payload.roomName,
            id : payload.id,
        })
            .then(() => {
                console.log('Is Read');
            })
            .catch(exp => alert('[FAIL] CHAT_READ is not working' + exp));
    },
  },

  mutations: {
    [Constant.GET_CHATROOMLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.rooms = payload.rooms;
    },
    [Constant.GET_CHATROOMMEMBER]: (state, payload) => {
        state.members = payload.members;
    },
    [Constant.GET_CHATLIST]: (state, payload) => {
        // console.log('mutation' + payload.boards);
        state.chats = payload.chats;
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

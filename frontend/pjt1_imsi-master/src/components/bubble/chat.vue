<template>
  <div class="chatroom">
    <section class="chatlist">

      내용: <input v-model="inputcontent" type="text" @keyup="sendMessage">
          <button @click="disconnect" style="color:black;">구독취소테스트</button>
      <div style = "height : 500px; width:570px; padding:25px; background:white; overflow-y : auto;color:black; background:skyblue">
        <div v-for="chat in chats" :key="chat.chno">
          <div>
            <div style="width:30px; height:100%; float:left; margin:10px; margin-top:0px;" >
              <img src="" style="width:30px;height:30px"> 
            </div>

            <div style="font-size:6px; height:4px; margin:5px">{{ chat.nickname }} </div>
            <div>{{ chat.content }}</div>
            <br>
          </div>
        </div>

        <div v-for="(item, idx) in recvList" :key="idx">
          <div style="width:30px; height:100%; float:left; margin:10px; margin-top:0px;" >
            <img src="" style="width:30px;height:30px"> 
          </div>

          <div style="font-size:6px; height:4px; margin:5px">{{ item.nickname }} </div>
          <div>{{ item.content }}</div>
          <br>
        </div>
      </div>
      
    </section>
  </div>
</template>

<script>
//import Constant from "../../Constant";


import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Constant from "../../Constant";

const storage = window.sessionStorage;

export default {
  name: "chatroom",
  props: ['rname'],
  data: function() {
    return {
        room : this.rname,
        id : storage.getItem("userid"),
        nickname : "",
        content : "",
        makedate : "",
        recvList: [],
    };
  },
  computed: {
    chats() {
        console.log("chats 실행");
        return this.$store.state.chatstore.chats;
    },
  },
  created() {
      console.log("채팅 create 실행됨");
      console.log("room : " + this.room);
      console.log("roomname : " + this.room);
      this.connect();
      console.log("connect 했음");
         this.$store.dispatch(Constant.CHAT_READ, {
          roomName : this.room,
          id : this.id,}
          );
      //this.stompClient.subscribe("/send/" + this.room);
      //console.log("구독했음");
      //this.$store.dispatch(Constant.GET_CHATLIST, {roomName : roomName});

      //소켓 파트
      /*
    console.log("소켓 커넥트 준비");
        this.connect();
        
    console.log("소켓 커넥트 실행끝");
    */
  },
  
  
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.id !== '' && this.inputcontent !== ''){
        //this.id = storage.getItem("userid");
        console.log("input값 : " + storage.getItem("userid"));
        this.nickname = storage.getItem("userid"); // 일단 넣어놨음 닉네임으로 바꿔야함
        this.content = this.inputcontent;
        this.send()
        this.inputcontent = '';
      }
    },    
    send() {
      console.log("Send message:" + this.inputcontent);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          id: this.id,
          nickname: this.nickname,
          content: this.content,
          roomName: this.room, 
        };
        this.stompClient.send("/receive/"+this.room, JSON.stringify(msg), {});



/////
              
                this.$store.dispatch(Constant.SEND_CHAT,{
                  //bno : auto increase
                    // bwriter : this.board.bwriter, 임시로 ssafy foreign key때문
                    roomName : this.rname,
                    id : this.id,
                    nickname : this.nickname,
                    content : this.content,
                    makedate : new Date(),
                    // changeDay : this.board.changeday,
                    // changeId : this.board.changeid
                }
                );

                

               /////
        //this.stompClient.send("/chat/message/" + this.room, JSON.stringify(msg), {});
      }
    },    
    connect() {  
        console.log("소켓 커넥트 실행");
        const serverURL = "http://i3a208.p.ssafy.io:8000"
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
        this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
            
            
          this.stompClient.subscribe("/send/" + this.room, res => {
          console.log('구독으로 받은 메시지 입니다.', res.body);
          console.log("구독 : " + "/send/" + this.room);
          console.log("여기서 ㅂ다아오니?");
          // roomname으로 들어오는 방 정보가 현재 접속한 방 정보와 일치하면 push해주게 해서 방을 구분함.
          // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
          this.recvList.push(JSON.parse(res.body));
          console.log("roomName : " + this.rname);
          console.log("id : " + this.id);
          
          this.$store.dispatch(Constant.CHAT_READ, {
          roomName : this.rname,
          id : this.id,}
          );

          });


        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    },
    disconnect() {
      if(this.stompClient !== null) {
        console.log("disconnect");
        this.stompClient.disconnect();
      }
    },

  },

  
};


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messages {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  .detaillist {
    div{
      border: 1px black solid;
      &.read{
        background-color: skyblue;
      }
    }
  }
}
</style>

<template>
  <div class="chatroom">
    <section class="roomlist" v-show="!chatting">
        <li v-for="room in rooms" :key="room.roomName" @click="startchat(room.roomName)">
          <div class="row">
            <div class="col-sm-2">사진</div>
            <div class="col-sm-8">
              {{room.roomName}}
              <p>안 읽은 개수 : {{room.cnt}}</p>
              <p>사람 수 {{room.membercnt}}</p>
              <p>최근 메시지 {{room.content}}</p>
            </div>
            <div class="col-sm-2">시간, 배지</div>
          </div>
            <!--{{room.roomName}}-->
        </li>
    </section>


 <chat :rname="rname" v-if="chatting" />

<!--
div시작
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.nickname }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>
끝
   
            <input
      v-model="id"
      type="text"
    >
    내용: <input
      v-model="content"
      type="text"
      @keyup="sendMessage"
    >

-->
  </div>
</template>

<script>
import Constant from "../../Constant";
import chat from "./chat.vue";
//import Stomp from 'webstomp-client'
//import SockJS from 'sockjs-client'

export default {
  name: "chatroom",
  components: {
    chat,
  },
  data : function() {
    return{
        chatting: false,
        rname: "",
        id : "",
        nickname : "",
        content : "",
        makedate : "",
        recvList: [],
    }
  },
  computed: {
    rooms() {
        console.log("rooms 실행");
      return this.$store.state.chatstore.rooms;
    },
  },
  created() {
        this.$store.dispatch(Constant.GET_CHATROOMLIST);

/*
        /////추가파트
        console.log("create 실행됨");
        console.log("소켓 커넥트 준비");
        this.connect();
        console.log("소켓 커넥트 실행끝");
        //////
        */
  },
  methods: {
      startchat: function(e) {
        //console.log("채팅창 시작");
        console.log("인자값 : " + e)
        console.log(this.chatting);
        this.chatting = true;
        this.rname = e;
        console.log("roomName : " + this.rname);
        
        //추가한 부분
        //this.stompClient.subscribe("/send/" + this.roomName);
        //console.log("구독 성공  : " + this.roomName); 
        //// 여기까지.
        this.$store.dispatch(Constant.GET_CHATLIST, {roomName : this.rname});
      },

        ///여기부터 소켓부분 connect 추가했음..!
        /*
    sendMessage (e) {
      if(e.keyCode == 13 && this.id !== '' && this.content !== ''){
        console.log("실행됨?");
        console.log(this.id);
        console.log(this.content)
        this.send();
        this.content = '';
        this.id = '';
      }
    },    
    send() {
      console.log("Send message:" + this.content);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          nickname: this.nickname,
          content: this.content,
          roomName: this.room 
        };
        this.stompClient.send("/receive/"+this.roomName, JSON.stringify(msg), {});
        
        //this.stompClient.send("/chat/message/" + this.pid, JSON.stringify(msg), {});
      }
    },    
    enter() {
        console.log("click 됨");
        //this.stompClient.subscribe("/send/" + this.pid, res => {
        this.stompClient.subscribe("/send/" + this.pid, res => {
        console.log('구독으로 받은 메시지 입니다.', res.body);
        console.log("구독 : " + "/send/" + this.pid);
        // roomname으로 들어오는 방 정보가 현재 접속한 방 정보와 일치하면 push해주게 해서 방을 구분함.
        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
        this.recvList.push(JSON.parse(res.body))
        });
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
            //this.stompClient.subscribe("/send/" + this.roomName);
            //console.log("구독 성공  : " + this.roomName) 
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          //this.stompClient.subscribe("/send", res => {
          //this.stompClient.subscribe("/send/" + this.pid, res => {
          //  console.log('구독으로 받은 메시지 입니다.', res.body);
          //  console.log("구독 : " + "/send/" + this.pid);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
          //  this.recvList.push(JSON.parse(res.body))
          //});
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
    */
  }
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

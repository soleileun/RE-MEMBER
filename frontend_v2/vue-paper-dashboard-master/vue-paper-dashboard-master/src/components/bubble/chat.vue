<template>
  <div class="chatroom" >
    <div class="chatlist"  >

      <div class="base" ref="base">
        <div v-for="chat in chats" :key="chat.chno">
          <div v-if="chat.id === id">
            <span >
            <div  class="me">{{ chat.content }}</div>
            </span>
            <p> </p>
          </div>

          <div v-else style="text-align:left">
            <div class = "namepart">
              <img src="" style="width:30px;height:30px; padding-top:6px;" > 
            </div>
          <div>
            <span style="font-size:13px; text-align:top">{{ chat.nickname }} </span>
            <br>
            <span>
            <div class="other" >{{ chat.content }} </div>
            </span>
            </div>
          </div>
  <br>

        </div>

        <div v-for="(item, idx) in recvList" :key="idx">
          <div v-if="item.id === id">
            <span >
            <div  class="me">{{ item.content }}</div>
            </span>
            <p> </p>
          </div>

         <div v-else style="text-align:left">
            <div style="width:30px; height:100%; float:left; margin:10px; margin-top:0px;" >
              <img src="" style="width:30px;height:30px;"> 
            </div>
          <div>
            <div style="font-size:6px; height:4px; margin:5px;">{{ item.nickname }} </div>
            <br>
            <span style="background-color:blue;">
            <div  class="other">{{ item.content }}</div>
            </span>
            </div>
          </div>

            <br>
        </div>
      </div>




    </div>
    <p></p>
      내용: <input v-model="inputcontent" type="text" @keyup="sendMessage">
          <button  @click="clickSend()" >입력</button>
          <button @click="back()" >뒤로</button>

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
  props: ['rname', 'chatting'],
  data: function() {
    return {
        room : this.rname,
        id : storage.getItem("userid"),
        nickname : "",
        content : "",
        makedate : "",
        recvList: [],
        propchatting:this.chatting,
        isFirst : true,
        //beforeScrollTop : "",
    };
  },
  computed: {
    chats() {
        return this.$store.state.chatstore.chats;
    },
  },
  updated: function(){ // 스크롤 관련입니다

  
    var a = this.$refs.base;

    if(this.isFirst == true) {
      this.isFirst = false;
      a.scrollTop = a.scrollHeight;
    }

  },
  created() { // 소켓 초기 연결을 시작합니다.
      this.connect();
      console.log("STOMP is working");
         this.$store.dispatch(Constant.CHAT_READ, {
          roomName : this.room,
          id : this.id,}
          );
          console.log(this.id + "가 쓴거 아님 다 읽음 표시로 바꿈");

    return this.$store.state.chatstore.chats;
  },
  methods: {
    
    clickSend () {
      if(this.id !== '' && this.inputcontent !== ''){
        this.nickname = storage.getItem("userid"); // 현재 접속중인 유저의 id를 사용합니다.
        this.content = this.inputcontent;
        this.send()
        this.inputcontent = '';
      }
    },    
    sendMessage (e) {
      if(e.keyCode === 13 && this.id !== '' && this.inputcontent !== ''){ // 엔터를 눌러 메시지를 발송합니다.
        this.nickname = storage.getItem("userid"); // 현재 접속중인 유저의 id를 사용합니다.
        this.content = this.inputcontent;
        this.send()
        this.inputcontent = '';
      }
    },    
    send() { // 메시지를 발송하는 함수입니다.
      console.log("Send message:" + this.inputcontent);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          id: this.id,
          nickname: this.nickname,
          content: this.content,
          roomName: this.room, 
        };
        this.stompClient.send("/receive/"+this.room, JSON.stringify(msg), {});

        this.$store.dispatch(Constant.SEND_CHAT,{
          roomName : this.rname,
          id : this.id,
          nickname : this.nickname,
          content : this.content,
          makedate : new Date(),
          });

        //this.stompClient.send("/chat/message/" + this.room, JSON.stringify(msg), {});
      }
    },    
    connect() {  
        console.log("socket start");
        const serverURL = "http://i3a208.p.ssafy.io:8000"
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        console.log(`target server : ${serverURL}`)
        this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('[SUCCESS] socket is connected', frame);
            
            
          this.stompClient.subscribe("/send/" + this.room, res => {
          // roomname으로 들어오는 방 정보가 현재 접속한 방 정보와 일치하면 push해주게 해서 방을 구분함.
          // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body));

            
            var a = this.$refs.base;
            a.scrollTop = a.scrollHeight;


            this.$store.dispatch(Constant.CHAT_READ, {
              roomName : this.rname,
              id : this.id,}
            );
          });
          console.log(this.id + "[UPDATE] isRead field");
        },
        error => {
          // 소켓 연결 실패
          console.log('[FAIL] socket connection is failed', error);
          this.connected = false;
        }
      );        
    },
    back() {
 //     console.log("back 클릭");
      this.$emit('endchat',false);
    }

  },
beforeDestroy:function(){
    if(this.stompClient !== null) {
        console.log("disconnect");
        this.stompClient.disconnect();
      }
  },
  
};


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.chatroom {
  height: 100%;
  padding-bottom: 10px;
  .chatlist{
  overflow-y : auto;
  overflow-x : hidden;
  height:88%;
  }
  .base {
    height : 100%; 
    width:100; 
    padding:15px;
    padding-left : 8px;
    overflow-y : auto;
    overflow-x : auto;
    color:black; 
    
  }


.me {
  position:relative;
  padding:7px 15px;
  margin:0;
  background-color:#ffdf89;
  border-radius: 15px;
  display:inline-block;
  font-size : 12px;
  float: right;
}

.other {
  position:relative;
  padding:7px 15px;
  margin:0;
  background-color: rgb(148, 183, 223);
  border-radius: 15px;
  display:inline-block;
  font-size : 12px;
}
.namepart {
  width:30px; 
  height:100%; 
  float:left; 
  margin:10px; 
  margin-top:0px;
}
button {
  cursor: pointer;
  border: 2px solid #008CBA;
  background-color : white;
  border-radius : 10px;
  color:black; 
  margin:10px;
  margin-bottom:0;
}

}
</style>

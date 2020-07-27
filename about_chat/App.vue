<template>

<div class="container p-3 my-3 bg-dark text-white" style= "height:700px; width:600px;">
  채팅창입니다.
  <div style = "height : 500px; width:570px; padding:25px; background:white; overflow-y : auto;color:black; background:skyblue">

    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <!--
        유저이름: {{ item.userName }} 
        내용: {{ item.content }}
      -->
      <br>
      <div>
        <div style="width:30px; height:100%; float:left; margin:10px; margin-top:0px;" >
        <img src="./sample.jpeg" style="width:30px;height:30px">
        </div>
        <div style="font-size:6px; height:4px; margin:5px">{{ item.userName }} </div>
        <div>{{ item.content }}</div>
      </div>
    </div>
  </div>

  <div id="app" style="color:white;">
    
    <navibar />
    <sidebar />
    <login />
    유저이름: 
    <input
      v-model="userName"
      type="text"
    >
    <br>
    내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >
    <br>
    방id: 
    <input
      v-model="pid"
      type="text"
    >
    <br>
          <button @click="enter" style="color:black;">enter</button>
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.nickname }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>
    <section class="rtViewContainer">
      <router-view></router-view>
    </section>
    <buble v-if="userNick" />
    <footerr />
  </div>
  </div>
</template>

<script>
import login from "./components/login.vue";
import navibar from "./components/navibar.vue";
import sidebar from "./components/sidebar.vue";
import buble from "./components/buble.vue";
import footerr from "./components/footer.vue";


import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

const storage = window.sessionStorage;

export default {
  name: "App",
  components: {
    navibar,
    sidebar,
    buble,
    footerr,
    login
  },
  data() {
    return {
      userName: "vzxc", // 나중에는 토큰에서 유저이름 넣어주시면 됩니다.
      message: "",
      pid: "vzxc",  // 나중에는 url에서 pid 파싱해서 넣어주시면 됩니다.
      recvList: []
    }
  },
  created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          nickname: this.userName,
          content: this.message,
          roomName: this.pid 
        };
        this.stompClient.send("/receive/"+this.pid, JSON.stringify(msg), {});
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
  },
  mounted: function(){
    console.log(storage)
    this.$store.dispatch("init", {});
  },
  beforeUpdate: function(){
    this.$store.dispatch("update", {});
  },
  computed: {
    userNick: function (){
      return this.$store.state.userstore.userNick
    },
  },
};
</script>

<style lang="scss">
html,
body {
  margin: 0;
  padding: 0;
  noscript {
    height: 0;
  }
}
body {
  padding-top: 7vh;
  section {
    min-height: 500px;
  }
}
#app {
  color: red;
  img {
    width: 100%;
    height: 250px;
  }
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  z-index: 999;
}
textarea {
  resize: none;
}
</style>

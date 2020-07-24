
<template>


<div class="container p-3 my-3 bg-dark text-white" style= "height:600px; width:600px;">
  채팅창입니다.
  <section style = "height : 500px; width:570px; padding:25px; background:white; overflow-y : scroll">
    </section>
  <div id="app">
    유저이름: 
    <input
      v-model="userName"
      type="text"
    >
    내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >
    방id: 
    <input
      v-model="pid"
      type="text"
    >
          <button @click="enter">enter</button>
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>
  </div>
</div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: 'App',
  data() {
    return {
      userName: "",
      message: "",
      pid: "",
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
          userName: this.userName,
          content: this.message,
          pid: this.pid 
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

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
          });
      },
    connect() {  
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    }
  }
}

</script>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
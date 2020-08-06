<template>
  <div class="chatroom" >
    
    <div style="height 6%; text-align : center;" >
      <div style="float:left; margin-top:10px;" class="hand" @click="back()">
      <i class="fa fa-chevron-left " style="float:left; margin-left:12px; margin-top: 5px;" ></i>
     이전
      </div>
      {{ this.room }}
<div style="float:right; margin-top:10px; margin-right:12px;" class="hand"  @click="exit()">
<i class="fa fa-sign-out" style="float:left; margin-top: 5px;"></i>
나가기
</div>
      
          <drop-down class="nav-item" title-classes="nav-link" icon="fa fa-envelope hand" title="초대">
            

                <input v-model="target" type="text">
                <button @click="invite()" >초대</button>
              <a class="dropdown-item" href="#">Notification 1</a>
              <a class="dropdown-item" href="#">Notification 2</a>
          </drop-down>


<!--
      <div class="btn-group dropdown" style="float:right;">
    <i type="button" class="fa fa-align-justify dropdown-toggle" data-toggle="dropdown">

    </i>

    <div class="dropdown-menu">
      
      <p class="dropdown-item" data-toggle="collapse" data-target="#demo" >초대하기</p>
      
              <div id="demo" class="collapse">
                <input v-model="userinvite" type="text" @keyup="inviteuser">
                <button @click="invite()" >초대</button>

              </div>

           

      <a class="dropdown-item" href="#" @click="exit()">나가기</a>
    </div>
    -
    </div>
    -->
      <!--
      <div class="menu dropdown">
          <i type="button" class="fa fa-align-justify hand dropdown-toggle" data-toggle="dropdown" ></i>
        
          <div class="dropdown-content">
            <button data-toggle="collapse" data-target="#demo">초대하기</button>

              <div id="demo" class="collapse">
                <input v-model="userinvite" type="text" @keyup="inviteuser">
                <button @click="invite()" >초대</button>

              </div>
              <button @click="exit()" >나가기</button>
        </div>
      </div>
      -->
    </div>
    <div class="chatlist"  >

      <div class="base" ref="base">
        <div v-for="chat in chats" :key="chat.chno">
          <div v-if="chat.id === id">
            <span >
            <div  class="me">{{ chat.content }}</div>
            </span>
            <p> </p>
          </div>

          
          <div v-else-if="chat.id === 'system'">
            <span >
              <p></p>
            <div style="text-align:center;">{{ chat.content }}</div>
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

          <div v-else-if="item.id === 'system'">
            <span >
              <p></p>
            <div style="text-align:center;">{{ item.content }}</div>
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
    <div style="margin-left : 20px;">
      내용: <input v-model="content" type="text" @keyup="sendMessage" style="width:70%; margin-left:12px;">
          <button  @click="clickSend()" >입력</button>
    </div>
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
        target:"",
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
      if(this.id !== '' && this.content !== ''){
        this.nickname = storage.getItem("userid"); // 현재 접속중인 유저의 id를 사용합니다.
        this.content = this.content;
        this.send()
        this.content = '';
      }
    },    
    sendMessage (e) {
      if(e.keyCode === 13 && this.id !== '' && this.content !== ''){ // 엔터를 눌러 메시지를 발송합니다.
        this.nickname = storage.getItem("userid"); // 현재 접속중인 유저의 id를 사용합니다.
        this.content = this.content;
        this.send()
        this.content = '';
      }
    },    
    send() { // 메시지를 발송하는 함수입니다.
      console.log("Send message:" + this.content);
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
    },
    /*
    inviteuser(e) {
      
      if(e.keyCode === 13 && this.id !== '' && this.target !== ''){ // 엔터를 눌러 메시지를 발송합니다.
        this.invite()
        console.log("target");
      }
    },
    */
    invite() {
      if(this.id !== '' && this.target !== '') {
        //만약 유저 아이디로 조회해서 널이 아니면 초대함.
        //널이면 alert 보내자.
        this.$store.dispatch(Constant.ADD_CHATROOM,{
          roomName : this.rname,
          uid : this.target,
          })
          .then(response =>{
            if(response == null)
            console.log("then에서 찍어주는거야");
            console.log(response);
            
          })
          
      console.log(this.target + "님을 초대");
      alert(this.target + "님을 초대했습니다.");
      }


      //초대 관련 시스템 메시지

      
      console.log("Send message:" + this.target + "님께서 입장하셨습니다.");
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          id: "system",
          nickname: "system",
          content: this.target + "님께서 입장하셨습니다.",
          roomName: this.room, 
        };
        this.stompClient.send("/receive/"+this.room, JSON.stringify(msg), {});

        this.$store.dispatch(Constant.SEND_CHAT,{
          roomName : this.rname,
          id : "system",
          nickname : "system",
          content : this.target + "님께서 입장하셨습니다.",
          makedate : new Date(),
          });
      }
      // 끝
    },
    exit() {
      console.log("나가기");

      //나감 관련 시스템 메시지
      console.log("Send message:" + this.id + "님께서 나가셨습니다.");
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          id: "system",
          nickname: "system",
          content: this.id + "님께서 나가셨습니다.",
          roomName: this.room, 
        };
        this.stompClient.send("/receive/"+this.room, JSON.stringify(msg), {});

        this.$store.dispatch(Constant.SEND_CHAT,{
          roomName : this.rname,
          id : "system",
          nickname : "system",
          content : this.id + "님께서 나가셨습니다.",
          makedate : new Date(),
          });
      }
      // 메시지 끝

        this.$store.dispatch(Constant.REMOVE_CHATROOM,{
          roomName : this.rname,
          });
          this.back();
        //this.$router.push('/mainboard/qaboard');
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
  height:80%; // 원래 88%줬음
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


.menu {
   //height:7%; 
   //opacity: 0.7;
   float:right;
}
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown > i {
  float:right; margin:4px;margin-right:12px; margin-top:10px;
}

.dropdown-content {
  display: none;
  position: absolute;
  right: 0;
  background-color: #f9f9f9;
  min-width: 120px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  text-align : center;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.hand:hover {
cursor:pointer
}
}
</style>

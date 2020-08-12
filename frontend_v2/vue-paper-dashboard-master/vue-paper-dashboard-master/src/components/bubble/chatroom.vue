<template>
  <div class="chatroom">
    <div class="roomlist" v-show="!chatting">
        <div class = "list" v-for="room in rooms" :key="room.roomName" @click="startchat(room.roomName)">
          <div class="row">
            <div class="col-sm-2" style="padding : 15px;">
            <div style="background : yellow; width:40px; height:40px; border-radius:10px"> </div>
            </div>
            <div class="col-sm-8" style="text-align : left; overflow:hidden">
                <p class="font-weight-bold">{{room.roomName}} ({{room.membercnt}}명)</p>
              <p>최근 메시지 {{room.content}}</p>
            </div>
            <div class="col-sm-2" >
              <p style="font-size:15px;">
                {{room.makedate.slice(5,10) }}
                <br> 
                {{room.makedate.slice(11,16) }}
                </p>
              <span v-if="room.membercnt === 2 && room.cnt != 0" class="badge badge-danger" style="margin-left:10px;">{{room.cnt}}</span>
            </div>
          </div>
            <!--{{room.roomName}}-->
        </div>
    </div>


 <chat @endchat="endchat" :rname="rname" :chatting="chatting" v-if="chatting" />

  </div>
</template>

<script>
import Constant from "../../Constant";
import chat from "./chat.vue";
//import Stomp from 'webstomp-client'
//import SockJS from 'sockjs-client'
const storage = window.sessionStorage;

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
      return this.$store.state.chatstore.rooms;
    },
  },
  created() { // 채팅방 정보를 받아옵니다.
    this.$store.dispatch(Constant.GET_CHATROOMLIST);
    
  },
  methods: {
      startchat: function(e) { //채팅방을 눌렀을 때 실행됩니다. 해당 chat.vue로 연결되어 채팅방에 입장합니다. 
        
        this.chatting = true;
        this.rname = e;
        this.$store.dispatch(Constant.GET_CHATLIST, {roomName : this.rname, id:storage.getItem("userid")});
      },
      endchat(f) { // 채팅방을 닫을 때 실행됩니다. chat.vue에서 사용되며, chatroom으로 돌아옵니다.
        this.chatting = f;
        this.$store.dispatch(Constant.GET_CHATROOMLIST);
      }

  }
};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.chatroom {
  height: 100%;
  padding-bottom: 3px;
  overflow-y : auto;
  overflow-x : hidden;
  height:98%;
  .list {
    padding: 10px;
    margin: 8px;
    background-color: white;
    cursor: pointer;
    /* 박스 테두리 만드는 것인데, 없는게 더 예쁜것 같습니다.
    //border-radius: 5px;
    //box-shadow: 1px 1px 3px black ;
    */
    text-align: start;
    max-height:120px;
    overflow:hidden;
    h6 {
      display: flex;
      justify-content: space-between;
      border-bottom: 1px gray solid;
    }
    &.read {
      background-color: #ddddff;
    }
    &:hover{
      border: 1px solid black;
    }
  }
  p {
    margin-bottom : 4px;
  }
}
</style>

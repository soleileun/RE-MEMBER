<template>
  <div class="bubble">
    <i class="far fa-comments" @click="act"></i>

    <div class="bubble-items btns">
      <button @click="bubbleState('1')">알림</button>
      <button @click="bubbleState('2')">채팅창</button>
      <button @click="bubbleState('3')">팔로우</button>
      <button @click="bubbleState('4')">쪽지함</button>
    </div>
    <div class="bubble-items">
      <news class="news" v-if="bubbleS === '1'" />
      <chatroom class="chatroom" v-if="bubbleS == '2'" />
      <follows class="follows" v-if="bubbleS === '3'" />
      <messages class="messages" v-if="bubbleS === '4'" />
    </div>
    <div class="bubble-items exit" @click="act">닫기</div>
  </div>
</template>

<script>
import news from "./bubble/news.vue";
//import users from "./bubble/users.vue";
import follows from "./bubble/follows.vue";
import messages from "./bubble/messages.vue";
import chatroom from "./bubble/chatroom.vue";

export default {
  name: "bubble",
  components: {
    news,
 //   users,
    follows,
    messages,
    chatroom,
  },
  computed: {
    bubbleS: function(){
      return this.$store.state.userstore.bubbleS
    },
  },
  mounted: function () {},
  methods: {
    act: function () {
      document.querySelector(".bubble").classList.toggle("active");
      this.$store.dispatch("update");
      this.$store.commit('bubbleState', { st: '1'})
      this.$store.commit("viewMes",{id:"",view:false})
    },
    bubbleState: function (state) {
      this.$store.commit('bubbleState', { st: state })
      this.$store.commit("viewMes",{id:"",view:false})
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.bubble {
  position: fixed;
  right: 50px;
  bottom: 50px;
  height: 80px;
  width: 80px;
  border-radius: 80px;
  box-sizing: border-box;
  border: 2px black solid;
  background-color: #ddddaa;
  z-index: 9999;
  i {
    padding: 13px;
    font-size: 49px;
    color: black;
  }
  .bubble-items {
    display: none;
  }
  @media (max-width: 758px) {
    &.active {
      right: 0px;
      width: 100vw;
      height: 90vh;
      border-radius: 30px;
      overflow: hidden;
      animation-duration: 0.5s;
      animation-name: big2;
      .bubble-items {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-content: center;
        button {
          width: 25%;
        }
        &.btns {
          flex-direction: row;
        }
        .news,
        .users,
        .follows,
        .messages {
          height: 70vh;
          overflow-y: scroll;
        }
        &.exit {
          position: fixed;
          height: 1rem;
          bottom: 90vh;
          right: 10vw;
          animation-duration: 0.5s;
          animation-name: visi;
        }
      }
    }
  }
  @media (min-width: 758px) {
    &.active {
      width: 450px;
      height: 550px;
      overflow: hidden;
      animation-duration: 0.5s;
      animation-name: big1;
      .bubble-items {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-content: center;
        button {
          width: 25%;
        }
        .news,
        .users,
        .follows,
        .messages {
          height: 440px;
          border-bottom-left-radius: 80px;
          border-bottom-right-radius: 80px;
        }
        &.btns {
          flex-direction: row;
          height: 35px;
        }
        &.exit {
          position: fixed;
          height: 1rem;
          bottom: 550px;
          right: 100px;
          animation-duration: 0.5s;
          animation-name: visi;
        }
      }
    }
  }
}
@keyframes big1 {
  from {
    // width: 80px;
    height: 80px;
  }
  to {
    width: 450px;
    height: 550px;
  }
}
@keyframes big2 {
  from {
    // width: 80px;
    height: 80px;
  }
  to {
    width: 100vw;
    height: 90vh;
  }
}
@keyframes visi {
  from {
    opacity: 0;
  }
  99% {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>

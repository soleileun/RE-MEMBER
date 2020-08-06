<template>
  <div class="bubble" :style="{height:`${bubbleY+4}px`,width:`${bubbleX+4}px`}">
    <div class="con" :style="{height:`${bubbleY}px`,width:`${bubbleX}px`}">
      <div class="icon" draggable="false">
        <i class="ti-comments" @click="act">
          <div class="newdot" v-if="newdot"></div>
        </i>
        <i class="ti-location-arrow bubble-items dragable" draggable="true" @dragend="dragend" @drag="drag"></i>
        <i class="ti-close exit bubble-items" @click="act"></i>
        <div class="bubble-items btns">
          <button class="act" id="bubble1" @click="bubbleState('1')">알림</button>
          <button id="bubble2" @click="bubbleState('2')">채팅창</button>
          <button id="bubble3" @click="bubbleState('3')">팔로우</button>
          <button id="bubble4" @click="bubbleState('4')">쪽지함</button>
        </div>
      </div>
      <div class="bubble-items contents" :style="{height:`${bubbleY-55}px`}" draggable="false">
        <news class="news" v-if="bubbleS === '1'" />
        <chatroom class="chatroom" v-if="bubbleS == '2'" />
        <follows class="follows" v-if="bubbleS === '3'" />
        <messages class="messages" v-if="bubbleS === '4'" />
      </div>
    </div>
  </div>
</template>

<script>
import news from "./bubble/news.vue";
import follows from "./bubble/follows.vue";
import messages from "./bubble/messages.vue";
import chatroom from "./bubble/chatroom.vue";
const storage = window.sessionStorage;

export default {
  name: "bubble",
  components: {
    news,
    follows,
    messages,
    chatroom,
  },
  data: function () {
    return {
      bubbleX: 60,
      bubbleY: 60,
    };
  },
  computed: {
    bubbleS: function () {
      return this.$store.state.userstore.bubbleS;
    },
    newdot: function(){
      return this.$store.state.userstore.bubbleNew;
    }
  },
  watch: {
    bubbleS: function (x) {
      for (let i = 1; i < 5; i++) {
        if (x === `${i}`) {
          document.querySelector(`#bubble${i}`).classList.add("act");
          if (x==='4' ){
            this.bubbleSizing()
          }
        } else {
          document.querySelector(`#bubble${i}`).classList.remove("act");
        }
      }
    },
  },
  methods: {
    act: function () {
      document.querySelector(".bubble").classList.toggle("active");
      this.bubbleSizing()
      this.$store.dispatch("update");
      this.$store.commit("bubbleState", { st: "1" });
      this.$store.commit("viewMes", { id: "", view: false });
    },
    bubbleSizing:function(){
      if (
        document
          .querySelector(".bubble")
          .classList.value.split(" ")
          .indexOf("active") >= 0
      ) {
        this.bubbleX =
          storage.getItem("bubbleX") * 1 > 450 &&
          storage.getItem("bubbleX") * 1 <= window.innerWidth - 50
            ? storage.getItem("bubbleX") * 1
            : 450;
        this.bubbleY =
          storage.getItem("bubbleY") * 1 > 550 &&
          storage.getItem("bubbleY") * 1 <= window.innerHeight - 100
            ? storage.getItem("bubbleY") * 1
            : 550;
      } else {
        this.bubbleX = 60;
        this.bubbleY = 60;
      }
    },
    bubbleState: function (state) {
      this.$store.commit("bubbleState", { st: state });
      this.$store.commit("viewMes", { id: "", view: false });
    },
    drag: function (e) {
      this.bubbleX = Math.min(
        Math.max(window.innerWidth - e.pageX - 25, 400),
        window.innerWidth - 50
      );
      this.bubbleY = Math.min(
        Math.max(window.innerHeight - (e.pageY - window.scrollY) - 25, 500),
        window.innerHeight - 100
      );
    },
    dragend: function (e) {
      this.bubbleX = Math.min(
        Math.max(window.innerWidth - e.pageX - 25, 400),
        window.innerWidth - 50
      );
      this.bubbleY = Math.min(
        Math.max(window.innerHeight - (e.pageY - window.scrollY) - 25, 500),
        window.innerHeight - 100
      );
      storage.setItem("bubbleX", this.bubbleX);
      storage.setItem("bubbleY", this.bubbleY);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.bubble {
  position: fixed;
  right: 15px;
  bottom: 15px;
  padding: 2px;
  border-radius: 80px;
  background-color: #cce;
  // box-shadow: -2px 0 #537, 0 2px #537, 2px 0 #537, 0 -2px #537;
  box-shadow: 2px 2px 12px #77a;
  box-sizing: border-box;
  z-index: 9999;
  .con {
    background-color: white;
    border-radius: 80px;
    .icon {
      padding: 7px;
      padding-left: 10px;
      font-size: 39px;
      color: #537;
      .ti-comments {
        position: relative;
        .newdot {
          position: absolute;
          top: 0px;
          right: 0px;
          width: 10px;
          height: 10px;
          border-radius: 10px;
          background-color: red;
        }
      }
    }
    .bubble-items {
      display: none;
    }
  }
  &:hover {
    background-color: #aaf;
    cursor: pointer;
  }
  &.active {
    border-radius: 10px;
    .ti-comments {
      display: none;
    }
    .con {
      border-radius: 10px;
      .bubble-items {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-content: center;
      }
      .icon {
        position: relative;
        padding-top: 5px;
        display: flex;
        justify-content: space-around;
        align-content: center;
        flex-direction: row;
        height: 55px;
        box-shadow: #aaa 0px 1px 10px;
        margin-bottom: 3px;
        .dragable {
          cursor: nw-resize;
          color: black;
          font-size: 1rem;
          text-align: start;
          position: absolute;
          left: 0px;
          top: 0px;
        }
        .exit {
          color: black;
          height: 2.4rem;
          width: 2.4rem;
          font-size: 2.5rem;
          border-radius: 2.5rem;
          cursor: pointer;
          &:hover {
            background-color: #ccc;
          }
        }
        .btns {
          font-size: 1.2rem;
          flex-direction: row;
          box-sizing: border-box;
          border: none;
          width: 80%;
          height: 100%;
          button {
            width: 25%;
            height: 90%;
            background: none;
            box-sizing: border-box;
            border: none;
            outline: none;
            &.act {
              border-bottom: 5px solid blue;
            }
            &:hover {
              background-color: #aaa;
            }
          }
        }
      }
      &:hover {
        cursor: default;
      }
    }
    &:hover {
      background-color: skyblue;
      cursor: default;
    }
  }
}
</style>

<template>
  <div class="follows">
      <h4>활동중인 유저</h4>
    <div v-if="followings.length===0">팔로우한 사람이 없습니다.</div>
    <div v-if="followings">
      <div v-if="!followings.filter(item=> item.state ===true).length===0">활동중인 팔로우가 없습니다.</div>
      <div
        class="follow"
        v-for="target in followings.filter(item=> item.state ===true)"
        :key="target.id"
      >
        <drop-down :title="target.nickname+'님'">
          <div class="dropdown-item" @click="mes(target.id)">메세지</div>
          <div class="dropdown-item" v-if="sameroom[target.id]">채팅방 있음</div>
          <div class="dropdown-item" v-else @click="doChat(target.id)">채팅방 개설</div>
          <div class="dropdown-item" @click="viewProf(target.id)">프로필 보기</div>
          <div class="dropdown-item" @click="delFollow(target.id)">팔로우 해제</div>
        </drop-down>
        <div>마지막 접속일 {{datee(target.lastDate)}}일 전</div>
      </div>
    </div>
    <div v-if="followings">
      <h4>휴면중인 유저</h4>
      <div v-if="followings.filter(item=> item.state ===false).length===0">휴면중인 팔로우가 없습니다.</div>
      <div
        class="dropdown follow"
        v-for="target in followings.filter(item=> item.state ===false)"
        :key="target.id"
      >
        <drop-down :title="target.nickname+'님'">
          <div class="dropdown-item" @click="mes(target.id)">메세지</div>
          <div class="dropdown-item" @click="viewProf(target.id)">프로필 보기</div>
          <div class="dropdown-item" @click="delFollow(target.id)">팔로우 해제</div>
        </drop-down>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
import http from "../../http-common.js";
const storage = window.sessionStorage;
export default {
  name: "follows",
  data: function () {
    return {
      // followings:[]
    };
  },
  beforeCreate: function () {
    this.$store.dispatch("getFollow");
    this.$store.dispatch("getFollower");
  },
  computed: {
    followings: function () {
      return this.$store.state.userstore.followings;
    },
    followers: function () {
      return this.$store.state.userstore.followers;
    },
    rooms() {
      let tmp = [];
      this.$store.state.chatstore.rooms.forEach((item) => {
        item.roomName.split(",").forEach((item) => {
          tmp.push(item);
        });
      });
      return tmp;
    },
    sameroom() {
      return this.$store.state.chatstore.sameroom;
    },
  },
  mounted() {},
  methods: {
    datee(day) {
      if (day) {
        console.log();
        return new Date().getDate() - new Date(day).getDate();
      } else {
        return "";
      }
    },
    mes: function (id) {
      this.$store.dispatch("sendMes", { toUser: id });
    },
    fol: function (id) {
      this.$store.dispatch("follow", { target: id });
    },
    viewProf(id) {
      this.$router.push("/profile/" + id);
    },
    delFollow(id) {
      // console.log(id);
      this.$store.dispatch("delFollow", {
        uid: storage.getItem("userid"),
        target: id,
      });
    },
    doChat(id) {
      this.$store.dispatch(Constant.ADD_CHATROOM, {
        roomName: `${storage.getItem("userid")},${id}`,
        uid: storage.getItem("userid"),
      });
      this.$store.dispatch(Constant.ADD_CHATROOM, {
        roomName: `${storage.getItem("userid")},${id}`,
        uid: id,
      });
      /*
      this.$store.dispatch(Constant.SEND_CHAT, {
        roomName: `${storage.getItem("userid")},${id}`,
        id: "system",
        nickname: "system",
        content: `${storage.getItem("userid")}님이 입장하셨습니다.`,
        date: new Date(),
      });
      this.$store.dispatch(Constant.SEND_CHAT, {
        roomName: `${storage.getItem("userid")},${id}`,
        id: "system",
        nickname: "system",
        content: `${id}님이 입장하셨습니다.`,
        date: new Date(),
      });
      */
      this.$store.commit(Constant.GET_CHATROOMONETOONE, {
        sameroom: "임시",
        uid: id,
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.follows {
  padding: 10px;
  height: 100%;
  padding-top: 15px;

  display: flex;
  flex-direction: column;
  justify-items: flex-start;
  align-content: stretch;
  overflow-y: auto;
  h4 {
    margin: 0;
  }
  .follow {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 98%;
    height: 30px;
    margin: 5px;
    padding: 0px 15px 0px 10px;
    border: 1px black solid;
    border-radius: 8px;
    li {
      list-style: none;
      span.notification {
        color: white;
      }
    }
  }
  &::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  &::-webkit-scrollbar-track {
    background-color: #aae;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
}
</style>

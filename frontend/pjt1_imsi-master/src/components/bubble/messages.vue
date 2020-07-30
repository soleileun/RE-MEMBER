<template>
  <div class="messages">
    <div v-if="messageList.length===0 &&!mesviewdetail">쪽지 중인 상대가 없습니다.</div>
    <div v-if="!mesviewdetail">
      <div class="mes" v-for="mes in messageList" :key="mes.id" @click="detailview(mes.fromUser,mes.toUser)" :class="{read:!mes.read}">
        <h6>
          <span>{{abc(mes.toUser,mes.fromUser)}}</span>
          <!-- <span>{{mes.time.slice(11, 20)}}</span> -->
        </h6>
        {{mes.content}}
      </div>
    </div>
    <messagedetail v-if="mesviewdetail" />
  </div>
</template>


<script>
import messagedetail from "./messagedetail.vue";
const storage = window.sessionStorage;

export default {
  name: "messages",
  components: {
    messagedetail,
  },
  computed: {
    messageList: function () {
      return this.$store.state.userstore.messageList;
    },
    mesviewdetail: function () {
      return this.$store.state.userstore.mesviewdetail;
    },
  },
  methods: {
    abc: function (a, b) {
      return a === storage.getItem("userid") ? b : a;
    },
    detailview: function (id1, id2) {
      let detailid;
      if (storage.getItem("userid") === id1) {
        detailid = id2;
      } else if (storage.getItem("userid") === id2) {
        detailid = id1;
      }
      this.$store.commit("viewMes", { id: detailid, view: true });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messages {
  height: 100%;
  .mes {
    padding: 10px;
    margin: 8px;
    background-color: white;
    cursor: pointer;
    border-radius: 5px;
    box-shadow: 1px 1px 3px black ;
    text-align: start;
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
}
</style>

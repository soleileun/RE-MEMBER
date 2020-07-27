<template>
  <div class="messages">
    쪽지 목록
    <span v-show="viewdetail" @click="nodetail">
      <i class="far fa-times-circle"></i>
    </span>
    <div v-show="!viewdetail">
      <li v-for="mes in messageList" :key="mes.id" @click="sending(mes.fromUser,mes.toUser)" :class="{read:!mes.read}">
        from : {{mes.fromUser}} | to : {{ mes.toUser}} | 시간 : {{mes.time}}
        <br />
        {{mes.content}} (클릭해서 답장)
      </li>
    </div>
    <!-- <messagedetail v-if="viewdetail" :id="detailid" /> -->
    <messagesend :id="detailid" />
  </div>
</template>


<script>
// import messagedetail from "./messagedetail.vue";
import messagesend from "./messagesend.vue";



export default {
  name: "messages",
  components: {
    // messagedetail,
    messagesend,
  },
  data: function () {
    return {
      detailid: 1,
      viewdetail: false,
      mesUser: [],
      // { "mnum": 0, "toUser": "", "fromUser": "", "content": "", "time": , "read": false },}
    };
  },
  computed: {
    messageList: function () {
      return this.$store.state.userstore.messageList;
    },
  },
  methods: {
    sending: function (id1, id2) {
      const storage = window.sessionStorage;
      if (storage.getItem("userid") === id1) {
        this.detailid = id2;
      }
      else if (storage.getItem("userid") === id2){
        this.detailid = id1;
      }
    },
    nodetail: function () {
      this.viewdetail = false;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.read {
  background-color: #ddddff;
}
</style>

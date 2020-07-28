<template>
  <div class="messages">
    쪽지 목록
    <span v-show="mesviewdetail" @click="nodetail">
      <i class="far fa-times-circle"></i>
    </span>
    <div v-show="!mesviewdetail">
      <li v-for="mes in messageList" :key="mes.id" @click="detailview(mes.fromUser,mes.toUser)" :class="{read:!mes.read}">
        상대 : {{abc(mes.toUser,mes.fromUser)}} | 시간 : {{mestime(mes.time)}} / {{mes.mnum}}
        <br />
        {{mes.content}} (클릭해서 답장)
      </li>
    </div>
    <messagedetail v-if="mesviewdetail"  />
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

    //
  },
  methods: {
    mestime: function (x) {
      if (x){
        return x.slice(0, 10);
      }
    },
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
      this.$store.commit("viewMes",{id:detailid,view:true})
    },
    nodetail: function () {
      this.$store.commit("viewMes",{id:"",view:false})
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

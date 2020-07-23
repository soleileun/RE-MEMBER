<template>
  <div class="messages">
    쪽지 목록
    <span v-show="viewdetail" @click="nodetail">
      <i class="far fa-times-circle"></i>
    </span>
    <div v-show="!viewdetail">
      <li v-for="mes in messageList" :key="mes.id" @click="detail(mes.from)">
        from : {{mes.fromUser}} | {{mes.content}}
        <!-- {{mes.message[0].content}} (클릭하면 디테일) -->
      </li>
    </div>
    <messagedetail v-if="viewdetail" :id="detailid" />
  </div>
</template>

<script>
import messagedetail from "./messagedetail.vue";

export default {
  name: "messages",
  components: {
    messagedetail
  },
  data: function() {
    return {
      detailid: 1,
      viewdetail: false,
    };
  },
  computed:{
    messageList: function(){
      return this.$store.state.userstore.messageList
    },
  },
  methods: {
    detail: function(id) {
      this.viewdetail = true;
      this.detailid = id;
    },
    nodetail: function() {
      this.viewdetail = false;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

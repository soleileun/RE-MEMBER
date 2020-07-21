<template>
  <div class="messagelist">
    <span v-show="viewdetail" @click="viewlist">
      <i class="far fa-times-circle"></i>
    </span>
    <div v-show="!viewdetail">
      <li v-for="mes in mess" :key="mes.id" @click="detail(mes.from)">
        from : {{mes.nick}} |
        {{mes.message[0].content}} (클릭하면 디테일)
      </li>
    </div>
    <messageDetail v-if="viewdetail" :id="detailid" />
  </div>
</template>

<script>
import messageDetail from "../bubble/messagedetail.vue";
export default {
  name: "messagelist",
  components: {
    messageDetail
  },
  data: function() {
    return {
      detailid: 1,
      viewdetail: false,
      mess: this.$store.state.messageList
    };
  },
  methods: {
    detail: function(id) {
      this.viewdetail = true;
      this.detailid = id;
    },
    viewlist: function() {
      this.viewdetail = false;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messagelist {
  border: 1px black solid;
  margin-left: 5vw;
  width: 90vw;
  min-height: 70vh;
}
</style>

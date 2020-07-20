<template>
  <div class="messages">
    쪽지 목록
    <span v-show="viewdetail" @click="nodetail">
      <i class="far fa-times-circle"></i>
    </span>
    <div v-show="!viewdetail">
      <li v-for="mes in mess" :key="mes.id" @click="detail(mes.id)">
        {{mes.from}} |
        {{mes.content}} (클릭하면 디테일)
      </li>
    </div>
    <messagedetail v-if="viewdetail" :msg="detailid" />
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
      mess: [
        {
          id: 0,
          from: "사람이름",
          content: "123"
        },
        {
          id: 1,
          from: "사람이름",
          content: "안녕"
        }
      ]
    };
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

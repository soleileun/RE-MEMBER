<template>
  <div class="messages">
    <section class="detaillist">
      <div v-for="mes in msgs" :key="mes.mnum" :class="{read:mes.read}">
        보낸이 : {{mes.fromUser}} | 시간 : {{mestime(mes.time)}}
        <br />
        {{mes.content}}
      </div>
    </section>
    <messagesend />
  </div>
</template>

<script>
import messagesend from "./messagesend.vue";

export default {
  name: "messages",
  components: {
    messagesend,
  },
  data: function () {
    return {};
  },
  computed: {
    msgs: function () {
      let tmp = this.$store.state.userstore.mesDetail;
      if (tmp) {
        tmp.sort(function (a, b) {
          return b.mnum - a.mnum;
        });
      }
      return tmp;
    },
    id: function(){
      return this.$store.state.userstore.mesdetailid;
    }
  },
  mounted: function () {
    this.$store.dispatch("detailMes", { other: this.id });
  },
  methods: {
    mestime: function (x) {
      if (x) {
        return x.slice(0, 10);
      }
    },
    delmes: function (x) {
      this.$store.dispatch("delMes", { mnum: x });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messages {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  .detaillist {
    max-height: 300px;
    overflow-y: scroll;
    div {
      border: 1px black solid;
      &.read {
        background-color: skyblue;
      }
    }
  }
}
</style>

<template>
  <div class="messages">
    <section class="detaillist">
      <div v-for="mes in msgs" :key="mes.mnum" :class="{read:mes.read}">{{mes}}</div>
    </section>
    <messagesend :id="id" />
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
      let tmp = this.$store.state.userstore.mesDetail
      tmp.sort(function(a,b) {
        return  a.mnum -b.mnum 
      });
      return tmp
    },
  },
  props: {
    id: String,
  },
  mounted: function () {
    this.$store.dispatch("detailMes", { other: this.id });
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messages {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  .detaillist {
    div{
      border: 1px black solid;
      &.read{
        background-color: skyblue;
      }
    }
  }
}
</style>

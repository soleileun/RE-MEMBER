<template>
  <div class="notice">
    <notice1 />
  </div>
</template>

<script>
import notice1 from "../../components/notice/notice1.vue";

export default {
  name: "notice",
  components: {
    notice1,
  },
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

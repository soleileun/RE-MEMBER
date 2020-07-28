<template>
  <div class="project">
    <poollist />필요 유무 회의에서 결정
  </div>
</template>

<script>
import poollist from "../../components/pool/poollist.vue";

export default {
  name: "project",
  components: {
    poollist,
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

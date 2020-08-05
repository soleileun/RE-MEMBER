<template>
  <div class="usermessenger">
    메신저
    <messagelist />
  </div>
</template>

<script>
import messagelist from "./messagelist.vue";

export default {
  name: "usermessenger",
  components: {
    messagelist,
  },
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

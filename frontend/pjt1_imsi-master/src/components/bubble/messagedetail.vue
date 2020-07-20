<template>
  <div class="messages">
    쪽지 디테일.. id가 {{msg}} 인 사람
  </div>
</template>

<script>
export default {
  name: "messages",
  props:{
    msg: Number,
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

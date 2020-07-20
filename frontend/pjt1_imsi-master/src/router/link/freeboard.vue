<template>
  <div class="freeboard">
    <!-- 수정 -->
    <!-- <freeboard1 /> -->
    <mainboard />
  </div>
</template>

<script>
// import freeboard1 from "../../components/freeboard/freeboard1.vue";
import mainboard from "../../components/board/mainboard.vue";

export default {
  name: "freeboard",
  components: {
    // freeboard1,
    mainboard
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

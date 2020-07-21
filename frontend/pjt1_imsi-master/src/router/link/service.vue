<template>
  <div class="service">
    사이트 이용 약관
    <terms />
    
    자주 묻는 질문
    <siteQ50 />

    문의 / 신고 / 제안
    <sitereport />
  </div>
</template>

<script>
import terms from "../../components/service/terms.vue";
import siteQ50 from "../../components/service/siteQ50.vue";
import sitereport from "../../components/service/sitereport.vue";

export default {
  name: "service",
  components: {
    terms,
    siteQ50,
    sitereport,
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

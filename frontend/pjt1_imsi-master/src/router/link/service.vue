<template>
  <div class="service">
    <userlist/>
    <mesToAll v-if="userid === 'admin'"/>
    사이트 이용 약관
    <terms />
    
    자주 묻는 질문
    <siteQ50 />

    문의 / 신고 / 제안
    <sitereport />
  </div>
</template>

<script>
import userlist from "../../components/signup/userlist.vue";
import terms from "../../components/service/terms.vue";
import siteQ50 from "../../components/service/siteQ50.vue";
import sitereport from "../../components/service/sitereport.vue";
import mesToAll from "../../components/service/mesToAll.vue";

export default {
  name: "service",
  components: {
    userlist,
    terms,
    siteQ50,
    sitereport,
    mesToAll
  },
  data: function (){
    return {
      userid:window.sessionStorage.getItem('userid')
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

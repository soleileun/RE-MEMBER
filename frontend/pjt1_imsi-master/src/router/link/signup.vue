<template>
  <div class="signup">
    <userlist /> <!-- 백엔드 디버깅용, 서비스전 제거 필수 -->
    <signupTerm />
    <signupform />
    <signupInterest />
  </div>
</template>

<script>
import userlist from "../../components/signup/userlist.vue";
import signupTerm from "../../components/signup/signupterm.vue";
import signupform from "../../components/signup/signupform.vue";
import signupInterest from "../../components/signup/signupInterest.vue";

export default {
  name: "signup",
  components: {
    userlist,
    signupform,
    signupTerm,
    signupInterest
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//   
// }
</style>

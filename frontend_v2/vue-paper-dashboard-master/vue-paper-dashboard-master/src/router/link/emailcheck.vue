<template>
  <div class="emailcheck">
    <h1>회원가입이 완료되었습니다.</h1>
    <div>
      이메일 검증을 위해 확인 메일이 발송되었습니다.
      <br />모든 기능을 이용하기 위해서는 가입하신 아이디 이메일 계정으로 접속하여 이메일 확인을 해주세요
      <br />만약 메일이 오지 않았다면 아래 버튼을 클릭하세요
      <br />
      <button class="btn btn-primary" @click="resendEmail">이메일 재발송</button>
    </div>
    <userlist/>
  </div>
</template>

<script>
import http from "@/http-common.js";
import userlist from "@/components/signup/userlist.vue";
const storage = window.sessionStorage;

export default {
  name: "emailcheck",
  components: {
      userlist
  },
  beforeDestroy:function(){
      if (storage.getItem("jwt-auth-token").length >0 && storage.getItem("idvalid") !== "true") {
      this.$router.push({ name: "emailcheck" });
    }
  },
  methods: {
    resendEmail: function () {
      http
        .get("/api/email/resend/" + storage.getItem("userid"))
        .then((res) => {
          console.log(res);
        })
        .catch((e) => console.log(e));
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .main {
//
// }
</style>

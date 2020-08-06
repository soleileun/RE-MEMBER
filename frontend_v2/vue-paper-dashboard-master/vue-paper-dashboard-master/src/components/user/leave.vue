<template>
  <div class="leave">
    <h4>회원을 탈퇴하면 복구할 수 없습니다.</h4>비밀번호를 다시 입력해 주세요
    <br />
    <input type="password" v-model="pw" @keyup.enter="leavemodal" />
    <button @click="leavemodal">탈퇴하기</button>
  </div>
</template>

<script>
export default {
  name: "leave",
  data: function () {
    return {
      pw: "",
    };
  },
  methods: {
    leavemodal: function () {
      if (this.pw !== "") {
        console.log(this.pw);
        this.$store.dispatch("leave", { pw: this.pw });
      } else {
        alert("비밀번호를 정확하게 입력해주세요");
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

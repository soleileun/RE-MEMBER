<template>
  <div class="findIDPW">
    <div>
      <form action="#">
        <h1>아이디 찾기</h1>
        <input type="radio" name="idradio" value="phone" />전화번호로 찾기
        <input type="radio" name="idradio" value="email" />이메일로 찾기
        이름 : 
      </form>
    </div>
    <hr />
    <div>
      <form action="#">
        <h1>비밀번호 찾기</h1>
        <input type="radio" name="pwradio" value="HTML" />가입시 입력한 전화번호로 전송
        <input type="radio" name="pwradio" value="CSS" />가입시 입력한 전화번호로 전송
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "findIDPW"
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

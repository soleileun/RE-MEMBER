<template>
  <div class="user">
    <h1>내 프로필</h1>
    <h1>(github.io로 직접 작성하는것이라고 함)</h1>
    <iframe src="https://renielcanlas.github.io" width="900vw" height="500px" frameborder="0"></iframe>

    <router-link class="nav-link" :to="{name:'editinfo'}">개인정보 수정</router-link>

    <router-link class="nav-link" :to="{name:'service'}">고객센터</router-link>

    <router-link class="nav-link" :to="{name:'leave'}">회원탈퇴</router-link>
    팔로우 목록
  </div>
</template>

<script>
export default {
  name: "user",
  components: {},
  beforeCreate:function(){
    document.cookie = "SameSite=Lax;"
    console.log(document.cookie)
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.profile {
  border: 1px black solid;
  li {
    display: inline;
  }
}
</style>

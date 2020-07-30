<template>
  <div class="follows">
    팔로우
    <hr />
    <div v-if="followings.length===0">팔로우한 사람이 없습니다.</div>
    <div v-for="user in followings" :key="user.id">{{user.target}}</div>
  </div>
</template>

<script>
export default {
  name: "follows",
  data: function () {
    return {};
  },
  beforeCreate: function () {
    this.$store.dispatch("getFollow");
  },
  computed: {
    followings: function () {
      return this.$store.state.userstore.followings;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

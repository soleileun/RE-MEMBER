<template>
<div class="follows">
  팔로우된 사람
  <li v-for="user in users" :key="user.id">
    {{user.nick}} / last : {{user.lastdate}}
  </li>
</div>
</template>

<script>
export default {
  name: 'follows',
  data:function(){
    return{
      users:this.$store.state.followings
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

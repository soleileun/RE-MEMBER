<template>
  <div class="follows">
    팔로우된 사람
    {{followings}}
    <!-- <li v-for="user in followings" :key="user.id">
    {{user.nick}} / last : {{user.lastdate}}
    </li>-->
  </div>
</template>

<script>
export default {
  name: 'follows',
  data:function(){
    return{
    }
  },
  computed:{
    followings: function(){
      return this.$store.state.userstore.followings
    },
    
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

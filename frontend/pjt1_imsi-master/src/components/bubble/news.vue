<template>
<div class="news">
  알림
  <li v-for="neww in news" :key="neww.id">{{neww.content}}</li>
</div>
</template>

<script>
export default {
  name: 'news',
  computed:{
    news: function(){
      return this.$store.state.userstore.news;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

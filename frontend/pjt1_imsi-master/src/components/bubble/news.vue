<template>
<div class="news">
  알림
  <li v-for="neww in newss" :key="neww.id">{{neww.content}}</li>
</div>
</template>

<script>
export default {
  name: 'news',
  data:function(){
    return{
      newss:[
      {
        id:0,
        content:'123'
      },
      {
        id:1,
        content:'알림2'
      },
      ]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }
</style>

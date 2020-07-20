<template>
<div class="users">
  유저
  <li v-for="user in users" :key="user.id">{{user.name}}</li>
</div>
</template>

<script>
export default {
  name: 'users',
  data:function(){
    return{
      users:[
      {
        id:0,
        name:'123'
      },
      {
        id:1,
        name:'사람'
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

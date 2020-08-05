<template>
<div class="users">
  프로젝트 멤버
  <div class="user" v-for="user in users" :key="user.id">
    <div class="dropdown userdrop">
        <button class="btn dropdown-toggle" type="button" id="usersToggleBtn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          {{user.nickname}} / last :{{user.lastDate.slice(0,10)}}
        </button>
        <div class="dropdown-menu" aria-labelledby="usersToggleBtn">
          <button class="btn btn-dark dropdown-item">팔로우</button>
          <button class="btn btn-dark dropdown-item">쪽지</button>
          <button class="btn btn-dark dropdown-item">차단</button>
        </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'users',
  data:function(){
    return{
      
    }
  },
  computed:{
    users: function(){
      return this.$store.state.userstore.users.filter(x => x.state === true)
      //"id": "", "nickname": "", "name": "", "pw": "", "address1": "", "address2": "", "phone": "", "git": "", 
      // "points": 0, "lastDate": , "state": false, "responsibility": ""
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.user{
  width: 100%;
  .userdrop{
    width: 100%;
    button{
      width: 100%;
    }
  }
}
</style>

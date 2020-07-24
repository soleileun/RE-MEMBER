<template>
  <div class="userlist">
    유저목록(백엔드 디버깅용) : 
    <div v-for="userr in users" :key='userr.id'>{{userr}} </div> 
    <br />
  </div>
</template>

<script>
import http from '@/http-common.js';

export default {
  name: "userlist",
  data: function() {
    return {
      users: "아직 유저목록을 받아오지 못했습니다"
    };
  },
  mounted: function() {
    http.get('/api/userinfo/')
      .then(response => {
        this.users = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

<template>
  <div class="userlist">
    유저목록(백엔드 디버깅용) : {{users}}<br>
    유저목록(프론트엔드 디버깅용) : {{users2}}<br>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "userlist",
  data: function() {
    return {
      users: '아직 유저목록을 받아오지 못했습니다',
      users2: '아직 유저목록을 받아오는 코드를 짜지 않았습니다'
    };
  },
  mounted: function() {
    axios({
      method: "get",       // 유저 리스트를 받아오는 메소드
      url: "/user/list/",  // url
      responseType: "json"
    })
      .then(response => {
        this.users = response.data.users
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

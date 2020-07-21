<template>
  <div class="messageDetail">
    <!-- 쪽지 디테일.. id가 {{id}} 인 사람
    <li v-for="mes in msgs" :key="mes.mnum">
      {{mes.mnum}} : {{mes.content}}
    </li>
    <send /> -->
  </div>
</template>

// 일단 작은 메신저 컴포넌트를 재사용



<script>
// import axios from 'axios'

// export default {
//   name: "messageDetail",
//   props: {
//     id: String
//   },
//   data: function() {
//     return {
//       msgs: []
//     };
//   },
//   mounted: function() {
//     axios({
//       method: "get",
//       url: "쪽지목록 url",
//       data: {
//         'user1': this.$store.state.user.id,
//         'user2': this.id,
//       },
//       responseType: "json"
//     })
//       .then(response => {
//         this.msgs = response
//       })
//       .catch(e => {
//         console.log(e);
//       });
//   }
// };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

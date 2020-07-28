<template>
  <div class="userlist">
    유저목록(백엔드 디버깅용) :
    <div v-for="userr in users" :key="userr.id">
      {{userr}}
      <div @click="fol(userr.id)">팔로</div>
      <div @click="mes(userr.id)">메세지</div>
    </div>
    <br />
  </div>
</template>

<script>
import http from "@/http-common.js";

export default {
  name: "userlist",
  data: function () {
    return {
      users: "아직 유저목록을 받아오지 못했습니다",
    };
  },
  mounted: function () {
    http
      .get("/api/userinfo/")
      .then((response) => {
        this.users = response.data;
      })
      .catch((e) => {
        console.log(e);
      });
  },
  methods: {
    mes: function (id) {
      this.$store.dispatch("sendMes", { toUser: id });
      },
    fol: function (id) {
      this.$store.dispatch("follow", { target: id });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

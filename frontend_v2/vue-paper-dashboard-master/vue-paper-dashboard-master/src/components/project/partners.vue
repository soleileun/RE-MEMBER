<template>
  <div>
    <h4>아직 어디 들어갈지 모르는 팀원관리 ^-^!!</h4>

    <ul>
      <li v-for="pm in pmlist" :key="pm.userId">{{ pm.userId }}</li>
    </ul>
  </div>
</template>

<script>
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;
export default {
  name: "partners",
  component: {},
  data: function () {
    return {};
  },
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
      pid: this.$route.params.pid,
    });
  },
};
</script>

<style>
</style>
<template>
  <div>
    <h4>(먼지모르지만 일단.... 팀원관리)</h4>

    <h3>프로젝트 팀원</h3>

    <ul>
      <li v-for="pm in pmlist" :key="pm.userId">{{ pm.userId }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "partners",
  component: {},
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
      pid: this.project.pid,
    });
  },
};
</script>

<style>
</style>
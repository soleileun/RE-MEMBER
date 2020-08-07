<template>
  <div class="project1">
    <h1>프로젝트 관리 페이지</h1>
    <hr />

    <button @click="movePage" class="btn btn-info btn-round">새로운 프로젝트 생성하기</button>
    <br />
    <br />
    <div class="row">
      <div class="col-4" v-for="project in projects" :key="project.pid">
        <project  :project="project" />
        <!-- @delete-project="deleteComment" -->
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
import project from "../project/project1";
import http from "@/http-common.js";
const storage = window.sessionStorage;
export default {
  name: "myproject",
  components: {
    project,
  },
  data: function () {
    return {
      index: 0,
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),
    };
  },
  computed: {
    projects() {
      console.log("확인2" + this.$store.state.projectstore.projects);
      return this.$store.state.projectstore.projects;
    },
  },
  created() {
    this.getPmemberList(this.$route.params.userId);
    this.getProjectListByPmember(this.$route.params.userId);
  },
  methods: {
    getPmemberList(userId) {
      console.log("과정1");
      this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, { userId });
    },
    movePage() {
      this.$router.push("/project/makeproject");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
div.div {
  display: "inline-block";
}
</style>

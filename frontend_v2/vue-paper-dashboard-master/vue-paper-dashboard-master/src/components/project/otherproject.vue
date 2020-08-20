<template>
  <div class="project1">
    <h1>{{targetId}}님의 프로젝트 페이지</h1>
    <hr />

    <br />
    <div class="row">
      <div class="col-4" v-if="projects.length<1">이 사용자는 아직 참여하는 프로젝트가 없습니다.</div>
      <div class="col-4" v-for="project in projects" :key="project.pid">
        <project :project="project" />
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
      targetId: this.$route.params.userid,
    };
  },

  computed: {
    projects() {
      return this.$store.state.projectstore.projects;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, { userId:this.$route.params.userid });
  },
  mounted() {
    
  },
  methods: {
    // getPmemberList(userId) {

      
    // },
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

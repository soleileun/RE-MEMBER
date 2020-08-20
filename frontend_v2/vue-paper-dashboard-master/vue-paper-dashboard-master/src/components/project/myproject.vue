<template>
  <div class="project1">
    <div v-if="userId.length>0">
      <button @click="movePage" class="btn btn-info btn-round">새로운 프로젝트 생성하기</button>

      <br />
      <br />
      <div class="row">
        <div class="col-12" v-if="projects.length<1">
          <nomyproject />
        </div>
        <project :project="project" v-for="project in projects" :key="project.pid" />
        <!-- @delete-project="deleteComment" -->
      </div>
    </div>
    <div v-else>
      <notlogin />
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
import project from "../project/project1";
import http from "@/http-common.js";
import nomyproject from "@/components/notfound/nomyproject.vue";
import notlogin from "@/components/notfound/notlogin.vue";

const storage = window.sessionStorage;
export default {
  name: "myproject",
  components: {
    project,
    nomyproject,
    notlogin
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
      return this.$store.state.projectstore.projects;
    },
  },
  created() {
    if (storage.getItem("userid") === "") {
      document.querySelector(".login").classList.remove("active");
      document.querySelector(".login").classList.add("active");
    } else {
      this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
        userId: storage.getItem("userid"),
      });
    }
  },
  mounted() {},
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

<template>
  <div>
    <h3>{{targetId}}님의 참여 프로젝트</h3>
    <hr />
    <br />
    <div class="row">
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
  name: "otherproject",
  components: {
    project,
  },
  props: {
    userid: String,
  },
  watch: {
    userid: function () {
      this.init();
    },
  },

  data: function () {
    return {
      index: 0,
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),
      targetId: this.$route.params.userid,
      targetNick: "",
    };
  },
  computed: {
    projects() {
      console.log("확인2" + this.$store.state.projectstore.projects);
      return this.$store.state.projectstore.projects;
    },
  },
  created() {
    console.log(userId);
    console.log("userid" + userid);
    this.getPmemberList(this.$route.params.userId);
    this.getProjectListByPmember(this.$route.params.userId);
  },
  mounted() {
    this.getProjectListByPmember(this.$route.params.userId);
  },

  methods: {
    getPmemberList(userId) {
      console.log("과정1");
      this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, { userId });
    },
    init: function () {
      http
        .get("/api/searchByUserId/userId=" + this.userid)
        .then((response) => {
          if (response.data.length > 0) {
            console.log("뭘 불러와야하지..");
          } else {
            this.content = "아직 프로필이 없습니다";
          }
        })
        .catch((exp) =>
          alert("내 프로필을 로드하는데에 실패하였습니다." + exp)
        );
    },
  },
};
</script>

<style>
</style>
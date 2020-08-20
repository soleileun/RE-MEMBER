<template>
  <div class="project1">
   
    <div class="row">
      <project :project="project" v-for="(project,index) in projects" :key="index" />
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
   props: {
     
    projects: {
      type: Array,
      required: true,
    },
     
  },
  data: function () {
    return {
      index: 0,
    //   userNick: storage.getItem("userNick"),
    //   userId: storage.getItem("userid"),
    };
  },

//   computed: {
//     projects() {
//       return this.$store.state.projectstore.projects;
//     },
//   },
//   created() {
//     if (storage.getItem("userid") === "") {
//       document.querySelector(".login").classList.remove("active");
//       document.querySelector(".login").classList.add("active");
//     } else {
//       this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
//         userId: storage.getItem("userid"),
//       });
//     }
//   },
//   mounted() {},
//   methods: {
//     // getPmemberList(userId) {

//     // },
//     movePage() {
//       this.$router.push("/project/makeproject");
//     },
//   },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
div.div {
  display: "inline-block";
}
</style>

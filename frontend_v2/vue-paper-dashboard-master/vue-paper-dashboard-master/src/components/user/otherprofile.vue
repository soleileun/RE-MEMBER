<template>
  <div class="profile text-center">
    <h3>{{board.btitle}}님의 소개</h3>
    <div class="ql-editor profileContainer" v-html="content" v-if="!profileEdit"></div>
    <vue-editor v-show="profileEdit" v-model="content" :editorToolbar="customToolbar"></vue-editor>
    <!-- 에디터를 v-show로 숨겨두지 않으면 일부 꾸밈 코드가 안먹힘 -->
  </div>
</template>

<script>
import { VueEditor } from "vue2-editor";
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;

export default {
  name: "profile",
  components: {
    VueEditor,
  },
  props: {
    userid: String,
  },
  watch: {
    userid: function () {
      this.init();
    },
  },
  mounted() {
    this.init();
  },
  data() {
    return {
      content: "불러오는 중입니다.",
      profileEdit: false,
      customToolbar: [],
      bno: "0",
      myprofile: true,
      board: {
        btitle: "profile",
        bview: "",
        bfile: "",
        bstate: "",
        makeDay: "",
        changeDay: "",
        makeId: "",
        changeId: "",
      },
    };
  },
  methods: {
    init: function () {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
        },
      };
      http
        .get(
          "/api/board/typesearch/writer=" + this.userid + "&bstate=profile",
          config
        )
        .then((response) => {
          if (response.data.length > 0) {
            this.content = response.data[0].bcontent;
            this.bno = response.data[0].bno;
            this.board.bview = response.data[0].bview;
            this.board.btitle = response.data[0].btitle;

            this.$emit("btitle", response.data[0].btitle);
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.profile {
  font: 100;
  .profileContainer {
    min-height: 400px;
    border: 1px gray solid;
    padding: 20px;
    margin-bottom: 50px;
    border-radius: 10px;
  }
}
</style>

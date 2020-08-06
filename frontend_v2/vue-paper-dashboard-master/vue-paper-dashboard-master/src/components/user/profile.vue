<template>
  <div class="profile">
    <h3>내 소개</h3>
    <div class="ql-editor profileContainer" v-html="content" v-if="!profileEdit"></div>
    <vue-editor v-show="profileEdit" v-model="content" :editorToolbar="customToolbar"></vue-editor>
    <!-- 에디터를 v-show로 숨겨두지 않으면 일부 꾸밈 코드가 안먹힘 -->
    <div v-if="myprofile">
      <button class="btn btn-success" v-if="!profileEdit" @click="edit">프로필 수정하기</button>
      <button class="btn btn-success" v-else @click="complete">수정 완료</button>
    </div>
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
  mounted: function () {
    if (storage.getItem("jwt-auth-token").length>10){

      http
      .get(
        "/api/board/typesearch/writer=" +
          storage.getItem("userid") +
          "&bstate=profile"
      )
      .then((response) => {
        if (response.data.length > 0) {
          this.content = response.data[0].bcontent;
          this.bno = response.data[0].bno;
          this.board.bview = response.data[0].bview;
        } else {
          if (storage.getItem("userid").length > 0) {
            this.$store.dispatch(Constant.ADD_BOARD, {
              //bno : auto increase
              // bwriter : this.board.bwriter, 임시로 ssafy foreign key때문
              bwriter: storage.getItem("userid"),
              btitle: this.board.btitle,
              bcontent: "프로필 초기",
              bview: this.board.bview,
              bfile: this.board.bfile,
              bstate: "profile",
              makeDay: new Date(),
              // changeDay : this.board.changeday,
              makeId: this.board.makeid,
              // changeId : this.board.changeid
            });
            setTimeout(() => {
              this.$router.go();
            }, 500);
          }
        }
      })
      .catch((exp) => alert("내 프로필을 로드하는데에 실패하였습니다." + exp));
    }else{
      this.$router.push('/')
      alert('로그인이 필요합니다.')
    }
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
    edit: function () {
      console.log(this.content);
      this.profileEdit = true;
    },
    complete: function () {
      this.profileEdit = false;
      http
        .put("/api/board/change/" + this.bno, {
          bno: this.bno,
          bwriter: storage.getItem("userid"),
          btitle: "profile",
          bcontent: this.content,
          bstate: "profile",
          changeDay: new Date(),
          makeId: storage.getItem("userid"),
          changeId: storage.getItem("userid"), //세션 id
        })
        .then((response) => {
          console.log("수정하였습니다." + response.data);
        })
        .catch((exp) => alert("수정 처리에 실패하였습니다." + exp));
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.profile {
  .profileContainer {
    min-height: 400px;
    border: 1px gray solid;
    padding: 20px;
    margin-bottom: 50px;
    border-radius: 10px;
  }
}
</style>

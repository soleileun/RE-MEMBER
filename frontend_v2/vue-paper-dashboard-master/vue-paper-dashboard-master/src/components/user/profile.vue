<template>
  <div class="profile text-center">
    <h3>내 소개</h3>
    <div id="editor" class="container ql-snow" v-if="!profileEdit">
      <div class="ql-editor profileContainer" v-html="content"></div>
    </div>
    <vue-editor id="editor" v-if="profileEdit" v-model="content" useCustomImageHandler @imageAdded="handleImageAdded"></vue-editor>
    <!-- 에디터를 v-show로 숨겨두지 않으면 일부 꾸밈 코드가 안먹힘 -->
    <div v-if="myprofile">
      <button class="btn btn-round btn-success" v-if="!profileEdit" @click="edit">프로필 수정하기</button>
      <button class="btn btn-round btn-info" v-else @click="complete">수정 완료</button>
    </div>
  </div>
</template>

<script>
import { VueEditor } from "vue2-editor";

import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;

import axios from "axios";
export default {
  name: "profile",
  components: {
    VueEditor,
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
      files: [],
    };
  },
  mounted: function () {
    if (storage.getItem("jwt-auth-token").length > 10) {
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
            this.$emit("bno", response.data[0].bno);
            this.board.bview = response.data[0].bview;
          } else {
            alert("내 프로필을 로드하는데에 실패하였습니다." + exp);
          }
        })
        .catch((exp) =>
          alert("내 프로필을 로드하는데에 실패하였습니다." + exp)
        );
    } else {
      this.$router.push("/");
      alert("로그인이 필요합니다.");
    }
  },
  methods: {
    handleImageAdded(file, Editor, cursorLocation) {
      if (file) {
        const fileName = new Date().getTime() - 1597262625477;
        const file2 = new File(
          [file],
          `${fileName}.${file.name.split(".")[1]}`,
          {
            type: file.type,
          }
        );
        this.$store.dispatch("upFiledirect", {
          file: file2,
          bno: this.bno,
        });
        let url = this.$store.state.filestore.fileUrl + file2.name;
        console.log(url);
        setTimeout(() => {
          Editor.insertEmbed(cursorLocation, "image", url);
        }, 500);
      }
    },
    edit: function () {
      this.profileEdit = true;
    },
    complete: function () {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
        },
      };
      this.profileEdit = false;
      http
        .put(
          "/api/board/change/" + this.bno,
          {
            bno: this.bno,
            bwriter: storage.getItem("userid"),
            btitle: "profile",
            bcontent: this.content,
            bstate: "profile",
            changeDay: new Date(),
            makeId: storage.getItem("userid"),
            changeId: storage.getItem("userid"), //세션 id
          },
          config
        )
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
  font: 100;
  .ql-editor.profileContainer {
    min-height: 400px;
    border: 1px gray solid;
    padding: 20px;
    margin-bottom: 50px;
    border-radius: 10px;
  }
}
#editor > div > ol > li > img {
  width: 100px;
}
</style>

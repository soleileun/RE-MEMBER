<template>
  <div class="freeboard1">
    <div class="card">
      <div class="card-header">
        <h1>글쓰기</h1>
      </div>
      <hr />
      <div class="card-body">
        <div style="display:block;">
          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left">글머리</div>

              <div class="col-10 write-right">
                <p class="card-title" v-if="this.type==='free'">자유</p>
                <p class="card-title" v-if="this.type==='notice'">공지사항</p>
                <p class="card-title" v-if="this.type==='qa'">질문</p>
                <!-- <select
                  name="option"
                  id="statetag"
                  style="width:20%; padding-left:2px;"
                  v-model="board.bstate"
                >
                  <option value="free">자유게시판</option>
                  <option value="qa">질문게시판</option>
                  <option value="notice" v-show="this.board.bwriter === 'admin'">공지게시판</option>
                </select> -->
              </div> 
            </div>
          </div>

          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left">제목</div>

              <div class="col-10 write-right">
                <input v-model="board.btitle" type="text" />
              </div>
            </div>
          </div>

          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left">작성자</div>
              <div class="col-10 write-right">{{this.board.bwriter}}</div>
            </div>
          </div>

          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left">이메일</div>

              <div class="col-10 write-right">{{this.board.makeId}}</div>
            </div>
          </div>

          <div class="container-fluid">
            <div class="row" style="min-height:600px;">
              <div class="col-2 write-left">내용
<br><br>  
              <div v-if="filein" style="color:red">
                사진은 글 작성 완료 후 표시됩니다.
              </div>
              </div>
              <div class="col-10 write-right">
                <vue-editor v-model="board.bcontent" style="height:80%;" useCustomImageHandler @imageAdded="handleImageAdded"></vue-editor>
                <!--<textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>-->
              </div>
            </div>
          </div>
        </div>

        <div class="container">
          <div class="row">
            <div class="col text-center btndiv">
              <button class="btn btn-outline-primary" @click="addFree">등록하기</button>
              <button class="btn btn-outline-primary" @click="check">목록으로</button>
            </div>
          </div>
        </div>
        <!--
    <router-link to="/mainboard/freeboard" tag="button">목록으로</router-link>
        -->
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
import { VueEditor } from "vue2-editor";
import axios from "axios";

const storage = window.sessionStorage;
export default {
  name: "writefree",
  components: {
    VueEditor,
  },
  computed: {
    type() {
      return this.$route.params.type;
    },
    loginId() {
      return storage.getItem("userid");
    },
  },
  created() {
    this.$store.commit("delTempFiles");
  },
  data: function () {
    return {
      board: {
        //bno : '',// 자동 증가라 필요가 없음
        bwriter: this.loginId, //writer를 닉네임으로 할건지 메일로 할건지에 대해서
        btitle: "", // 됐음
        bcontent: "", //됐음
        bview: 0, // 초기 0임
        bfile: "",
        bstate: "", // 됐음
        makeDay: "", // 현재 자동으로 들어가게 할거임
        changeDay: "", //null
        makeId: this.loginId,
        changeId: this.loginId,
      },
      filein:false
    };
  },
  methods: {
    handleImageAdded(file, Editor, cursorLocation) {
      if (file) {
        this.filein = true
        const fileName = new Date().getTime() - 1597262625477;
        const file2 = new File(
          [file],
          `${fileName}.${file.name.split(".")[1]}`,
          {
            type: file.type,
          }
        );
        this.$store.commit('upBoardFiles',{file:file2})
        let url = this.$store.state.filestore.fileUrl + file2.name;
        Editor.insertEmbed(cursorLocation, "image", url);
      }
    },
    addFree() {
      if (this.board.btitle.trim() === "") {
        alert("제목을 입력해주세요.");
      }
      // else if (this.board.bcontent.trim() === "") {
      //   alert('내용을 입력해주세요.');
      // }
      else {
        this.$store.dispatch(Constant.ADD_BOARD, {
          //bno : auto increase
          // bwriter : this.board.bwriter, 임시로 ssafy foreign key때문
          bwriter: this.loginId,
          btitle: this.board.btitle,
          bcontent: this.board.bcontent,
          bview: this.board.bview,
          bfile: this.board.bfile,
          bstate: this.type,
          makeDay: new Date(),
          // changeDay : this.board.changeday,
          makeId: this.board.makeid,
          // changeId : this.board.changeid
        });
        this.$router.push("/freeboard/type/" + this.type + '/'+ this.$route.params.currentPage); // mainboard 뺐음.
      }
      this.clear();
    },
    clear() {
      this.board = {}; //this.board = {} 이렇게 하면 안나옴
    },
    check() {
      //this.btitle = this.inputtitle;
      let addr = "/freeboard/type/" + this.type + '/'+ this.$route.params.currentPage;
      this.$router.push(addr);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.write-left {
  //float:left;
  background: rgb(233, 233, 233);
  //width:20%;
  border: solid 0.5px rgb(128, 127, 127);
  padding: 15px;
}
.write-right {
  //float:left;
  background: rgb(255, 255, 255);
  //width:80%;
  text-align: left;
  border: solid 0.5px rgb(128, 127, 127);
  padding: 15px;
}
input {
  width: 70%;
  margin-left: 2px;
}
.input-div {
  padding-top: 12px;
  padding-bottom: 12px;
}
.btndiv {
  padding: 10px;
}
.btndiv > button {
  margin: 10px;
}
</style>

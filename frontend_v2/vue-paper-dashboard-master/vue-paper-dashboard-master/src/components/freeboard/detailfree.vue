<template>
  <div class="detailfree">
    <div class="card">
      <div class="card-header"></div>


      <div class="card-body">
        <div class="container-fluid">
          <div class="row">
            <div class="col-12" style="text-align:center">
              <h1>{{board.btitle}}</h1>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-1 pics">사진</div>
          <div class="col-3">
            <div class="col-12 name">{{board.bwriter}}</div>
            <div class="col-12 date">{{board.makeDay.slice(0,16)}}</div>
          </div>
        </div>
        <hr />

        <div class="col-12 contents ql-editor" v-html="board.bcontent">
          <vue-editor v-show="false" style="height:80%;"></vue-editor>
        </div>

        <div class="container">
          <div class="row">
            <div class="col text-center btndiv">
              <button
                v-if="board.bwriter === loginId || loginId === 'admin' "
                class="btn btn-outline-warning"
                @click="change"
              >수정하기</button>
              <button
                v-if="board.bwriter === loginId || loginId === 'admin' "
                class="btn btn-outline-danger"
                @click="deleteFree"
              >삭제하기</button>
              <button class="btn btn-outline-primary" @click="back">목록으로</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
    <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
    <!-- 자유게시판일 경우 댓글 및 코멘트 -->
    <!--
    <router-link :to="'/freeboard/modifyfree/' + board.bno" tag="button">수정</router-link>
    <button @click="deleteFree">삭제</button>
    <router-link to="/freeboard" tag="button">목록으로</router-link>
    -->
    <br />
    <br />

    <div class="card" v-if="board.bstate == 'free'">
      <div class="card-body">
        <div class="row" v-for="comment in comments" :key="comment.cno">
          <div class="col-3">{{comment.cwriter}}</div>
          <div class="col-6">{{comment.ccontent}}</div>
          <div class="col-3">
            {{comment.makeDay}}
            <span class="ti-trash"></span>
            <!-- @click 해서 삭제 넣어주시면 됩니다. -->
          </div>
        </div>

        <div class="row">
          <div class="col-1">댓글</div>
          <div class="col-9">
            <input v-model="comment2.ccontent" type="text" style="width:100%" />
          </div>
          <div class="col-2">
            <button @click="addComment">입력</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 자유게시판 경우 끝 -->

    <!-- 질문게시판일 경우 -->
    <div v-if="board.bstate == 'qa'">
      <h3 style=" margin:20px;">답변 내역</h3>

      <div class="card" v-for="comment in comments" :key="comment.cno">
        <div class="card-body">
          <div class="row">
            <div class="col-1 pics">사진</div>
            <div class="col-3">
              <div class="col-12 name">{{comment.cwriter}}</div>
              <div class="col-12 date">{{comment.makeDay.slice(0,16)}}</div>
            </div>
          </div>
          <hr />

          <div class="col-12 ql-editor" style="min-height:200px;" v-html="comment.ccontent">
            <vue-editor v-show="false" style="height:80%;"></vue-editor>
          </div>
        </div>
      </div>
    </div>

    <div class="card" v-if="board.bstate == 'qa'">
      <div class="card-body">
        <div class="container-fluid">
          <div class="row" style="min-height:300px;">
            <div class="col-12">
              <vue-editor v-model="comment2.ccontent" style="height:60%;"></vue-editor>
              <!--<textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>-->
            </div>
          </div>

          <div class="container">
            <div class="row">
              <div class="col text-center btndiv">
                <button class="btn btn-outline-primary" @click="addComment">작성하기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 질문게시판의 경우 끝-->

    <!-- 삭제 안 하고 주석처리 해두겠습니다. -->
    <!-- comment 싱글파일컴포넌트 제작시 테이블 빼고 컴포로 대체 -->
    <!--
    <comment v-for="comment in comments" 
    :key="comment.cno"
    :comment="comment"
    @delete-comment="deleteComment"
    />
    -->
  </div>
</template>

<script>
import Constant from "../../Constant";
import comment from "../comment/comment";
import { VueEditor } from "vue2-editor";
import http from "@/http-common.js";
const storage = window.sessionStorage;

export default {
  components: {
    comment,
    VueEditor,
  },
  name: "detailfree",
  data() {
    return {
      comment2: {
        cno: "",
        cwriter: "",
        ccontent: "",
        bno: "",
        isSelected: "",
        makeDay: "",
        changeDay: "",
        makeId: "",
        changeId: "",
      },
    };
  },
  created() {
    this.getBoard(this.$route.params.bno);
  },
  computed: {
    loginId() {
      //this.$forceUpdate();
      return this.$store.state.userstore.userid;
    },
    board: {
      get() {
        // 화살표함수 사용하면 안됨. this : undefined 로 나옴.
        // console.log(this.$store.state.boardstore.board);
        return this.$store.state.boardstore.board;
      },
      set(newBoard) {
        this.board.bno = newBoard.bno;
        this.board.bwriter = newBoard.bwriter;
        this.board.btitle = newBoard.btitle;
        this.board.bcontent = newBoard.bcontent;
        this.board.bview = newBoard.bview;
        this.board.bfile = newBoard.bfile;
        this.board.bstate = newBoard.bstate;
        this.board.makeDay = newBoard.makeDay;
        this.board.changeDay = newBoard.changeDay;
        this.board.makeId = newBoard.makeId;
        this.board.changeId = newBoard.changeId;
      },
    },

    comments() {
      // console.log('도착'+this.$store.state.commentstore.comments);
      return this.$store.state.commentstore.comments;
    },
  },
  methods: {
    getBoard(bno) {
      this.$store.dispatch(Constant.GET_BOARD, { bno });
      this.$store.dispatch(Constant.GET_COMMENTLIST, { bno });
    },

    deleteFree() {
      var con_test = confirm("삭제하시겠습니까?");
      if (con_test == true) {
        this.$store.dispatch(Constant.REMOVE_BOARD, {
          bno: this.board.bno,
          bstate: this.board.bstate,
        });
        // console.log('삭제요청완료.' + this.board.bno);
        this.$router.push("/freeboard");
      } else if (con_test == false) {
        console.log("");
      }
    },

    deleteComment(cno) {
      this.$emit("delete-comment", cno);
    },

    change() {
      let addr = "/freeboard/modifyfree/" + this.board.bno;
      this.$router.push(addr);
    },
    back() {
      let addr = "/freeboard";
      this.$router.push(addr);
    },

    addComment() {
      if(this.loginId == '') {
        document.querySelector(".login").classList.remove("active");
        document.querySelector(".login").classList.add("active");
        alert("로그인이 필요한 서비스입니다.");
      }
      else if (this.loginId != '' && this.comment2.ccontent.trim() != "") {
        this.$store.dispatch(Constant.ADD_COMMENT, {
          // cno :this.comment2.cno,
          // cwriter:this.comment2.cwriter,
          cwriter: this.loginId,
          ccontent: this.comment2.ccontent,
          bno: this.board.bno,
          isSelected: this.comment2.isSelected,
          makeDay: new Date(),
          // changeDay:this.comment2.changeDay,
          // makeId:this.comment2.makeId,
          makeId: storage.getItem("userid"),
          // changeId:this.comment2.changeId
        });
        alert("댓글 추가가 완료되었습니다.");
        this.comment2.ccontent = "";
      } else {
        console.log("공백입력.");
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
table,
td,
tr,
th {
  width: 1000px;
  margin: 0 auto;
  text-align: center;
  border: 1px solid black;
}

.name {
  font-size: 18px;
  margin: 2px;
}
.date {
  font-size: 11px;
  margin: 2px;
}
.pics {
  margin: 4px;
}
.contents {
  min-height: 400px;
}
</style>

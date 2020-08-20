<template>
  <div class="recruitdetail">
    <div class="card">
      <div class="card-body container">
        <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
        <div class="row">
          <div class="col-11 title">{{recruit.title}}</div>
          <div class="col-6">주소 : {{project.location}}</div>
          <div class="col-2 pid">프로젝트번호 : {{recruit.pid}}</div>
          <div class="col-4 day">등록날짜 : {{recTime(recruit.makeDay)}}</div>
          <hr />
        </div>
        <div class="row">
          <div style="min-height:350px;" class="col-12 ql-editor" v-html="recruit.contents"></div>
          <vue-editor v-show="edit" v-model="con" :editorToolbar="customToolbar"></vue-editor>
          <hr />
          <div class="col-4">
          <router-link :to="'/project/partners/'+recruit.pid">
            <button class="btn btn-success">팀원보기</button>
          </router-link>
          &nbsp;&nbsp;&nbsp;
          <router-link to="/recruit/recruit1">
            <button class="btn btn-info">목록으로</button>
          </router-link>
          </div>
            <div v-if="recruit.makeId === id || id === 'admin'" class="col-8 text-right btndiv">
            <button class="btn btn-info"  style="margin-left:10px;margin-right:10px;" @click="openModify">수정</button>
            <button class="btn btn-info" style="margin-left:10px;margin-right:10px;" @click="deleteRecruit">삭제</button>
            </div>
        </div>
        
    <div class="modal" :id="`myModal${recruit.pid}`">
      <div class="modal-content">
        <span class="close">&times;</span>
        <div class="row">
          <div class="col-25">
            <label for="fname">아이디</label>
          </div>
          <div class="col-75">
            <label for="fname">{{loginId}}</label>
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="country">프로젝트</label>
          </div>
          <div class="col-75">
            <select id="myproject" name="myproject" disabled>
              <option selected :value="recruit.pid">{{recruit.pjtName}}</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="lname">게시글 제목</label>
          </div>
          <div class="col-75">
            <input type="text" v-model="recruit.title" placeholder="제목을 입력하세요" />
          </div>
        </div>

        <div class="row">
          <div class="col-25">
            <label for="subject">내용</label>
          </div>
          <div class="col-75">
            <vue-editor :id="`editor${recruit.pid}`" class="viewEditor" v-model="recruit.contents" style="height:80%;"></vue-editor>
          </div>
        </div>

        <div class="row">
          <button class="btn btn-info" @click="modifyRecruit">수정</button>
        </div>
      </div>
    </div>
      </div>
    </div>
    <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
    <!-- <router-link :to="'/freeboard/modifyfree/' + recruit.bno" tag="button">수정</router-link> -->
    <!-- <button @click="deleteRecruit">삭제</button> -->
    <!-- * 댓글 목록
    <button @click="addComment">댓글달기</button><br>
    <textarea name="" id="" cols="30" rows="10" v-model="comment2.ccontent" placeholder="내용을 입력하세요"></textarea>
    comment 싱글파일컴포넌트 제작시 테이블 빼고 컴포로 대체
    <comment v-for="comment in comments" 
    :key="comment.cno"
    :comment="comment"
    @delete-comment="deleteComment"
    />-->
    
  </div>
</template>

<script>
import { VueEditor } from "vue2-editor";
import Constant from "../../Constant";
const storage = window.sessionStorage;
// import comment from '../comment/comment';

export default {
  name: "recruitdetail",
  components: {
    VueEditor,
  },
  data() {
    return {
      edit: false,
      customToolbar: [],
      id: storage.getItem("userid"),
      con: "",
       customToolbar: [
        ["bold", "italic", "underline"],
        [{ list: "ordered" }, { list: "bullet" }],
        ["code-block"],
      ],
      loginId: storage.getItem('userid')
    };
  },
  created() {
    // console.log(this.$route.params.bno);
    this.$store.dispatch(Constant.GET_RECRUIT, {
      rnum: this.$route.params.rnum,
    });
    //딜레이 해결할 것
    // console.log("ㅇㄴㄹ"+recruit.pid);

    this.$store.dispatch(Constant.GET_PROJECT, { pid: this.$route.params.pid });
  },
  computed: {
    recruit() {
      // console.log("어어어"+this.$store.state.recruitstore.recruit.rnum);
      return this.$store.state.recruitstore.recruit;
    },
    project() {
      return this.$store.state.projectstore.project;
    },
  },
  methods: {
    recTime(str) {
      if (str) {
        const year = str.slice(0, 4),
          month = str.slice(5, 7),
          day = str.slice(8, 10),
          si = str.slice(11, 13),
          boon = str.slice(14, 16),
          cho = str.slice(17, 19);
        return `${year}/${month}/${day}`;
      } else {
        return "";
      }
    },
    
    deleteRecruit() {
      // console.log("삭제 rnum : " + this.recruit.rnum);
      this.$store.dispatch(Constant.REMOVE_RECRUIT, {
        rnum: this.recruit.rnum,
      });
      
      var url ='/recruit';
      this.$router.push(url);
    },




    openModify() {
      // Get the modal
      let mid = "myModal" + this.recruit.pid;
      var modal = document.getElementById(mid);

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

      // When the user clicks on the button, open the modal
      modal.style.display = "block";

      // When the user clicks on <span> (x), close the modal
      span.onclick = function () {
        modal.style.display = "none";
      };

      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    },
    modifyRecruit() {
      console.log("contents : " + this.recruit.contents);
      this.$store.dispatch(Constant.MODIFY_RECRUIT, {
        rnum: this.recruit.rnum,
        // pid: this.recruit.rnum,
        title: this.recruit.title,
        contents: this.recruit.contents,
        // endDate: this.recruit.endDate,
        // makeDay: this.recruit.makeDay,
        // changeDay: this.recruit.changeDay,
        // makeId: this.loginId,
        changeId: this.loginId,
      });

      let mid = "myModal" + this.recruit.pid;
      var modal = document.getElementById(mid);
      modal.style.display = "none";
    },
    // deleteRecruit(){
    //   var con_test = confirm("삭제하시겠습니까?");
    //   if(con_test == true){
    //     this.$store.dispatch(Constant.REMOVE_BOARD, { bno : this.board.bno, bstate : this.board.bstate});
    //     // console.log('삭제요청완료.' + this.board.bno);
    //     this.$router.push('/mainboard/freeboard');
    //   }
    //   else if(con_test == false){
    //     console.log('');
    //   }
    // },
    // deleteComment(cno){
    //   this.$emit('delete-comment',cno);
    // },
    // addComment(){
    //   if(this.comment2.ccontent.trim() != ''){
    //       this.$store.dispatch(Constant.ADD_COMMENT,{
    //         // cno :this.comment2.cno,
    //         // cwriter:this.comment2.cwriter,
    //         cwriter:'ssafy',
    //         ccontent:this.comment2.ccontent,
    //         bno:this.board.bno,
    //         isSelected:this.comment2.isSelected,
    //         makeDay:new Date(),
    //         // changeDay:this.comment2.changeDay,
    //         // makeId:this.comment2.makeId,
    //         makeId:'ssafy',
    //         // changeId:this.comment2.changeId
    //       });
    //       alert('댓글 추가가 완료되었습니다.');
    //       this.comment2.ccontent='';
    //   }else{
    //       console.log('공백입력.');
    //   }
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.card {
  padding: 10px;
}
hr {
  color: gray;
  width: 100%;
}
div.day {
  text-align: right;
}
div.title {
  font-size: 1.5rem;
  font-weight: 800;
}
div.pid {
  border-left: 1px solid #cdcdcd;
  border-right: 1px solid #cdcdcd;
}
table,
td,
tr,
th {
  width: 1000px;
  margin: 0 auto;
  text-align: center;
  border: 1px solid black;
}

//모달 인풋 css
.modal {
  box-sizing: border-box;
}
.modal-content {
  width: 80%;
  padding-left: 50px;
}
.modal input[type="text"],
.modal select {
  width: 80%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}
.quillWrapper.viewEditor {
  width: 80%;
  height: 500px;
  max-height: 45vh;
  border-radius: 4px;
  resize: vertical;
  margin-bottom: 100px;
}

.modal label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

.modal input[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

.modal input[type="submit"]:hover {
  background-color: #45a049;
}

// .container2 {
//   border-radius: 5px;
//   background-color: #f2f2f2;
//   padding: 20px;
// }

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 50%; /* Could be more or less, depending on screen size */
}
/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>

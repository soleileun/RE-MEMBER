<template>
  <div class="freeboard1">
    <h1>자유게시판 글 조회 샘플</h1>
    
    <hr>
    <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
    아이디 : {{board.bwriter}}<br> 
    제목 : {{board.btitle}}<br>
    등록날짜 : {{board.makeday}}<br>
    내용<br>
    {{board.bcontent}}<br>
    <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
    <!-- <button @click="modifyFree">수정</button>
    <button @click="deleteFree">삭제</button> -->
    <router-link to="/freeboard" tag="button">목록으로</router-link>
    <br>
    <br>
    * 댓글 목록
    <!-- comment 싱글파일컴포넌트 제작시 테이블 빼고 컴포로 대체 -->
    <table>
    <thead>
      <tr>
        <th>no</th>
        <th>id</th>
        <th>contents</th>
        <th>register_day</th>
        <th> - </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="comment in comments" :key="comment.cno">
        <td>{{comment.cno}}</td>
        <!-- bno 쿼리스트링 달아서 분기 -->
        <td>{{comment.cwriter}}</td>
        <td>{{comment.ccontent}}</td>
        <td>{{comment.makeday}}</td>
        <!-- 작성자 또는 관리자일 때만 활성화로 설정 변경할 것 -->
        <td><a href="#">삭제</a></td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<script>
import Constant from '../../Constant';

export default {
  name: "detailfree",
  created() {
    this.getBoard(this.$route.params.bno);
  },
  computed: {
        board:{
          get() {
            // 화살표함수 사용하면 안됨. this : undefined 로 나옴.
            return this.$store.state.board;
          },
          set(newBoard) {
            this.board.bno = newBoard.bno;
            this.board.bwriter = newBoard.bwriter;
            this.board.btitle = newBoard.btitle;
            this.board.bcontent = newBoard.bcontent;
            this.board.bview = newBoard.bview;
            this.board.bfile = newBoard.bfile;
            this.board.bstate = newBoard.bstate;
            this.board.makeday = newBoard.makeday;
            this.board.changeday = newBoard.changeday;
            this.board.makeid = newBoard.makeid;
            this.board.changeid = newBoard.changeid;
          }
      }
  },
  methods: {
    getBoard(bno) {
      this.$store.dispatch(Constant.GET_BOARD, { bno });
    },
    // modifyBoard() {
    //   if (this.todo.qna_content != "") {
    //     console.log("할일 수정:: ");
    //     console.log(this.todo);
    //     this.$store.dispatch(Constant.MODIFY_TODO, { todo: this.todo });
    //     this.$router.push("/");
    //   } else {
    //     console.log("공백입력.");
    //   }
    //   this.clear();
    // },
    // clear() {
    //   this.todo = {}; 
    // }
  },
  data: function() {
    return {
      comments : [
        {
          cno:1,
          cwriter:'ydg',
          ccontent:'i agree',
          bno:1,
          isSelected:1,
          makeday:"2020-04-11",
          changeday:"2020-04-13",
          makeid:"makeid",
          changeid:"changeid"
        },
         {
          cno:2,
          cwriter:'hsh',
          ccontent:'i dont agree',
          bno:1,
          isSelected:0,
          makeday:"2020-04-11",
          changeday:"2020-04-13",
          makeid:"makeid",
          changeid:"changeid"
        }
      ]
    };
  },
  // methods: {
  //   modifyFree(){
  //     alert('db 연동하여 글 수정하는 코드로 대체');
  //   },
  //   deleteFree(){
  //     alert('db 연동하여 글 삭제하는 코드로 대체');
  //   },
  // }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
table, td, tr, th {
  width : 1000px;
  margin : 0 auto;
  text-align: center;
  border: 1px solid black;
}
</style>

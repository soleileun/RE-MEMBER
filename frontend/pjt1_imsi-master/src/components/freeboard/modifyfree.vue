<template>
    <div>

        <h1>자유게시판 글 수정 샘플</h1>
        
        <hr>
        <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
        아이디 : {{board.bwriter}}<br> 
        제목 : {{board.btitle}}<br>
        등록날짜 : {{board.makeDay}}<br>
        내용<br>
        {{board.bcontent}}<br>
        <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
        <router-link :to="'/freeboard/modifyfree/' + free.bno">수정</router-link>
        <button @click="deleteFree">삭제</button>
        <router-link to="/freeboard" tag="button">목록으로</router-link>
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
          }
      }
  },
  methods: {
    getBoard(bno) {
      this.$store.dispatch(Constant.GET_BOARD, { bno });
    },
    // modifyFree() {
    //   if (this.board.qna_content != "") {
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
    // },
    deleteFree(){
      var con_test = confirm("삭제하시겠습니까?");
      if(con_test == true){
        this.$store.dispatch(Constant.REMOVE_BOARD, { bno : this.board.bno});
        console.log('삭제요청완료.' + this.board.bno);
        this.$router.push('/freeboard');
      }
      else if(con_test == false){
        console.log('');
      }
    }
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
  
};
</script>

<style>

</style>
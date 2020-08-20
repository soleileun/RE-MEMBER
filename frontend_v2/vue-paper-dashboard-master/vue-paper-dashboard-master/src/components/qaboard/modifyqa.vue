<template>
    <div>

        <h1>질문게시판 글 수정 샘플</h1>
        
        <hr>
        아이디 : store의 state에서 가져오는 세션 아이디<br>
        제목 : <input type="text" v-model="board.btitle" placeholder="제목을 입력하세요" /><br>
        내용<br>
        <textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>
        
        <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
        <button @click="modifyFree">수정</button>
        <router-link :to="'/qaboard/detailqa/' + board.bno" tag="button">취소</router-link>
    </div>
</template>

<script>
    import Constant from '../../Constant';

    export default {
    name: "modifyqa",
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
        modifyFree() {
          if (this.board.bcontent != "") {
            
            this.$store.dispatch(Constant.MODIFY_BOARD, { board: this.board });
            alert('수정이 완료되었습니다.');
            let addr = "/qaboard/detailqa/"+ this.board.bno;
            this.$router.push(addr);
          } else {
          }
          // this.clear();
        },
        // clear() {
        //   this.board = {}; 
        // },
        
  },
  
  
};
</script>

<style>

</style>
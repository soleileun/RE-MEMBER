<template>
  <div class="recruitdetail">
    <h1>구인글 상세조회 샘플</h1>
    
    <hr>
    
    <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
    프로젝트번호 : {{recruit.pid}}<br> 
    제목 : {{recruit.title}}<br>
    등록날짜 : {{recruit.makeDay}}<br>
    내용<br>
    {{recruit.contents}}<br>
    <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
    <!-- <router-link :to="'/freeboard/modifyfree/' + recruit.bno" tag="button">수정</router-link> -->
    <!-- <button @click="deleteRecruit">삭제</button> -->
    <router-link to="/recruit/recruit1" tag="button">목록으로</router-link>
    <br>
    <br>
    <!-- * 댓글 목록
    <button @click="addComment">댓글달기</button><br>
    <textarea name="" id="" cols="30" rows="10" v-model="comment2.ccontent" placeholder="내용을 입력하세요"></textarea>
    comment 싱글파일컴포넌트 제작시 테이블 빼고 컴포로 대체
    <comment v-for="comment in comments" 
    :key="comment.cno"
    :comment="comment"
    @delete-comment="deleteComment"
    /> -->
  </div>
</template>

<script>
import Constant from '../../Constant';
// import comment from '../comment/comment';

export default {
//   components:{
//     comment
//   },
//   data() {
//     return {
//       comment2 : {
//         cno :'',
//         cwriter:'',
//         ccontent:'',
//         bno:'',
//         isSelected:'',
//         makeDay:'',
//         changeDay:'',
//         makeId:'',
//         changeId:''
//       }
//     }
//   },
  created() {
    // console.log(this.$route.params.bno);
    this.getBoard(this.$route.params.rnum);
  },
  computed: {
      recruit(){
        return this.$store.state.recruitstore.recruit;
      }
    //     recruit:{
    //       get() {
    //         return this.$store.state.recruitstore.recruit;
    //       },
    //       set(newBoard) {
    //         this.board.bno = newBoard.bno;
    //         this.board.bwriter = newBoard.bwriter;
    //         this.board.btitle = newBoard.btitle;
    //         this.board.bcontent = newBoard.bcontent;
    //         this.board.bview = newBoard.bview;
    //         this.board.bfile = newBoard.bfile;
    //         this.board.bstate = newBoard.bstate;
    //         this.board.makeDay = newBoard.makeDay;
    //         this.board.changeDay = newBoard.changeDay;
    //         this.board.makeId = newBoard.makeId;
    //         this.board.changeId = newBoard.changeId;
    //       }
    //   },

    //   comments(){
    //     // console.log('도착'+this.$store.state.commentstore.comments);
    //     return this.$store.state.commentstore.comments; 
    //   }
  },
  methods: {
    getBoard(rnum) {
      this.$store.dispatch(Constant.GET_RECRUIT, { rnum });
    //   this.$store.dispatch(Constant.GET_COMMENTLIST,{bno});

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
table, td, tr, th {
  width : 1000px;
  margin : 0 auto;
  text-align: center;
  border: 1px solid black;
}
</style>

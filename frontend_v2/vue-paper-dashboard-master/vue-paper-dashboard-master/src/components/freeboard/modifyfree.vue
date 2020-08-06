<template>
    <div>
    <div class="card" >
      
      <div class="card-header">
        <h1>게시판 글 수정 샘플</h1>
      </div>
      <hr>
      <div class="card-body">
        <div style="display:block;">

          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left"  > 
                제목
              </div>

              <div class="col-10 write-right"> 
                <input v-model="board.btitle" type="text" value="board.title">
              </div>
            </div>
          </div>
          


          <div class="container-fluid">
            <div class="row" style="min-height:600px;">
              <div class="col-2 write-left" > 
                내용
              </div>

              <div class="col-10 write-right">       
                <vue-editor v-model="board.bcontent" style="height:80%;"></vue-editor>
              <!--<textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>-->
              </div>
            </div>
          </div>

          <div class="container-fluid">
            <div class="row">
              <div class="col-2 write-left" > 
                첨부파일
              </div>
              <div class="col-10 write-right"> 
                첨부파일 넣을 수 있는거 넣어주자
              </div>
            </div>
          </div>
        </div>

        <div class="container">
          <div class="row">
            <div class="col text-center btndiv">
              <button class="btn btn-outline-primary" @click="modifyFree">수정</button>
              <button class="btn btn-outline-primary" @click="back">취소</button>
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
import Constant from '../../Constant';
import { VueEditor } from "vue2-editor";
const storage = window.sessionStorage;

    export default {
    name: "modifyfree",
components: {
    VueEditor,
  },
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
            let addr = "/freeboard/detailfree/"+ this.board.bno;
            this.$router.push(addr);
          } else {
            console.log("공백입력.");
          }
          // this.clear();
        },
        back() {
            let addr = "/freeboard/detailfree/"+ this.board.bno;
            this.$router.push(addr);
        }
        // clear() {
        //   this.board = {}; 
        // },
        
  },
  
  
};
</script>

<style scoped lang="scss">

.write-left {
  //float:left;
  background:rgb(233, 233, 233);
  //width:20%;
  border: solid 0.5px rgb(128, 127, 127);
  padding:15px;
}
.write-right {
  //float:left; 
  background:rgb(255, 255, 255); 
  //width:80%; 
  text-align:left;
  border: solid 0.5px rgb(128, 127, 127);
  padding:15px;
}
input {
  width:70%; 
  margin-left:2px;
}
.input-div{
  padding-top:12px;
  padding-bottom:12px;
}
.btndiv {
  padding:10px;
}
.btndiv > button {
  margin:10px;
}
</style>
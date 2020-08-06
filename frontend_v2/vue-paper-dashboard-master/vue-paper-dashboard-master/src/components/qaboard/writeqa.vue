<template>
  <div class="qaboard1">
    <h1>질문게시판 글 작성 샘플</h1>
    
    <hr>
    아이디 : store의 state에서 가져오는 세션 아이디<br>
    제목 : <input type="text" v-model="board.btitle" placeholder="제목을 입력하세요" /><br>
    내용<br>
    <textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>
    <button @click="addFree">등록</button>
    <router-link to="/qaboard" tag="button">목록으로</router-link>
  </div>
</template>

<script>
import Constant from '../../Constant';

export default {
  name: "writeqa",

  data: function() {
    return {
      board : {
        bno : '',
        bwriter : '',
        btitle : '',
        bcontent : '',
        bview :'',
        bfile :'',
        bstate : '',
        makeDay : '',
        changeDay : '',
        makeId :'',
        changeId : ''
      },
    };
  },
  methods: {
    addFree(){
            if(this.board.bcontent.trim() != ''){
                this.$store.dispatch(Constant.ADD_BOARD,{
                  //bno : auto increase
                    // bwriter : this.board.bwriter, 임시로 ssafy foreign key때문
                    bwriter : 'ssafy',
                    btitle : this.board.btitle,
                    bcontent : this.board.bcontent,
                    bview : this.board.bview,
                    bfile : this.board.bfile,
                    bstate : 'qa',
                    makeDay : new Date(),
                    // changeDay : this.board.changeday,
                    makeId : this.board.makeid,
                    // changeId : this.board.changeid
                });
                this.$router.push('/qaboard');
            }else{
                console.log('공백입력.');
            }    
            this.clear();
        },
        clear(){
            this.board = {
            };  //this.board = {} 이렇게 하면 안나옴
        }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

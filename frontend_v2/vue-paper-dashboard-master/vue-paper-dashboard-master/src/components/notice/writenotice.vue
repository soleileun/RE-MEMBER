<template>
  <div class="writenotice">
    <h1>공지사항 글 작성 샘플</h1>
    
    <hr>
    
    아이디 : store의 state에서 가져오는 세션 아이디<br>
    제목 : <input type="text" v-model="board.btitle" placeholder="제목을 입력하세요" /><br>
    내용<br>
    <textarea name="" id="" cols="30" rows="10" v-model="board.bcontent" placeholder="내용을 입력하세요"></textarea><br>
    <button @click="addFree">등록</button>
    <router-link to="/notice" tag="button">목록으로</router-link>
  </div>
</template>

<script>
import Constant from '../../Constant';
const storage= window.sessionStorage;
export default {
  name: "writenotice",

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
                    bwriter : storage.getItem('userid'),
                    btitle : this.board.btitle,
                    bcontent : this.board.bcontent,
                    bview : this.board.bview,
                    bfile : this.board.bfile,
                    bstate : 'notice',
                    makeDay : new Date(),
                    // changeDay : this.board.changeday,
                    makeId : this.board.makeid,
                    // changeId : this.board.changeid
                });
                this.$router.push('/notice');
            }else{
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

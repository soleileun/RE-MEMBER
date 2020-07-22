<template>
  <div class="freeboard1">
    <h1>자유게시판  샘플 1</h1>
  <hr>
  <!-- 로그인 여부에 따라 활성 비활성 여부 결정 -->
  <router-link to="/freeboard/writefree" tag="button">글쓰기</router-link>
  <table>
    <thead>
      <tr>
        <th>no</th>
        <th>title</th>
        <th>id</th>
        <th>register_date</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="free in frees" :key="free.bno">
        <td>{{free.bno}}</td>
        <!-- bno 쿼리스트링 달아서 분기 -->
        <td>  <router-link :to="'/freeboard/detailfree/' + free.bno">{{free.btitle}}</router-link></td>
        <td>{{free.bwriter}}</td>
        <td>{{free.makeday}}</td>
      </tr>
    </tbody>
  </table>
  <!-- 디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br> -->
    <select name="standard" id="standard" >
      <option value="t" selected>제목</option>
      <option value="w">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord"/>
    <button v-on:click="searchFree">검색</button>
  


  </div>
</template>

<script>
import Constant from '../../Constant';
// import { mapActions } from 'vuex'

export default {
  name: "freeboard1",
  computed: {
    frees() {
      // console.log(this.$store.state.boards);
      return this.$store.state.boards; 
    }
  },
  created () {
    this.$store.dispatch(Constant.GET_BOARDLIST,{bstate : 'free'});
  },

  data() {
    return {
      // searchWord : '검색어',
      // selected : '조건',
    }
  },
  // methods: {
  //   getBoard(no) {
  //     console.log('getBoard :::: '+no);
  //     this.$router.push('/detail/'+no);
  //   },
  //   ...mapActions([
  //     Constant.REMOVE_TODO,
  //     Constant.COMPLETE_TODO
  //   ])
  // },

  methods: {
    searchFree(){
      let val  = document.getElementById("searchWord").value;
      let std = document.getElementById("standard").value;

      //제목 검색
      if(std == 't'){
        console.log(val);
        this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val});
      }else{ //작성자 검색
        this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val});
      }
    }
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

<template>
  <div class="qaboard1">
    <h1>질문게시판  샘플 1</h1>
  <hr>
  <!-- 로그인 여부에 따라 활성 비활성 여부 결정 -->
  <router-link to="/qaboard/writeqa" tag="button">글쓰기</router-link>
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
        <td>  <router-link :to="'/qaboard/detailqa/' + free.bno">{{free.btitle}}</router-link></td>
        <td>{{free.bwriter}}</td>
        <td>{{free.makeDay}}</td>
      </tr>
    </tbody>
  </table>
  <!-- 디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br> -->
    <select name="standard" id="standard3" >
      <option value="t" selected>제목</option>
      <option value="w">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord3"/>
    <button v-on:click="searchFree">검색</button>
  


  </div>
</template>

<script>
import Constant from '../../Constant';
// import { mapActions } from 'vuex'

export default {
  name: "qaboard1",
  computed: {
    frees() {
      // console.log('안녕');
      // console.log('확인 : ' + this.$store.state.boardstore.boards);
      return this.$store.state.boardstore.boards; 
    }
  },
  created () {
    this.$store.dispatch(Constant.GET_BOARDLIST,{bstate : 'qa'});
  },

  methods: {
    searchFree(){
      let val  = document.getElementById("searchWord3").value;
      let std = document.getElementById("standard3").value;

      //제목 검색
      if(std == 't'){
        console.log(val);
        this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val, bstate : 'qa'});
      }else{ //작성자 검색
        this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val, bstate : 'qa'});
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

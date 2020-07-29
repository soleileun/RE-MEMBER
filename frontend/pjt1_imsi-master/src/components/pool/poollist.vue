<template>
  <div>
      <h1>인재풀 샘플</h1>
      pool db crud 제작??
      일단 급한대로 userinfo에서 가져옴
  <hr>
  <router-link to="/pool/registpool" tag="button">인재풀 등록</router-link>
  <table>
    <thead>
      <tr>
        <th>id</th>
        <th>field</th>
        <th>git</th>
        <th>project</th>
        <th>call</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="pool in pools" :key="pool.id">
        <td>{{pool.id}}</td>
        <td>관심분야칼럼??스택??필터링기준넣기</td>
        <!-- 깃 링크 넣기 -->
        <td><a href="">{{pool.git}}</a></td>
        <td>  <router-link :to="'/project/myproject/' + pool.id" tag="button">프로젝트 보기</router-link></td>
        <td><button>메시지 전송</button></td>
      </tr>
    </tbody>
  </table>

필터링 기준??? 어떤식으로 할 것인지. 조건에 따른 유저인포 검색 쿼리 추가<br>
    <select name="standard" id="standard" >
      <option value="t" selected>스택으로 검색</option>
      <option value="w">id로 검색</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord"/>
    <button v-on:click="searchPool">검색</button>
  


  </div>
</template>

<script>

import Constant from '../../Constant';

export default {
  name: "poollist1",
  computed: {
    pools() {
      return this.$store.state.poolstore.pools; 
    }
  },
  created () {
    this.$store.dispatch(Constant.GET_POOLLIST);
  },

  methods: {
    searchPool(){
      // let val  = document.getElementById("searchWord").value;
      // let std = document.getElementById("standard").value;

      // //제목 검색
      // if(std == 't'){
      //   console.log(val);
      //   this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val, bstate : 'free'});
      // }else{ //작성자 검색
      //   this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val, bstate : 'free'});
      // }
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

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
        <td>  <router-link to="/freeboard/detailfree">{{free.btitle}}</router-link></td>
        <td>{{free.bwriter}}</td>
        <td>{{free.makeday}}</td>
      </tr>
    </tbody>
  </table>
  디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br>
    <select name="standard" id="standard" >
      <option value="제목" selected>제목</option>
      <option value="작성자">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord"/>
    <button v-on:click="searchFree">검색</button>
  


  </div>
</template>

<script>
export default {
  name: "freeboard1",
  data() {
    return {
      searchWord : '검색어',
      selected : '조건',
      //후에 디비로 교체
      frees:[
        { 
          bno : 1 ,
          bwriter : 'ssafy',
          btitle : 'love you ssafy',
          bcontent : 'fall in love ssafy',
          bview : 12, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy',
          bstate : 'free', //게시글 종류. 후에 수정
          makeday : '2020-03-31',
          changeday : '2020-07-01',
          makeid : 'makeid1',
          changeid : 'changeid1'
        },
        { 
          bno : 2 ,
          bwriter : 'meeting',
          btitle : 'ssafy meeting',
          bcontent : 'offline good good',
          bview : 7, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy/meeting',
          bstate : 'free', //게시글 종류. 후에 수정
          makeday : '2020-04-31',
          changeday : '2020-05-01',
          makeid : 'makeid2',
          changeid : 'changeid2'
        }
      ]
    }
  },
  methods: {
    searchFree(){
      this.searchWord = document.getElementById("searchWord").value;
      this.selected = document.getElementById("standard").value;
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

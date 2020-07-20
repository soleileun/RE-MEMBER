<template>
  <div class="qaboard1">
    <h1>질문게시판 메인 샘플 1</h1>

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
      <tr v-for="qa in qas" :key="qa.bno">
        <td>{{qa.bno}}</td>
        <!-- bno 쿼리스트링 달아서 분기 -->
        <td>  <router-link to="/qaboard/detailqa">{{qa.btitle}}</router-link></td>
        <td>{{qa.bwriter}}</td>
        <td>{{qa.makeday}}</td>
      </tr>
    </tbody>
  </table>
  디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br>
    <select name="standard" id="standard3" >
      <option value="제목" selected>제목</option>
      <option value="작성자">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord3"/>
    <button v-on:click="searchQa">검색</button>
  
  </div>
</template>

<script>
export default {
  name: "qaboard1",
  data() {
    return {
      searchWord : '검색어',
      selected : '조건',
      //후에 디비로 교체
      qas:[
        { 
          bno : 5 ,
          bwriter : 'hissafy',
          btitle : '화장실은 어디에 있습니까',
          bcontent : '너무 급합니다',
          bview : 12, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy',
          bstate : 'qa', //게시글 종류. 후에 수정
          makeday : '2020-03-31',
          changeday : '2020-07-01',
          makeid : 'makeid1',
          changeid : 'changeid1'
        },
        { 
          bno : 6 ,
          bwriter : 'hellossafy',
          btitle : '이벤트는 언제 시작합니까',
          bcontent : '기다리고 있겠습니다',
          bview : 7, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy/meeting',
          bstate : 'qa', //게시글 종류. 후에 수정
          makeday : '2020-04-31',
          changeday : '2020-05-01',
          makeid : 'makeid2',
          changeid : 'changeid2'
        }
      ]
    }
  },
  methods: {
    searchQa(){
      this.searchWord = document.getElementById("searchWord3").value;
      this.selected = document.getElementById("standard3").value;
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

<template>
  <div class="notice1">
    <h1>공지사항  샘플 1</h1>
  <hr>
  <!-- 로그인 여부에 따라 활성 비활성 여부 결정 -->
  <router-link to="/notice/writenotice" tag="button">공지사항 작성</router-link>
  <table>
    <thead>
      <tr>
        <th>no</th>
        <th>title</th>
        <th>관리자</th>
        <th>register_date</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="notice in notices" :key="notice.bno">
        <td>{{notice.bno}}</td>
        <!-- 여기에 bno줘서 라우팅 -->
        <td>  <router-link to="/notice/detailnotice">{{notice.btitle}}</router-link></td>
        <td>{{notice.bwriter}}</td>
        <td>{{notice.makeday}}</td>
      </tr>
    </tbody>
  </table>
  디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br>
    <select name="standard" id="standard2" >
      <option value="제목" selected>제목</option>
      <option value="작성자">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord2"/>
    <button v-on:click="searchNotice">검색</button>
  


  </div>
</template>

<script>
export default {
  name: "notice1",
  data() {
    return {
      searchWord : '검색어',
      selected : '조건',
      //후에 디비로 교체
      notices:[
        { 
          bno : 3 ,
          bwriter : 'admin1',
          btitle : 'event on',
          bcontent : 'mileige give you',
          bview : 125, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy',
          bstate : 'notice', //게시글 종류. 후에 수정
          makeday : '2020-03-31',
          changeday : '2020-07-01',
          makeid : 'makeid1',
          changeid : 'changeid1'
        },
        { 
          bno : 4 ,
          bwriter : 'admin2',
          btitle : 'ssafy meeting canceled',
          bcontent : 'offline good good but we cant meet',
          bview : 27, //조회 시 increase 나중에 구현
          bfile : 'c:/ssafy/meeting',
          bstate : 'notice', //게시글 종류. 후에 수정
          makeday : '2020-04-31',
          changeday : '2020-05-01',
          makeid : 'makeid2',
          changeid : 'changeid2'
        }
      ]
    }
  },
  methods: {
    searchNotice(){
      this.searchWord = document.getElementById("searchWord2").value;
      this.selected = document.getElementById("standard2").value;
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

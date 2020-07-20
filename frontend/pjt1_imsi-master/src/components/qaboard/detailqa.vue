<template>
  <div class="freeboard1">
    <h1>질문게시판 글 조회 샘플</h1>
    
    <hr>
    <!-- 게시물 번호로 게시물 search 후 id 꺼내기 -->
    아이디 : 게시글아이디<br> 
    제목 : 게시글제목<br>
    등록날짜 : 게시글등록날짜<br>
    내용<br>
    게시글내용<br>
    <!-- 작성자 본인, 관리자만 수정 삭제 가능 -->
    <button @click="modifyQa">수정</button>
    <button @click="deleteQa">삭제</button>
    <router-link to="/qaboard" tag="button">목록으로</router-link>
    <br>
    <br>
    * 댓글 목록
    <!-- comment 싱글파일컴포넌트 제작시 테이블 빼고 컴포로 대체 -->
    <table>
    <thead>
      <tr>
        <th>no</th>
        <th>id</th>
        <th>contents</th>
        <th>register_day</th>
        <th> - </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="comment in comments" :key="comment.cno">
        <td>{{comment.cno}}</td>
        <!-- bno 쿼리스트링 달아서 분기 -->
        <td>{{comment.cwriter}}</td>
        <td>{{comment.ccontent}}</td>
        <td>{{comment.makeday}}</td>
        <!-- 작성자 또는 관리자일 때만 활성화로 설정 변경할 것 -->
        <td><a href="#">삭제</a></td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<script>
export default {
  name: "detailqa",

  data: function() {
    return {
      comments : [
        {
          cno:1,
          cwriter:'ydg',
          ccontent:'i agree',
          bno:1,
          isSelected:1,
          makeday:"2020-04-11",
          changeday:"2020-04-13",
          makeid:"makeid",
          changeid:"changeid"
        },
         {
          cno:2,
          cwriter:'hsh',
          ccontent:'i dont agree',
          bno:1,
          isSelected:0,
          makeday:"2020-04-11",
          changeday:"2020-04-13",
          makeid:"makeid",
          changeid:"changeid"
        }
      ]
    };
  },
  methods: {
    modifyQa(){
      alert('db 연동하여 글 수정하는 코드로 대체');
    },
    deleteQa(){
      alert('db 연동하여 글 삭제하는 코드로 대체');
    },
  }
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

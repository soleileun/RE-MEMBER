<template>
  <div class="freeboard1">
    <h1>질문게시판 글 작성 샘플</h1>
    
    <hr>
    아이디 : store의 state에서 가져오는 세션 아이디<br>
    제목 : <input type="text" placeholder="제목을 입력하세요"/><br>
    등록날짜 : {{now}}<br>
    내용<br>
    <textarea name="" id="" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea><br>
    <button @click="registQa">등록</button>
    <router-link to="/qaboard" tag="button">목록으로</router-link>
  </div>
</template>

<script>
export default {
  name: "writeqa",

  data: function() {
    return {
      now: new Date()
    };
  },
  methods: {
    registQa(){
      alert('db에 등록하는 코드로 대체');
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

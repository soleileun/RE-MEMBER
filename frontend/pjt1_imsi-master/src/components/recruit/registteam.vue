<template>
  <div>
    팀장 아이디 : store의 state에서 가져오는 세션 아이디<br>
    프로젝트명 : <input type="text" placeholder="프로젝트명을 입력하세요"/><br>
    프로젝트원 수 : <input type="text"><br>
    시작날짜 : <input type="date"><br>
    프로젝트 상세 내용<br>
    <textarea name="" id="" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea><br>
    <button @click="registTeam">등록</button>
    <router-link to="/recruit" tag="button">목록으로</router-link>
    <!-- 목록으로 돌아갈 때 tab 메뉴가 디폴트로 돌아가는 거 수정하기 -->

  </div>

</template>

<script>
export default {

    methods: {
        registTeam(){
            alert('팀 등록 db 연동할 것');
        }
    },
}
</script>

<style>

</style>
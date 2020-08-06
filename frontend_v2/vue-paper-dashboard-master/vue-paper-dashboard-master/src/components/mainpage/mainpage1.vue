<template>
<div class="main1">
  인터레스트 입력을 토대로 추천 position 보여주기

 
        <aside>
            내 프로젝트 3개<br>
            여긴 간단히 1 2 3 개 텍스트
            <!-- 세션에서 꺼낸 id 넣기 -->
            <br>
            <router-link :to="'/project/myproject/' + storage.getItem('userid')">내 프로젝트</router-link>

        </aside>
    
</div>
</template>

<script>
const storage = window.sessionStorage;
export default {
  name: 'main1',
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// .maina{
// }

//임시 템플릿 css//////////
aside {
    width: 140px;
    height: 50%;
    background-color: plum;
    position: absolute;
    top: 170px;
    left: 863px;
    text-align: center;
}
////////////////////

</style>

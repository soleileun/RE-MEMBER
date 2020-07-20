<template>
  <div class="project1">
    <!-- <h1>프로젝트는 네비바에 추가가 아닌 마이페이지나 남의 포트폴리오 열람 시 모달로 열수 있도록 sfc로 구현하는 것이 좋을 거 같습니다. 일단 여기다 구현</h1> -->
    <table>
      <tr>
        <td>프로젝트명</td>
        <td>{{project.pjtname}}</td>
      </tr>    
      <tr>
        <td>착수 날짜</td>
        <td>{{project.pjtstartdate}}</td>
      </tr>    
      <tr>
        <td>프로젝트 내용</td>
        <td>{{project.pjtcontent}}</td>
      </tr>    
      <tr>
        <td>팀원 수</td>
        <td>{{project.pjtmember_cnt}}</td>
      </tr>    
    </table>
  </div>
</template>

<script>
export default {
  name: "project1",
  data() {
    return {
      project : {
        pid : 'ssafy',
        pjtname : '블록체인 프로젝트',
        pjtstartdate : '2020-02-11',
        pjtcontent : '블록 체인을 이용',
        pjtstate : '완료',
        pjtmember_cnt : 3,
        makeday : '2020-03-31',
        changeday : '2020-07-01',
        makeid : 'makeid1',
        changeid : 'changeid1'
      }
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

<template>
  <div>
      <h1>포트폴리오</h1>
      <hr>
      <h2>프로젝트 종류 (여기서 배치 UI 회의후 결정)</h2>

      <table>
            <thead>
            <tr>
                <th>no</th>
                <th>프로젝트명</th>
                <th>프로젝트 요약</th>
                <th>상세보기</th>
                <th>진행상황</th>

            </tr>
            </thead>
            <tbody>
            <tr v-for="project in projects" :key="project.bno">
                <td>1부터 하나씩 올리기</td>
                <td>{{project.pjtname}}</td>
                <td>{{project.pjtcontent}}</td>
                <td>  <router-link to="/project/project1" tag="button">상세보기</router-link></td>
                <td>{{project.pjtstate}}</td>
            </tr>
            </tbody>
        </table>
  </div>
</template>

<script>
export default {
    data() {
        return {
            projects:[
                { 
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
                },
                { 
                    pid : 'ssafy',
                    pjtname : 'ai 프로젝트',
                    pjtstartdate : '2020-02-11',
                    pjtcontent : 'ai 이용',
                    pjtstate : '진행중',
                    pjtmember_cnt : 5,
                    makeday : '2020-03-31',
                    changeday : '2020-07-01',
                    makeid : 'makeid2',
                    changeid : 'changeid2'
                },
            ]
        }
    },
}
</script>

<style>
    table, td, tr, th {
    width : 85%;
    margin : 0 auto;
    text-align: center;
    border: 1px solid black;
    }
</style>
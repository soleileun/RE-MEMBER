<template>
  <div>
    <h4>아직 어디 들어갈지 모르는 팀원관리 ^-^!! 일단 테이블로했는데 카드가 더 이쁠거같아...</h4>

    <ul>
      <li v-for="pm in pmlist" :key="pm.userId">{{ pm.userId }}</li>
    </ul>

    <div class="container">
      <div class="row row--top-40">
        <div class="col-md-12">
          <h2 class="row__title">프로젝트 팀원</h2>
        </div>
      </div>
      <div class="row row--top-20">
        <div class="col-md-12">
          <div class="table-container">
            <table class="table">
              <thead class="table__thead">
                <tr>
                  <th class="table__th text-center">
                    <strong>ID</strong>
                  </th>
                  <th class="table__th text-center">
                    <strong>역할</strong>
                  </th>
                  <!-- <th class="table__th">프로젝트명</th>
                  <th class="table__th">마감일시</th>
                  <th class="table__th">구인현황</th>-->
                  <!-- <th class="table__th">Progress</th> -->
                  <!-- <th class="table__th"></th> -->
                </tr>
              </thead>
              <tbody class="table__tbody">
                <partnerlist v-for="pm in pmlist" :key="pm.userId" :pm="pm" :pid="pm.userId" />
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "@/Constant.js";
// import http from "@/http-common.js";
import partnerlist from "@/components/project/partnerlist.vue";
const storage = window.sessionStorage;
export default {
  name: "partners",
  components: { partnerlist },
  data: function () {
    return {};
  },
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
      pid: this.$route.params.pid,
    });
  },
};
</script>

<style>
</style>
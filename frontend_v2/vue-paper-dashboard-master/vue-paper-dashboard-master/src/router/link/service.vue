<template>
  <div class="service">
    <div class="container">
      <!-- <h1>고객 센터</h1> -->
      <div class="row" id="test">
        <br />
        <div class="col-5" id="box">
          <br />
          <br />
          <terms />
          <br />
          <h5>자주 묻는 질문</h5>
          <siteQ50 />
        </div>
        <div class="col-7" style="border-radius: 10px;">
          <br />
          <br />
          <h5>문의 / 신고 / 제안</h5>

          <sitereport />
          <br />
          <br />
        </div>
        <br />
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import terms from "../../components/service/terms.vue";
import siteQ50 from "../../components/service/siteQ50.vue";
import sitereport from "../../components/service/sitereport.vue";
export default {
  name: "service",
  components: {
    terms,
    siteQ50,
    sitereport,
  },
  data: function () {
    return {
      userid: window.sessionStorage.getItem("userid"),
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic&family=Noto+Sans+KR&family=Open+Sans&family=Roboto&display=swap");
.service {
  // font-family: "Black Han Sans", sans-serif;

  font-family: "Nanum Gothic", sans-serif;
  background-color: white;
  border-radius: 10px;
}
.h5 {
  font-family: "Nanum Gothic", sans-serif;
}
</style>

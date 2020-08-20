<template>
  <div class="findIDPW">
    <card class="card text-center">
      <h1>아이디 찾기</h1>
      <div class="row">
        <div class="col-12">
          이름
          <input v-model="name" maxlength="10" type="text" />
        </div>
        <div class="col-12">
          전화번호:
          <input type="text" maxlength="3" v-model="phone0" />-
          <input id="p1" type="text" maxlength="4" v-model="phone1" />-
          <input id="p2" type="text" maxlength="4" v-model="phone2" @keyup.enter="search()" />
        </div>
      </div>
      <br />
      <button class="btn-info btn-round" @click="search()">찾기</button>
    </card>
    <!-- <card class="card text-center">
      <h1>비밀번호 찾기</h1>
      <div class="row">
        <div class="col-12">
          이름
          <input v-model="name" maxlength="10" type="text" />
        </div>
        <div class="col-12">
          전화번호:
          <input type="text" maxlength="3" v-model="phone0" />-
          <input id="p1" type="text" maxlength="4" v-model="phone1" />-
          <input id="p2" type="text" maxlength="4" v-model="phone2" @keyup.enter="search()" />
        </div>
      </div>
      <br />
      <button class="btn-info btn-round" @click="searchPW()">찾기</button>
    </card> -->
  </div>
</template>

<script>
import http from "../../http-common.js";
export default {
  name: "findIDPW",
  data: function () {
    return {
      id: "",
      name: "",
      phone0: "",
      phone1: "",
      phone2: "",
    };
  },
  watch: {
    name: function () {
      this.name = this.name.replace(/[^가-힣]/g, "");
    },
    phone0: function () {
      this.phone0 = this.phone0.replace(/[^0-9]/g, "");
      if (this.phone0.length === 3) {
        document.querySelector("#p1").focus();
      }
    },
    phone1: function () {
      this.phone1 = this.phone1.replace(/[^0-9]/g, "");
      if (this.phone1.length === 4) {
        document.querySelector("#p2").focus();
      }
    },
    phone2: function () {
      this.phone2 = this.phone2.replace(/[^0-9]/g, "");
    },
  },
  mounted(){
    if (window.localStorage.getItem('userid').length>0){
      this.$router.push('/')
    }
  },
  methods: {
    search() {
      http
        .get("/api/userinfo/")
        .then((res) => {
          if (
            !res.data.find((item) => {
              if (
                item.name === this.name &&
                item.phone === `${this.phone0}-${this.phone1}-${this.phone2}`
              ) {
                alert(`아이디는 '${item.id}'입니다.`);
                return true;
              }
            })
          ) {
            alert("찾는 정보의 아이디가 없습니다.");
          }
        })
        .catch((exp) =>{});
    },
    searchPW() {
      http
        .get("/api/userinfo/")
        .then((res) => {
          if (
            !res.data.find((item) => {
              if (
                item.name === this.name &&
                item.phone === `${this.phone0}-${this.phone1}-${this.phone2}`
              ) {
                alert(`아이디는 '${item.id}'입니다.`);
                return true;
              }
            })
          ) {
            alert("찾는 정보의 아이디가 없습니다.");
          }
        })
        .catch((exp) => {});
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

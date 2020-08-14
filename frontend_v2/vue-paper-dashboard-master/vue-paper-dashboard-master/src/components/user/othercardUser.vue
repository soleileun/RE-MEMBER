<template>
  <card class="card-user">
    <div slot="image">
      <img src="@/assets/img/whiteBg.png" alt="..." />
    </div>
    <div>
      <div class="author">
        <img class="avatar border-white" :src="url" alt="..." />
        <h4 class="title">{{userid}}</h4>
      </div>
    </div>
    <hr />
    <div class="text-center">
      <div class="row">
        <div class="col-12">
          <h4 class="card-title m-t-10">관심사</h4>
          <div class="ints btn btn-success" v-for="inter in inters" :key="inter.interest">{{inter.interest}}</div>
        </div>
      </div>
    </div>
  </card>
</template>
<script>
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;
export default {
  props: {
    userid: String,
  },
  data() {
    return {
      inters: [],
      url: this.$store.state.filestore.fileUrl + this.userid,
    };
  },
  watch: {
    userid: function () {
      this.init();
    },
  },
  mounted() {
    this.init();
    http
      .get(this.url + ".png")
      .then((res) => {
        this.url = this.url + ".png";
      })
      .catch((e) => {
        http
          .get(this.url + ".jpg")
          .then((res) => {
            this.url = this.url + ".jpg";
          })
          .catch((e) => {
            this.url = this.$store.state.filestore.fileUrl + "default.png";
          });
      });
  },
  methods: {
    init() {
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
        },
      };
      http
        .get("/api/interest/selectById/" + this.userid, config)
        .then((response) => {
          if (response.data.length > 0) {
            this.inters = response.data;
          } else {
            this.inters = [{ interest: "관심사가 아직 없습니다." }];
          }
        })
        .catch((exp) => alert("관심사를 로드하는데에 실패하였습니다." + exp));
    },
  },
};
</script>
<style>
.ints {
  margin: 5px;
  border-radius: 20px;
}
</style>

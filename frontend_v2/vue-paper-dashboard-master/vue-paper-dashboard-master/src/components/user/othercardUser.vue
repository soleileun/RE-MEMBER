<template>
  <card class="card-user">
    <div slot="image">
      <img src="@/assets/img/whiteBg.png" alt="..." />
    </div>
    <div>
      <div class="author">
        <img class="avatar border-white" src="@/assets/img/faces/face-2.jpg" alt="..." />
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
      inters:[]
    };
  },
  watch:{
    userid:function(){
      this.init()
    }
  },
  mounted(){
    this.init()
  },
  methods: {
    init(){
      http
      .get("/api/interest/selectById/" + this.userid )
      .then((response) => {
        if (response.data.length > 0) {
          this.inters= response.data
        } else {
          this.inters = [{interest:"관심사가 아직 없습니다."}];
        }
      })
      .catch((exp) => alert("관심사를 로드하는데에 실패하였습니다." + exp));
    }
  },
};
</script>
<style>
.ints {
  margin: 5px;
  border-radius: 20px;
}
</style>

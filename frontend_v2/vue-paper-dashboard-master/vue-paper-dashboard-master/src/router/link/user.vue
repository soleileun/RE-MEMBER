<template>
  <div class="user">
    <div class="container-fluid">
      <div class="row page-titles">
        <div class="col-md-12 align-self-center">
          <h1 class="text-themecolor">내 정보</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-xlg-3 col-md-5">
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                <img src="assets/images/users/5.jpg" class="img-circle" width="150" />
                <h4 class="card-title m-t-10">{{userNick}}</h4>
                <div class="row text-center justify-content-md-center">내 점수 : </div>
                <div class="row text-center justify-content-md-center">깃 주소 : </div>
                <div class="row text-center justify-content-md-center">나를 팔로우한 유저 수</div>
              </center>
            </div>
          </div>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                <h4 class="card-title m-t-10">내 관심사</h4>
              </center>
            </div>
          </div>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                { "id": "abc", "nickname": "sdfkja", "name": "황수", "pw": "abcd1234", "address1": "ㅁㄴㅇ", "address2": "ㅁㄴㅇㅇㄴㄹ", "phone": "010-3390-0000", "git": "sdagfdgkk", "points": 0, "lastDate": null, "state": false, "responsibility": "개발", "valid": false }
                <br />
                <button>
                  <router-link class="nav-link" :to="{name:'editinfo'}">개인정보 수정</router-link>
                </button>
                <br />
                <button>
                  <router-link class="nav-link" :to="{name:'service'}">고객센터</router-link>
                </button>
                <br />
                <button>
                  <router-link class="nav-link" :to="{name:'leave'}">회원탈퇴</router-link>
                </button>
              </center>
            </div>
          </div>
        </div>
        <div class="col-lg-8 col-xlg-9 col-md-7">
          <div class="card">
            <div class="card-body">
              <profile />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profile from "../../components/user/profile.vue";
const storage = window.sessionStorage;

export default {
  name: "user",
  components: {
    profile,
  },
  mounted: function(){
    this.$store.dispatch("getMyProfile")
  },
  data: function () {
    return {
      userNick: storage.getItem("userNick"),
    };
  },
  methods:{
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

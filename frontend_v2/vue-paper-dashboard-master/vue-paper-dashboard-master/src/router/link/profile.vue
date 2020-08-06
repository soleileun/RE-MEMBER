<template>
  <div class="user">
    <div class="container-fluid">
      <div class="row page-titles">
        <div class="col-md-12 align-self-center">
          <h1 class="text-themecolor">PROFILE</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-xlg-3 col-md-5">
          <card-user></card-user>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                <h4 class="card-title m-t-10">내 관심사</h4>
                <div
                  class="ints btn btn-success"
                  v-for="inter in inters"
                  :key="inter.interest"
                >{{inter.interest}}</div>
                <hr />
                <router-link class="nav-link" to="/user/editinterest">
                  <button class="btn btn-info">관심사 수정</button>
                </router-link>
              </center>
            </div>
          </div>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
<<<<<<< HEAD
                <router-link class="nav-link" :to="userid">
=======
                <router-link class="nav-link" :to="`project/myproject/${userId}`">
>>>>>>> c182374e200b228da38cd933578faf16e3ab20b2
                  <button class="profBtn btn btn-info">내 프로젝트 보기</button>
                </router-link>
                <router-link class="nav-link" to="/user/editinfo">
                  <button class="profBtn btn btn-info">개인정보 수정</button>
                </router-link>

                <router-link class="nav-link" :to="{path:'/service'}">
                  <button class="profBtn btn btn-info">고객센터</button>
                </router-link>

                <router-link class="nav-link" to="/user/leave">
                  <button class="profBtn btn btn-danger">회원탈퇴</button>
                </router-link>
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
import cardUser from "../../components/user/cardUser.vue";
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;

export default {
  name: "user",
  components: {
    profile,
    cardUser,
  },
  mounted: function () {
    http
      .get("/api/interest/selectById/" + storage.getItem("userid"))
      .then((response) => {
        if (response.data.length > 0) {
          this.inters = response.data;
        } else {
          this.inters = [{ interest: "관심사가 아직 없습니다." }];
        }
      })
      .catch((exp) => alert("관심사를 로드하는데에 실패하였습니다." + exp));
  },
  data: function () {
    return {
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),
      inters: [],
    };
  },
  computed:{
    userid(){
      return '/project/myproject/'+storage.getItem('userid')
    }
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.ints {
  margin: 5px;
  border-radius: 20px;
}
button.profBtn {
  width: 70%;
  border-radius: 18px;
}
</style>
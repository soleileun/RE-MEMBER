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
              </center>
            </div>
          </div>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                <br />
                <button>
                  <router-link class="nav-link" to="/user/editinfo">개인정보 수정</router-link>
                </button>
                <br />
                <button>
                  <router-link class="nav-link" :to="{path:'/service'}">고객센터</router-link>
                </button>
                <br />
                <button>
                  <router-link class="nav-link" to="/user/leave">회원탈퇴</router-link>
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
import cardUser from "../../components/user/cardUser.vue";
const storage = window.sessionStorage;

export default {
  name: "user",
  components: {
    profile,
    cardUser,
  },
  mounted: function () {
    this.$store.dispatch("getMyProfile");
  },
  data: function () {
    return {
      userNick: storage.getItem("userNick"),
    };
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
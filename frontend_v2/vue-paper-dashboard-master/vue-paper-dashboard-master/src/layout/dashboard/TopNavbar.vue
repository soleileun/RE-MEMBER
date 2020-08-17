<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">{{routeName}}</a>
      <button class="navbar-toggler navbar-burger" type="button" @click="toggleSidebar" :aria-expanded="$sidebar.showSidebar" aria-label="Toggle navigation">
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
      </button>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item card-user" v-if="userNick">
            <div style="margin-top:20px">
              <img v-if="!hasProfile" class="avatar" :src="url" style="width:40px;height:40px" />
            </div>
          </li>
          {{abc()}}
          <drop-down class="nav-item" :title="userNick+'님'" title-classes="nav-link" v-if="userNick">
            <router-link to="/mypage" class="nav-link">
              <i class="ti-user"></i>
              <p>마이페이지</p>
            </router-link>
            <a href="#" class="nav-link" @click="logout">
              <i class="ti-power-off"></i>
              <p>로그아웃</p>
            </a>
          </drop-down>
          <li class="nav-item" v-if="!userNick">
            <a href="#" class="nav-link" @click="login">
              <i class="ti-key"></i>
              <p>로그인</p>
            </a>
          </li>
          <li class="nav-item" v-if="!userNick">
            <router-link to="/signup" class="nav-link">
              <i class="ti-id-badge"></i>
              <p>회원가입</p>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
const storage = window.localStorage;
import http from "../../http-common.js";
import JWT from 'jwt-decode';
export default {
  computed: {
    hasProfile() {
      return false;
    },
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
    userNick: function () {
      return this.$store.state.userstore.userNick;
    },
  },
  beforeUpdate() {
    http
      .get(this.$store.state.filestore.fileUrl +
          storage.getItem("userid") +
          ".png")
      .then((res) => {
        this.url =
          this.$store.state.filestore.fileUrl +
          storage.getItem("userid") +
          ".png";
      })
      .catch(
        (e) => (this.url = this.$store.state.filestore.fileUrl + "default.png")
      );
  },
  data() {
    return {
      activeNotifications: false,
      url:
        this.$store.state.filestore.fileUrl +
        storage.getItem("userid") +
        ".png",
    };
  },
  methods: {
    abc(){
      return JWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi7Zmp7IiY7ZiEIiwiYWRtaW4iOiJmYWxzZSIsIlVzZXJfSUQiOjE0Mzg0NDQzODZ9.QgzlVW7DrjwN5NvYtSYLgXF8z0moSN_j4WYY_pl_XmY")
    },
    login() {
      document.querySelector(".login").classList.remove("active");
      document.querySelector(".login").classList.add("active");
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    logout: function () {
      this.$store.dispatch("logout");
    },
  },
};
</script>
<style scoped lang="scss">
img.nav-link {
  height: 40px;
  width: 40px;
  border-radius: 40px;
  background-color: #555;
}
a.navbar-brand {
  font-size: 1.5rem;
}
</style>

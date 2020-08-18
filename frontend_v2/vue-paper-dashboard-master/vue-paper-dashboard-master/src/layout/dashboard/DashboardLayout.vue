<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/main" name="홈" icon="ti-home" />
        <sidebar-link to="/pool" name="인재풀" icon="ti-face-smile" />
        <sidebar-link to="/recruit" name="모집" icon="ti-search" />
        <sidebar-link to="/project/myproject/" name="내 프로젝트" icon="ti-briefcase" />
        <sidebar-link to="/freeboard/type/free" name="자유게시판" icon="ti-clipboard" />
        <sidebar-link to="/freeboard/type/qa" name="질문게시판" icon="ti-help-alt" />
        <sidebar-link to="/freeboard/type/notice" name="공지사항" icon="ti-announcement" />
        <sidebar-link to="/service" name="고객센터" icon="ti-headphone-alt" />
        <sidebar-link to="/issuetest" name="이슈테스트" icon="ti-headphone-alt" />

        <!-- 서버에 올릴땐 빼주세요 -->
        <!-- <sidebar-link to="/4444" name="<<<<서버에 올릴때는 꼭 빼주세요<<" icon="ti-panel" />

        <sidebar-link to="/dashboard" name="Dashboard" icon="ti-panel" />
        <sidebar-link to="/stats" name="User Profile" icon="ti-user" />
        <sidebar-link to="/table-list" name="Table List" icon="ti-view-list-alt" />
        <sidebar-link to="/typography" name="Typography" icon="ti-text" />
        <sidebar-link to="/icons" name="Icons" icon="ti-pencil-alt2" />
        <sidebar-link to="/maps" name="Map" icon="ti-map" />
        <sidebar-link to="/notifications" name="Notifications" icon="ti-bell" />-->
      </template>
      <mobile-menu>
        <li class="nav-item" v-if="userNick">
          <drop-down
            class="nav-item"
            :title="userNick+'님'"
            title-classes="nav-link"
            v-if="userNick"
          >
            <router-link to="/mypage" class="nav-link">
              <i class="ti-user"></i>
              <p>마이페이지</p>
            </router-link>
            <a href="#" class="nav-link" @click="logout">
              <i class="ti-power-off"></i>
              <p>로그아웃</p>
            </a>
          </drop-down>
        </li>
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
        <li class="divider"></li>
      </mobile-menu>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar"></dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">
</style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu,
  },
  computed: {
    userId() {
      return this.$store.state.userstore.userid;
    },
    userNick: function () {
      return this.$store.state.userstore.userNick;
    },
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
    logout: function () {
      this.$store.dispatch("logout");
      this.$router.push("/");
    },
    login() {
      document.querySelector(".login").classList.remove("active");
      document.querySelector(".login").classList.add("active");
    },
  },
};
</script>

<style lang="scss" scoped>
img.nav-link {
  height: 40px;
  width: 40px;
  border-radius: 40px;
  background-color: #555;
}
</style>
<template>
<body id="app" class="fix-header fix-sidebar card-no-border">
  <!-- Preloader - style you can find in spinners.css -->
  <!-- <div class="preloader">
      <div class="loader">
        <div class="loader__figure"></div>
        <p class="loader__label">RE:멤버</p>
      </div>
  </div>-->

  <!-- Main wrapper - style you can find in pages.scss -->
  <div id="main-wrapper">
    <!-- Topbar header - style you can find in pages.scss -->
    <header class="topbar">
      <nav class="navbar top-navbar navbar-expand-md navbar-light">
        <!-- ============================================================== -->
        <!-- Logo -->
        <!-- ============================================================== -->
        <div class="navbar-header">
          <a class="navbar-brand" href="index.html">
            <!-- Logo icon -->
            <b>
              <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
              <!-- Dark Logo icon -->
              <img src="resources/assets/images/logo-icon.png" alt="homepage" class="dark-logo" />
              <!-- Light Logo icon -->
              <img
                src="resources/assets/images/logo-light-icon.png"
                alt="homepage"
                class="light-logo"
              />
            </b>
            <!--End Logo icon -->
            <!-- Logo text -->
            <span>
              <!-- dark Logo text -->
              <img src="resources/assets/images/logo-text.png" alt="homepage" class="dark-logo" />
              <!-- Light Logo text -->
              <img
                src="resources/assets/images/logo-light-text.png"
                class="light-logo"
                alt="homepage"
              />
            </span>
          </a>
        </div>
        <!-- ============================================================== -->
        <!-- End Logo -->
        <!-- ============================================================== -->
        <div class="navbar-collapse">
          <!-- ============================================================== -->
          <!-- toggle and nav items -->
          <!-- ============================================================== -->
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a
                class="nav-link nav-toggler hidden-md-up waves-effect waves-dark"
                href="javascript:void(0)"
              >
                <i class="fa fa-bars"></i>
              </a>
            </li>
            <!-- ============================================================== -->
            <!-- Search -->
            <!-- ============================================================== -->
            <li class="nav-item hidden-xs-down search-box">
              <a class="nav-link hidden-sm-down waves-effect waves-dark" href="javascript:void(0)">
                <i class="fa fa-search"></i>
              </a>
              <form class="app-search">
                <input type="text" class="form-control" placeholder="Search & enter" />
                <a class="srh-btn">
                  <i class="fa fa-times"></i>
                </a>
              </form>
            </li>
          </ul>
          <!-- ============================================================== -->
          <!-- User profile and search -->
          <!-- ============================================================== -->
          <ul class="navbar-nav my-lg-0">
            <!-- ============================================================== -->
            <!-- Profile -->
            <!-- ============================================================== -->

            <!-- 수현형님 햄버거쪽 -->
            <div class="dropdown" v-if="!userNick">
              <button
                class="btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >계정</button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <button class="btn btn-dark nav-link dropdown-item" @click="loginon">로그인</button>
                <router-link class="nav-link dropdown-item" :to="{name:'signup'}">회원가입</router-link>
                <router-link class="nav-link" :to="{name:'service'}">고객센터</router-link>
              </div>
            </div>
            <div class="dropdown" v-else>
              <button
                class="btn btn-primary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >{{ userNick }}님</button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <router-link class="nav-link dropdown-item" :to="{name:'user'}">user</router-link>
                <router-link class="nav-link" :to="{name:'service'}">고객센터</router-link>
                <button class="btn btn-dark nav-link dropdown-item" @click="logout">로그아웃</button>
              </div>
            </div>

            <!-- 햄버거 종료 -->

            <li class="nav-item dropdown u-pro">
              <a
                class="nav-link dropdown-toggle waves-effect waves-dark profile-pic"
                href
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                <img src="resources/assets/images/users/1.jpg" alt="user" class />
                <span class="hidden-md-down">Mark Sanders &nbsp;</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <!-- End Topbar header -->

    <!-- 여기 좌측 사이드 네비바 -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <navibar />
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->

    <!-- <sidebar /> -->

    <!-- 여기에 우리 컴포넌트 들어갑니다 -->

    <!-- Page wrapper  -->
    <div class="page-wrapper">
      <!-- Container fluid  -->
      <div class="container-fluid">
        <section class="rtViewContainer">
          <router-view></router-view>
        </section>
        <buble v-if="userNick" />
      </div>
    </div>
    <login />
    <!-- <footerr /> -->
  </div>
</body>
</template>

<!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="resources/assets/node_modules/jquery/jquery.min.js"></script>
    <!-- Bootstrap popper Core JavaScript -->
    <script src="resources/assets/node_modules/bootstrap/js/popper.min.js"></script>
    <script src="resources/assets/node_modules/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="resources/js/perfect-scrollbar.jquery.min.js"></script>
    <!--Wave Effects -->
    <script src="resources/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="resources/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="resources/js/custom.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugins -->
    <!-- ============================================================== -->
    <!--morris JavaScript -->
    <script src="resources/assets/node_modules/raphael/raphael-min.js"></script>
    <script src="resources/assets/node_modules/morrisjs/morris.min.js"></script>
    <!--c3 JavaScript -->
    <script src="resources/assets/node_modules/d3/d3.min.js"></script>
    <script src="resources/assets/node_modules/c3-master/c3.min.js"></script>
    <!-- Chart JS -->
    <script src="resources/js/dashboard1.js"></script>
<script>
import login from "./components/login.vue";
import navibar from "./components/navibar.vue";
// import sidebar from "./components/sidebar.vue";
import buble from "./components/buble.vue";
// import footerr from "./components/footer.vue";

const storage = window.sessionStorage;

export default {
  name: "App",
  components: {
    navibar,
    // sidebar,
    buble,
    // footerr,
    login,
  },
  mounted: function () {
    console.log(storage);
    this.$store.dispatch("init", {});
    if (
      storage.getItem("jwt-auth-token").length >0 &&
      storage.getItem("idvalid") !== "true"
    ) {
      this.$router.push({ name: "emailcheck" });
    }
  },
  beforeUpdate: function () {
    if (this.userNick) {
      this.$store.dispatch("update", {});
    }
    if (
      storage.getItem("jwt-auth-token").length >0 &&
      storage.getItem("idvalid") !== "true"
    ) {
      this.$router.push({ name: "emailcheck" });
    }
  },
  computed: {
    userNick: function () {
      return this.$store.state.userstore.userNick;
    },
  },
  methods: {
    update: function () {
      this.$store.dispatch("News", {});
    },
    loginon: function () {
      document.querySelector(".login").classList.toggle("active");
    },
    logout: function () {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style lang="scss">
// html,
// body {
//   margin: 0;
//   padding: 0;
//   noscript {
//     height: 0;
//   }
// }
// body {
//   padding-top: 7vh;
//   .rtViewContainer {
//     min-height: 500px;
//   }
// }
// #app {
//   color: red;
//   img {
//     width: 100%;
//     height: 250px;
//   }
//   font-family: Avenir, Helvetica, Arial, sans-serif;
//   -webkit-font-smoothing: antialiased;
//   -moz-osx-font-smoothing: grayscale;
//   text-align: center;
//   color: #2c3e50;
// }

// nav {
//   z-index: 999;
// }
textarea {
  resize: none;
}
</style>

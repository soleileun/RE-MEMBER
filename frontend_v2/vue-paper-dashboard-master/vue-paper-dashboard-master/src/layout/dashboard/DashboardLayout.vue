<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/main" name="홈" icon="ti-home" />
        <sidebar-link to="/pool" name="인재풀" icon="ti-face-smile" />
        <sidebar-link to="/recruit" name="모집" icon="ti-search" />
        <sidebar-link to="/freeboard" name="자유게시판" icon="ti-clipboard" />
        <sidebar-link to="/qaboard" name="질문게시판" icon="ti-help-alt" />
        <sidebar-link to="/notice" name="공지사항" icon="ti-announcement" />
        <sidebar-link to="/service" name="고객센터" icon="ti-headphone-alt" />
        <sidebar-link to="/profile" name="프로필" icon="ti-face-smile" />
        <sidebar-link to="/4444" name="<<<<<<<<<<<<<<<<<<<<<<<<" icon="ti-panel" />

        <sidebar-link to="/dashboard" name="Dashboard" icon="ti-panel" />
        <sidebar-link to="/stats" name="User Profile" icon="ti-user" />
        <sidebar-link to="/table-list" name="Table List" icon="ti-view-list-alt" />
        <sidebar-link to="/typography" name="Typography" icon="ti-text" />
        <sidebar-link to="/icons" name="Icons" icon="ti-pencil-alt2" />
        <sidebar-link to="/maps" name="Map" icon="ti-map" />
        <sidebar-link to="/notifications" name="Notifications" icon="ti-bell" />
      </template>
      <mobile-menu>
        <li class="nav-item">
          <a class="nav-link">
            <i class="ti-panel"></i>
            <p>Stats</p>
          </a>
        </li>
        <drop-down class="nav-item" title="5 Notifications" title-classes="nav-link" icon="ti-bell">
          <a class="dropdown-item">Notification 1</a>
          <a class="dropdown-item">Notification 2</a>
          <a class="dropdown-item">Notification 3</a>
          <a class="dropdown-item">Notification 4</a>
          <a class="dropdown-item">Another notification</a>
        </drop-down>
        <li class="nav-item">
          <a class="nav-link">
            <i class="ti-settings"></i>
            <p>Settings</p>
          </a>
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
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
  },
};
</script>

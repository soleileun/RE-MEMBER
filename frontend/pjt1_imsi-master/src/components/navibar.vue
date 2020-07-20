<template>
  <nav class="Navibar navbar navbar-expand-lg navbar-light">
    <router-link class="navbar-brand" to="/">이름을 뭘로하지</router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item dropdown">
          <router-link
            class="nav-link dropdown-toggle"
            to="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >user</router-link>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link class="dropdown-item" to="/user">user</router-link>
            <div class="dropdown-divider"></div>
            <router-link class="dropdown-item" to="/user/messenger">messenger</router-link>
          </div>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/freeboard">
            자유게시판
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/notice">
            공지
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/project">
            프로젝트
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/qaboard">
            질문게시판
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/recruit">
            모집
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/service">
            고객센터
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/signin">
            회원가입
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Navibar",
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
nav {
  display: flex;
  margin-top: 0;
  width: 100%;
  height: 7vh;
  position: fixed;
  left: 0px;
  top: 0px;
  background-color: rgba($color: white, $alpha: 0.6);
}
</style>

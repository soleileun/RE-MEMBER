<template>
  <aside class="sidebar left-sidebar">
    <div class="scroll-sidebar">
      <!-- Sidebar navigation-->
      <div class="sidebar-nav">
        <router-link class="navbar-brand" :to="{name:'mainpage'}">RE:멤버</router-link>

      
          <!-- <router-link class="nav-link" :to="{name:'service'}">고객센터</router-link> -->
        <!-- <button class="btn btn-dark nav-link dropdown-item" @click="logout">로그아웃</button> -->
      
        <ul id="sidebarnav">
          <li>
            <router-link :to="{name:'freeboard'}" class="waves-effect waves-dark" href="pages-profile.html" aria-expanded="false">
              <i class="fa fa-tachometer"></i>
              <span class="hide-menu">게시판</span>
            </router-link>
          </li>
          <li>
            <router-link :to="{name:'pool'}" class="waves-effect waves-dark" href="pages-profile.html" aria-expanded="false">
          
              <i class="fa fa-user-circle-o"></i>
              <span class="hide-menu">인재풀</span>
            </router-link>
          </li>
          <li>
            
      <router-link :to="{name:'recruit'}" class="waves-effect waves-dark" href="pages-profile.html" aria-expanded="false"> 
              <i class="fa fa-table"></i>
              <span class="hide-menu">모집</span>
            </router-link>
          </li>
          <li>
            <a class="waves-effect waves-dark" href="icon-fontawesome.html" aria-expanded="false">
              <i class="fa fa-smile-o"></i>
              <span class="hide-menu">Icons</span>
            </a>
          </li>
          <li>
            <a class="waves-effect waves-dark" href="map-google.html" aria-expanded="false">
              <i class="fa fa-globe"></i>
              <span class="hide-menu">Map</span>
            </a>
          </li>
          <li>
            <a class="waves-effect waves-dark" href="pages-blank.html" aria-expanded="false">
              <i class="fa fa-bookmark-o"></i>
              <span class="hide-menu">Blank</span>
            </a>
          </li>
          <li>
            <a class="waves-effect waves-dark" href="pages-error-404.html" aria-expanded="false">
              <i class="fa fa-question-circle"></i>
              <span class="hide-menu">404</span>
            </a>
          </li>
        </ul>
      </div>
      <!-- End Sidebar navigation -->
    </div>
  </aside>
</template>

<script>
export default {
  name: "sidebar",
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

<template>
  <div id="app">
    <navibar />
    <sidebar />
    <router-view></router-view>
    <footerr />
  </div>
</template>

<script>
import navibar from "./components/navibar.vue";
import sidebar from "./components/sidebar.vue";
import footerr from "./components/footer.vue";

export default {
  name: "App",
  components: {
    navibar,
    sidebar,
    footerr
  }
};
</script>

<style lang="scss">
html,
body {
  margin: 0;
  padding: 0;
  noscript {
    height: 0;
  }
}
body{
  padding-top: 7vh;
}
#app {
  color: red;
  img {
    width: 100%;
    height: 250px;
  }
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 3000px;
}
</style>

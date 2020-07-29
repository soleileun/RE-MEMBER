<template>
  <div id="app">
    <navibar />
    <sidebar />
    <login />
    <section class="rtViewContainer">
      <router-view></router-view>
    </section>
    <buble v-if="userNick" />
    <footerr />
  </div>
</template>

<script>
//test123
import login from "./components/login.vue";
import navibar from "./components/navibar.vue";
import sidebar from "./components/sidebar.vue";
import buble from "./components/buble.vue";
import footerr from "./components/footer.vue";

const storage = window.sessionStorage;

export default {
  name: "App",
  components: {
    navibar,
    sidebar,
    buble,
    footerr,
    login,
  },
  mounted: function () {
    console.log(storage);
    this.$store.dispatch("init", {});
  },
  beforeUpdate: function () {
    if (this.userNick) {
      this.$store.dispatch("update", {});
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
  },
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
body {
  padding-top: 7vh;
  .rtViewContainer {
    min-height: 500px;
  }
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
}

nav {
  z-index: 999;
}
textarea {
  resize: none;
}
</style>

<template>
  <div id="app">
    <navibar />
    <sidebar />
    <login />
    <section class="rtViewContainer">
      <section class="reView">
        <router-view></router-view>
      </section>
      <footerr />
    </section>
    <buble v-if="userNick" />
  </div>
</template>

<script>
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
    if (
      storage.getItem("jwt-auth-token").length > 0 &&
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
      storage.getItem("jwt-auth-token").length > 0 &&
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
#app {
  color: red;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  nav {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 999;
    width: 80vw;
    height: 7vh;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 0px 30px 0px 30px;
    margin: 0px;
    .nav-items {
      display: flex;
      align-items: center;
      padding: 0px;
      margin: 0px;
      height: 100%;
      .users {
        border-left: #777 1px solid;
        display: flex;
        align-items: center;
        height: 100%;
        div {
          width: 50px;
          height: 30px;
          border-radius: 50px;
          background-color: #777;
          margin-left: 10px;
        }
      }
    }
  }
  aside.sidebar {
    position: fixed;
    top: 0;
    left: 0;
    width: 20vw;
    height: 100vh;
    border-right: 1px gray solid;
    .navbar-brand {
      font-size: 40px;
      height: 7vh;
    }
    ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
      li {
        padding: 10px;
        width: 100%;
        text-align: left;
        height: 70px;
        font-size: 26px;
        padding-top: 20px;
        a {
          color: #777;
        }
      }
    }
  }
  .rtViewContainer {
    margin: 0px;
    padding: 0px;
    position: fixed;
    top: 7vh;
    right: 0;
    height: 93vh;
    width: 80vw;
    overflow: auto;
    .reView {
      margin: 0;
      background-color: #f3f3f3;
      min-height: 83vh;
      padding: 50px;
    }
    footer {
      margin: 0;
      height: 10vh;
    }
  }
}

textarea {
  resize: none;
}
</style>

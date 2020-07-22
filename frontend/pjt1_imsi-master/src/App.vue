<template>
  <div id="app">
    <navibar />
    <sidebar />
    <login />
    <section class="rtViewContainer">
      <router-view></router-view>
    </section>
    <buble  />
    <footerr />
  </div>
</template>

<script>
import login from "./components/login.vue";
import navibar from "./components/navibar.vue";
import sidebar from "./components/sidebar.vue";
import buble from "./components/buble.vue";
import footerr from "./components/footer.vue";

export default {
  name: "App",
  components: {
    navibar,
    sidebar,
    buble,
    footerr,
    login
  },
  data: () => {
    return {};
  },
  mounted: function() {
    // let form = new FormData();
    // form.append("id", this.pwID);
    // form.append("name", this.pwname);
    // if (this.pwradio === "phone") {
    //   form.append("method", "phone");
    // } else {
    //   form.append("method", "email");
    // }
    // axios({
    //   method: "post",
    //   url: "초기요청 url",
    //   data: form,
    //   responseType: "json"
    // })
    //   .then(response => {
    //     console.log(response.data);
    //   })
    //   .catch(e => {
    //     console.log(e);
    //   });
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
body {
  padding-top: 7vh;
  section {
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

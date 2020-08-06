<template>
  <div :class="{'nav-open': $sidebar.showSidebar}">
    <notifications></notifications>
    <router-view></router-view>
    <login />
    <buble v-if="userNick" />
  </div>
</template>

<script>
import login from "./components/login.vue";
import buble from "./components/buble.vue";
const storage = window.sessionStorage;
export default {
  name: "App",
  components: {
    login,
    buble,
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
body{
  overflow-x: hidden;
}
.vue-notifyjs.notifications {
  .alert {
    z-index: 10000;
  }
  .list-move {
    transition: transform 0.3s, opacity 0.4s;
  }
  .list-item {
    display: inline-block;
    margin-right: 10px;
  }
  .list-enter-active {
    transition: transform 0.2s ease-in, opacity 0.4s ease-in;
  }
  .list-leave-active {
    transition: transform 1s ease-out, opacity 0.4s ease-out;
  }

  .list-enter {
    opacity: 0;
    transform: scale(1.1);
  }
  .list-leave-to {
    opacity: 0;
    transform: scale(1.2, 0.7);
  }
}
</style>

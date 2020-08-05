<template>
  <nav class="navibar top-navbar navbar-expand-md navbar-light">
    <i class="fas fa-search nav-items fa-2x"></i>
    <div class="nav-items">
      <div class="id" v-if="!userNick">
        <button class="btn btn-dark nav-link dropdown-item" @click="loginon">로그인</button>
        <router-link class="nav-link dropdown-item" :to="{name:'signup'}">회원가입</router-link>
      </div>
      <div class="users" v-else>
        <div class="imgs"></div>
        <router-link class="nav-link dropdown-item" :to="{name:'user'}">user</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "navibar",
  //햄버거
  mounted: function () {
    document.querySelector(".hamburg").addEventListener("click", () => {
      document.querySelector(".nav-items").classList.toggle("active");
      document.querySelector(".hamburg").classList.toggle("active");
    });
    document.querySelectorAll(".nav-link").forEach(function (e) {
      e.addEventListener("click", () => {
        document.querySelector(".nav-items").classList.toggle("active");
        document.querySelector(".hamburg").classList.toggle("active");
      });
    });
  },
  computed: {
    userNick: function () {
      return this.$store.state.userstore.userNick;
    },
  },
  methods: {
    loginon: function () {
      document.querySelector(".login").classList.toggle("active");
    },
    logout: function () {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// @import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo&family=Sunflower:wght@300&display=swap");
// @mixin font-hyeon {
//   font-family: "Do Hyeon", sans-serif;
//   font-weight: 400;
// }
// @mixin font-myung {
//   font-family: "Nanum Myeongjo", serif;
//   font-weight: 400;
// }
// @mixin font-sunflower {
//   font-family: "Sunflower", sans-serif;
//   font-weight: 300;
//   // font-style: li;
// }
// @mixin flexrow($jty-con: space-around) {
//   display: flex;
//   flex-direction: row;
//   justify-content: $jty-con;
//   align-items: center;
// }
// @mixin flexcol($jty-con: space-around) {
//   display: flex;
//   flex-direction: column;
//   justify-content: $jty-con;
//   align-items: center;
// }
// nav {
//   box-sizing: border-box;
//   position: fixed;
//   top: 0px;
//   width: 130vw;
//   height: 7vh;
//   left: -15vw;
//   @include flexrow;
//   @include font-hyeon;
//   font-size: 16px;
//   z-index: 100;
//   background-color: rgba($color: white, $alpha: 0.75);
//   .nav-brand {
//     padding-top: 6px;
//   }
//   .nav-items a {
//     margin: 0px 2vw 0px 2vw;
//     color: #888;
//     text-decoration: none;
//   }
// }
// @media (max-width: 768px) {
//   nav {
//     .nav-items {
//       display: none;
//     }
//     .hamburg {
//       display: block;
//       color: #666;
//       -webkit-animation-duration: 0.7s;
//       -webkit-animation-name: turn2;
//       animation-duration: 0.7s;
//       animation-name: turn2;
//       &:hover {
//         cursor: pointer;
//       }
//       &.active {
//         -webkit-transform: rotate(45deg);
//         transform: rotate(45deg);
//         -webkit-animation-duration: 0.7s;
//         -webkit-animation-name: turn1;
//         animation-duration: 0.7s;
//         animation-name: turn1;
//       }
//     }
//     .nav-items.active {
//       @include flexcol();
//       position: fixed;
//       top: 7vh;
//       width: 100%;
//       background-color: rgba($color: white, $alpha: 0.9);
//       padding: 10px;
//     }
//   }
// }
// @media (min-width: 768px) {
//   nav {
//     padding: 2vh 15vw 0px 15vw;
//     .nav-items {
//       @include flexrow;
//       color: black;
//     }
//     .hamburg {
//       display: none;
//     }
//   }
// }
// @keyframes turn1 {
//   from {
//     transform: rotate(0deg);
//   }
//   to {
//     transform: rotate(45deg);
//   }
// }
// @keyframes turn2 {
//   from {
//     transform: rotate(45deg);
//   }
//   to {
//     transform: rotate(0deg);
//   }
// }
</style>

<template>
  <div class="login">
    <i class="far fa-times-circle fa-3x" @click="loginexit"></i>
    <div>
      아이디 :
      <input class="id" v-model="id" type="text" />
      <br />비밀번호 :
      <input class="id" v-model="pw" type="password" />
      <br />
      <button @click="goLogin">로그인</button>
      <span @click="loginexit">
        <router-link :to="{name:'signup'}">회원가입</router-link>
        <br>
        <router-link :to="{name:'findIDPW'}">아이디 / 비밀번호 찾기</router-link>

      </span>
      <div v-if="error">아이디와 비밀번호를 모두 입력해주세요</div>
    </div>
  </div>
</template>

<script>
// import Axios from 'axios';

export default {
  name: "login",
  data: function() {
    return {
      id: "",
      pw: "",
      error: false,
      logining: false,
      findform: false
    };
  },
  mounted: function() {},
  methods: {
    goLogin: function() {
      if (!this.logining) {
        this.logining = true;
        if (this.id == "" || this.pw == "") {
          this.error = true;
        } else {
          this.error = false;
          let form = new FormData();
          form.append("id", this.id);
          form.append("pw", this.pw);
          console.log(form);
          document.querySelector(".login").classList.toggle("turn");
          setTimeout(() => {
            document.querySelector(".login").classList.toggle("turn");
            this.logining = false;
          }, 1000);
        }
      }
    },
    loginexit: function() {
      document.querySelector(".login").classList.toggle("active");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.login {
  visibility: hidden;
  height: 0;
  width: 0;
}
.login.active {
  padding: 5vh;
  visibility: visible;
  position: fixed;
  z-index: 99999;
  top: 25vh;
  left: 25vw;
  width: 50vw;
  height: 60vh;
  background-color: rgba($color: white, $alpha: 0.9);
  border: black 1.5px solid;
  i {
    position: fixed;
    top: 27vh;
    right: 26vw;
    width: 50px;
    &:hover {
      cursor: pointer;
    }
  }
}
@media (max-width: 768px) {
  .login.active {
    width: 100vw;
    height: 100vh;
    top: 5px;
    left: 5px;
    i {
      top: 2vh;
      right: 0px;
    }
  }
}
.login.turn {
  animation-name: turn;
  animation-duration: 0.5s;
}
@keyframes turn {
  from {
    transform: rotateY(0deg);
  }
  49% {
    transform: rotateY(90deg);
  }
  50% {
    transform: rotateY(-90deg);
  }
  to {
    transform: rotateY(0deg);
  }
}
</style>

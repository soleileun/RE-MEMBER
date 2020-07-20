<template>
  <div class="login">
    <i class="far fa-times-circle fa-3x"></i>
    아이디 :
    <input class="id" v-model="id" type="text" />
    <br />비밀번호 :
    <input class="id" v-model="pw" type="password" />
    <br />
    <button @click="goLogin">로그인</button>
    <findID />
    <findPW />
    <div v-if="error">아이디와 비밀번호를 모두 입력해주세요</div>
  </div>
</template>

<script>
import findID from "./user/findID.vue";
import findPW from "./user/findPW.vue";
// import Axios from 'axios';
export default {
  name: "login",
  components:{
    findID,
    findPW
  },
  data: function() {
    return {
      id: "",
      pw: "",
      error: false,
      logining: false
    };
  },
  mounted: function (){
    document.querySelector(".fa-times-circle").addEventListener("click", () => {
      document.querySelector(".login").classList.toggle("active");
    });
  },
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
  left: 30vw;
  width: 40vw;
  height: 50vh;
  background-color: rgba($color: white, $alpha: 0.9);
  border: black 1.5px solid;
  i {
    margin-top: -3vh;
    float: right;
    &:hover {
      cursor: pointer;
    }
  }
}
@media (max-width: 768px) {
  .login.active {
    width: 100vw;
    height: 100vh;
    top: 0;
    left: 0;
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

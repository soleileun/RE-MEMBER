<template>
  <card class="login card" title="로그인">
    <i class="ti-close" @click="loginexit"></i>
    <div>
      <form @submit.prevent>
        <div class="row">
          <div class="col-md-12" @keyup.enter="goLogin">
            <fg-input id="mi" type="text" label="아이디" placeholder="E-mail" v-model="id"></fg-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12" @keyup.enter="goLogin">
            <fg-input type="password" label="비밀번호" placeholder="password" v-model="pw"></fg-input>
          </div>
        </div>
        <strong>{{error}}</strong>
        <br />
        <div class="row">
          <div class="col-md-12">
            자동 로그인 :
            <input type="checkbox" v-model="autologin" data-tooltip-text="비밀번호가 브라우저에 저장되어 취약하므로 개인 PC에서만 사용하세요" />
          </div>
        </div>
        <div @click="loginexit">
          <router-link to="/signup">회원가입</router-link>
        </div>
        <div @click="loginexit">
          <router-link to="/user/findid">아이디 찾기</router-link>
        </div>
        <div class="text-center">
          <p-button type="info" round @click.native.prevent="goLogin">로그인</p-button>
        </div>
        <div @click="kakao">
          <KakaoLogin api-key="8e0034110bcf020d70bf38e4fe0f3fb7" image="kakao_account_login_btn_medium_narrow_ov" :on-success="onSuccess" :on-failure="onFailure" />
        </div>
        <!-- <a id="kakao-login-btn"></a> -->

        <!-- <GoogleLogin
          class="big-button"
          :params="params"
          :onSuccess="GoogleLoingSuccess"
          :onFailure="GoogleLoginFailure"
        >구글 로그인</GoogleLogin>-->

        <div class="clearfix"></div>
      </form>
    </div>
  </card>
</template>



<script>
const storage = window.sessionStorage;
import KakaoLogin from "vue-kakao-login";
export default {
  name: "login",
  components: {
    KakaoLogin,
  },
  data: function () {
    return {
      autologin: false,
      id: "",
      pw: "",
    };
  },
  computed: {
    logining: function () {
      return this.$store.state.userstore.logining;
    },
    error: function () {
      return this.$store.state.userstore.loginError;
    },
  },
  mounted() {
    storage.setItem("kakao", false);
  },
  props: {},
  methods: {
    kakao() {
      if (storage.getItem("kakao") === "true" &&storage.getItem("kakaoid").length>0 ) {
        setTimeout(() => {
          this.$store.dispatch("kakao", {
            kakaoid: storage.getItem("kakaoid"),
            email: storage.getItem("kakaoemail"),
          });
          setTimeout(() => {
            storage.setItem("kakao", false);
            storage.setItem("kakaoid", "");
            storage.setItem("kakaoemail", "");
          }, 500);
        }, 500);
      } else {
        setTimeout(() => {
          this.kakao();
        }, 500);
      }
    },
    onSuccess: (data) => {
      storage.setItem("kakaoid",'')
      Kakao.API.request({
        url: "/v2/user/me",
        success: function (response) {
          storage.setItem("kakao", "true");
          storage.setItem("kakaoid", response.id);
          storage.setItem("kakaoemail", response.kakao_account.email);
        },
        fail: function (error) {
        },
      });
    },
    onFailure: (data) => {
    },

    goLogin: function () {
      if (this.id === "" || this.pw === "") {
        this.$store.commit("loginError", {
          e: "아이디와 비밀번호를 모두 입력해 주세요",
        });
      } else if (!this.$store.state.userstore.logining) {
        if (this.autologin) {
          window.localStorage.setItem("id", this.id);
          window.localStorage.setItem("autologin", "t");
          window.localStorage.setItem("pw", this.pw);
        } else {
          window.localStorage.setItem("autologin", "f");
          window.localStorage.setItem("pw", "");
        }
        this.$store.state.userstore.logining = true;
        this.$store.dispatch("login", { id: this.id, pw: this.pw });
      }
    },
    loginexit: function () {
      document.querySelector(".login").classList.remove("active");
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.login {
  visibility: hidden;
  height: 0;
  width: 0;
  position: fixed;
  top: 0;
  left: 0;
}
.login.active {
  padding: 5vh;
  visibility: visible;
  position: fixed;
  z-index: 99999;
  top: 10%;
  left: 25vw;
  width: 50vw;
  height: 500px;
  background-color: white;
  border: black 1.5px solid;
  i {
    position: fixed;
    top: 13%;
    right: 28vw;
    width: 1rem;
    font-size: 1rem;
    &:hover {
      cursor: pointer;
      border-radius: 50px;
      background-color: #aaa;
    }
  }
  [data-tooltip-text]:hover {
    position: relative;
    &:after {
      content: attr(data-tooltip-text);
      position: absolute;
      bottom: 100%;
      height: 50px;
      width: 300px;
      left: 0;
      padding: 10px;
      border: 1px rgb(255, 215, 105) solid;
      background-color: rgba(0, 0, 0, 0.8);
      color: #ffffff;
      font-size: 12px;

      z-index: 9999;
    }
  }
}
@media (max-width: 768px) {
  .login.active {
    box-sizing: border-box;
    width: 100vw;
    height: 100vh;
    top: 0px;
    left: 0px;
    i {
      top: 2vh;
      right: 30px;
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

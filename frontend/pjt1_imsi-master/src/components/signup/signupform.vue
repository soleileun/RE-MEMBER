<template>
  <div class="signupform">
    <br />아이디 :
    <input v-model="id" type="text" />
    <br />
    {{error.id}}
    <br />비밀번호 :
    <input v-model="pw" type="password" />
    <br />
    {{error.pw}}
    <br />비밀번호 확인 :
    <input v-model="pw2" type="password" />
    <br />
    {{error.pw2}}
    <br />닉네임(별명) :
    <input v-model="nickname" maxlength="10" type="text" />
    <br />이름(국문) :
    <input v-model="name" maxlength="10" type="text" />
    <br />주소1 :
    <input v-model="address1" type="text" />
    <br />주소2 :
    <input v-model="address2" type="text" />
    <br />전화번호 :
    <input v-model="phone0" maxlength="3" type="tel" />-
    <input id="p1" v-model="phone1" maxlength="4" type="tel" />-
    <input id="p2" v-model="phone2" maxlength="4" type="tel" />
    <br />깃주소 :
    <input v-model="git" type="text" />
    <br />역할 :
    <input v-model="responsibility" type="radio" value="개발" /> 개발 |
    <input v-model="responsibility" type="radio" value="디자인" /> 디자인 |
    <input v-model="responsibility" type="radio" value="기획" /> 기획
    <br />
    <button @click="goSignin" class="gosignin" :class="{submitable:submitable}">회원가입</button>
  </div>
</template>


<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import http from "@/http-common.js";
// import Daum from "./address.html"

export default {
  name: "signupform",
  components:{
    // Daum
  },
  created() {
    this.pwSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters()
      .has()
      .not()
      .spaces()
      .has()
      .not()
      .symbols();
  },
  data: function () {
    return {
      id: "",
      pw: "",
      pwSchema: new PV(),
      pw2: "",
      nickname: "",
      name: "",
      address1: "",
      address2: "",
      phone0: "",
      phone1: "",
      phone2: "",
      git: "",
      submitable: false,
      error: {
        id: "",
        pw: "",
        pw2: "",
      },
      responsibility: "",
    };
  },
  watch: {
    pw: function () {
      this.checkpw();
    },
    pw2: function () {
      this.checkpw();
    },
    id: function () {
      this.checkid();
    },
    name: function () {
      this.name = this.name.replace(/[^가-힣]/g, "");
    },
    nickname: function () {
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎㅏ-ㅣ]/g, "");
    },
    phone0: function () {
      this.phone0 = this.phone0.replace(/[^0-9]/g, "");
      if (this.phone0.length === 3) {
        document.querySelector("#p1").focus();
      }
    },
    phone1: function () {
      this.phone1 = this.phone1.replace(/[^0-9]/g, "");
      if (this.phone1.length === 4) {
        document.querySelector("#p2").focus();
      }
    },
    phone2: function () {
      this.phone2 = this.phone2.replace(/[^0-9]/g, "");
    },
  },
  methods: {
    checkpw() {
      if (this.pw.length >= 0 && !this.pwSchema.validate(this.pw))
        this.error.pw =
          "영문, 숫자로 이루어진 8 자리 이상, 20자 미만이어야 합니다.";
      else this.error.pw = "";
      if (this.pw !== this.pw2)
        this.error.pw2 = "비밀번호가 일치하지 않습니다.";
      else this.error.pw2 = "";
      this.submitable = true;
      Object.values(this.error).map((v) => {
        if (v) this.submitable = false;
      });
    },
    checkid() {
      if (this.id.length >= 0 && !EmailValidator.validate(this.id))
        this.error.id = "아이디로 이메일을 작성해주세요";
      else this.error.id = "";
      this.submitable = true;
      Object.values(this.error).map((v) => {
        if (v) this.submitable = false;
      });
    },
    goSignin: function () {
      http
        .post("/api/userinfo/", {
          id: this.id,
          pw: this.pw,
          nickname: this.nickname,
          name: this.name,
          address1: this.address1,
          address2: this.address2,
          phone: `${this.phone0}-${this.phone1}-${this.phone2}`,
          git: this.git,
          responsibility: this.responsibility,
        })
        .then((res) => {
          console.log(res);
          this.$store.dispatch("login", { id: this.id, pw: this.pw });
        })
        .catch((e) => console.log(e));
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.gosignin {
  opacity: 0.2;
}
.submitable {
  opacity: 1;
}
</style>

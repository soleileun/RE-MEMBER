<template>
  <div class="signupform">
    <br />아이디 :
    <input class="id" v-model="id" type="text" />
    <br />비밀번호 :
    <input class="id" v-model="pw" type="password" />
    <br />
    {{error.pw}}
    <br />비밀번호 확인 :
    <input class="id" v-model="pw2" type="password" />
    <br />
    {{error.pw2}}
    <br />닉네임(별명) :
    <input class="id" v-model="nickname" type="text" />
    <br />이름(실명) :
    <input class="id" v-model="name" type="text" />
    <br />주소1 :
    <input class="id" v-model="address1" type="text" />
    <br />주소2 :
    <input class="id" v-model="address2" type="text" />
    <br />전화번호 :
    <input class="id" v-model="phone" type="text" />
    <br />이메일주소 :
    <input class="id" v-model="email" type="text" />
    <br />
    {{error.email}}
    <br />깃주소 :
    <input class="id" v-model="git" type="text" />
    <br />
    <button @click="goSignin" class="gosignin" :class="{submitable:submitable}">회원가입</button>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";

export default {
  name: "signupform",
  created() {
    this.pwSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  data: function() {
    return {
      id: "",
      pw: "",
      pwSchema: new PV(),
      pw2: "",
      nickname: "",
      name: "",
      address1: "",
      address2: "",
      phone: "",
      email: "",
      git: "",
      submitable: false,
      error: {
        email: '',
        pw: '',
        pw2: ''
      }
    };
  },
  watch: {
    pw: function() {
      this.checkForm();
    },
    pw2: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = '';

      if (this.pw.length >= 0 && !this.pwSchema.validate(this.pw))
        this.error.pw = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.pw = '';

      if (this.pw !== this.pw2)
        this.error.pw2 = "비밀번호가 일치하지 않습니다.";
      else this.error.pw2 = '';

      let submitable = true;
      Object.values(this.error).map(v => {
        if (v) submitable = false;
      });
      this.submitable = submitable;
    },
    goSignin: function() {
      let form = new FormData();
      form.append("id", this.id);
      form.append("pw", this.pw);
      form.append("pw2", this.pw2);
      form.append("nickname", this.nickname);
      form.append("name", this.name);
      form.append("address1", this.address1);
      form.append("address2", this.address2);
      form.append("phone", this.phone);
      form.append("email", this.email);
      form.append("git", this.git);
      console.log(form);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.gosignin{
  opacity: .2;
}
.submitable{
  opacity: 1;
}
</style>

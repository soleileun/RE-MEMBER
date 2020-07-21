<template>
  <div class="signupform">
    <form action=""><input type="checkbox"></form>
    <button @click="goSignin" class="gosignin" :class="{submitable:submitable}">선택 완료</button>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from 'axios';

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
      axios({
        method: "post",
        url: "회원가입 url",
        data: form,
        responseType: "json"
      })
        .then(response => {
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
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

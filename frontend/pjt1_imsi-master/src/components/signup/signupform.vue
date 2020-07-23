<template>
  <div class="signupform">
    <br />아이디 :
    <input class="id" v-model="id" type="text" />
    <br />
    {{error.id}}
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
    <br />이름(국문) :
    <input class="id" v-model="name" type="text" />
    <br />주소1 :
    <input class="id" v-model="address1" type="text" />
    <br />주소2 :
    <input class="id" v-model="address2" type="text" />
    <br />전화번호 :
    <input class="id" v-model="phone0" type="text"  />-
    <input class="id" id="p1" v-model="phone1" type="text" />-
    <input class="id" id="p2" v-model="phone2" type="text"/>
    <br />깃주소 :
    <input class="id" v-model="git" type="text" />
    <br />sadasdfasdf :
    <input class="id" v-model="responsibility" type="text" />
    <br />
    <button @click="goSignin" class="gosignin" :class="{submitable:submitable}">회원가입</button>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import http from "@/http-common.js";

export default {
  name: "signupform",
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
      phone0:"",
      phone1:"",
      phone2:"",
      git: "",
      submitable: false,
      error: {
        id: "",
        pw: "",
        pw2: ""
      },
      responsibility: ""
    };
  },
  watch: {
    pw: function() {
      this.checkpw();
    },
    pw2: function() {
      this.checkpw();
    },
    id: function() {
      this.checkid();
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
      Object.values(this.error).map(v => {
        if (v) this.submitable = false;
      });
    },
    checkid() {
      if (this.id.length >= 0 && !EmailValidator.validate(this.id))
        this.error.id = "아이디로 이메일을 작성해주세요";
      else this.error.id = "";
      this.submitable = true;
      Object.values(this.error).map(v => {
        if (v) this.submitable = false;
      });
    },
    // checkPhone0: function(t){
    //   t=t.replace(/[^0-9]/g,'');
    //   console.log(t)
    //   if (t=='0' && t.length >=3){
    //     t=t.slice(0, 3)
    //     document.querySelector('#p1').focus();
    //   }
    //   else if (t=='1' && t.length >=4){
    //     t = t.slice(0, 4)
    //     document.querySelector('#p2').focus();
    //   }
    // },
    goSignin: function() {
      http
        .post("/api/userinfo/", {
          id: this.id,
          pw: this.pw,
          nickname: this.nickname,
          name: this.name,
          address1: this.address1,
          address2: this.address2,
          phone: this.phone0 + "-" + this.phone1 + "-" + this.phone2,
          git: this.git,
          responsibility: this.responsibility
        })
        .then(res => {
          console.log(res);
          // this.$router.push({ name: "user" });
        });
    }
  }
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

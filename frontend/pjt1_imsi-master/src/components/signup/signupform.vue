<template>
  <div class="signupform">
    <span>
      이메일(아이디) :
      <input v-model="id" type="text" />
      {{error.id}}
    </span>
    <span>
      비밀번호 :
      <input v-model="pw" type="password" />
      {{error.pw}}
    </span>
    <span>
      비밀번호 확인 :
      <input v-model="pw2" type="password" />
      {{error.pw2}}
    </span>
    <span>
      닉네임(별명) :
      <input v-model="nickname" maxlength="10" type="text" />
      {{error.nickname}}
    </span>
    <span>
      이름(국문) :
      <input v-model="name" maxlength="10" type="text" />
      {{error.name}}
    </span>
    <span>
      주소 :
      <input v-model="address1" type="text" disabled />
      <button @click="postActive">검색</button>
      {{error.address}}
    </span>
    <div class="postcode" v-if="postAct">
      <DaumPostcode :on-complete="handleAddress" />
    </div>
    <span>
      추가 주소 :
      <input v-model="address2" type="text" />
    </span>

    <span>
      전화번호 :
      <input v-model="phone0" maxlength="3" type="tel" />-
      <input id="p1" v-model="phone1" maxlength="4" type="tel" />-
      <input id="p2" v-model="phone2" maxlength="4" type="tel" />
      {{error.phone}}
    </span>

    <span>
      깃주소 :
      <input v-model="git" type="text" />
    </span>

    <span>
      역할 :
      <input v-model="responsibility" type="radio" value="개발" /> 개발 |
      <input v-model="responsibility" type="radio" value="디자인" /> 디자인 |
      <input v-model="responsibility" type="radio" value="기획" />
      기획{{error.responsibility}}
    </span>
    <hr />
    <signupInterest :signup="submitable" @goSignup="goSignup" />
  </div>
</template>


<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import http from "@/http-common.js";
import DaumPostcode from "vuejs-daum-postcode";
import signupInterest from "./signupInterest.vue";

export default {
  name: "signupform",
  components: {
    DaumPostcode,
    signupInterest,
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
      fullAddress: "",
      phone0: "",
      phone1: "",
      phone2: "",
      git: "",
      submitable: 0,
      postAct: false,
      error: {
        id: "",
        pw: "",
        pw2: "",
        nickname: "",
        name: "",
        address: "",
        phone: "",
        responsibility: "",
      },
      responsibility: "",
    };
  },
  watch: {
    pw: function () {
      this.checker();
    },
    pw2: function () {
      this.checker();
    },
    id: function () {
      this.checker();
    },
    name: function () {
      this.checker();
      this.name = this.name.replace(/[^가-힣]/g, "");
    },
    nickname: function () {
      this.checker();
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎㅏ-ㅣ]/g, "");
    },
    phone0: function () {
      this.phone0 = this.phone0.replace(/[^0-9]/g, "");
      if (this.phone0.length === 3) {
        document.querySelector("#p1").focus();
      }
      this.checker();
    },
    phone1: function () {
      this.phone1 = this.phone1.replace(/[^0-9]/g, "");
      if (this.phone1.length === 4) {
        document.querySelector("#p2").focus();
      }
      this.checker();
    },
    phone2: function () {
      this.phone2 = this.phone2.replace(/[^0-9]/g, "");
      this.checker();
    },
    address1: function () {
      this.checker();
    },
    responsibility: function () {
      this.checker();
    },
  },
  methods: {
    handleAddress: function (data) {
      let fullAddress = data.address;
      let extraAddress = "";
      if (data.addressType === "R") {
        if (data.bname !== "") {
          extraAddress += data.bname;
        }
        if (data.buildingName !== "") {
          extraAddress +=
            extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
        }
        // fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
      }
      this.address1 = fullAddress;
      this.address2 = extraAddress;
      this.postAct = false;
    },
    checker() {
      //     id: "",
      if (!EmailValidator.validate(this.id)) {
        this.error.id = "이메일 형식이 아닙니다.";
      } else {
        this.error.id = "";
      }
      //     pw: "",
      //     pw2: "",
      if (!this.pwSchema.validate(this.pw)) {
        this.error.pw =
          "영문, 숫자로 이루어진 8 자리 이상, 20자 미만이어야 합니다.";
      } else {
        this.error.pw = "";
      }
      if (this.pw !== this.pw2) {
        this.error.pw2 = "비밀번호가 일치하지 않습니다.";
      } else {
        this.error.pw2 = "";
      }
      //     nickname: "",
      if (this.nickname === "") {
        this.error.nickname = "필수 항목입니다.";
      } else {
        this.error.nickname = "";
      }
      //     name: "",
      if (this.name === "") {
        this.error.name = "필수 항목입니다.";
      } else {
        this.error.name = "";
      }
      //     address: "",
      if (this.address1 === "") {
        this.error.address = "필수 항목입니다.";
      } else {
        this.error.address = "";
      }
      //     phone: "",
      if (
        this.phone0.length !== 3 ||
        this.phone1.length < 3 ||
        this.phone2.length !== 4
      ) {
        this.error.phone = "필수 항목입니다.";
      } else {
        this.error.phone = "";
      }
      //     responsibility: "",
      if (this.responsibility !== "") {
        this.error.responsibility = "";
        } else {
        this.error.responsibility = "필수 항목입니다.";
      }
      if (Object.values(this.error).join('') === "") {
        this.submitable = 1;
      } else {
        this.submitable = 0;
      }
    },
    goSignup: function () {
      console.log(123);
      if (!this.submitable) {
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
      }
    },
    postActive: function () {
      this.postAct = true;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.signupform {
  display: flex;
  flex-direction: column;
  align-items: center;
  .postcode {
    height: 300px;
    width: 500px;
    border: 2px black solid;
    overflow-y: scroll;
  }
  .gosignin {
    opacity: 0.2;
  }
  .submitable {
    opacity: 1;
  }
}
</style>

<template>
  <card class="signupform card">
    <div>
      <form @submit.prevent class="forms">
        <div class="row">
          <h2>회원 정보 입력</h2>
          <div class="col-md-12">
            <br />
          </div>
          <div class="col-md-8">
            이메일(아이디)
            <fg-input type="text" placeholder="example@naver.com" v-model="id"></fg-input>
          </div>
          <div class="col-md-4">
            <br />
            <button class="btn btn-info btn-round" @click="jungbok">중복조회</button>
          </div>
          <div class="col-md-12">
            {{error.id}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            비밀번호
            <fg-input type="password" placeholder="영문/숫자로 구성된 8-20자" v-model="pw"></fg-input>
            {{error.pw}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            비밀번호 확인
            <fg-input type="password" placeholder="비밀번호를 한번 더 입력해주세요" v-model="pw2"></fg-input>
            {{error.pw2}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            이름
            <fg-input type="text" placeholder="실명" v-model="name"></fg-input>
          </div>
          <div class="col-md-6">
            닉네임
            <fg-input type="text" placeholder="영문/한글/숫자만 입력 가능" v-model="nickname"></fg-input>
          </div>
          <div class="col-md-6">
            {{error.name}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            {{error.nickname}}
            <br />
            <br />
          </div>

          <div class="col-md-5">
            주소
            <fg-input type="text" placeholder="검색 버튼을 눌러주세요" v-model="address1" :disabled="true"></fg-input>
          </div>
          <div class="col-md-5">
            상세주소
            <fg-input type="text" placeholder="건물 호수" v-model="address3"></fg-input>
          </div>
          <div class="col-md-2">
            <br />
            <button class="btn btn-primary" @click="postActive">주소</button>
          </div>
          <div class="col-12 postcode" v-if="postAct">
            <DaumPostcode :on-complete="handleAddress" />
          </div>
          <div class="col-12">
            {{error.address}}
            <br />
            <br />
          </div>
          <div class="col-4">
            전화번호
            <fg-input type="text" maxlength="3" placeholder="010" v-model="phone0"></fg-input>
            {{error.phone}}
            <br />
            <br />
          </div>
          <div class="col-4">
            <br />
            <fg-input id="p1" type="text" maxlength="4" placeholder="0000" v-model="phone1"></fg-input>
          </div>
          <div class="col-4">
            <br />
            <fg-input id="p2" type="text" maxlength="4" placeholder="0000" v-model="phone2"></fg-input>
          </div>
          <div class="col-3">
            깃 주소
            <br />
            <button class="btn">https://github.com/</button>
          </div>
          <div class="col-9">
            <br />
            <fg-input type="text" placeholder="example" v-model="git"></fg-input>
          </div>
          <div class="col-12">
            포지션
            <br />
            <div class="checks">
              <span class="btn btn-primary" @click="chek('개발')">
                <input v-model="responsibility" type="radio" value="개발" /> 개발
              </span>
              <span class="btn btn-primary" @click="chek('디자인')">
                <input v-model="responsibility" type="radio" value="디자인" /> 디자인
              </span>
              <span class="btn btn-primary" @click="chek('기획')">
                <input v-model="responsibility" type="radio" value="기획" />
                기획
              </span>
              <br />
              {{error.responsibility}}
              <br />
              <br />
            </div>
          </div>
        </div>
      </form>
    </div>
    <signupInterest :signup="submitable" @goSignup="goSignup" />
  </card>
  <!--<span>
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
  </div>-->
</template>


<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import DaumPostcode from "vuejs-daum-postcode";
import signupInterest from "./signupInterest.vue";
import http from "../../http-common.js";

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
      address3: "",
      fullAddress: "",
      phone0: "",
      phone1: "",
      phone2: "",
      git: "",
      submitable: false,
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
      jungboks: false,
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
      this.jungboks = false;
      this.checker();
    },
    name: function () {
      this.checker();
      setTimeout(()=>{
        this.name = this.name.replace(/[^가-힣]/g, "");
      },500)
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
    jungbok() {
      if (this.error.id === "" && this.id !== "") {
        http
          .get("/api/userinfo/")
          .then((res) => {
            if (
              !res.data.find((item) => {
                if (item.id === this.id) {
                  alert(`이미 가입된 이메일입니다.`);
                  this.jungboks = false;
                  return true;
                }
              })
            ) {
              alert("사용 가능한 이메일입니다.");
              this.jungboks = true;
            }
          })
          .catch((exp) => console.log("서버가 불안정합니다." + exp));
      }
    },
    chek: function (a) {
      this.responsibility = a;
    },
    handleAddress: function (data) {
      let fullAddress = data.address;
      let extraAddress = "";
      if (data.addressType === "R") {
        if (data.bname !== "") {
          extraAddress += data.bname;
        }
        // fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
      }
      this.address1 = fullAddress;
      this.address2 =
        fullAddress.split(" ").slice(0, -2).join(" ") + " " + extraAddress;
      this.postAct = false;
    },
    checker() {
      //     id: "",
      if (!EmailValidator.validate(this.id)||this.id.split('.')[1].length>3) {
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
      if (Object.values(this.error).join("") === "") {
        this.submitable = true;
      } else {
        this.submitable = false;
      }
    },
    goSignup: function () {
      if (!this.jungboks) {
        alert("아이디 중복조회를 하세요");
      } else if (this.submitable) {
        this.$store.dispatch("signup", {
          id: this.id,
          pw: this.pw,
          nickname: this.nickname,
          name: this.name,
          address1: this.address1,
          address2: this.address2,
          phone: `${this.phone0}-${this.phone1}-${this.phone2}`,
          git: this.git,
          responsibility: this.responsibility,
        });
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
  background-color: #bbb;
}
.forms {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  .control-label {
    color: white;
  }
  input {
    border: 1px black solid;
  }
  .postcode {
    border: 2px black solid;
    padding: 0;
  }
  .gosignin {
    opacity: 0.2;
  }
  .submitable {
    opacity: 1;
  }
  .checks {
    display: inline;
    span {
      margin: 10px;
    }
  }
}
</style>

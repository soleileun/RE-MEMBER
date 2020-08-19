<template>
  <card class="signupform card">
    <img id="spinner1122" src="@/assets/img/spinner.png" />
    <div>
      <span style="position:fixed;top:-100px">{{nothing}}</span>
      <form @submit.prevent class="forms">
        <div class="row">
          <h2>회원 정보 입력</h2>
          <div class="col-md-12">
            <br />
          </div>
          <div class="col-md-8">
            <span class="reddot">*</span>
            이메일(아이디)
            <fg-input id="emailid" type="text" placeholder="example@naver.com" v-model="id"></fg-input>
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
            <span class="reddot">*</span>
            비밀번호
            <fg-input type="password" placeholder="영문/숫자로 구성된 8-20자" v-model="pw"></fg-input>
            {{error.pw}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            <span class="reddot">*</span>
            비밀번호 확인
            <fg-input type="password" placeholder="비밀번호를 한번 더 입력해주세요" v-model="pw2"></fg-input>
            {{error.pw2}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            <span class="reddot">*</span>
            이름
            <fg-input type="text" placeholder="실명" v-model="name"></fg-input>

            <br />
            {{error.name}}
            <br />
            <br />
          </div>
          <div class="col-md-6">
            <span class="reddot">*</span>
            닉네임(별명)
            {{nickname.length}}/12
            :
            <fg-input type="text" placeholder="영문/한글/숫자만 입력 가능" maxlength="12" v-model="nickname"></fg-input>
            <br />
            {{error.nickname}}
            <br />
            <br />
          </div>
          <div class="col-md-5">
            <span class="reddot">*</span>
            주소
            <fg-input type="text" placeholder="검색 버튼을 눌러주세요" v-model="address1" :disabled="true"></fg-input>
          </div>
          <div class="col-md-5">
            상세주소
            <fg-input type="text" placeholder="건물 호수" v-model="address3"></fg-input>
          </div>
          <div class="col-md-2">
            <br />
            <button class="btn btn-primary" @click="postActive">
              <span v-if="!postAct">주소</span>
              <span v-else>X</span>
            </button>
          </div>
          <div class="col-12 postcode" v-if="postAct">
            <DaumPostcode :on-complete="handleAddress" />
          </div>
          <div class="col-12">
            {{error.address}}
            <br />
            <br />
          </div>
          <div class="col-12">
            <span class="reddot">*</span>
            전화번호
            <span style="display:flex; align-items: center">
              <fg-input type="text" maxlength="3" placeholder="010" v-model="phone0"></fg-input>
              <i class="ti-minus" style="margin:0px 10px 10px 10px"></i>
              <fg-input id="p1" type="text" maxlength="4" placeholder="0000" v-model="phone1"></fg-input>
              <i class="ti-minus" style="margin:0px 10px 10px 10px"></i>
              <fg-input id="p2" type="text" maxlength="4" placeholder="0000" v-model="phone2"></fg-input>
            </span>
            {{error.phone}}
            <br />
            <br />
          </div>
          <div class="col-12">
            깃 주소
            <br />
            <span style="display:flex">
              <input class="form-control" type="text" value="https://github.com/" disabled />
              &nbsp;
              <input
                class="form-control"
                type="text"
                placeholder="example"
                v-model="git"
              />
            </span>
            <br />
          </div>
          <div class="col-12">
            <span class="reddot">*</span>
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
          <div v-if="kakao" class="col-md-12">
            카카오 아이디 : &nbsp;
            <input type="text" :value="kakaosignupID" disabled />
          </div>
        </div>
      </form>
    </div>
    <h5>
      <span class="reddot">*</span> : 필수 입력
    </h5>
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
      geocoder: {},
      y: "",
      x: "",
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
      kakao: false,
      kakaosignupID: "",
      nothing: 0,
    };
  },
  beforeUpdate() {},
  mounted() {
    this.geocoder = new kakao.maps.services.Geocoder();

    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
      document.querySelector("div.main-panel").scrollTop = 0;
      document.querySelector("html").scrollTop = 0;
    });
    // document.querySelector('html').sc = 0
    if (window.sessionStorage.getItem("kakaosignup") === "true") {
      setTimeout(() => {
        this.kakao = true;
        this.id = window.sessionStorage.getItem("kakaosignupEmail");
        this.kakaosignupID = window.sessionStorage.getItem("kakaosignupID");
      }, 300);
    }
    setTimeout(() => {
      window.sessionStorage.setItem("kakaosignup", "false");
      window.sessionStorage.setItem("kakaosignupID", "");
      window.sessionStorage.setItem("kakaosignupEmail", "");
    }, 500);
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
      setTimeout(() => {
        this.name = this.name.replace(/[^가-힣]/g, "");
      }, 200);
    },
    nickname: function () {
      this.checker();
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎ]/g, "");
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
    getAddress(address) {
      this.geocoder.addressSearch(address, (result, status) => {
        // console.log("여긴가");
        if (status === kakao.maps.services.Status.OK) {
          //this.markerCenter.setMap(null);
          this.y = result[0].road_address.y;
          this.x = result[0].road_address.x;
          console.log(address);
          console.log(this.x + " : " + this.y + "실행됨");

          /////
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
            kakaoId: this.kakaosignupID,
            y: this.y,
            x: this.x,
          });
        }
      });
    },
    jungbok() {
      this.nothing += 1;
      setTimeout(() => {
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
            .catch((exp) => alert("서버가 불안정합니다." + exp));
        }
      }, 100);
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
      if (
        !EmailValidator.validate(this.id) ||
        this.id.split(".")[1].length > 3
      ) {
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
        this.getAddress(this.address1);
        document.querySelector("#spinner1122").classList.add("active");
      }
    },
    postActive: function () {
      this.postAct = !this.postAct;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
#spinner1122 {
  display: none;
}
#spinner1122.active {
  display: block;
  position: fixed;
  z-index: 999;
  width: 40vw;
  height: 30vw;
  top: 30vh;
  left: 30vw;
  animation-name: spinner;
  animation-iteration-count: infinite;
  // animation-timing-function: linear;
  animation-duration: 1.2s;
}
.signupform {
  background-color: #bbb;
}
.reddot {
  color: red;
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
@keyframes spinner {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>

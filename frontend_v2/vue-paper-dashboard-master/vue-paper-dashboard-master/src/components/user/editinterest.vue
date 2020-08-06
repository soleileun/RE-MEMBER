<template>
  <div class="editinterest">
    <div v-if="!pwvalid">
      <card class="card text-center">
        <h4>개인정보 수정을 위해 비밀번호를 다시 한 번 입력해주세요</h4>

        <br />
        <div class="row justify-content-md-center">
          <div class="col-sm-4 center">
            <fg-input type="password" label="비밀번호" v-model="oldpw" placeholder="비밀번호를 입력해주세요" />
          </div>
        </div>

        <br />
        <button class="btn btn-info btn-round" @click="pwconfirm">개인정보 바꾸기</button>
      </card>
    </div>
    <div v-else>
      <h4>Edit Profile</h4>
      <card class="card text-center">
        <span class="form">
          비밀번호 :
          <input v-model="pw" type="password" />
          {{error.pw}}
          비밀번호 확인 :
          <input v-model="pw2" type="password" />
          {{error.pw2}}
          닉네임(별명) :
          <input
            v-model="nickname"
            maxlength="10"
            type="text"
          />
          {{error.nickname}}
          주소 :
          <input v-model="address1" type="text" disabled />
          {{error.address}}
          <span>
            추가 주소 :
            <br />
            <input v-model="address2" type="text" />
          </span>
          <button @click="postActive" class="btn btn-info btn-round">주소 검색</button>
          <div class="postcode" v-if="postAct">
            <DaumPostcode :on-complete="handleAddress" />
          </div>
        </span>
        <br />
        <span>
          전화번호 :
          <input v-model="phone0" maxlength="3" type="tel" />-
          <input id="p1" v-model="phone1" maxlength="4" type="tel" />-
          <input id="p2" v-model="phone2" maxlength="4" type="tel" />
          {{error.phone}}
        </span>
        <br />
        <br />
        <span>
          깃주소 :
          <input v-model="git" type="text" />
        </span>
        <br />
        <br />
        <span>
          역할 :
          <input v-model="responsibility" type="radio" value="개발" /> 개발 |
          <input v-model="responsibility" type="radio" value="디자인" /> 디자인 |
          <input v-model="responsibility" type="radio" value="기획" />
          기획{{error.responsibility}}
        </span>
        <br />
        <br />활성 여부
        <input type="checkbox" v-model="st" />
        <br />
        <br />
        <button @click="edit" class="btn-info btn-round">수정</button>
      </card>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import DaumPostcode from "vuejs-daum-postcode";
import http from "../../http-common.js";
const storage = window.sessionStorage;

export default {
  name: "editinterest",
  components: {
    DaumPostcode,
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
      oldpw: "",
      pw: "",
      pwvalid: false,
      pwSchema: new PV(),
      pw2: "",
      nickname: "",
      address1: "",
      address2: "",
      fullAddress: "",
      phone0: "",
      phone1: "",
      phone2: "",
      git: "",
      submitable: false,
      postAct: false,
      error: {
        pw: "",
        pw2: "",
        nickname: "",
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
    nickname: function () {
      this.checker();
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎㅏ-ㅣ]/g, "");
    },
    phone0: function () {
      this.phone0 = this.phone0.replace(/[^0-9]/g, "");
      this.checker();
    },
    phone1: function () {
      this.phone1 = this.phone1.replace(/[^0-9]/g, "");
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
    postActive: function () {
      this.postAct = true;
    },
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
    pwconfirm: function () {
      http
        .post("/api/userinfo/signin", {
          id: storage.getItem("userid"),
          pw: this.oldpw,
        })
        .then((response) => {
          if (response.data.data) {
            storage.setItem(
              "jwt-auth-token",
              response.headers["jwt-auth-token"]
            );
            this.pw = this.oldpw;
            this.pw2 = this.oldpw;
            this.pwvalid = true;
            this.nickname = response.data.data.nickname;
            this.name = response.data.data.name;
            this.address1 = response.data.data.address1;
            this.address2 = response.data.data.address2;
            this.phone0 = response.data.data.phone.split("-")[0];
            this.phone1 = response.data.data.phone.split("-")[1];
            this.phone2 = response.data.data.phone.split("-")[2];
            this.git = response.data.data.git;
            this.responsibility = response.data.data.responsibility;
            this.st = response.data.data.state;
          } else {
            alert("비밀번호가 다릅니다.");
          }
        })
        .catch((exp) => {
          alert("오류가 발생했습니다." + exp);
        });
    },
    edit: function () {
      http
        .put(
          "/api/userinfo/" + storage.getItem("userid"),
          {
            id: storage.getItem("userid"),
            pw: this.pw,
            nickname: this.nickname,
            name: this.name,
            address1: this.address1,
            address2: this.address2,
            phone: `${this.phone0}-${this.phone1}-${this.phone2}`,
            git: this.git,
            responsibility: this.responsibility,
            state: this.st,
          },
          {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token"),
            },
          }
        )
        .then((res) => {
          console.log("결과");
          console.log(res.data);
          this.$store.dispatch("login", {
            id: storage.getItem("userid"),
            pw: this.pw,
          });
          this.$router.push({ path: "/profile" });
        })
        .catch((e) => console.log(e));
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.form {
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

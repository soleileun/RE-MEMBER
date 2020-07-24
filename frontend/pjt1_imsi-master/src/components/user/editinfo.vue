<template>
  <div class="editinfo">
    <div v-if="!pwvalid">
      비밀번호를 입력해주세요
      <br />
      <input type="password" v-model="oldpw" />
      <br />
      <button @click="pwconfirm">개인정보 바꾸기</button>
    </div>
    <div v-else>
      <br />새 비밀번호 :
      <input v-model="pw" type="password" />
      <br />
      {{error.pw}}
      <br />새 비밀번호 확인 :
      <input v-model="pw2" type="password" />
      <br />
      {{error.pw2}}
      <br />닉네임(별명) :
      <input v-model="nickname" maxlength="10" type="text" />
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
      <button @click="edit" class="edit">수정</button>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import http from "../../http-common.js";
const storage = window.sessionStorage;

export default {
  name: "editinfo",
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
      pwvalid: false,
      oldpw: "",
      pw: "",
      pwSchema: new PV(),
      pw2: "",
      nickname: "",
      name:"",
      address1: "",
      address2: "",
      phone0: "",
      phone1: "",
      phone2: "",
      git: "",
      submitable: false,
      error: {
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
    nickname: function () {
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎㅏ-ㅣ]/g, "");
    },
    phone0: function () {
      this.phone0 = this.phone0.replace(/[^0-9]/g, "");
    },
    phone1: function () {
      this.phone1 = this.phone1.replace(/[^0-9]/g, "");
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
        .put("/api/userinfo/" + storage.getItem("userid"), { 
          id: storage.getItem("userid"),
          pw: this.pw,
          nickname: this.nickname,
          name: this.name,
          address1: this.address1,
          address2: this.address2,
          phone: `${this.phone0}-${this.phone1}-${this.phone2}`,
          git: this.git,
          responsibility: this.responsibility,
        },
        {
          headers:{
            "jwt-auth-token":storage.getItem("jwt-auth-token")
          }
        })
        .then((res) => {
          console.log('결과')
          console.log(res.data);
          this.$store.dispatch("login", { id: storage.getItem("userid"), pw: this.pw });
        })
        .catch((e) => console.log(e));
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

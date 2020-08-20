<template>
  <div class="editinfo">






    
    <div v-if="!pwvalid">
      <card class="card text-center">
        <h4>개인정보 수정을 위해 비밀번호를 다시 한 번 입력해주세요</h4>

        <br />

        <div class="row justify-content-md-center">
          <div class="col-sm-4 center">
            <input type="password" label="비밀번호" v-model="oldpw" @keyup.enter="pwconfirm" placeholder="비밀번호를 입력해주세요" />
          </div>
        </div>

        <br />
        <button class="btn btn-info btn-round" @click="pwconfirm">개인정보 바꾸기</button>
      </card>
    </div>
    
    <div v-else class="col-12">


                <div class="row" >
                    <!-- Column -->
                    <div class="col-lg-4 col-xlg-3 col-md-5">
                        <div class="card" >
                            <div class="card-body" style="min-height:400px;">
                                <center class="m-t-30" > <img src="assets/images/users/5.jpg" class="img-circle" width="150" style="margin-bottom:50px;"/>
                                    <h4 class="card-title m-t-10">{{board.bwriter}}</h4>
                                    <h6 class="card-subtitle">{{nickname}}</h6>
                                    <h6 class="card-subtitle">{{responsibility}}</h6>
                                    <!--
                                    <div class="row text-center justify-content-md-center">
                                        <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-people"></i> <font class="font-medium">254</font></a></div>
                                        <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-picture"></i> <font class="font-medium">54</font></a></div>
                                    </div>
                                    -->
                                </center>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-8 col-xlg-9 col-md-7">
                        <div class="card">
                            <!-- Tab panes -->
                            <div class="card-body">
                                <form class="form-horizontal form-material">
                                    <div class="form-group">
                                        <label class="col-md-12">닉네임</label>
                                        <div class="col-md-12">
                                            <input v-model="nickname" maxlength="12" type="text"  class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-md-12">비밀번호</label>
                                        <div class="col-md-12">
                                            <input v-model="pw" type="password"  class="form-control form-control-line" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">비밀번호 확인</label>
                                        <div class="col-md-12">
                                            <input v-model="pw2" type="password" class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">주소</label>
                                        <div class="col-md-12">
                                            <input v-model="address1" type="text" disabled  class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">추가 주소</label>
                                        <div class="col-md-12">
                                            <input  v-model="address3" type="text" class="form-control form-control-line">
                                            <div class="btn btn-primary" @click="postActive">
                                              <span v-if="!postAct">주소</span>
                                              <span v-else>X</span>
                                            </div>
                                              <div class="postcode" v-if="postAct">
                                                <DaumPostcode :on-complete="handleAddress" />
                                              </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">전화번호</label>
                                        <div class="row col-12">
                                        <div class="col-md-3">
                                            <input v-model="phone0" maxlength="3" type="tel" class="form-control form-control-line">
                                        </div>
                                        <div style="padding-top:15px;padding-bottom:15px;">ㅡ</div>
                                        <div class="col-md-3">
                                            <input id="p1" v-model="phone1" maxlength="4" type="tel" class="form-control form-control-line">
                                        </div>
                                        <div style="padding-top:15px;padding-bottom:15px;">ㅡ</div>
                                        <div class="col-md-3">
                                            <input id="p2" v-model="phone1" maxlength="4" type="tel" class="form-control form-control-line">
                                        </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Git 계정</label>
                                        <div class="col-md-12">
                                            <input v-model="git" type="text" class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">인재풀 등록 여부  
                                            <input type="checkbox" v-model="st" /></label>
                                        
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">자기소개</label>
                                        <div class="col-md-12">
                                            <textarea v-model="intro" maxlength="100" type="text" rows="5" cols="20" class="form-control form-control-line"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-12">역할</label>
                                        <div class="col-sm-12">
                                            <select v-model="responsibility" class="form-control form-control-line">
                                                <option>개발</option>
                                                <option>디자인</option>
                                                <option>기획</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                          
                                            <div @click="edit" class="btn btn-success">수정</div>
                                          <!--
                                            <button class="btn btn-success">Update Profile</button>
                                          -->
                                        </div>
                                    </div>
                                </form> <!-- form이었음 -->
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>











<!--
      <div class="row">
      <div class="col-1">
        </div>
      <div class="col-10 center">
      <card class="card text-center">
        <div class="form col-12">
          <span>
            한줄 자기 소개
            {{intro.length}}/100
            :
          </span>
          <textarea style="resize:none;width:300px" rows="5" cols="20" v-model="intro" maxlength="100" type="text" />
          <div class="row col-12">
            <div class="col-4" style="text-align:right; font-size:22px;">
          비밀번호 :
          </div>
          <div class="col-8" style="text-align:left; font-size:22px;">
          <input v-model="pw" type="password" />
          {{error.pw}}
          </div>
          </div>
          
          <div class="row col-12">
          비밀번호 확인 :
          <input v-model="pw2" type="password" />
          {{error.pw2}}
          </div>
          <span>
            닉네임(별명)
            {{nickname.length}}/12
            :
          </span>
          <input v-model="nickname" maxlength="12" type="text" />
          {{error.nickname}}
          주소 :
          <input v-model="address1" type="text" disabled />
          {{error.address}}
          <span>
            추가 주소 :
            <br />
            <input v-model="address3" type="text" />
          </span>
          <button class="btn btn-primary" @click="postActive">
            <span v-if="!postAct">주소</span>
            <span v-else>X</span>
          </button>
          <div class="postcode" v-if="postAct">
            <DaumPostcode :on-complete="handleAddress" />
          </div>
        </div>
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
    
      <div class="col-1">
        </div>
    </div>
    -->
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import DaumPostcode from "vuejs-daum-postcode";
import http from "../../http-common.js";
const storage = window.sessionStorage;
import Constant from "../../Constant";

export default {
  name: "editinfo",
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
  mounted() {
    http
      .get(
        "/api/board/typesearch/writer=" +
          storage.getItem("userid") +
          "&bstate=profile"
      )
      .then((response) => {
        if (response.data.length > 0) {
          this.board.bno = response.data[0].bno;
          this.board.bwriter = response.data[0].bwriter;
          this.board.btitle = response.data[0].btitle;
          this.board.bcontent = response.data[0].bcontent;
          this.board.bview = response.data[0].bview;
          this.board.bfile = response.data[0].bfile;
          this.board.bstate = response.data[0].bstate;
          this.board.makeDay = response.data[0].makeDay;
          this.board.changeDay = response.data[0].changeDay;
          this.board.makeId = response.data[0].makeId;
          this.board.changeId = response.data[0].changeId;
        } else {
          alert("내 프로필을 로드하는데에 실패하였습니다.");
        }
      })
      .catch((exp) => alert("내 프로필을 로드하는데에 실패하였습니다." + exp));
  },
  data: function () {
    return {
      board: {
        bno: '',
        bwriter: '',
        btitle: '',
        bcontent:'',
        bview:'',
        bfile:'',
        bstate:'',
        makeDay:'',
        changeDay:'',
        makeId: '',
        changeId: '',
      },
      address3: "",
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
      intro: "",
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
      this.nickname = this.nickname.replace(/[^0-9가-힣a-zA-Zㄱ-ㅎ]/g, "");
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
      if(this.postAct == true) { this.postAct = false;}
      else this.postAct = true;
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
      const config = {
        headers: {
          "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
        },
      };
      http
        .post(
          "/api/userinfo/signin",
          {
            id: storage.getItem("userid"),
            pw: this.oldpw,
          },
          config
        )
        .then((response) => {
          if (response.data.data) {
            storage.setItem(
              "jwt-auth-token",
              response.headers["jwt-auth-token"]
            );
            this.pw = this.oldpw;
            this.pw2 = this.oldpw;
            this.intro = response.data.data.intro;
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
      if (this.submitable) {
        const config = {
          headers: {
            "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
          },
        };
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
              intro: this.intro,
            },
            config
          )
          .then((res) => {
            this.board.btitle = this.nickname
            this.$store.dispatch(Constant.MODIFY_BOARD, { board: this.board });
            this.$store.dispatch("login", {
              id: storage.getItem("userid"),
              pw: this.pw,
            });
            this.$router.push({ path: "/mypage" });
          })
          .catch((e) => {});
      }
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


.form-material {
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

}
</style>

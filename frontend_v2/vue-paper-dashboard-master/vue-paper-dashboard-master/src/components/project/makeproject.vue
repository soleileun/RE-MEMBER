<template>
  <div>
    <card class="makePJTcard">
      <div class="row">
        <div class="col-md-12 text-center">
          <h3>새로운 프로젝트를 만듭니다!</h3>
          <br />
        </div>
        <div class="col-md-12 text-center">
          <h5>프로젝트 팀장 : {{userNick}}</h5>
          <br />
        </div>
        <br />
        <div class="col-md-8">
          프로젝트 이름
          <span class="reddot">*</span>
          <fg-input
            type="text"
            name="pjtname"
            placeholder="멋찐 이름으로 프로젝트를 만들어주세요!"
            v-model="wproject.pjtName"
          ></fg-input>
        </div>
        <br />

        <div class="col-md-4">
          <fg-input
            type="number"
            label="프로젝트 예상 인원"
            placeholder="몇 명의 팀원이 필요할까요?"
            v-model="wproject.pjtMemberCnt"
          ></fg-input>
        </div>
        <div class="col-12">
          <h5>대표 이미지
          <span class="reddot">*</span></h5>
          <input type="file" id="ex_file" @change="previewFiles" accept="image/png" >
          <br>
          <br>
        </div>

          <div class="col-md-10">
            프로젝트 희망 지역<span class="reddot">*</span>
            <fg-input type="text" placeholder="검색 버튼을 눌러주세요" v-model="address1" :disabled="true"></fg-input>
          </div>
          
          <div class="col-md-2">
            <br />
            <button class="btn btn-primary" @click="postActive">주소</button>
          </div>
          <div class="col-12 postcode" v-if="postAct">
            <DaumPostcode :on-complete="handleAddress" />
          </div>

        <div class="col-md-12">
          <div class="form-group">
            <label>프로젝트 소개</label>
            <textarea
              rows="5"
              class="form-control border-input"
              placeholder="Here can be your description"
              v-model="wproject.pjtContent"
              name="contents"
            ></textarea>
          </div>
        </div>
        <card class="signupinterest card">
          <div>
            <h5>
              <strong>관심 분야 및 스택</strong>
            </h5>
            <form @submit.prevent>
              <div class="row">
                <div class="col-12">
                  <ul>
                    (EXAMPLE)
                    <li>+ Language : C / C++ / Java / Python / C#</li>
                    <li>web FrontEnd / BackEnd / Spring / Jenkins</li>
                    <li>Mobile -> Hybrid / Android / iOS /</li>
                    <li>Javascript -> node.js / Angular.JS / React.js / Vue.js / jQuery</li>
                    <li>IoT : Aduino / RaspberryPi / Embedded / Qt / MachineVision</li>
                    <li>DB : Oracle / MSSQL / MySQL / MariaDB / MongoDB</li>
                  </ul>
                </div>
                <div class="col-12 selectform">
                  <button
                    class="btn btn-primary btn-round"
                    v-for="pick in picks"
                    :key="pick"
                    @click="del(pick)"
                  >{{pick}}</button>
                  <br />
                </div>
                <div class="col-12 searchform">
                  <div class="input">
                    <input
                      type="text"
                      v-model="inputVal"
                      @input="searchQuery()"
                      @keyup.up="up()"
                      @keyup.down="down()"
                      @keyup.enter="enter()"
                    />
                  </div>
                  <div class="autoComplete">
                    <div v-for="list in lists" :key="list" @click="add(list)">{{list}}</div>
                  </div>
                </div>
                <div class="col-12 text-center">
                  <br />
                  <button
                    class="gosignup btn btn-success"
                    @click="addProject"
                    :class="{submitable:submitable}"
                  >생성하기!</button>
                </div>
              </div>
            </form>
          </div>
        </card>
      </div>
    </card>
  </div>
</template>

<script>
import Constant from "../../Constant";
import DaumPostcode from "vuejs-daum-postcode";
const storage = window.sessionStorage;
//import project from "../project/project1";
export default {
  name: "makeproject",
  components: {
    DaumPostcode,
  },
  computed: {
    projects() {
      return this.$store.state.projectstore.projects;
    },
  },

  created() {
    // 세션 아이디로 교체
    this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
      userId: storage.getItem("userid"), // userId: "ssafy",
    });
  },
  data() {
    return {
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),
      address1: "",
      postAct: false,
      file:'',
      wproject: {
        pid: "",
        pjtName: "",
        pjtContent: "",
        pjtState: "",
        pjtMemberCnt: "5",
        makeDay: "",
        changeDay: "",
        makeId: "",
        changeId: "",
        location: "",
      },
      lists: [],
      pints: [
        "C",
        "C++",
        "Java",
        "Python",
        "Frontend",
        "Backend",
        "Spring",
        "Jenkins",
        "Django",
        "Android",
        "iOS",
        "Unity",
        "Unreal",
        "react-native",
        "Javascript",
        "node.js",
        "node.express",
        "Angular.js",
        "jQuery",
        "React.js",
        "Vue.js",
        "IoT",
        "Arduino",
        "RasberryPi",
        "Embedded",
        "Qt",
        "MachineVision",
        "BlockChain",
        "MachinLearning",
        "DB",
        "Oracle",
        "MySQL",
        "MSSQL",
        "MariaDB",
        "MongoDB",
        "GraphQL",
      ],
      picks: [],
      submitable: false,
      inputVal: "",
    };
  },
  methods: {
    previewFiles(event) {
      if (event.target.files[0]) {
        if (event.target.files[0].type!=='image/png'){
          alert("확장자가 PNG인 파일만 업로드 가능합니다.")
        }else{
          this.file = event.target.files[0]
        }
      }
    },
    check: function () {
      if (this.signup === 0) {
        this.submitable = false;
      } else {
        this.submitable = this.picks.length > 0 ? true : false;
      }
    },
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
        // fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
      }
      this.address1 = fullAddress;
      this.wproject.location =
        fullAddress.split(" ").slice(0, -2).join(" ") + " " + extraAddress;
      this.postAct = false;
    },
    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
      } else {
        this.lists = [];
      }
    },
    add: function (x) {
      if (x !== "") {
        if (!this.picks.find((i) => i === x)) {
          this.picks.push(x);
        }
      }
      this.inputVal = "";
      this.lists = [];
    },
    del: function (x) {
      const idx = this.picks.indexOf(x);
      if (idx > -1) this.picks.splice(idx, 1);
    },
    addProject() {
      this.$store.commit("pinterest", { picks: this.picks });
      if (
        this.wproject.pjtContent.trim() != "" &&
        this.wproject.pjtName.trim() != "" &&
        this.wproject.pjtMemberCnt != "0"&&
        this.file!=''
      ) {
        this.$store.dispatch(Constant.ADD_PROJECT, {
          // pid: "",
          pjtName: this.wproject.pjtName,
          pjtContent: this.wproject.pjtContent,
          pjtState: "ing",
          pjtMemberCnt: this.wproject.pjtMemberCnt,
          makeDay: new Date(),
          //changeDay: this.wproject.title,
          makeId: this.userId,
          location: this.wproject.location,
          file:this.file
          //changeId: this.wproject.title,
          //location: this.wproject.location,
        });
        //userid 수정해야됨!
        this.$router.push("/project/myproject/");
      } else {
        alert("필수 항목을 모두 입력 해주세요.");
      }
    },
  },
};
</script>

<style scoped lang="scss">
.centered {
  display: table;
  margin-left: auto;
  margin-right: auto;
  display: inline-block;
}

.makePJTcard {
  background-color: #ffffff;
}
.reddot {
  color: red;
}
.signupinterest {
  ul {
    text-align: left;
    li {
      list-style-type: none;
    }
  }
  .searchform {
    position: relative;
    display: flex;
    flex-direction: column;
    // height: 50px;
    .autoComplete {
      position: absolute;
      max-height: 100px;
      overflow: auto;
      background-color: #f4f0fc;
      width: 90%;
      left: 5px;
      bottom: 49px;
      cursor: pointer;
    }
    .input {
      height: 100%;
      width: 100%;

      font-size: 1.2rem;
      border: 1px black solid;
      padding: 5px;
      input {
        width: 100%;
        height: 100%;
        border: none;
        outline: none;
        &:focus {
          border: none;
        }
      }
    }
  }
}
</style>

<template>
  <div>
    <card class="makePJTcard">
      <div class="row">
        <div class="col-md-12 text-center">
          <h3>새로운 프로젝트를 만듭니다!</h3>
          <br />
        </div>
        <div class="col-md-12 text-center">
          <h5>프로젝트 팀장 : {{userId}}</h5>
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
const storage = window.sessionStorage;
//import project from "../project/project1";
export default {
  name: "makeproject",
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
        "C#",
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
    check: function () {
      if (this.signup === 0) {
        this.submitable = false;
      } else {
        this.submitable = this.picks.length > 0 ? true : false;
      }
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
      console.log(this.picks);
      if (
        this.wproject.pjtContent.trim() != "" &&
        this.wproject.pjtName.trim() != "" &&
        this.wproject.pjtMemberCnt != "0"
      ) {
        console.log("여긴통과함");
        this.$store.dispatch(Constant.ADD_PROJECT, {
          // pid: "",
          pjtName: this.wproject.pjtName,
          pjtContent: this.wproject.pjtContent,
          pjtState: "ing",
          pjtMemberCnt: this.wproject.pjtMemberCnt,
          makeDay: new Date(),
          //changeDay: this.wproject.title,
          makeId: this.userId,
          //changeId: this.wproject.title,
          //location: this.wproject.location,
        });
        //userid 수정해야됨!
        this.$router.push("/project/myproject/" + this.userId);
        console.log(this.userId);
      } else {
        console.log("공백입력.");
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

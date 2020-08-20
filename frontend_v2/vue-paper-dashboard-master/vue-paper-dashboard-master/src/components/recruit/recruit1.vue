<template>
  <div class="recruit1" style="z-index:50;">
    <div>
      <div class="row" style="display : flex;">
        <div class="col-3">
          <p>
            <strong>프로젝트 검색</strong>
          </p>
        </div>

        <div class="col-4" style="display : flex;">
          <select name="std" id="std" style="height:40px; margin-right:15px;">
            <option value="title" selected>제목</option>
            <option value="writer">작성자</option>
          </select>
          <fg-input type="text" placeholder="검색어를 입력하세요" id="keyword" style="width:85%;" />
        </div>

        <div class="col-5" style="display:flex;">
          <div
            @click="searchPool()"
            class="button-7"
            style="float: none; margin: 0 auto; height:50px;"
          >
            <div class="eff-7"></div>
            <a style="display:inline-block; padding-bottom:10px; height:40px;">프로젝트 검색</a>
          </div>
          <div>
            <button v-if="loginId !== ''" id class="btn btn-primary" @click="openModal">등록하기</button>
          </div>
        </div>
      </div>
    </div>

    <hr />

    <div class="row">
      <br />
      <div class="filter-sidebar col-12 col-sm-4 col-md-3">
        <!-- 시군구동 검색 -->
        <div class="card-body cb">
          <div class="row">
            <label for="sido">
              <strong>
                시도
                <span id="userid" class="text-danger"></span>
              </strong>
            </label>
            <select
              id="sido"
              class="form-control"
              @change="changeSido(selectedSido)"
              v-model="selectedSido"
            >
              <option value="0">선택</option>
              <option v-for="(sido, index) in sidoList" :value="sido" :key="index">
                {{
                sido
                }}
              </option>
            </select>

            <br />
            <br />
            <br />

            <label for="gugun">
              <strong>
                구군
                <span id="userid" class="text-danger"></span>
              </strong>
            </label>
            <select
              v-if="selectedSido!=0"
              id="gugun"
              class="form-control"
              @change="changeGugun(selectedSido, selectedGugun)"
              v-model="selectedGugun"
            >
              <option value="0">선택</option>
              <option v-for="(gu, index) in gugunList" :value="gu" :key="index">
                {{
                gu
                }}
              </option>
            </select>
            <select v-else id="gugun" class="form-control" disabled>
              <option value="0">선택</option>
            </select>

            <br />
            <br />
            <br />
            <label for="dong">
              <strong>
                읍면동
                <span id="userid" class="text-danger"></span>
              </strong>
            </label>

            <select v-if="selectedGugun!=0" id="dong" class="form-control" v-model="selectedDong">
              <!-- @change="changeDong(selectedDong)" -->

              <option value="0">선택</option>
              <option v-for="(don, index) in dongList" :value="don" :key="index">
                {{
                don
                }}
              </option>
            </select>
            <select v-else id="dong" class="form-control" disabled>
              <option value="0">선택</option>
            </select>
          </div>

          <br />
          <hr />

          <div class="row">
            <div class="col-12 selectform">
              <div
                class="btn btn-primary btn-round"
                v-for="(pick,index) in picks"
                :key="index"
                @click="deleteStack(index)"
                style="z-index : 0;"
              >{{pick}}</div>
            </div>
          </div>
          <br />

          <div class="col-12 searchform">
            <div style="padding:0;padding-bottom:5px">
              <strong>기술스택</strong>
            </div>
            <div class="input">
              <input
                type="text"
                v-model="inputVal"
                @input="searchQuery()"
                @keyup.up="upQ()"
                @keyup.down="downQ()"
                @keyup.enter="enterQ()"
                placeholder="스택 입력"
              />
            </div>
            <div class="autoComplete">
              <div
                v-for="(list,index) in lists"
                :key="list"
                @click="add(list)"
                :class="`pk${index}`"
              >{{list}}</div>
            </div>
          </div>

          <br />
          <!--
        <div class="row">
          <div class="col-12 selectform">
            <div
              class="btn btn-primary btn-round"
              v-for="(pick,index) in picks"
              :key="index"
              @click="deleteStack(index)"
              style="z-index : 0;"
            >{{pick}}</div>
          </div>
        </div>
          -->
        </div>
      </div>
      <div class="col-12 col-sm-8 col-md-9">
        <notfound v-if="rows === 0" />
        <div v-else>
          <div class="row" style="margin-top:20;">
            <div class="col-10">
              <select id="showcnt" @change="changeShowCnt">
                <option value="6" selected>6개씩 보기</option>
                <option value="12">12개씩 보기</option>
                <option value="18">18개씩 보기</option>
              </select>
            </div>
            <div class="col-2"></div>
          </div>
          <div class="row">
            <recruitcomponent
              class="col-12 col-lg-6 col-xl-4"
              v-for="(recruit,index) in recruits.slice(this.perPage*(currentPage-1),perPage*(currentPage))"
              :key="index"
              :recruit="recruit"
              :pid="recruit.pid"
            />
          </div>
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="recruitpage"
            align="center"
            id="pagination"
          ></b-pagination>
        </div>
      </div>
    </div>

    <!--  ////////////////////////////////////////////////////////////////////////// -->
    <!-- The Modal -->
    <div class="modal" id="momo" style="z-index : 100;">
      <div class="modal-content">
        <span class="close">&times;</span>
        <div class="row">
          <div class="col-25">
            <label for="fname">아이디</label>
          </div>
          <div class="col-75">
            <label for="fname">{{loginId}}</label>
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="country">내 프로젝트 선택</label>
          </div>
          <div class="col-75">
            <select id="myproject" name="myproject">
              <option
                v-for="(project,index) in projects"
                :key="index"
                :value="project.pid"
              >{{project.pjtName}}</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-25">
            <label for="lname">게시글 제목</label>
          </div>
          <div class="col-75">
            <input type="text" v-model="wrecruit.title" placeholder="제목을 입력하세요" />
          </div>
        </div>

        <div class="row">
          <div class="col-25">
            <label for="subject">내용</label>
          </div>
          <div class="col-75">
            <vue-editor
              class="viewEditor"
              id="editor"
              v-model="wrecruit.contents"
              :editor-toolbar="customToolbar"
            ></vue-editor>
          </div>
        </div>

        <div class="row">
          <button class="btn btn-info" @click="addRecruit">등록</button>
        </div>
      </div>
    </div>
    <!-- Modal end  -->
  </div>
</template>

<script>
import { VueEditor } from "vue2-editor";
import Constant from "../../Constant";
import recruitcomponent from "@/components/recruit/recruitcomponent.vue";
import notfound from "@/components/notfound/notfound.vue";
const storage = window.sessionStorage;
export default {
  name: "recruit1",
  components: {
    recruitcomponent,
    VueEditor,
    notfound,
  },
  computed: {
    rows() {
      return this.recruits.length;
    },
    recruits() {
      return this.$store.state.recruitstore.recruits;
    },
    projects() {
      return this.$store.state.projectstore.projects;
    },
    loginId() {
      return this.$store.state.userstore.userid;
    },

    //필터링 스택
    pools() {
      return this.$store.state.poolstore.pools;
    },
    sidoList() {
      // console.log("확인" + this.$store.state.stackstore.sidolist);
      return this.$store.state.stackstore.sidolist;
    },
    gugunList() {
      return this.$store.state.stackstore.gugunlist;
    },
    dongList() {
      return this.$store.state.stackstore.donglist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_RECRUITLIST);
    if (storage.getItem("userid") !== "") {
      this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
        userId: storage.getItem("userid"),
      });
    }
    // sido리스트 불러오기
    this.$store.dispatch(Constant.GET_SIDOLIST);
  },
  data() {
    return {
      customToolbar: [
        [{ font: [] }],
        [{ header: [false, 1, 2, 3, 4, 5, 6] }],
        [{ size: ["small", false, "large", "huge"] }],
        ["bold", "italic", "underline", "strike"],
        [
          { align: "" },
          { align: "center" },
          { align: "right" },
          { align: "justify" },
        ],
        [{ header: 1 }, { header: 2 }],
        ["blockquote", "code-block"],
        [{ list: "ordered" }, { list: "bullet" }, { list: "check" }],
        [{ script: "sub" }, { script: "super" }],
        [{ indent: "-1" }, { indent: "+1" }],
        [{ color: [] }, { background: [] }],
        ["link", "video", "formula"],
        [{ direction: "rtl" }],
        ["clean"],
      ],
      //페이징
      perPage: 6,
      currentPage: 1,
      // pjtName:this.$store.state.projectstore.pjtName,
      wrecruit: {
        rnum: "",
        pid: "",
        title: "",
        contents: "",
        endDate: "",
        makeDay: "",
        changeDay: "",
        makeId: "",
        changeId: "",
      },

      //스택 필터링
      selectedSido: 0,
      selectedGugun: 0,
      selectedDong: 0,

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
      inputVal: "",
      cursor: 0,
    };
  },

  methods: {
    //페이징
    changeShowCnt() {
      this.perPage = parseInt(document.getElementById("showcnt").value);
    },
    openModal() {
      // Get the modal
      var modal = document.getElementById("momo");

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

      // When the user clicks on the button, open the modal
      modal.style.display = "block";
      window.scrollY = 0;
      document.querySelector("html").scrollTop = 0;
      document.querySelector(".main-panel").scrollTop = 0;
      // When the user clicks on <span> (x), close the modal
      span.onclick = function () {
        modal.style.display = "none";
      };
      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    },
    addRecruit() {
      let ppid = document.getElementById("myproject");
      ppid = ppid.options[ppid.selectedIndex].value;
      // console.log("선택된 프로젝트넘버 : " + ppid);

      // console.log(this.recruits);
      var flag = true;
      for (var i = 0; i < this.recruits.length; i++) {
        if (this.recruits[i].pid == ppid) {
          flag = false;
        }
      }
      // console.log(flag);
      if (flag) {
        if (this.wrecruit.contents.trim() != "") {
          this.$store.dispatch(Constant.ADD_RECRUIT, {
            //rnum:'',
            pid: ppid,
            title: this.wrecruit.title,
            contents: this.wrecruit.contents,
            endDate: this.wrecruit.endDate,
            makeDay: this.wrecruit.makeDay,
            changeDay: this.wrecruit.changeDay,
            makeId: this.loginId,
            changeId: this.loginId,
          });

          var modal = document.getElementById("momo");
          modal.style.display = "none";
          // this.$router.push("/recruit/recruit1");
        } else {
          alert("공백 입력이 있어요 ㅠㅠ");
        }
      } else {
        alert("이미 게시된 프로젝트입니다 :( 이전 글을 확인해주세요!");
      }
    },

    //필터링 스택
    changeSido(selectedSido) {
      // gugun
      // console.log(selectedSido);
      this.selectedGugun = 0;
      this.selectedDong = 0;
      this.$store.dispatch(Constant.GET_GUGUNLIST, { sido: selectedSido });
    },
    changeGugun(selectedSido, selectedGugun) {
      // dong
      // console.log(selectedSido + selectedGugun);
      this.selectedDong = 0;

      this.$store.dispatch(Constant.GET_DONGLIST, {
        sido: selectedSido,
        gugun: selectedGugun,
      });
    },
    // changeDong(selectedDong) {
    //   // apt

    // },

    searchPool() {
      let sd = "";
      let gg = "";
      let dn = "";

      if (this.selectedSido == 0) {
        sd = " ";
      } else {
        sd = this.selectedSido;
      }
      if (this.selectedGugun == 0) {
        gg = " ";
      } else {
        gg = this.selectedGugun;
      }
      if (this.selectedDong == 0) {
        dn = " ";
      } else {
        dn = this.selectedDong;
      }
      let addr = sd + "," + gg + "," + dn + ",";
      let stacks = "";
      if (this.picks.length != 0) {
        for (let i = 0; i < this.picks.length; i++) {
          stacks += this.picks[i] + ",";
        }
      } else {
        stacks = null;
      }
      //통합
      this.$store.dispatch(Constant.SEARCH_RECRUIT, {
        addr,
        stacks,
        by: document.getElementById("std").value,
        keyword: document.getElementById("keyword").value,
      });
    },

    deleteStack(idx) {
      this.picks.splice(idx, 1);
    },

    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
        setTimeout(() => {
          if (this.lists.length > 0) {
            this.cursor = 0;
            document.querySelector(`.pk${this.cursor}`).classList.add("act");
          }
        }, 50);
      } else {
        this.lists = [];
      }
    },
    add: function (x) {
      if (this.picks.length == 5) {
        alert("검색 스택은 5개까지만 입력 가능합니다.");
        this.lists = [];
        this.inputVal = "";
      } else {
        if (x !== "") {
          if (!this.picks.find((i) => i === x)) {
            this.picks.push(x);
          }
        }
        this.inputVal = "";
        this.lists = [];
      }
    },
    upQ() {
      document.querySelector(`.pk${this.cursor}`).classList.remove("act");
      this.cursor--;
      if (this.cursor === -1) {
        this.cursor = this.lists.length - 1;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add("act");
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document
        .querySelector(".autoComplete")
        .scrollTo({ top: location, behavior: "auto" });
    },
    downQ() {
      document.querySelector(`.pk${this.cursor}`).classList.remove("act");
      this.cursor++;
      if (this.cursor === this.lists.length) {
        this.cursor = 0;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add("act");
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document
        .querySelector(".autoComplete")
        .scrollTo({ top: location, behavior: "auto" });
    },
    enterQ() {
      this.add(document.querySelector(`.pk${this.cursor}`).innerText);
    },

    // deleteRecruit(rnum) {
    //   this.$emit("delete-recruit", rnum);
    // },
    // modifyRecruit() {
    //   this.$emit("modify-recruit");
    // },
    // openModify() {
    //   this.$emit("open-modify");
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
// #table1,
// td,
// tr,
// th {
//   width: 1000px;
//   margin: 0 auto;
//   text-align: center;
//   border: 1px solid black;
// }

// /* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100vh; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 50%; /* Could be more or less, depending on screen size */
}
/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

// 테이블
/*----------------------------------------------------------
GENERAL
----------------------------------------------------------*/
* {
  margin: 0;
  padding: 0;
  font-family: "Roboto", sans-serif;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25,
  .col-75,
  input[type="submit"] {
    width: 100%;
    margin-top: 0;
  }
}
#pagination {
  z-index: 0;
}

//filter-sidebar
.filter-sidebar {
  padding: 15px;
  // padding: 20px 10px 20px 20px;
  border-right: solid 1px #e0e0e0;
}

//버튼

.button-7 {
  width: 140px;
  height: 50px;
  border: 2px solid #34495e;
  float: left;
  text-align: center;
  cursor: pointer;
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  margin: 0 0 40px 50px;
}
.button-7 a {
  font-family: arial;
  font-size: 16px;
  color: #34495e;
  text-decoration: none;
  line-height: 50px;
  transition: all 0.5s ease;
  z-index: 2;
  color: white;
  position: relative;
}
.eff-7 {
  width: 140px;
  height: 50px;
  border: 0px solid #34495e;
  position: absolute;
  transition: all 0.5s ease;
  z-index: 1;
  box-sizing: border-box;
}
.button-7:hover .eff-7 {
  border: 70px solid #34495e;
}
.button-7:hover a {
  color: #fff;
}

.searchform {
  position: relative;
  display: flex;
  flex-direction: column;
  // height: 50px;
  .autoComplete {
    border: 0.5px gray solid;
    position: absolute;
    max-height: 100px;
    overflow: auto;
    background-color: white;
    width: 98%;
    left: 6px;
    bottom: 45px;
    cursor: pointer;
    .act {
      background-color: #aaa;
    }
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
.cb {
  min-height: 470px;
  height: 100%;
}
</style>
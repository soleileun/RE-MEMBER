<template>
  <div class="recruit1" style="z-index:50;">
    <div>
      <div class="row" style="display : flex;">
        <div class="col-6">
          <p>
            <strong>프로젝트 검색</strong>
          </p>
        </div>

        <div class="col-4" style="display : flex;">
          <select name="std" id="std">
            <option value="title" selected>제목</option>
            <option value="writer">작성자</option>
          </select>
          <fg-input type="text" placeholder="검색어를 입력하세요" id="keyword" style="width:85%;" />
        </div>

        <div class="col-2">
          <div @click="searchPool()" class="button-7" style="float: none; margin: 0 auto;">
            <div class="eff-7"></div>
            <a >프로젝트 검색</a>
          </div>
        </div>
      </div>
    </div>

    <hr />

    <section class="filter-sidebar">
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
        <br />

        <div class="col-12 searchform">
          <p>
            <strong>기술스택</strong>
          </p>
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

        <br>

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
      </div>
    </section>

    <!--  ////////////////////////////////////////////////////////////////////////// -->

    <notfound v-if="rows === 0" />
    <div v-else>
      <div class="row">
        <div class="col-10">
          <select id="showcnt" @change="changeShowCnt">
            <option value="6" selected>6개씩 보기</option>
            <option value="12">12개씩 보기</option>
            <option value="18">18개씩 보기</option>
          </select>
        </div>
        <div class="col-2">
          <button v-if="loginId !== ''" id class="btn btn-primary" @click="openModal">구인글 등록</button>
        </div>
      </div>
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
              <vue-editor class="viewEditor" v-model="wrecruit.contents" :editor-toolbar="customToolbar"></vue-editor>
            </div>
          </div>

          <div class="row">
            <button class="btn btn-info" @click="addRecruit">등록</button>
          </div>
        </div>
      </div>
      <!-- Modal end  -->

      <br />

      <div class="overflow-auto" style="padding:0; ">
        <div class="row col-12" style="margin:0; padding:0;  z-index : 0;" id="recruitpage">
          <recruitcomponent
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
      <!-- 카드뷰 -->
      <!--
    <div class="container">
      <div class="row row--top-40">
        <div class="col-md-12">
          <h2 class="row__title">구인중인 프로젝트</h2>
      -->
      <!-- <div class="col-1">
            <select id="showcnt" @change="changeShowCnt">
              <option value="5">5개씩 보기</option>
              <option value="10" selected>10개씩 보기</option>
              <option value="20">20개씩 보기</option>
            </select>
      </div>-->
      <!--
        </div>
      </div>
      <div class="row row--top-20">
        <div class="col-md-12">
          <div class="table-container">
            <table class="table">
              <thead class="table__thead">
                <tr>
      -->
      <!-- <th class="table__th">
                    <input id="selectAll" type="checkbox" class="table__select-row" />
      </th>-->
      <!--
                  <th class="table__th">제목</th>
                  <th class="table__th">게시인</th>
                  <th class="table__th">프로젝트명</th>
                  <th class="table__th">마감일시</th>
                  <th class="table__th">구인현황</th>
      -->
      <!-- <th class="table__th">Progress</th> -->
      <!--
                  <th class="table__th">모집분야</th>
                  <th class="table__th"></th>
                </tr>
              </thead>
              <tbody class="table__tbody">
                <recruitcomponent
                  v-for="(recruit,index) in recruits"
                  :key="index"
                  :recruit="recruit"
                  :pid="recruit.pid"
                  @delete-recruit="deleteRecruit"
                  @open-modify="openModify"
                  @modify-recruit="modifyRecruit"
                />
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
      -->
      <!-- <select name="standard" id="standard">
      <option value="t" selected>제목</option>
      <option value="w">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord" />
      <button v-on:click="searchRecruit">검색</button>-->
    </div>
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
      ["bold", "italic", "underline"],
      [{ list: "ordered" }, { list: "bullet" }],
      [ "code-block"]
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
      console.log("선택된 프로젝트넘버 : " + ppid);
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
        console.log("공백입력.");
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

      console.log(sd + " " + gg + " " + dn);
      console.log("태그길이:" + this.picks.length);
      console.log("stack is + " + this.stacks);

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
            document.querySelector(`.pk${this.cursor}`).classList.add('act');
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
      document.querySelector(`.pk${this.cursor}`).classList.remove('act');
      this.cursor--;
      if (this.cursor === -1) {
        this.cursor = this.lists.length - 1;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add('act');
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document.querySelector('.autoComplete').scrollTo({top:location, behavior:'auto'});
    },
    downQ() {
      document.querySelector(`.pk${this.cursor}`).classList.remove('act');
      this.cursor++;
      if (this.cursor === this.lists.length) {
        this.cursor = 0;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add('act');
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document.querySelector('.autoComplete').scrollTo({top:location, behavior:'auto'});
    },
    enterQ() {
      this.add(document.querySelector(`.pk${this.cursor}`).innerText)
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
@import url("https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i");

/*----------------------------------------------------------
GENERAL
----------------------------------------------------------*/
* {
  margin: 0;
  padding: 0;
  font-family: "Roboto", sans-serif;
}

html,
body {
  display: table;
  height: 100%;
  width: 100%;
  color: #252a3b;
  background-color: #f8f8f8;
}
.row__title {
  color: #53646f;
  font-weight: 400;
  font-size: 20px;
  margin: 0;
}

.row--top-40 {
  margin-top: 40px;
}

.row--top-20 {
  margin-top: 20px;
}
.table__th {
  color: #9eabb4;
  font-weight: 500;
  font-size: 12px;
  text-transform: uppercase;
  cursor: pointer;
  border: 0 !important;
  padding: 15px 8px !important;
  text-align: center;
}

.table-row {
  border-bottom: 1px solid #e4e9ea;
  background-color: #fff;
}
.table__th:hover {
  color: #01b9d1;
}

.table--select-all {
  width: 18px;
  height: 18px;
  padding: 0 !important;
  border-radius: 50%;
  border: 2px solid #becad2;
}
.table-row__td {
  text-align: center;
  padding: 12px 8px !important;
  vertical-align: middle !important;
  color: #53646f;
  font-size: 13px;
  font-weight: 400;
  position: relative;
  line-height: 18px !important;
  border: 0 !important;
}

.table-row__img {
  width: 36px;
  height: 36px;
  display: inline-block;
  border-radius: 50%;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  vertical-align: middle;
}

.table-row--chris .table-row__img {
  background-image: url("https://images.pexels.com/photos/428333/pexels-photo-428333.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
}

.table-row--angie .table-row__img {
  background-image: url("https://images.pexels.com/photos/785667/pexels-photo-785667.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
}

.table-row--ronald .table-row__img {
  background-image: url("https://images.pexels.com/photos/211050/pexels-photo-211050.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
}

.table-row--june .table-row__img {
  background-image: url("https://images.pexels.com/photos/709802/pexels-photo-709802.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
}

.table-row--ben .table-row__img {
  background-image: url("https://images.pexels.com/photos/736716/pexels-photo-736716.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
}

.table-row--natalie .table-row__img {
  background-image: url("https://images.pexels.com/photos/38554/girl-people-landscape-sun-38554.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
}

.table-row--thomas .table-row__img {
  background-image: url("https://images.pexels.com/photos/415326/pexels-photo-415326.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
}

.table-row__info {
  display: inline-block;
  padding-left: 12px;
  vertical-align: middle;
}

.table-row__name {
  color: #53646f;
  font-size: 14px;
  font-weight: 400;
  line-height: 18px;
  margin-bottom: 0px;
}

.table-row__small {
  color: #9eabb4;
  font-weight: 300;
  font-size: 12px;
}

.table-row__policy {
  color: #53646f;
  font-size: 13px;
  font-weight: 400;
  line-height: 18px;
  margin-bottom: 0px;
}
.table-row__p-status {
  margin-bottom: 0;
  font-size: 13px;
  vertical-align: middle;
  display: inline-block;
  color: #9eabb4;
}

.table-row__status {
  margin-bottom: 0;
  font-size: 13px;
  vertical-align: middle;
  display: inline-block;
  color: #9eabb4;
}

.table-row__progress {
  margin-bottom: 0;
  font-size: 13px;
  vertical-align: middle;
  display: inline-block;
  color: #9eabb4;
}

.status:before {
  content: "";
  margin-bottom: 0;
  width: 9px;
  height: 9px;
  display: inline-block;
  margin-right: 7px;
  border-radius: 50%;
}

.status--red:before {
  background-color: #e36767;
}

.status--red {
  color: #e36767;
}

.status--blue:before {
  background-color: #3fd2ea;
}

.status--blue {
  color: #3fd2ea;
}

.status--yellow:before {
  background-color: #ecce4e;
}

.status--yellow {
  color: #ecce4e;
}

.status--green {
  color: #6cdb56;
}
.status--green:before {
  background-color: #6cdb56;
}

.status--grey {
  color: #9eabb4;
}
.status--grey:before {
  background-color: #9eabb4;
}

.table__select-row {
  appearence: none;
  -moz-appearance: none;
  -o-appearance: none;
  -webkit-appearance: none;
  width: 17px;
  height: 17px;
  margin: 0 0 0 5px !important;
  vertical-align: middle;
  border: 2px solid #beccd7;
  border-radius: 50%;
  cursor: pointer;
}

.table__select-row:hover {
  border-color: #01b9d1;
}

.table__select-row:checked {
  background-image: url(data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDI2IDI2IiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCAyNiAyNiIgd2lkdGg9IjE2cHgiIGhlaWdodD0iMTZweCI+CiAgPHBhdGggZD0ibS4zLDE0Yy0wLjItMC4yLTAuMy0wLjUtMC4zLTAuN3MwLjEtMC41IDAuMy0wLjdsMS40LTEuNGMwLjQtMC40IDEtMC40IDEuNCwwbC4xLC4xIDUuNSw1LjljMC4yLDAuMiAwLjUsMC4yIDAuNywwbDEzLjQtMTMuOWgwLjF2LTguODgxNzhlLTE2YzAuNC0wLjQgMS0wLjQgMS40LDBsMS40LDEuNGMwLjQsMC40IDAuNCwxIDAsMS40bDAsMC0xNiwxNi42Yy0wLjIsMC4yLTAuNCwwLjMtMC43LDAuMy0wLjMsMC0wLjUtMC4xLTAuNy0wLjNsLTcuOC04LjQtLjItLjN6IiBmaWxsPSIjMDFiOWQxIi8+Cjwvc3ZnPgo=);
  background-position: center;
  background-size: 7px;
  background-repeat: no-repeat;
  border-color: #01b9d1;
}

.table-row--overdue {
  width: 3px;
  background-color: #e36767;
  display: inline-block;
  position: absolute;
  height: calc(100% - 24px);
  top: 50%;
  left: 0;
  transform: translateY(-50%);
}

.table-row__edit {
  width: 46px;
  padding: 8px 17px;
  display: inline-block;
  background-color: #daf3f8;
  border-radius: 18px;
  vertical-align: middle;
  margin-right: 10px;
  cursor: pointer;
}

.table-row__bin {
  width: 16px;
  display: inline-block;
  vertical-align: middle;
  cursor: pointer;
}

.table-row--red {
  background-color: #fff2f2;
}

@media screen and (max-width: 991px) {
  .table__thead {
    display: none;
  }
  .table-row {
    display: inline-block;
    border: 0;
    background-color: #fff;
    width: calc(33.3% - 13px);
    margin-right: 10px;
    margin-bottom: 10px;
  }
  .table-row__img {
    width: 42px;
    height: 42px;
    margin-bottom: 10px;
  }

  .table-row__td:before {
    content: attr(data-column);
    color: #9eabb4;
    font-weight: 500;
    font-size: 12px;
    text-transform: uppercase;
    display: block;
  }

  .table-row__info {
    display: block;
    padding-left: 0;
  }

  .table-row__td {
    display: block;
    text-align: center;
    padding: 8px !important;
  }
  .table-row--red {
    background-color: #fff2f2;
  }
  .table__select-row {
    display: none;
  }

  .table-row--overdue {
    width: 100%;
    top: 0;
    left: 0;
    transform: translateY(0%);
    height: 4px;
  }
}

@media screen and (max-width: 680px) {
  .table-row {
    width: calc(50% - 13px);
  }
}

@media screen and (max-width: 480px) {
  .table-row {
    width: 100%;
  }
}

//모달 인풋 css
.modal {
  box-sizing: border-box;
}
.modal-content {
  width: 80%;
  padding-left: 50px;
}
.modal input[type="text"],
.modal select {
  width: 80%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}
.quillWrapper.viewEditor {
  width: 80%;
  height: 500px;
  max-height: 45vh;
  border-radius: 4px;
  resize: vertical;
  margin-bottom: 100px;
}

.modal label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

.modal input[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

.modal input[type="submit"]:hover {
  background-color: #45a049;
}

// .container2 {
//   border-radius: 5px;
//   background-color: #f2f2f2;
//   padding: 20px;
// }

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
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
  width: 20%;
  min-width: 220px;
  // padding: 20px 10px 20px 20px;
  border-right: solid 1px #e0e0e0;
  float: left;
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
      border: .5px gray solid;
      position: absolute;
      max-height: 100px;
      overflow: auto;
      background-color: white;
      width: 98%;
      left: 6px;
      bottom: 45px;
      cursor: pointer;
      .act{
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
  .cb{
  min-height: 470px;
  height: 100%;
}
</style>
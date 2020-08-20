<template>
  <div class="container row" display:centered>
    <transition name="fade">
      <div class="loading">
        <span class="fa fa-spinner fa-spin"></span> Loading
      </div>
    </transition>
    <div class="col-7">
      <card class="card text-center" :title="title">
        <div>
          <ul class="list-unstyled team-members">
            <li>
              <div class="row" v-for="pm in pmlist" :key="pm.userId">
                <div class="col-2">
                  <div class="avatar">
                    <template v-if="pm.priority === 1">
                      <img
                        src="@/assets/img/crown.png"
                        alt="Circle Image"
                        class="rounded img-fluid"
                      />
                    </template>
                    <template v-if="pm.priority != 1">
                      <img
                        src="@/assets/img/shield.png"
                        alt="Circle Image"
                        class="rounded img-fluid"
                      />
                    </template>
                  </div>
                </div>
                <div class="col-2">
                  <div class="avatar">
                    <img
                      src="@/assets/img/faces/face-1.jpg"
                      alt="Circle Image"
                      class="rounded img-fluid"
                    />
                  </div>
                </div>
                <div class="col-4 text-center">
                  <div :class="getStatusClass(pm.state)">
                    <small v-if="pm.state ==='ing'">Active</small>
                    <small v-if="pm.state !='ing'" colcor="red">Disable</small>
                  </div>
                  <router-link :to="'/profile/' + pm.userId">
                    <h5>
                      <strong>{{ pm.userId }}</strong>
                    </h5>
                  </router-link>
                </div>

                <div class="col-2">
                  <p-button type="success" outline icon>
                    <i class="fa fa-envelope" @click="mes(pm.userId)"></i>
                  </p-button>
                </div>

                <div class="col-2">
                  <p-button type="info" outline icon>
                    <i
                      class="fa fa-user-plus"
                      v-if="!follow.find(item=>item.id === pm.userId)"
                      @click="fol(pm.userId)"
                    ></i>
                  </p-button>
                </div>
              </div>
            </li>
          </ul>
        </div>

        <div class="row" v-for="pm in pmlist" :key="pm.userId">
          <template v-if="pm.priority === 1">
            <template v-if="pm.userId === userId">
              <div class="col-4">
                <button class="btn btn-warning" @click="openModal2">팀장 위임하기</button>
              </div>
            </template>
          </template>

          <template v-if="pm.userId === userId">
            <div class="col-4">
              <button class="btn btn-info" @click="openModal">새 멤버 추가하기</button>
            </div>
          </template>
          <template v-if="pm.userId === userId">
            <div class="col-4">
              <button class="btn btn-danger" @click="openModal3">팀 나가기</button>
            </div>
          </template>
        </div>
        <template
          v-if="!pmlist.find(item => item.userId === userId) && !applys.find(item=>item.userId===userId)"
        >
          <button class="btn btn-info" @click="openModal4">프로젝트 팀 참여하기</button>
        </template>
        <button
          v-else-if="!pmlist.find(item => item.userId === userId)"
          class="btn btn-danger"
        >지원되었습니다.</button>
      </card>
    </div>

    <div class="col-5" v-if="pmlist.find(item => item.userId === userId &&item.priority ===1)">
      <card>
        <h5>참여 신청자</h5>
        <div v-if="applys.length>0">
          <div v-for="apply in applys" :key="apply.wno">
            <div v-if="apply.type ==='Apply'">
              {{apply.userId}}&nbsp;&nbsp; :&nbsp;&nbsp; {{apply.comment}}&nbsp;
              <button
                class="btn btn-success btn-round"
                @click="addNewMem(apply.code)"
              >수락</button> &nbsp;&nbsp;&nbsp;
              <button
                class="btn btn-danger btn-round"
                @click="delApply(apply.userId)"
              >거절</button>
            </div>
          </div>
        </div>
        <div v-else>신청자가 없습니다.</div>
      </card>
      <card>
        <h5>초대 대기자</h5>
        <div v-if="applys.length>0">
          <div v-for="apply in applys" :key="apply.wno">
            <div v-if="apply.type ==='Invite'">
              {{apply.userId}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button
                class="btn btn-danger btn-round"
                @click="delApply(apply.userId)"
              >초대 취소하기</button>
            </div>
          </div>
        </div>
        <div v-else>초대된 사람이 없습니다.</div>
      </card>
    </div>

    <!-- The Modal -->
    <div id="myModal" class="modal">
      프로젝트 팀원 추가하기
      <div class="modal-content">
        <span class="close">&times;</span>
        <div class="col-12">
          <h3>프로젝트 팀원</h3>
          <br />

          <h5>팀원으로 추가할 유저의 아이디를 입력해주세요</h5>

          <fg-input type="text" placeholder="example@naver.com" v-model="newid"></fg-input>
          <div class="checks">
            <span class="btn btn-primary" @click="checkstate('개발')">
              <input v-model="state" type="radio" value="개발" /> 개발
            </span>
            <span class="btn btn-primary" @click="checkstate('디자인')">
              <input v-model="state" type="radio" value="디자인" /> 디자인
            </span>
            <span class="btn btn-primary" @click="checkstate('기획')">
              <input v-model="state" type="radio" value="기획" />
              기획
            </span>
            <br />
            <br />
            <br />
          </div>
          <button class="btn btn-info btn-round" @click="addnewpm">팀원 추가하기</button>
          <br />
          <br />
        </div>
      </div>
    </div>
    <div id="myModal2" class="modal">
      프로젝트 리더 위임하기
      <div class="modal-content">
        <span class="myModal2close">&times;</span>
        <div class="col-12">
          <h3>프로젝트 리더 위임하기</h3>
          <br />

          <h5>프로젝트의 새로운 리더가 될 유저의 아이디를 입력해주세요</h5>
          <fg-input type="text" placeholder="example@naver.com" v-model="newleaderID"></fg-input>
          <small>고생하셨습니다, 팀장님! 멋찐 리더였어요 :)</small>
          <div>
            <br />
            <br />
            <br />
          </div>
          <button class="btn btn-info btn-round" @click="changeLeader">리더 위임하기</button>
          <br />
          <br />
        </div>
      </div>
    </div>
    <div id="myModal3" class="modal">
      프로젝트를 떠나요
      <div class="modal-content">
        <span class="close" id="modal3Close">&times;</span>
        <div class="col-12">
          <h3>팀 나가기</h3>
          <br />

          <h5>프로젝트를 떠나려면 본인의 아이디를 한 번 더 입력해주세요</h5>

          <fg-input type="text" placeholder="example@naver.com" v-model="bye"></fg-input>
          <small>정말 팀을 떠나실건가요? 하지만 언제든 지원해 다시 팀에 들어올 수 있어요!</small>
          <div>
            <br />
            <br />
            <br />
          </div>
          <button class="btn btn-info btn-round" @click="leaveTeam">팀 나가기</button>
          <br />
          <br />
        </div>
      </div>
    </div>
    <div id="myModal4" class="modal">
      신청하기
      <div class="modal-content" style="width:600px">
        <span class="close" id="modal4Close">&times;</span>
        <div class="col-12">
          <h3>신청하기</h3>
          <br />

          <fg-input type="text" v-model="applyComment"></fg-input>
          <div class="checks">
            <span class="btn btn-primary" @click="checkstate('개발')">
              <input v-model="state" type="radio" value="개발" /> 개발
            </span>
            <span class="btn btn-primary" @click="checkstate('디자인')">
              <input v-model="state" type="radio" value="디자인" /> 디자인
            </span>
            <span class="btn btn-primary" @click="checkstate('기획')">
              <input v-model="state" type="radio" value="기획" />
              기획
            </span>
          </div>
          <div>
            <br />
            <br />
            <br />
          </div>
          <button class="btn btn-info btn-round" @click="addWait">지원하기</button>
          <br />
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "@/Constant.js";
// import http from "@/http-common.js";
import partnerlist from "@/components/project/partnerlist.vue";
const storage = window.sessionStorage;
export default {
  name: "partners",
  components: { partnerlist },
  data() {
    return {
      newid: "",
      newleaderID: "",
      bye: "",
      state: "",
      test1: true,
      title: "Team members",
      pid: this.$route.params.pid,
      userId: storage.getItem("userid"),
      applyComment: "",
      applys: [],
    };
  },
  computed: {
    follow() {
      return this.$store.state.userstore.followings;
    },
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
      pid: this.$route.params.pid,
    });
    this.$store.dispatch(Constant.PROJECT_APPLY, {
      pid: this.$route.params.pid,
    });
    this.loadApplys();
  },
  methods: {
    addNewMem(code) {
      this.$store.dispatch(Constant.ADD_NEWMEMBER, { code: code });
    },
    delApply(id) {
      this.$store.dispatch(Constant.REMOVE_APPLY, {
        target: id,
        pid: this.pid,
      });
    },
    loadApplys() {
      if (
        this.$store.state.projectstore.applysId === this.$route.params.pid &&
        this.$store.state.projectstore.applys.length > 0
      ) {
        this.applys = this.$store.state.projectstore.applys;
        this.$store.commit(Constant.PROJECT_APPLY, { applys: [] });
      } else {
        this.applys = [];
        if (
          this.$store.state.projectstore.applysId !== this.$route.params.pid
        ) {
          setTimeout(() => {
            this.loadApplys();
          }, 500);
        }
      }
    },
    addWait() {
      if (this.applyComment.trim() !== "") {
        this.$store.dispatch(Constant.ADD_WAITMEMBER, {
          pid: this.pid,
          comment: this.applyComment,
          state: this.state,
        });
        document.getElementById("myModal4").style.display = "none";
        this.applyComment = "";
      }
    },
    fol: function (id) {
      this.$store.dispatch("follow", { target: id });
    },
    mes: function (id) {
      this.$store.dispatch("sendMes", { toUser: id });
    },
    openModal() {
      // console.log("눌린다");
      this.pid = this.$route.params.pid;
      // console.log(this.$store.state.projectstore.pmlist);
      // Get the modal
      let modal = document.getElementById("myModal");
      // Get the <span> element that closes the modal
      let span = document.getElementsByClassName("close")[0];
      // When the user clicks on the button, open the modal
      modal.style.display = "block";
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
    openModal2: function () {
      let modal = document.getElementById("myModal2");
      // Get the <span> element that closes the modal
      let span = document.getElementsByClassName("myModal2close")[0];
      // When the user clicks on the button, open the modal
      modal.style.display = "block";
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
    openModal3: function () {
      console.log(this.userId);
      let modal = document.getElementById("myModal3");
      // Get the <span> element that closes the modal
      let span = document.querySelector("#modal3Close");
      // When the user clicks on the button, open the modal
      modal.style.display = "block";
      // When the user clicks on <span> (x), close the modal
      span.addEventListener("click", function () {
        modal.style.display = "none";
      });
      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    },
    openModal4: function () {
      console.log(this.userId);
      let modal = document.getElementById("myModal4");
      // Get the <span> element that closes the modal
      let span = document.querySelector("#modal4Close");
      // When the user clicks on the button, open the modal
      modal.style.display = "block";
      // When the user clicks on <span> (x), close the modal
      span.addEventListener("click", function () {
        modal.style.display = "none";
      });
      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    },
    changeLeader: function () {
      if (this.newleaderID != "") {
        this.$store.dispatch("changeLeader", {
          userId: this.newleaderID,
          pid: this.$route.params.pid,
        });
      }
    },
    leaveTeam: function () {
      // console.log(userId);
      if (this.bye === this.userId) {
        this.$store.dispatch("leaveTeam", {
          userId: window.localStorage.getItem("userid"),
          pid: this.$route.params.pid,
        });
      }
    },
    addnewpm: function () {
      if (this.state != "" && this.newid != "") {
        this.$store.dispatch(Constant.ADD_INVITE, {
          userId: this.newid,
          state: this.state,
          pid: this.$route.params.pid,
        });
      }
    },

    checkstate: function (a) {
      this.state = a;
    },
    getStatusClass(status) {
      switch (status) {
        case "Offline":
          return "text-muted";
        case "ing":
          return "text-success";
        case "stop":
          return "text-danger";
        case "Busy":
          return "text-danger";
        default:
          return "text-success";
      }
    },
  },
};
</script>

<style>
#myModal,
#myModal2,
#myModal4,
#myModal3 {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  height: 100vh;
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
#myModal .modal-content,
#myModal2 .modal-content,
#myModal4 .modal-content,
#myModal3 .modal-content {
  width: 50%; /* Full width */
  height: 50%; /* Full height */
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  margin-bottom: 0;
  padding: 20px;
  border: 1px solid #888;
}
/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.myModal2close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.myModal2close:hover,
.close:focus,
.myModal2close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
.hash {
  font-size: 12px;
  color: rgb(142, 118, 248);
  margin: 4px;
}
.checks {
  display: inline;
}
.loading {
  display: none;
}
.loading.act {
  display: grid;
  text-align: center;
  position: absolute;
  color: #fff;
  z-index: 9;
  background: #aaaa88;
  padding: 8px 18px;
  border-radius: 5px;
  left: calc(50% - 45px);
  top: calc(50% - 18px);
}
</style>
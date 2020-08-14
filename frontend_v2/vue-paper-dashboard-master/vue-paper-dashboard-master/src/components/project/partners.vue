<template>
  <div class="col-7" display:centered>
    <card class="card text-center" :title="title">
      <div>
        <ul class="list-unstyled team-members">
          <li>
            <div class="row" v-for="pm in pmlist" :key="pm.userId">
              <div class="col-2">
                <div class="avatar">
                  <template v-if="pm.priority === 1">
                    <img src="@/assets/img/crown.png" alt="Circle Image" class="rounded img-fluid" />
                  </template>
                  <template v-if="pm.priority != 1">
                    <img src="@/assets/img/shield.png" alt="Circle Image" class="rounded img-fluid" />
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
                </div>
                <router-link :to="'/profile/' + pm.userId">
                  <h5>
                    <strong>{{ pm.userId }}</strong>
                  </h5>
                </router-link>
              </div>

              <div class="col-1">
                <p-button type="success" outline icon>
                  <i class="fa fa-envelope"></i>
                </p-button>
              </div>

              <div class="col-1">
                <p-button type="info" outline icon>
                  <i class="fa fa-user-plus"></i>
                </p-button>
              </div>
              <div class="col-1">
                <p-button type="info" outline icon>
                  <i class="fa fa-user-plus"></i>
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
              <button class="btn btn-warning" @click="openModal2">Change Leader</button>
            </div>
          </template>
        </template>

        <template v-if="pm.userId === userId">
          <div>{{checkmember(1)}}</div>
          <div class="col-4">
            <button class="btn btn-info" @click="openModal">+ INVITE NEW TEAM MEMBER</button>
          </div>
        </template>
        <template v-if="pm.userId === userId">
          <div class="col-4">
            <button class="btn btn-danger" @click="openModal3">Leave This Team</button>
          </div>
        </template>

        <!-- The Modal -->
      </div>
      <template v-if="test1">
        <button class="btn btn-info">프로젝트 팀 참여하기</button>
      </template>

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
          <span class="close">&times;</span>
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
          <span class="close">&times;</span>
          <div class="col-12">
            <h3>팀 나가기</h3>
            <br />

            <h5>프로젝트를 떠나려면 아래 문장을 똑같이 입력해주세요</h5>
            <h4>
              안녕히 계세요 여러분 ~!
              <br />저는 이 세상의 모든 굴레와 속박을 벗어던지고
              <br />제 행복을 찾아 떠납니다~~~!
            </h4>
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
    </card>
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
    };
  },
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  created() {
    this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
      pid: this.$route.params.pid,
    });
  },
  methods: {
    openModal() {
      // console.log("눌린다");
      this.pid = this.$route.params.pid;
      // console.log(this.$store.state.projectstore.pmlist);
      // Get the modal
      var modal = document.getElementById("myModal");
      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];
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
      var modal = document.getElementById("myModal2");
      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];
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
      var modal = document.getElementById("myModal3");
      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];
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
      if (this.bye === "z") {
        this.$store.dispatch("leaveTeam", {
          userId: window.localStorage.getItem("userid"),
          pid: this.$route.params.pid,
        });
      }
    },
    addnewpm: function () {
      if (this.state != "" && this.newid != "") {
        this.$store.dispatch("addNewMember", {
          userId: this.newid,
          state: this.state,
          pid: this.$route.params.pid,
        });
      }
    },

    checkstate: function (a) {
      this.state = a;
    },
    checkmember: function (a) {
      this.test1 = false;
    },
    getStatusClass(status) {
      switch (status) {
        case "Offline":
          return "text-muted";
        case "ing":
          return "text-success";
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
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 50%; /* Full width */
  height: 75%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
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
.hash {
  font-size: 12px;
  color: rgb(142, 118, 248);
  margin: 4px;
}
.checks {
  display: inline;
}
</style>
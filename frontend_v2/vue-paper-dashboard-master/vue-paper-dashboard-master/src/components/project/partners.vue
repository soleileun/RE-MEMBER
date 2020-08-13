<template>
  <div class="col-7" display:centered>
    <card class="card text-center" :title="title">
      <div>
        <ul class="list-unstyled team-members">
          <li>
            <div class="row" v-for="pm in pmlist" :key="pm.userId">
              <div class="col-1 center">
                <div class="avatar">
                  <img
                    src="@/assets/img/faces/face-1.jpg"
                    alt="Circle Image"
                    class="rounded img-fluid"
                  />
                </div>
              </div>
              <div class="col-2 center">
                <div class="avatar">
                  <template v-if="pm.priority === 1">
                    <img src="@/assets/img/crown.png" alt="Circle Image" class="rounded img-fluid" />
                  </template>
                  <template v-if="pm.priority != 1">
                    <img src="@/assets/img/shield.png" alt="Circle Image" class="rounded img-fluid" />
                  </template>
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
      <div class="col-12">
        <button class="btn btn-info" @click="openModal">+ INVITE NEW TEAM MEMBER</button>
        <!-- The Modal -->
      </div>
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
      state: "",
      title: "Team members",
      pid: this.$route.params.pid,
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
      console.log(this.$store.state.projectstore.pmlist);
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
    addnewpm: function () {
      // console.log("프로젝트 아이디 확인하고싶어 ㅠ");
      //console.log(this.$route.params.pid);
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
  span {
    margin: 10px;
  }
}
</style>
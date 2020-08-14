<template>
  <div class="issuecreate card" style="overflow-x:hidden; overflow-y:hidden;">
    <!-- <i class="ti-close" @click="exit"></i> -->
    <div class="biggest">
      <div class="modal-header">
        <h5 class="modal-title">이슈 추가하기</h5>
        <button type="button" class="close" @click="exit">x</button>
      </div>

      <div class="card-body" style="height:100%;">
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label>이슈 제목</label>
              <input
                type="text"
                v-model="issuetitle"
                class="form-control"
                placeholder="이슈 제목을 적으세요"
                value
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label>선제 작업</label>
              <!-- <input
                type="text"
                v-model="prework"
                class="form-control"
                placeholder="등록하려는 이슈 전 선제되야 하는 작업을 고르시오"
                value
              />-->
              <select id="myIssues" class="form-control">
                <option value="">설정 안함</option>
                <option
                  v-for="(issue,index) in issues"
                  :key="index"
                  :value="issue.issueid"
                >{{issue.issuetitle}} / {{issue.changeDay.slice(0,10)}} {{issue.changeDay.slice(11,19)}}</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-4">
            <div class="form-group">
              <label>이슈 상태</label>
              <!-- <input type="text" v-model="issuestate" class="form-control" placeholder="생성/진행/완료" /> -->
              <select v-model="issuestateSelected" class="form-control">
                <option>생성</option>
                <option>진행중</option>
                <option>완료</option>
              </select>
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <label>이슈 종류</label>
              <!-- <input type="text" v-model="issuetype" class="form-control" placeholder="에픽/스토리/태스크" /> -->
              <select v-model="issuetype" class="form-control">
                <option>에픽</option>
                <option>스토리</option>
                <option>태스크</option>
              </select>
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <label>이슈 중요도</label>
              <!-- <input type="text" v-model="priority" class="form-control" placeholder="상/중/하" /> -->
              <select v-model="priority" class="form-control">
                <option>상</option>
                <option>중</option>
                <option>하</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label>이슈 내용</label>
              <textarea
                rows="5"
                v-model="issuecontent"
                class="form-control"
                placeholder="이슈 내용을 적으세요"
                value="Mike"
              ></textarea>
            </div>
          </div>
        </div>

        <button
          v-on:click="register()"
          type="submit"
          class="btn btn-info btn-fill pull-right"
        >이슈 등록하기</button>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</template>


<script>
import Constant from "../../Constant";
const storage = window.sessionStorage;

export default {
  name: "issuecreate",
  props: {
    issues: {
      type: Array,
      required: true,
    },
  },
  data: function () {
    return {
      autologin: false,
      issuetitle: "",
      priority: "",
      issuestateSelected: "",
      issuecontent: "",
      prework: "",
      pid: 0,
      uid: "",
      issuetype: "",
    };
  },
  computed: {},

  created() {},

  methods: {
    register() {
      //alert(this.issuetitle);
      //alert(this.issuestateSelected);
      if(confirm("해당 이슈를 생성하시겠습니까?")){
        if (this.issuestateSelected == "생성") {
        this.issuestateSelected = "created";
      } else if (this.issuestateSelected == "진행중") {
        this.issuestateSelected = "ongoing";
      } else if (this.issuestateSelected == "완료") {
        this.issuestateSelected = "done";
      } else {
        alert("에러입니다");
        return;
      }
      if(this.issuetitle == "" || this.issuecontent == ""){
        alert("이슈 제목과 이슈 내용은 필수 항목입니다.");
        return;
      }
      let prework = document.getElementById("myIssues").value;
      
      this.$store.dispatch(Constant.ADD_ISSUE, {
        pid: this.$route.params.pid,
        uid: this.$route.params.userId,
        response: "",
        issuetype: this.issuetype,
        issuestate: this.issuestateSelected,
        issuetitle: this.issuetitle,
        issuecontent: this.issuecontent,
        prework: document.getElementById("myIssues").value,
        priority: this.priority,
        makeDay: "",
        startDay: "",
        endDay: "",
        changeDay: "",
      });
      alert("생성되었습니다!");
      this.exit();
      this.$router.go();
      }
    },

    exit: function () {
      document.querySelector(".issuecreate").classList.remove("active");
    },
    updateview: function () {
      document.querySelector(".updateform").classList.toggle("active");
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.row {
  padding-top: 8px;
  padding-bottom: 8px;
}
.btn {
  margin-top: 8px;
  margin-bottom: 8px;
  margin-left: 3px;
  margin-right: 3px;
}
.data {
  font-weight: bold;
}
.icontent {
  background-color: rgb(229, 239, 250);
  border-radius: 12px;
  padding: 8px;
}
.updateform {
  overflow-y: auto;
}
.updateform.active {
  margin-bottom: 20px;
  height: 47vh;
}
.issuecreate {
  visibility: hidden;
  height: 0;
  width: 0;
  position: fixed;
  top: 0;
  left: 0;
}
.issuecreate.active {
  padding: 5vh;
  visibility: visible;
  position: fixed;
  z-index: 99999;
  top: 10vh;
  left: 25vw;
  width: 60vw;
  height: 85vh;
  background-color: white;
  border: black 1.5px solid;
  i {
    position: fixed;
    top: 27vh;
    right: 15vw;
    width: 1rem;
    font-size: 1rem;
    &:hover {
      cursor: pointer;
      border-radius: 50px;
      background-color: #aaa;
    }
  }
  [data-tooltip-text]:hover {
    position: relative;
    &:after {
      content: attr(data-tooltip-text);
      position: absolute;
      bottom: 100%;
      height: 50px;
      width: 300px;
      left: 0;
      padding: 10px;
      border: 1px rgb(255, 215, 105) solid;
      background-color: rgba(0, 0, 0, 0.8);
      color: #ffffff;
      font-size: 12px;

      z-index: 9999;
    }
  }
}
@media (max-width: 1000px) {
  .issuecreate.active {
    box-sizing: border-box;
    width: 100vw;
    height: 100vh;
    top: 0px;
    left: 0px;
    i {
      top: 2vh;
      right: 30px;
    }
  }
}
.issuecreate.turn {
  animation-name: turn;
  animation-duration: 0.5s;
}
@keyframes turn {
  from {
    transform: rotateY(0deg);
  }
  49% {
    transform: rotateY(90deg);
  }
  50% {
    transform: rotateY(-90deg);
  }
  to {
    transform: rotateY(0deg);
  }
}
</style>

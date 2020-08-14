<template>
  <div
    class="issuedetail card"
    title="여기에 프로젝트 이름 / 이슈 번호"
    style="overflow-x:hidden; overflow-y:hidden;"
  >
    <i class="ti-close" @click="exit"></i>
    <div class="biggest">
      <div class="card-header">
        <div id="projectTitle" style="font-size:12px;">프로젝트 명 : {{project.pjtName}}</div>
        <div style=" font-weight:bold; font-size:18px;" id="title">제목 : {{issue.issuetitle}}</div>
      </div>
      <div class="card-body" style="height:100%;">
        <div class="icontent">{{issue.issuecontent}}</div>
        <div class="container-fluid">
          <div class="row">
            <div class="col-8">
              <div class="updateform">
                <b-collapse id="collapse-1" class="mt-2">
                  <div class="biggest">
                    <div class="modal-header">
                      <h5 class="modal-title">이슈 수정하기</h5>
                      <button type="button" class="close" @click="updateview()">x</button>
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
                              :placeholder="issue.issuetitle"
                              value
                            />
                          </div>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label>선제 작업</label>
                            <input
                              type="text"
                              v-model="prework"
                              class="form-control"
                              placeholder="등록하려는 이슈 전 선제되야 하는 작업을 고르시오"
                              value
                            />
                          </div>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-4">
                          <div class="form-group">
                            <label>이슈 상태</label>
                            <!-- <input type="text" v-model="issuestate" class="form-control" placeholder="생성/진행/완료" /> -->
                            <select v-model="issuestateSelected" class="form-control" >
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
                              :placeholder="issue.issuecontent"
                              value="Mike"
                            ></textarea>
                          </div>
                        </div>
                      </div>

                      <button
                        v-on:click="updateIssue()"
                        type="submit"
                        class="btn btn-info btn-fill pull-right"
                      >이슈 수정하기</button>
                      <div class="clearfix"></div>
                    </div>
                  </div>
                </b-collapse>
              </div>
              <div class="row">
                <div class="col-5 form">이슈 타입 :</div>
                <div class="col-7 data">{{issue.issuetype}}</div>
              </div>
              <div class="row">
                <div class="col-5 form">상태:</div>
                <div class="col-7 data" v-if="issue.issuestate == 'done'" style="color:yellowgreen">완료</div>
                <div class="col-7 data" v-if="issue.issuestate == 'created'" style="color:skyblue">생성</div>
                <div class="col-7 data" v-if="issue.issuestate == 'ongoing'" style="color:red">진행중</div>
              </div>

              <div class="row">
                <div class="col-5 form">앞선 작업:</div>
                <div class="col-7 data" v-if="issue_prework">{{issue_prework.issuetitle}}</div>
                <div class="col-7 data" v-else>없음</div>
              </div>

              <div class="row">
                <div class="col-5 form">대기중인 작업:</div>
                <div class="col-7 data">
                  {{issue.issuetitle}}
                  쿼리로 이 이슈를 선행작업으로 가지는 이슈들 보여줌
                </div>
              </div>
              <br />
              <hr />
              <div class="row">
                <div class="col-5 form" style="text-align:right;">{{issue.uid}}가</div>
                <div class="col-7 data">{{issue.startDay.slice(0,10)}}   {{issue.startDay.slice(11,19)}} 에 작성하였음.</div>
              </div>
            </div>

            <div class="col-4" style="text-align:center;">
              <h3>담당자</h3>
              <hr />
              <h4 v-if="issue.response != ''">{{issue.response}}</h4>
              <h4 v-if="issue.response == ''">없음</h4>
              <hr />
              <b-button
                pill
                variant="outline-success"
                size="sm"
                class="col-10 btn"
                @click="assign()"
              >내가 담당하기</b-button>
              <b-button
                pill
                variant="outline-warning"
                size="sm"
                class="col-10 btn"
                @click="drop()"
              >때려치기</b-button>

              <div class="col-12 btngroup">
                <b-button-group>
                  <b-button
                    v-b-toggle.collapse-1
                    variant="outline-info"
                    size="sm"
                    class="btn"
                    @click="updateview"
                  >수정</b-button>
                  <b-button @click="deleteIssue()" variant="outline-danger" size="sm" class="btn">삭제</b-button>
                </b-button-group>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- <div style="text-align:right;">최근 수정일 : {{issue.changeDay}}</div> -->
    </div>
  </div>
</template>


<script>
import Constant from "../../Constant";
const storage = window.sessionStorage;

export default {
  name: "issuedetail",
  props: {
    issue: {
      type: Object,
      required: true,
    },
    project: {
      type: Object,
      required: true,
    },
    issue_prework : {
      type: Object,
      //required : true,
    },
    issues : {
      type : Array,
      required : true,
    }
  },
  data: function () {
    return {
      autologin: false,
      issuetitle: "",
      prework: 0,
      issuestateSelected: "",
      issuetype: "",
      priority: "",
      issuecontent: "",
    };
  },
  computed: {},

  created() {},

  mounted() {
    // document.getElementById("title").innerHTML = this.issue.issuetitle;
  },

  methods: {
    updateIssue() {
      if (confirm("수정하시겠습니까?")) {
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
        this.$store.dispatch(Constant.MODIFY_ISSUE, {
          issueid : this.issue.issueid,
          pid: this.$route.params.pid,
          uid: this.$route.params.userId,
          response: this.issue.response,
          issuetype: this.issuetype,
          issuestate: this.issuestateSelected,
          issuetitle: this.issuetitle,
          issuecontent: this.issuecontent,
          prework: this.prework,
          priority: this.priority,
          makeDay: this.issue.makeDay,
          startDay: this.issue.startDay,
          endDay: this.issue.endDay,
          changeDay: "",
        });
        alert("수정되었습니다!");
        this.exit();
        this.$router.go();
      }
    },

    deleteIssue() {
      if (confirm("해당 이슈를 삭제하시겠습니까?")) {
        this.$store.dispatch(Constant.REMOVE_ISSUE, {
          issueid: this.issue.issueid,
        });
        this.exit();
        this.$router.go();
      } else {
      }
    },

    drop() {
      if (confirm("때려치시겠습니까?")) {
        this.$store.dispatch(Constant.CHANGE_RESPONSE, {
          issueid: this.issue.issueid,
          response: "",
        });
        alert("변경되었습니다.");
        this.exit();
        this.$router.go();
      }
    },

    assign() {
      if (confirm("해당 이슈를 담당하시겠습니까?")) {
        this.$store.dispatch(Constant.CHANGE_RESPONSE, {
          issueid: this.issue.issueid,
          response: this.$route.params.userId,
        });
        alert("변경되었습니다.");
        this.exit();
        this.$router.go();
      } else {
      }
    },

    exit: function () {
      document.querySelector(".issuedetail").classList.remove("active");
    },
    updateview: function () {
      document.querySelector(".updateform").classList.toggle("active");
    },

    closeUpdateForm() {
      document.querySelector(".biggest").classList.remove("active");
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
.issuedetail {
  visibility: hidden;
  height: 0;
  width: 0;
  position: fixed;
  top: 0;
  left: 0;
}
.issuedetail.active {
  padding: 5vh;
  visibility: visible;
  position: fixed;
  z-index: 99999;
  top: 25vh;
  left: 25vw;
  width: 60vw;
  height: 70vh;
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
@media (max-width: 768px) {
  .issuedetail.active {
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
.issuedetail.turn {
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

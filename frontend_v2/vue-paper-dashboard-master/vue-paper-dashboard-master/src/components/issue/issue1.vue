<template>
  <div>
    <div class="scroll-sidebar">
      <!-- Sidebar navigation-->
      <div class="sidebar-nav">
        <div class="sidebar" data-color="purple" data-image="./assets/issue_img/sidebar-5.jpg">
          <div class="sidebar-wrapper" style="background-color: #212120;">
            <div class="logo">
              <a
                href
                class="simple-text navbar-brand"
                style="font-weight:800; font-size: 18px;"
              >Project Info</a>
            </div>

            <ul class="nav">
              <li class="active test nav-item" @click="issue">
                <div class="navtest">
                  <i class="fa fa-exclamation"></i>
                  <span>이슈목록</span>
                </div>
              </li>
              <li class="active test nav-item" @click="maps">
                <div class="navtest">
                  <i class="fa fa-map-o"></i>
                  <span>지도</span>
                </div>
              </li>
              <!-- <router-link class="nav-link" :to="'/project/partners/'+this.$route.params.pid"> -->
              <li class="active test nav-item" @click="teamInfo">
                <div class="navtest">
                  <i class="fa fa-id-card-o"></i>
                  <span>팀 정보</span>
                </div>
              </li>
              <!-- </router-link> -->
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="content" v-if="issues.length != 0">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-4">
            <div class="card" style="width:100%; height:100%;">
              <div class="card-header">
                <i class="fas fa-chart-pie mr-1"></i>
                이슈 현황
              </div>
              <div class="card-body">
                <canvas id="myChart" width="100%" height="80%"></canvas>
              </div>
              <div class="card-footer small text-muted">
                <i class="fa fa-history"></i>
                Last Update {{issues[0].changeDay.slice(0,10)}} {{issues[0].changeDay.slice(11,19)}}
              </div>
            </div>
          </div>
          <!--
          <div class="col-5 " style="overflow:auto;">
            <div class="card">
              <div class="header">
                <h4 class="card-title">이슈 처리 현황 그래프</h4>
              </div>
              <div class="content" style="overflow:auto;">
                <div class="ct-chart" style="overflow:auto;">
                  <canvas id="myChart" style="height: 60%; width: 60%; display:block; max-width:300px; max-height:300px;"></canvas>
                </div>
                <div class="footer">
                  <hr />
                  <div class="stats">
                    <i class="fa fa-history"></i>
                    Last Update {{issues[0].changeDay.slice(0,10)}} {{issues[0].changeDay.slice(11,19)}}
                  </div>
                </div>
              </div>
            </div>
          </div>
          -->
          <div class="col-8">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title col-8" style="display:inline-block;">변경 이력</h4>
                <p align="right" class="category col-4" style="display:inline-block; padding:4px;">
                  <button v-on:click="createIssue()" class="btn btn-info btn-simple btn-xs">
                    <i class="fa fa-edit"></i>이슈 생성하기
                  </button>
                </p>
              </div>
              <div class="content" style="height:37vh; overflow-y:scroll; overflow-x:none;">
                <div class="overflow-auto table-responsive" style>
                  <table class="table" style="width:100%;">
                    <thead>
                      <th class="td-actions" style="width:15%;">상태</th>
                      <th style="width:65%;">제목</th>
                      <th style="width:20%;">시간</th>
                    </thead>
                    <tbody>
                      <tr v-for="(issue, index) in issues" :key="index">
                        <td class="td-actions" style="text-align:left;">
                          <div v-if="issue.issuestate == 'created'" style="color:skyblue">생성</div>
                          <div v-if="issue.issuestate == 'ongoing'" style="color:red">진행</div>
                          <div v-if="issue.issuestate == 'done'" style="color:yellowgreen">완료</div>
                        </td>
                        <td>{{issue.issuetitle}}</td>
                        <td>{{issue.changeDay.slice(5,10)}} / {{issue.changeDay.slice(11,16)}}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <div class="footer">
                <hr />
                <div class="stats">
                  <i class="fa fa-history"></i>
                  Last Update {{issues[0].changeDay.slice(0,10)}} {{issues[0].changeDay.slice(11,19)}}
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- drag and drop -->
        <div
          class="content container-fluid row col-14 card"
          style="margin-top:20px;"
          v-drag-and-drop:options="options"
        >
          <div class="card-header" style="max-height:120px;">
            <h4 class="header card-title col-8" style="display:inline-block;">이슈 현황</h4>
            <p align="right" class="category col-4" style="display:inline-block;">
              <button
                v-on:click="updateIssueChange()"
                class="btn btn-info btn-simple btn-xs"
              >변경사항 저장하기</button>
            </p>
            <hr />
          </div>
          <ul class="drag-list content" style="margin-top:10px;">
            <li class="drag-column" v-for="group in groups" :key="group.id">
              <span class="card-title">
                <h2 v-if="group.name == 'To Do'" style="color:skyblue">생성된 이슈</h2>
                <h2 v-if="group.name == 'In Progress'" style="color:red">진행중 이슈</h2>
                <h2 v-if="group.name == 'Done'" style="color:yellowgreen">완성된 이슈</h2>
                <!-- <feather-icon type="more-vertical"></feather-icon> -->
              </span>
              <vue-draggable-group
                v-model="group.items"
                :groups="groups"
                :data-id="group.id"
                @change="onGroupsChange"
              >
                <ul class="drag-inner-list" :data-id="group.id">
                  <li
                    class="drag-item"
                    v-for="item in group.items"
                    :key="item.id"
                    :data-id="item.id"
                  >
                    <div class="drag-item-text" @click="issuedetail(item.name.issueid)">
                      <span class="issueTitle">{{ item.name.issuetitle }}</span>
                      <span class="issueDate">{{ item.name.changeDay.slice(0,10) }}</span>
                    </div>
                  </li>
                </ul>
              </vue-draggable-group>
            </li>
          </ul>
          <div class="footer">
            <hr />
            <div class="stats">
              <i class="fa fa-history"></i>
              Last Update {{issues[0].changeDay.slice(0,10)}} {{issues[0].changeDay.slice(11,19)}}
            </div>
          </div>
        </div>
        <!-- drag and drop -->
      </div>
    </div>
    <div v-else>
      <div class="content">
        <h3>해당 프로젝트의 이슈가 없습니다</h3>
        <p align="right" class="category">
          <button v-on:click="createIssue()" class="btn btn-info btn-simple btn-xs">
            <i class="fa fa-edit"></i>이슈 생성하기
          </button>
        </p>
      </div>
    </div>
    <issuedetail
      :issue="issue"
      :project="project"
      :issue_prework="issue_prework"
      :issues="issues"
      :issue_by_prework="issue_by_prework"
    />
    <issuecreate :issues="issues" />
  </div>
</template>

<script>
import { VueDraggableDirective } from "vue-draggable";
import Constant from "../../Constant";
import issuedetail from "./issuedetail.vue";
import issuecreate from "./issuecreate.vue";

export default {
  components: {
    issuedetail,
    issuecreate,
  },

  directives: {
    //dragAndDrop: VueDraggableDirective,
  },
  name: "issue1",
  data() {
    const componentInstance = this;
    return {
      pid: this.$route.params.pid,
      issueid: 0,
      data: [],
      left: {},
      right: {},
      groups: [
        {
          id: 1,
          name: "To Do",
          items: [],
        },
        {
          id: 2,
          name: "In Progress",
          items: [],
        },
        {
          id: 3,
          name: "Done",
          items: [],
        },
      ],
      options: {
        dropzoneSelector: ".drag-inner-list",
        draggableSelector: ".drag-item",
        reactivityEnabled: true,
        showDropzoneAreas: true,
        multipleDropzonesItemsDraggingEnabled: true,
        onDrop(event) {
          
        },
        
        onDragend(event) {
          componentInstance.someDummyMethod();
          if (!event.droptarget) {
            
          } else {
            
          }
        },
      },
    };
  },

  created() {
    let pid = this.$route.params.pid;

    this.$store.dispatch(Constant.GET_ISSUELIST, {
      pid: pid,
    });
    this.$store.dispatch(Constant.GET_ISSUELIST_BY_STATE, {
      pid: pid,
      issuestate: "done",
    });
    this.$store.dispatch(Constant.GET_ISSUELIST_BY_STATE, {
      pid: pid,
      issuestate: "ongoing",
    });
    this.$store.dispatch(Constant.GET_ISSUELIST_BY_STATE, {
      pid: pid,
      issuestate: "created",
    });
    this.$store.dispatch(Constant.GET_PROJECT, {
      pid: this.$route.params.pid,
    });

    this.$store.dispatch(Constant.GET_ISSUE, {
      issueid: 1,
    });
    this.$store.dispatch(Constant.GET_ISSUE_PREWORK, {
      issueid: 1,
    });

    this.$store.dispatch(Constant.GET_ISSUE_BY_PREWORK, {
      prework: 1,
    });

    setTimeout(() => {
      this.loadIssues();
    }, 300);
  },

  computed: {
    issues() {
      return this.$store.state.issuestore.issues;
    },

    issues_created() {
      return this.$store.state.issuestore.issues_created;
    },

    issues_done() {
      return this.$store.state.issuestore.issues_done;
    },

    issues_ongoing() {
      return this.$store.state.issuestore.issues_ongoing;
    },
    project() {
      return this.$store.state.projectstore.project;
    },
    issue() {
      return this.$store.state.issuestore.issue;
    },
    issue_prework() {
      return this.$store.state.issuestore.issue_prework;
    },
    issue_by_prework() {
      return this.$store.state.issuestore.issue_by_prework;
    },
  },

  mounted() {
    var colors = [
      "skyblue",
      "red",
      "yellowgreen",
      "#c3e6cb",
      "#dc3545",
      "#6c757d",
    ];

    var donutOptions = {
      cutoutPercentage: 30,
      legend: {
        position: "bottom",
        padding: 5,
        labels: {
          pointStyle: "circle",
          usePointStyle: true,
        },
      },
    };
    //this.data = [30,71,11];
    setTimeout(() => {
      this.data = [];
      this.data = [
        this.issues_created.length,
        this.issues_ongoing.length,
        this.issues_done.length,
      ];
      var chDonutData1 = {
        labels: ["생성", "진행", "완료"],
        datasets: [
          {
            backgroundColor: colors.slice(0, 3),
            borderWidth: 0,
            data: this.data,
          },
        ],
      };
      var chDonut1 = document.getElementById("myChart");
      if (chDonut1) {
        new Chart(chDonut1, {
          type: "pie",
          data: chDonutData1,
          options: donutOptions,
        });
      }
    }, 300);
  },

  methods: {
    someDummyMethod() {},

    createIssue() {
      document.querySelector(".issuecreate").classList.toggle("active");
    },

    issue() {
      let addr =
        "/issuetest/" +
        this.$route.params.userId +
        "/" +
        this.$route.params.pid;
      this.$router.push(addr);
    },
    teamInfo() {
      this.$router.push("/issue/partners/" + this.$route.params.pid);
    },
    maps() {
      let addr =
        "/maps/" + this.$route.params.userId + "/" + this.$route.params.pid;
      this.$router.push(addr);
    },

    loadAll() {
      // let addr = "/issuetest/" + this.$route.params.userId + "/" + this.$route.params.pid;
      // this.$router.push(addr);
      this.$router.go();
    },
    issuedetail: function (issueid) {
      this.issueid = issueid;
      this.$store.dispatch(Constant.GET_ISSUE, {
        issueid: this.issueid,
      });
      setTimeout(() => {
        this.$store.dispatch(Constant.GET_ISSUE_PREWORK, {
          issueid: this.issue.prework,
        });
        this.$store.dispatch(Constant.GET_ISSUE_BY_PREWORK, {
          prework: this.issue.issueid,
        });
      }, 300);

      setTimeout(() => {
        document.querySelector(".issuedetail").classList.toggle("active");
      }, 100);
    },

    updateIssueChange() {
      // 저장하기

      // this.groups에는 변경사항이 적용 되어있고
      // 각각의 생성 / 진행 / 완성 이슈 수만큼
      // 반복하여 post로 수정
      // 만일 원래의 issuestate와 같다면 아무것도 변경하지 않음
      // 다르면 changeDay도 같이 변경

      this.groups[0].items.forEach((el) => {
        el.name.issuestate = "created";
        this.$store.dispatch(Constant.UPDATE_ISSUE_BY_STATE, el.name);
      });

      this.groups[1].items.forEach((el) => {
        el.name.issuestate = "ongoing";
        this.$store.dispatch(Constant.UPDATE_ISSUE_BY_STATE, el.name);
      });

      this.groups[2].items.forEach((el) => {
        el.name.issuestate = "done";
        this.$store.dispatch(Constant.UPDATE_ISSUE_BY_STATE, el.name);
      });

      setTimeout(() => {
        this.loadAll();
      }, 500);
      // -> 퀴리
      alert("변경되었습니다!");
    },

    // this.issues_created.forEach((el) => {
    //     this.groups[0].items.push({
    //       id: idx++,
    //       name: el,
    //       groupId: 1,
    //     });
    //   });

    loadIssues() {
      let idx = 1;
      this.groups[0].items = [];
      this.groups[1].items = [];
      this.groups[2].items = [];
      // this.groups[0].items = this.issues_created;
      // this.groups[1].items = this.issues_ongoing;
      // this.groups[2].items = this.issues_done;
      this.issues_created.forEach((el) => {
        this.groups[0].items.push({
          id: idx++,
          name: el,
          groupId: 1,
        });
      });

      this.issues_ongoing.forEach((el) => {
        this.groups[1].items.push({
          id: idx++,
          name: el,
          groupId: 1,
        });
      });

      this.issues_done.forEach((el) => {
        this.groups[2].items.push({
          id: idx++,
          name: el,
          groupId: 1,
        });
      });
    },

    onGroupsChange(e) {
      // 변경된 경우
    },
  },
};
</script>

<style lang="scss" scoped>
$ease-out: all 0.3s cubic-bezier(0.23, 1, 0.32, 1);
$to-do: #f4ce46;
$in-progress: #2a92bf;
$approved: #00b961;

* {
  box-sizing: border-box;
}

body {
  background: #33363d;
  color: white;
  font-family: "Roboto Mono", serif;
  font-weight: 300;
  line-height: 1.5;
  -webkit-font-smoothing: antialiased;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.drag-container {
  max-width: 1000px;
  margin: 20px auto;
}

.drag-list {
  display: flex;
  align-items: flex-start;

  @media (max-width: 500px) {
    display: block;
  }
}

.drag-column {
  flex: 1;
  margin: 0 10px;
  position: relative;
  background: rgba(white, 0.2);
  overflow: hidden;

  @media (max-width: 690px) {
    margin-bottom: 30px;
  }

  h2 {
    font-size: 0.8rem;
    margin: 0;
    text-transform: uppercase;
    font-weight: 600;
  }

  &-to-do {
    .drag-column-header,
    .drag-options {
      background: $to-do;
    }
  }

  &-in-progress {
    .drag-column-header,
    .drag-options {
      background: $in-progress;
    }
  }

  &-approved {
    .drag-column-header,
    .drag-options {
      background: $approved;
    }
  }
}

.drag-column-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  user-select: none;
}

.drag-inner-list {
  height: 40vh;
  overflow: auto;
}

.drag-item {
  margin: 10px;
  min-height: 50px;
  background: rgba(black, 0.4);
  transition: $ease-out;

  /* items grabbed state */
  &[aria-grabbed="true"] {
    background: #5cc1a6;
    color: #fff;
  }

  .drag-item-text {
    font-size: 1rem;
    padding-left: 1rem;
    padding-top: 1rem;
    .issueTitle {
      font-size: 1.3rem;
    }
    .issueDate {
      font-size: 0.8rem;
      white-space: nowrap;
    }
  }
}

.drag-header-more {
  cursor: pointer;
}

@keyframes nodeInserted {
  from {
    opacity: 0.2;
  }
  to {
    opacity: 0.8;
  }
}

.item-dropzone-area {
  height: 6rem;
  background: #888;
  opacity: 0.8;
  animation-duration: 0.5s;
  animation-name: nodeInserted;
  margin-left: 0.6rem;
  margin-right: 0.6rem;
}
.test {
  margin: 10px 0px;
  padding-left: 25px;
  padding-right: 25px;
  opacity: 0.7;
}
.navtest {
  display: block;
  padding: 0.5rem 1rem;
  color: rgba(255, 255, 255, 0.7);
  font-weight: 600;
  cursor: pointer;
}
.navtest:hover {
  color: #ffffff;
}
.drag-inner-list[data-id="1"] > li {
  border-radius: 15px;
  background-color: rgba(135, 206, 235, 0.7);
  vertical-align: middle;
  text-align: center;
  cursor: Pointer;
}
.drag-inner-list[data-id="1"] > li:active {
  cursor: move;
}
.drag-inner-list[data-id="2"] > li {
  background-color: rgba(255, 0, 0, 0.7);
  border-radius: 15px;
  vertical-align: middle;
  text-align: center;
  cursor: Pointer;
}
.drag-inner-list[data-id="2"] > li:active {
  cursor: move;
}
.drag-inner-list[data-id="3"] > li {
  background-color: rgba(154, 205, 50, 0.7);
  border-radius: 15px;
  cursor: Pointer;
  vertical-align: middle;
  text-align: center;
}
.drag-inner-list[data-id="3"] > li:active {
  cursor: move;
}
</style>

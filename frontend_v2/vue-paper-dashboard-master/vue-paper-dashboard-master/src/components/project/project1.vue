<template>
  <div class="project1">
    <div class="topp">
      <div class="pjtImg">
        <img :src="urls" alt="..." />
      </div>
      <div class="rigtt">
        <h4 class="title">{{project.pjtName}}</h4>
        <a href="#">
          <small>현재 프로젝트 인원 : {{project.cnt}} / {{project.pjtMemberCnt}}</small>
        </a>
        <div>
          팀장: {{project.makeId}}
          <span class="hashs">

          <a class="hash" v-if="project.tag1 != null">#{{project.tag1}}</a>
          <a class="hash" v-if="project.tag2 != null">#{{project.tag2}}</a>
          <a class="hash" v-if="project.tag3 != null">#{{project.tag3}}</a>
          <a class="hash" v-if="project.tag4 != null">#{{project.tag4}}</a>
          <a class="hash" v-if="project.tag5 != null">#{{project.tag5}}</a>
          
          </span>
          {{project.pjtContent}}
        </div>
        {{project.location}}
      </div>
    </div>
    <div class="text-center" style="min-width:50%">
      <div class="row">
        <div clss="col-lg-4">
          <router-link :to="'/project/partners/'+project.pid">
            <button id="myBtn" class="btn btn-round btn-dark">팀원 보기</button>
          </router-link>
        </div>
        <span></span>
        <div clss="col-lg-8">
          <button v-on:click="popup()" class="btn btn-round btn-success">일정관리페이지</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// { "pid": 96, "pjtName": "싸피 탈출", "pjtContent": "ㅁㄴㅇㅁㄴㅇ", "pjtState": "ing", "pjtMemberCnt": 5,
// "makeDay": "2020-08-18T15:00:00.000+00:00", "changeDay": null, "makeId": "sksmsmy@khu.ac.kr", "changeId": null,
// "location": "서울 강동구 천호동", "userId": "sksmsmy@khu.ac.kr", "cnt": 1, "priority": 1, "state": "ing", "tag1": "MachineVision",
// "tag2": "react-native", "tag3": null, "tag4": null, "tag5": null }
import Constant from "../../Constant";
const storage = window.sessionStorage;
export default {
  name: "project1",
  component: {},
  data: function () {
    return {
      urls: this.$store.state.filestore.fileUrl + this.project.pid + ".png",
      index: 0,
      myId: storage.getItem("userid"),
      userNick: storage.getItem("userNick"),
      // userId: storage.getItem("userid"),
      targetId: this.$route.params.userId,
      // pjtcnt:this.$store.state.projectstore.pjtcnt[this.project.pid],
    };
  },
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  mounted() {
    // console.log(this.project.pid);
    //  setTimeout(()=>{
    //    this.pjtcnt = this.$store.state.projectstore.pjtcnt[this.project.pid]
    //  },500)
  },
  created() {},
  props: {
    project: {
      type: Object,
      required: true,
    },
  },
  methods: {
    popup() {
      //배포 주소로 바꾸기! 포트도
      var url =
        "http://localhost:8081/issuetest/" +
        this.myId + //여기 세션 아이디로 교체
        "/" +
        this.project.pid;
      var name = "project detail";
      var option = "width = 1500, height = 750,  location = no";
      window.open(url, name, option);
    },
    getClasses(index) {
      var remainder = index % 3;
      if (remainder === 0) {
        return "col-lg-3 offset-lg-1";
      } else if (remainder === 2) {
        return "col-lg-4";
      } else {
        return "col-lg-3";
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
// <style scoped lang="scss">
.project1 {
  word-break: keep-all;
  margin: 20px;
  padding: 15px;
  width: 800px;
  height: 300px;
  max-width: 35vw;
  min-width: 290px;
  border: 1px solid #eeeeee;
  border-radius: 10px;
  box-shadow: gray 3px 3px 6px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  .topp {
    word-break: keep-all;
    width: 100%;
    display: flex;
    justify-content: space-between;
    h4 {
      word-break: keep-all;
      margin-top: 0;
    }
    .pjtImg {
      border: 1px solid #ccc;
      border-radius: 30px;
      width: 200px;
      height: 200px;
      margin-right: 10px;
      padding: 2px;
      display: flex;
      align-items: center;
      img {
        width: 100%;
        max-height: 100%;
      }
    }
    .rigtt {
      word-break: keep-all;
      height: 100%;
      width: 50%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      div {
        word-break: keep-all;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-content: flex-start;
        .hashs{
          display: flex;
        flex-flow: row wrap;
        justify-content: flex-start;
        align-content: flex-start;
        }
      }
    }
  }
  .row {
    display: flex;
    justify-content: space-around;
  }
}
.hash {
  font-size: 12px;
  color: rgb(142, 118, 248);
  margin: 4px;
}
</style>

<template>
  <div class="recruitcomponent">
    <!-- <div class="project1">
    <div class="topp">
      <div class="pjtImg">
        <img :src="urls" alt="..." />
      </div>
      <div class="rigtt">
        <h4 class="title">{{recruit.pjtName}}</h4>
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
    </div>-->
    <div class="card">
      <div class="imagee">
      <img :src="urls(recruit.pid)" alt="프로젝트 이미지" />

      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-12">
            <span v-if="recruit.makeId === id" class="badge badge-pill badge-warning">내프로젝트</span>
            <span v-if="recruit.rstate === '모집중'" class="badge badge-pill badge-success">모집중</span>
            <span v-if="recruit.rstate === '모집완료'" class="badge badge-pill badge-secondary">모집완료</span>
            <span v-if="recruit.rstate === '기간만료'" class="badge badge-pill badge-warning">기간만료</span>
          </div>
          <div class="col-12">
            <h3 style="display:block;cursor:pointer;" @click="move(recruit.rnum, recruit.pid)">{{recruit.title}}</h3>
          </div>
          <div class="col-12">
            <div class="container-fluid">
              <div class="row">
                <div class="col-8" style="padding-left:0;padding-right:0">
                  <div class="col-12" style="padding:0px;">
                    <a style="display:block;">{{recruit.pjtName}}</a>
                  </div>
                  <div class="inter">
                    <div v-for="(interest, idx) in recruit.interests" :key="idx" style="margin:2px; display:inline-block">#{{interest.interest}},</div>
                    <div></div>
                  </div>

                  <div class="col-12" style="max-height: 40px; padding-left:0;padding-right:0; overflow:hidden;">{{recruit.pjtContent}}</div>
                </div>
                <div class="col-4" style="padding-right:0">
                  <p v-if="recruit.rstate === '모집중'" class="table-row__status status--green status">모집중 {{recruit.cnt}}/{{recruit.pjtMemberCnt}}</p>
                  <p v-if="recruit.rstate === '모집완료'" class="table-row__status status--red status">모집완료</p>
                  <p v-if="recruit.rstate === '기한만료'" class="table-row__status status--red status">기한만료</p>
                  <a style="display:block;">지역 : {{recruit.location}}</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-6" style="margin-top:10px; padding-top:4px;">
            <hr />
            <a style="display:block;">by {{recruit.nickname}}</a>
          </div>
          <div class="col-6" style=" text-align:right; margin-top:10px; padding-top:4px;">
            <hr />
            {{recruit.makeDay.slice(0,10)}} ~ {{recruit.endDate.slice(0,10)}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
import Constant from "../../Constant";
const storage = window.sessionStorage;
import { VueEditor } from "vue2-editor";

export default {
  components: {
    VueEditor,
  },
  props: {
    recruit: {
      type: Object,
      required: true,
    },
    pid: {
      type: Number,
      required: true,
    },
    // pjtName: {
    //   type: String,
    //   required: true,
    // },
  },
  data: function () {
    return {
      id: storage.getItem("userid"),
    };
  },

  created() {
    // this.$store.dispatch(Constant.GET_PROJECT, { pid: this.pid });
  },
  computed: {
    // project() {
    //   return this.$store.state.projectstore.project;
    // },
    loginId() {
      return storage.getItem("userid");
    },
  },
  methods: {
    urls(pid){
      return this.$store.state.filestore.fileUrl + pid +'.png'
    },
    move(a, b) {
      var url = "/recruit/recruitdetail/" + a + "&" + b;
      this.$router.push(url);
    },
    imgUrl(id) {
      return this.$store.state.filestore.fileUrl + id + ".png";
    },
    times(str) {
      const year = str.slice(0, 4),
        month = str.slice(5, 7),
        day = str.slice(8, 10),
        si = str.slice(11, 13),
        boon = str.slice(14, 16),
        cho = str.slice(17, 19);
      return `${year}/${month}/${day}`;
    },
    deleteRecruit() {
      // console.log("삭제 rnum : " + this.recruit.rnum);
      this.$store.dispatch(Constant.REMOVE_RECRUIT, {
        rnum: this.recruit.rnum,
      });
    },
    openModify() {
      // Get the modal
      let mid = "myModal" + this.recruit.pid;
      var modal = document.getElementById(mid);

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
    modifyRecruit() {
      console.log("contents : " + this.recruit.contents);
      this.$store.dispatch(Constant.MODIFY_RECRUIT, {
        rnum: this.recruit.rnum,
        // pid: this.recruit.rnum,
        title: this.recruit.title,
        contents: this.recruit.contents,
        // endDate: this.recruit.endDate,
        // makeDay: this.recruit.makeDay,
        // changeDay: this.recruit.changeDay,
        // makeId: this.loginId,
        changeId: this.loginId,
      });

      let mid = "myModal" + this.recruit.pid;
      var modal = document.getElementById(mid);
      modal.style.display = "none";
    },
  },
};
</script>
  
<style scoped lang="scss">
.recruitcomponent {
  box-sizing: border-box;
  margin: 0px;
  padding: 10px;
  width: 10px;
  .card{
    word-break: keep-all;
    border: 1px #ddd solid;
    box-shadow: gray 3px 3px 7px;
    padding: 4px;
    .inter{
      color: blue;
    }
    .imagee{
      width: 100%;
      height: 300px;
      min-height: 300px;
      overflow: hidden;
      img{
        width: 100%;
      }
    }
    h3:hover{
      background-color: #bbb;
      font-size: 30px;
      border-radius: 5px;
    }
  }
}
</style>
<template>
<div>
  <card class="card-user"  display: inline-block>
    
    <div slot="image">
      <img src="@/assets/img/background.jpg" alt="..." />
    </div>
    <div>
      <div class="author">
        <img class="avatar border-white" src="@/assets/img/faces/face-2.jpg" alt="..." />
        <h4 class="title">
          {{project.pjtName}}
          <br />
          <a href="#">
            <small>현재 프로젝트 인원 : {{pjtcnt}} / {{project.pjtMemberCnt}}</small>
          </a>
        </h4>
      </div>
      <p class="description text-center">
        팀장: {{project.makeId}}
        <br />#이슈태그
        <br />
        {{project.pjtContent}}
      </p>
    </div>
    <hr />
    <div class="text-center">
      <div class="row justify-content-md-center">
        <div clss="col-lg-4">
          <button id="myBtn" @click="openModal" class="btn btn-round btn-dark">팀원보기</button>
        
        </div>
        <span></span>
        <div clss="col-lg-8">
          <button  v-on:click="popup()" class="btn btn-round btn-success">일정관리페이지</button>
        </div>
        <div clss="col-lg-8">
          <!-- <button class="btn btn-round btn-success">다른사람이 볼때 보이는 버튼</button> -->
         
        </div>

        <!-- <div v-for="(info, index) in details" :key="index" :class="getClasses(index)">
          <h5>
            {{info.title}}
            <br />
            <small>{{info.subTitle}}</small>
          </h5>
        </div>-->
      </div>
      
    </div>
  
  </card>
            <!-- The Modal -->
        <div id="myModal" class="modal">
          <!-- Modal content -->
          <div class="modal-content">
            <span class="close">&times;</span>
            <h3>프로젝트 팀원</h3>

            <ul>
              <li v-for="pm in pmlist" :key="pm.userId">{{ pm.userId }}</li>
            </ul>
          </div>
        </div>
  </div>
  <!-- <a href=""> 클릭 시 링크 설정 -->
  <!-- <router-link :to="'/project/projectdetail/'+project.pid"> -->

  <!-- </router-link> -->
</template>

<script>
import Constant from "../../Constant";
const storage = window.sessionStorage;
export default {
  name: "project1",
  component: {},
  data: function () {
    return {
      index: 0,
      myId : storage.getItem("userid"),
     userNick: storage.getItem("userNick"),
      // userId: storage.getItem("userid"),
      targetId: this.$route.params.userId,
      pjtcnt:this.$store.state.projectstore.pjtcnt[this.project.pid],
    };
  },
  computed: {
    pmlist() {
      return this.$store.state.projectstore.pmlist;
    },
  },
  mounted() {
   // console.log(this.project.pid);
   setTimeout(()=>{
     this.pjtcnt = this.$store.state.projectstore.pjtcnt[this.project.pid]

   },500) 
  },
    created() {
  },
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
        "http://localhost:8081/projectdetail.jsp?pid=" +
        this.project.pid +
        "&userId=" +
        storage.getItem('userid'); //여기 세션 아이디로 교체
      var name = "project detail";
      var option =
        "width = 1000, height = 750, top = 100, left = 200, location = no";
      window.open(url, name, option);
    },
    openModal() {
      console.log(this.project.pid);
      this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
        pid: this.project.pid,
      });
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
      this.$store.dispatch(Constant.GET_PROJECT_MEMBER_BY_PID, {
        pid: this.project.pid,
      });
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

// // 아래 css 변경 금지
// html,
// body,
// div,
// span,
// applet,
// object,
// iframes,
// h1,
// h2,
// h3,
// h4,
// h5,
// h6,
// p,
// blockquote,
// pre,
// a,
// abbr,
// acronym,
// address,
// big,
// quotes,
// code,
// del,
// dfn,
// em,
// img,
// ins,
// kbd,
// q,
// s,
// samp,
// small,
// strike,
// sub,
// sup,
// tt,
// var,
// u,
// i,
// center,
// dl,
// dt,
// dd,
// ol,
// ul,
// li,
// fieldset,
// form,
// label,
// legend,
// table,
// caption,
// tbody,
// tfoot,
// thead,
// tr,
// th,
// td,
// article,
// aside,
// canvas,
// details,
// embed,
// figure,
// figcaption,
// footer,
// header,
// hgroup,
// menu,
// nav,
// output,
// ruby,
// section,
// summary,
// time,
// mark,
// audio,
// video {
//   margin: 0;

//   padding: 0;

//   border: 0;

//   font-size: 100%;

//   do: inherit;

//   vertical-align: baseline;
// }

// article,
// aside,
// details,
// figcaption,
// figure,
// footer,
// header,
// hgroup,
// menu,
// nav,
// section {
//   display: block;
// }

// blockquote,
// q {
//   quotes: none;
// }

// blockquote:before,
// blockquote:after,
// q:before,
// q:after {
//   content: "";

//   content: none;
// }

// table {
//   border-collapse: collapse;

//   border-spacing: 0;
// }

// /*css 초기화*/

// .card {
//   height: 400px;

//   width: 20%;

//   border-radius: 15px;

//   display: inline-block;
//   // float:left;
//   margin-top: 30px;

//   margin-left: 30px;

//   margin-bottom: 0px;

//   position: relative;

//   box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

//   overflow: hidden;
// }

// .card-header {
//   -webkit-transition: 0.5s; /*사파리 & 크롬*/

//   -moz-transition: 0.5s; /*파이어폭스*/

//   -ms-transition: 0.5s; /*인터넷 익스플로러*/

//   -o-transition: 0.5s; /*오페라*/

//   transition: 0.5s;

//   width: 100%;

//   height: 270px;

//   border-radius: 15px 15px 0 0;

//   // background-image: url("images/korea.jpeg");

//   background-size: 100% 280px;

//   background-repeat: no-repeat;
// }

// .card:hover .card-header {
//   opacity: 0.8;

//   height: 100px;
// }

// .card-header-is_closed {
//   background-color: #ef5a31;

//   color: #fff;

//   font-weight: bold;

//   text-align: center;

//   float: right;

//   margin: 15px 15px 0 0;

//   border-radius: 50%;

//   font-weight: bold;

//   padding: 10px 10px;

//   line-height: 20px;
// }

// h1 {
//   font-size: 22px;

//   font-weight: bold;
// }

// .card-body {
// }

// .card-body-header {
//   line-height: 25px;

//   margin: 10px 20px 0px 20px;
// }

// .card-body-description {
//   opacity: 0;

//   color: #757b82;

//   line-height: 25px;

//   -webkit-transition: 0.2s ease-in-out; /*사파리&크롬*/

//   -moz-transition: 0.2s ease-in-out; /*파이어폭스*/

//   -ms-transition: 0.2s ease-in-out; /*익스플로러*/

//   -o-transition: 0.2s ease-in-out; /*오페라*/

//   transition: 0.2s ease-in-out;

//   overflow: hidden;

//   height: 180px;

//   margin: 5px 20px;
// }

// .card:hover .card-body-description {
//   opacity: 1;

//   -webkit-transition: 0.5s ease-in-out;

//   -moz-transition: 0.5s ease-in-out;

//   -ms-transition: 0.5s ease-in-out;

//   -o-transition: 0.5s ease-in-out;

//   transition: 0.5s ease-in-out;

//   overflow: scroll;
// }

// .card-body-hashtag {
//   color: #2478ff;

//   font-style: italic;
// }

// .card-body-footer {
//   position: absolute;
//   margin-top: 15px;
//   margin-bottom: 6px;
//   bottom: 0;
//   width: 314px;
//   font-size: 14px;
//   color: #9fa5a8;
//   padding: 0 15px;
// }

// .icon {
//   display: inline-block;

//   vertical-align: middle;

//   margin-right: 2px;
// }

// .icon-view_count {
//   width: 25px;

//   height: 17px;

//   // background: url("images/eye.jpg") no-repeat;
// }

// .icon-comments_count {
//   margin-left: 5px;

//   width: 25px;

//   height: 17px;

//   // background: url("images/comment.jpg") no-repeat;
// }

// .reg_date {
//   float: right;
// }

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 9999; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
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
</style>

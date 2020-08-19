<template>
  <div class="card col-5" style="text-align:left; margin-left:5%; margin-top:3%; padding:5px;">
    <img class="card-img-top" src="img_avatar1.png" alt="아무거나 이미지 넣고" />

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
                  <div
                    v-for="(interest, idx) in recruit.interests"
                    :key="idx"
                    style="margin:2px; display:inline-block"
                  >#{{interest.interest}},</div>
                  <div></div>
                </div>

                <div class="col-12" style="padding-left:0;padding-right:0">{{recruit.pjtContent}}</div>
              </div>
              <div class="col-4" style="padding-right:0">
                <p
                  v-if="recruit.rstate === '모집중'"
                  class="table-row__status status--green status"
                >모집중 {{recruit.cnt}}/{{recruit.pjtMemberCnt}}</p>
                <p
                  v-if="recruit.rstate === '모집완료'"
                  class="table-row__status status--red status"
                >모집완료</p>
                <p
                  v-if="recruit.rstate === '기한만료'"
                  class="table-row__status status--red status"
                >기한만료</p>
                <a style="display:block;">지역 : {{recruit.location}}</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-6" style="margin-top:10px; padding-top:4px;">
           <hr />
                  <a style="display:block;">by {{recruit.makeId}}</a>

        </div>
      <div class="col-6" style=" text-align:right; margin-top:10px; padding-top:4px;">
        <hr />
        {{recruit.makeDay.slice(0,10)}} ~ {{recruit.endDate.slice(0,10)}}
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
  data: function() {
    return {
        id : storage.getItem("userid"),
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
    move(a, b) {
    var url ='/recruit/recruitdetail/' + a + '&' + b;
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
  appearance: none;
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

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
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
.inter {
  display: inline-block;
  color: slateblue;
  max-height: 50px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>
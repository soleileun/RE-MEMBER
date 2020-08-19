<template>
  <div class="mains">
    <div class="container">
      <div class="text-center">
        <h3>
          Find Your Partner, HERE, WITH
          <strong>RE:MEMBER!</strong>
        </h3>
      </div>
      <div class="search open">
        <input type="search" class="search-box" placeholder="RE:cruit your MEMBER" />
        <span class="search-button">
          <span class="search-icon" @click="toggle"></span>
        </span>
      </div>

      <hr />
      <div v-if="this.loginId === '' ">
        <div class="container">
          <div class="row row--top-40">
            <div class="col-md-12">
              <h4 class="row__title">최근 모집글 보여주기</h4>
            </div>
          </div>
          <div class="row row--top-20">
            <div class="col-md-12 text-center">
              <div class="table-container">
                <table class="table">
                  <thead class="table__thead">
                    <tr>
                      <th class="table__th">제목</th>
                      <th class="table__th">게시인</th>
                      <th class="table__th">프로젝트명</th>
                      <th class="table__th">마감일시</th>
                      <th class="table__th">구인현황</th>
                      <!-- <th class="table__th">Progress</th> -->
                      <th class="table__th"></th>
                    </tr>
                  </thead>
                  <tbody class="table__tbody">
                    <recruitcomponent v-for="(recruit,index) in recruits" :key="index" :recruit="recruit" :pid="recruit.pid" />
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="this.loginId != '' " class="col-md-12">
        <h3>
          <strong>{{userNick}}</strong>님께 추천드리는 프로젝트입니다!
        </h3>
        <!-- <div class="dmswjdWKdWkd">
          <div class="col-4" name="rpjt" v-for="project in projects" :key="project.pid">
            <project :project="project" />
          </div>
        </div> -->
        <hr />

        <h3>
          <strong>{{userNick}}</strong>님께 추천드리는 팀원입니다!
        </h3>

        <div class="card-carousel">
          <div class="my-card">1</div>
          <div class="my-card">2</div>
          <div class="my-card">3</div>
          <div class="my-card">4</div>
          <div class="my-card">5</div>
        </div>

        <div class="dmswjdWKdWkd">
            <users />
        </div>
      </div>
    </div>
  </div>
</template>



<script>
// import 'expose-loader?$!expose-loader?jQuery!jquery';

import $ from 'jquery';
import Constant from "../../Constant";
import project from "../../components/project/project1";
import users from "../../components/pool/pick";
import http from "@/http-common.js";
import recruitcomponent from "../../components/recruit/recruitcomponent.vue";
const storage = window.sessionStorage;
export default {
  name: "mains",
  components: {
    recruitcomponent,
    project,
    users,
  },
  data: function () {
    return {
      isActive: false,
      isLoading: false,
      errorClass: "search",
      userId: storage.getItem("userid"),
      userNick: storage.getItem("userNick"),
      //  loginId: "",
    };
  },
  computed: {
    recruits() {
      return this.$store.state.recruitstore.recruits;
    },
    projects() {
      // console.log("유저스토어세ㅓ 꺼내보기..");
      // console.log(this.$store.state.userstore.recommendedPJT);
      return this.$store.state.userstore.recommendedPJT;
    },
    users() {
      console.log("유저확인");
      console.log(this.$store.state.userstore.recommendedUser);
      return this.$store.state.userstore.recommendedUser;
    },
    loginId() {
      return this.$store.state.userstore.userid;
    },
    // rusers() {
    //   //return this.$store.state.userstore.recommendedUser;
    // },
  },
  mounted() {
    let num = $(".my-card").length;
    let even = num / 2;
    let odd = (num + 1) / 2;

    if (num % 2 == 0) {
      $(".my-card:nth-child(" + even + ")").addClass("active");
      $(".my-card:nth-child(" + even + ")")
        .prev()
        .addClass("prev");
      $(".my-card:nth-child(" + even + ")")
        .next()
        .addClass("next");
    } else {
      $(".my-card:nth-child(" + odd + ")").addClass("active");
      $(".my-card:nth-child(" + odd + ")")
        .prev()
        .addClass("prev");
      $(".my-card:nth-child(" + odd + ")")
        .next()
        .addClass("next");
    }

    $(".my-card").click(function () {
      let slide = $(".active").width();
      console.log($(".active").position().left);

      if ($(this).hasClass("next")) {
        $(".card-carousel")
          .stop(false, true)
          .animate({ left: "-=" + slide });
      } else if ($(this).hasClass("prev")) {
        $(".card-carousel")
          .stop(false, true)
          .animate({ left: "+=" + slide });
      }

      $(this).removeClass("prev next");
      $(this).siblings().removeClass("prev active next");

      $(this).addClass("active");
      $(this).prev().addClass("prev");
      $(this).next().addClass("next");
    });

    // Keyboard nav
    $("html body").keydown(function (e) {
      if (e.keyCode == 37) {
        // left
        $(".active").prev().trigger("click");
      } else if (e.keyCode == 39) {
        // right
        $(".active").next().trigger("click");
      }
    });
  },
  methods: {
    toggle() {
      console.log(this.loginId);
    },
    changeShowCnt() {
      this.perPage = parseInt(document.getElementById("showcnt").value);
    },
    urls(id) {
      let url = this.$store.state.filestore.fileUrl + id + ".png";
      return url;
    },
    fol: function (id) {
      this.$store.dispatch("follow", { target: id });
    },
    toProfile(e) {
      //this.btitle = this.inputtitle;
      let addr = "/profile/" + e;
      this.$router.push(addr);
    },
    toProject(e) {
      //this.btitle = this.inputtitle;
      let addr = "/project/" + e;
      this.$router.push(addr);
    },
    follow() {
      return this.$store.state.userstore.followings;
    },
  },

  beforeCreate() {
    this.$store.dispatch(Constant.GET_RECRUITLIST);
    this.$store.dispatch("getRecommendedUser");
    this.$store.dispatch("getRecommendedPJT");
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
$background-color: #2a2e37;
$search-bg-color: #242628;
$icon-color: #dad63c;
$transition: all 0.5s ease;
* {
  box-sizing: border-box;
}

body {
  background: $background-color;
}
.dmswjdWKdWkd {
  display: flex;
  flex-flow: row wrap;
}

.search {
  width: 100px;
  height: 100px;
  margin: 40px auto 0;
  background-color: $search-bg-color;
  position: relative;
  overflow: hidden;
  transition: $transition;
  &:before {
    content: "";
    display: block;
    width: 3px;
    height: 100%;
    position: relative;
    background-color: $icon-color;
    transition: $transition;
  }
  &.open {
    width: 600px;
    &:before {
      height: 60px;
      margin: 20px 0 20px 30px;
      position: absolute;
    }
  }
}

.search-box {
  width: 100%;
  height: 100%;
  box-shadow: none;
  border: none;
  background: transparent;
  color: #fff;
  padding: 20px 100px 20px 45px;
  font-size: 40px;
  &:focus {
    outline: none;
  }
}

.search-button {
  width: 100px;
  height: 100px;
  display: block;
  position: absolute;
  right: 0;
  top: 0;
  padding: 20px;
  cursor: pointer;
}

.search-icon {
  width: 40px;
  height: 40px;
  border-radius: 40px;
  border: 3px solid $icon-color;
  display: block;
  position: relative;
  margin-left: 5px;
  transition: $transition;
  &:before {
    content: "";
    width: 3px;
    height: 15px;
    position: absolute;
    right: -2px;
    top: 30px;
    display: block;
    background-color: $icon-color;
    transform: rotate(-45deg);
    transition: $transition;
  }
  &:after {
    content: "";
    width: 3px;
    height: 15px;
    position: absolute;
    right: -12px;
    top: 40px;
    display: block;
    background-color: $icon-color;
    transform: rotate(-45deg);
    transition: $transition;
  }
  .open & {
    margin: 0;
    width: 60px;
    height: 60px;
    border-radius: 60px;
    &:before {
      transform: rotate(52deg);
      right: 22px;
      top: 23px;
      height: 18px;
    }
    &:after {
      transform: rotate(-230deg);
      right: 22px;
      top: 13px;
      height: 18px;
    }
  }
}

//캐러셀
.card-carousel {
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.card-carousel .my-card {
  height: 20rem;
  width: 12rem;
  position: relative;
  z-index: 1;
  -webkit-transform: scale(0.6) translateY(-2rem);
  transform: scale(0.6) translateY(-2rem);
  opacity: 0;
  cursor: pointer;
  pointer-events: none;
  background: #2e5266;
  background: linear-gradient(to top, #2e5266, #6e8898);
  transition: 1s;
}

.card-carousel .my-card:after {
  content: "";
  position: absolute;
  height: 2px;
  width: 100%;
  border-radius: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  bottom: -5rem;
  -webkit-filter: blur(4px);
  filter: blur(4px);
}

.card-carousel .my-card.active {
  z-index: 3;
  -webkit-transform: scale(1) translateY(0) translateX(0);
  transform: scale(1) translateY(0) translateX(0);
  opacity: 1;
  pointer-events: auto;
  transition: 1s;
}

.card-carousel .my-card.prev,
.card-carousel .my-card.next {
  z-index: 2;
  -webkit-transform: scale(0.8) translateY(-1rem) translateX(0);
  transform: scale(0.8) translateY(-1rem) translateX(0);
  opacity: 0.6;
  pointer-events: auto;
  transition: 1s;
}

.card-carousel .my-card:nth-child(0):before {
  content: "0";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  font-size: 3rem;
  font-weight: 300;
  color: #fff;
}

.card-carousel .my-card:nth-child(1):before {
  content: "1";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  font-size: 3rem;
  font-weight: 300;
  color: #fff;
}

.card-carousel .my-card:nth-child(2):before {
  content: "2";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  font-size: 3rem;
  font-weight: 300;
  color: #fff;
}

.card-carousel .my-card:nth-child(3):before {
  content: "3";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  font-size: 3rem;
  font-weight: 300;
  color: #fff;
}

.card-carousel .my-card:nth-child(4):before {
  content: "4";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  font-size: 3rem;
  font-weight: 300;
  color: #fff;
}
</style>

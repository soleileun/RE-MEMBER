<template>
  <div class="mains">
    <!-- 캐러셀 -->
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
        <h1>
          <div class="text-center">
            <img class="cimage" src="@/assets/img/newlogo.png" />
            <br />
            <strong style="color : white;">
              Find Your Partner, HERE, WITH
              <div style="color : yellow;">RE:MEMBER!</div>
            </strong>
          </div>
        </h1>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
      </b-carousel-slide>

      <!-- Slides with img slot    -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/이벤트1.jpg"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/이벤트2.png"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
    </b-carousel>

    <!-- <p class="mt-4">
      Slide #: {{ slide }}
      <br />
      Sliding: {{ sliding }}
    </p>-->

    <!-- 캐러셀 end -->

    <div class="container">
      <div class="text-center"></div>
      <!-- <div class="search open">
        <input type="search" class="search-box" placeholder="RE:cruit your MEMBER" />
        <span class="search-button">
          <span class="search-icon" @click="toggle"></span>
        </span>
      </div>-->
      <hr />

      <div style="text-align:center;">
        <img class="cimage" src="@/assets/img/newlogo.png" />
      </div>

      <br />
      <br />

      <div class="row d-flex" style="color : black;">
        <div class="col-md-3 d-flex align-self-stretch ftco-animate">
          <div class="media block-6 services d-block text-center">
            <div class="d-flex justify-content-center">
              <svg
                width="25%"
                height="25%"
                viewBox="0 0 16 16"
                class="bi bi-compass"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  d="M8 15.016a6.5 6.5 0 1 0 0-13 6.5 6.5 0 0 0 0 13zm0 1a7.5 7.5 0 1 0 0-15 7.5 7.5 0 0 0 0 15z"
                />
                <path
                  d="M6 1a1 1 0 0 1 1-1h2a1 1 0 0 1 0 2H7a1 1 0 0 1-1-1zm.94 6.44l4.95-2.83-2.83 4.95-4.95 2.83 2.83-4.95z"
                />
              </svg>
            </div>
            <div class="media-body p-2 mt-2">
              <h3 class="heading mb-3">위치 기반 서비스</h3>
              <p>같은 지역의 사람들과 프로젝트를 구해보세요! 팀원들과 모일 중간 위치를 정할 수 있어요!</p>
            </div>
          </div>
        </div>
        <div class="col-md-3 d-flex align-self-stretch ftco-animate">
          <div class="media block-6 services d-block text-center">
            <div class="d-flex justify-content-center">
              <svg
                width="25%"
                height="25%"
                viewBox="0 0 16 16"
                class="bi bi-layers-fill"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  d="M7.765 1.559a.5.5 0 0 1 .47 0l7.5 4a.5.5 0 0 1 0 .882l-7.5 4a.5.5 0 0 1-.47 0l-7.5-4a.5.5 0 0 1 0-.882l7.5-4z"
                />
                <path
                  fill-rule="evenodd"
                  d="M2.125 8.567l-1.86.992a.5.5 0 0 0 0 .882l7.5 4a.5.5 0 0 0 .47 0l7.5-4a.5.5 0 0 0 0-.882l-1.86-.992-5.17 2.756a1.5 1.5 0 0 1-1.41 0l.418-.785-.419.785-5.169-2.756z"
                />
              </svg>
            </div>
            <div class="media-body p-2 mt-2">
              <h3 class="heading mb-3">기술 스택 기반</h3>
              <p>모집하고자하는 기술 분야를 선택해 인재를 찾아보세요! 내 기술을 필요로 하는 프로젝트도 찾을 수 있어요!</p>
            </div>
          </div>
        </div>
        <div class="col-md-3 d-flex align-self-stretch ftco-animate">
          <div class="media block-6 services d-block text-center">
            <div class="d-flex justify-content-center">
              <svg
                width="25%"
                height="25%"
                viewBox="0 0 16 16"
                class="bi bi-person-circle"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"
                />
                <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                <path
                  fill-rule="evenodd"
                  d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"
                />
              </svg>
            </div>
            <div class="media-body p-2 mt-2">
              <h3 class="heading mb-3">신뢰도</h3>
              <p>이메일 인증과 프로필 사진으로 다른 사람들에게 믿음을 줘보세요!</p>
            </div>
          </div>
        </div>
        <div class="col-md-3 d-flex align-self-stretch ftco-animate">
          <div class="media block-6 services d-block text-center">
            <div class="d-flex justify-content-center">
              <svg
                width="25%"
                height="25%"
                viewBox="0 0 16 16"
                class="bi bi-list-check"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3.854 2.146a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 3.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 7.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"
                />
              </svg>
            </div>
            <div class="media-body p-2 mt-2">
              <h3 class="heading mb-3">이슈 관리 페이지</h3>
              <p>프로젝트 이슈를 관리해보세요! 동시에 팀 구성원을 관리할 수 있어요!</p>
            </div>
          </div>
        </div>
      </div>
      <hr />
      <div v-if="this.loginId === '' ">
        <div style="text-align:center;">
          <b-card title="팀원과 프로젝트를 추천해드립니다!">
            <br />
            <b-card-text style="font-size : 30px;">로그인해서 팀원과 프로젝트를 추천받아보세요!</b-card-text>
            <br />
            <br />
            <svg
              width="10%"
              height="10%"
              viewBox="0 0 16 16"
              class="bi bi-lock-fill"
              fill="currentColor"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z" />
              <path
                fill-rule="evenodd"
                d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"
              />
            </svg>
            <br />
            <br />
            <br />
            <br />
            <b-button ref="show" :disabled="show" variant="primary" @click="loginchang">로그인</b-button>
          </b-card>
        </div>
      </div>
      <div v-if="this.loginId != '' " class="col-md-12">
        <hr />

        <!-- 오버레이 -->
        <b-overlay
          :show="show"
          rounded="sm"
          @shown="onShown"
          @hidden="onHidden"
          style="text-align:center;"
        >
          <b-card title="팀원과 프로젝트를 추천해드립니다!" :aria-hidden="show ? 'true' : null">
            <br />
            <b-card-text>
              <strong>{{userNick}}</strong> 님께서 함께하시기 적합한 인재와 프로젝트를
              <strong>{{userNick}}</strong> 님의 위치, 기술 스택을 기반으로 추천해드립니다.
            </b-card-text>
            <b-card-text>버튼을 눌러 팀원과 프로젝트를 추천받아보세요!</b-card-text>
            <b-button ref="show" :disabled="show" variant="primary" @click="layout">{{btnName1}}</b-button>

            <recommend-pool v-if="showRecommendPool === false" />
            <div v-else>
              <b-card-text>
                <h3>
                  <strong>{{userNick}}</strong> 님께 추천드리는 팀원입니다!
                </h3>
              </b-card-text>
              <div class="dmswjdWKdWkd">
                <users :extendpools="users" />
              </div>
              <br />
              <br />
              <br />
              <br />
              <br />

              <hr />
              <b-card-text>
                <h3>
                  <strong>{{userNick}}</strong> 님께 추천드리는 프로젝트입니다!
                </h3>
              </b-card-text>
              <div class="dmswjdWKdWkd">
                <pick2 :projects="projects" />
              </div>
            </div>
          </b-card>
          <!-- 로딩화면 -->
          <template v-slot:overlay>
            <div class="text-center">
              <b-icon icon="stopwatch" font-scale="3" animation="cylon"></b-icon>
              <br />
              <br />
              <p id="cancel-label">
                <strong>팀원을 매칭하는 중입니다. 잠시만 기다려주세요...</strong>
              </p>
              <b-button
                ref="cancel"
                variant="outline-danger"
                size="sm"
                aria-describedby="cancel-label"
                @click="show = false"
              >Cancel</b-button>
            </div>
          </template>
        </b-overlay>
        <!-- 오버레이 end -->
      </div>
    </div>
  </div>
</template>



<script>
// import 'expose-loader?$!expose-loader?jQuery!jquery';

import $ from "jquery";
import Constant from "../../Constant";
import project from "../../components/project/project1";
import users from "../../components/pool/pick";
import http from "@/http-common.js";
import recruitcomponent from "../../components/recruit/recruitcomponent.vue";
import recommendPool from "../../components/notfound/recommendPool.vue";
import pick2 from "../../components/project/pick2.vue";
const storage = window.sessionStorage;
export default {
  name: "mains",
  components: {
    recruitcomponent,
    project,
    users,
    recommendPool,
    pick2,
  },
  data: function () {
    return {
      isActive: false,
      isLoading: false,
      errorClass: "search",
      userId: storage.getItem("userid"),
      userNick: storage.getItem("userNick"),
      //  loginId: "",
      slide: 0,
      sliding: null,
      show: false,
      showRecommendPool: false,
      btnName1: "추천받기",
    };
  },
  computed: {
    extendpools() {
      // console.log(
      //   "extendpools 호출" + this.$store.state.poolstore.extendpools.length
      // );
      return this.$store.state.poolstore.extendpools;
    },
    recruits() {
      return this.$store.state.recruitstore.recruits;
    },
    projects() {
      // console.log("유저스토어세ㅓ 꺼내보기..");
      // console.log(this.$store.state.userstore.recommendedPJT);
      // console.log(this.$store.state.projectstore.projects);
      return this.$store.state.userstore.recommendedPJT;
    },
    users() {
      // console.log("유저확인");
      // console.log(this.$store.state.userstore.recommendedUser);
      return this.$store.state.userstore.recommendedUser;
    },
    loginId() {
      return this.$store.state.userstore.userid;
    },
    // rusers() {
    //   //return this.$store.state.userstore.recommendedUser;
    // },
  },

  methods: {
    loginchang() {
      document.querySelector(".login").classList.add("active");
    },
    layout() {
      this.$store.dispatch(Constant.GET_EXTENDPOOLLIST);

      this.show = true;
      setTimeout(() => {
        this.show = false;
        this.showRecommendPool = true;
        this.btnName1 = "다시 추천받기";
      }, 3000);
    },
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    onShown() {
      // Focus the cancel button when the overlay is showing
      this.$refs.cancel.focus();
    },
    onHidden() {
      // Focus the show button when the overlay is removed
      this.$refs.show.focus();
    },
    toggle() {
      // console.log(this.loginId);
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

  created() {
    // this.$store.dispatch(Constant.GET_EXTENDPOOLLIST);
    // this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
    //   userId: "ejchoi0225@gmail.com",
    // }); //임시로 넣어놈.
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

.cimage {
  width: 30%;
  height: 20%;
}
</style>

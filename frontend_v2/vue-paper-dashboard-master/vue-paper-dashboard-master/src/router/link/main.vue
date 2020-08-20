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
        <h1><div class="text-center">
       <strong style="color : white;">Find Your Partner, HERE, WITH
          <div style="color : yellow;">RE:MEMBER!</div></strong>
      </div></h1>
      <br>  <br>
      <br>
      <br>
      <br>
      <br>
      </b-carousel-slide>

      <!-- Text slides with image -->
      <b-carousel-slide
        caption="First slide"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        img-src="https://picsum.photos/1024/480/?image=52"
      ></b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="https://picsum.photos/1024/480/?image=55"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
          a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
        </p>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}
      <br />
      Sliding: {{ sliding }}
    </p>

    <!-- 캐러셀 end -->

    <div class="container">
      <div class="text-center">
       Find Your Partner, HERE, WITH
          <strong>RE:MEMBER!</strong>
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
                    <!-- <recruitcomponent v-for="(recruit,index) in recruits" :key="index" :recruit="recruit" :pid="recruit.pid" /> -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="this.loginId != '' " class="col-md-12">
        <h3>
          <strong>{{userNick}}</strong> 님께 추천드리는 프로젝트입니다!
        </h3>
        <!-- <div class="dmswjdWKdWkd">
          <div class="col-4" name="rpjt" v-for="project in projects" :key="project.pid">
            <project :project="project" />
          </div>
        </div>-->
        <hr />

        <!-- 오버레이 -->
        <b-overlay
          :show="show"
          rounded="sm"
          @shown="onShown"
          @hidden="onHidden"
          style="text-align:center;"
        >
          <b-card title="팀원을 추천해드립니다!" :aria-hidden="show ? 'true' : null">
            <br />
            <b-card-text>
              <strong>{{userNick}}</strong> 님께서 함께하시기 적합한 인재를
              <strong>{{userNick}}</strong> 님의 위치, 기술 스택을 기반으로 추천해드립니다.
            </b-card-text>
            <b-card-text>버튼을 눌러 팀원을 추천받아보세요!</b-card-text>
            <b-button ref="show" :disabled="show" variant="primary" @click="layout" >{{btnName1}}</b-button>

            <recommend-pool v-if="showRecommendPool === false" />
            <div v-else>
              <b-card-text>
                <h3>
                  <strong>{{userNick}}</strong> 님께 추천드리는 팀원입니다!
                </h3>
              </b-card-text>
              <div class="dmswjdWKdWkd">
                <users :extendpools="extendpools" />
              </div>
              <br><br><br><br><br>
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
const storage = window.sessionStorage;
export default {
  name: "mains",
  components: {
    recruitcomponent,
    project,
    users,
    recommendPool,
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
      btnName1 : '추천받기',
    };
  },
  computed: {
    extendpools() {
      console.log(
        "extendpools 호출" + this.$store.state.poolstore.extendpools.length
      );
      return this.$store.state.poolstore.extendpools;
    },
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

  methods: {
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

  created() {
    this.$store.dispatch(Constant.GET_EXTENDPOOLLIST);
    // this.$store.dispatch("getRecommendedUser");
    // this.$store.dispatch("getRecommendedPJT");
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
</style>

<template>
  <div>
    <div class="card col-md-10 ml-auto col-xl-6 mr-auto">
      <!-- 시군구동 검색 -->

      <div class="card-body">
        <div class="row">
          <label class="col-md-2" for="sido" style="margin-left:-20px;">
            <strong>
              시도
              <span id="userid" class="text-danger"></span>
            </strong>
          </label>
          <select
            id="sido"
            class="form-control col-md-2"
            @change="changeSido(selectedSido)"
            v-model="selectedSido"
          >
            <option value="0">선택</option>
            <option v-for="(sido, index) in sidoList" :value="sido" :key="index">
              {{
              sido
              }}
            </option>
          </select>

          <label class="col-md-2" for="gugun">
            <strong>
              구군
              <span id="userid" class="text-danger"></span>
            </strong>
          </label>
          <select
            v-if="selectedSido!=0"
            id="gugun"
            class="form-control col-md-2"
            @change="changeGugun(selectedSido, selectedGugun)"
            v-model="selectedGugun"
          >
            <option value="0">선택</option>
            <option v-for="(gu, index) in gugunList" :value="gu" :key="index">
              {{
              gu
              }}
            </option>
          </select>
          <select v-else id="gugun" class="form-control col-md-2" disabled>
            <option value="0">선택</option>
          </select>

          <label class="col-md-2" for="dong">
            <strong>
              읍면동
              <span id="userid" class="text-danger"></span>
            </strong>
          </label>

          <select
            v-if="selectedGugun!=0"
            id="dong"
            class="form-control col-md-2"
            v-model="selectedDong"
          >
            <!-- @change="changeDong(selectedDong)" -->

            <option value="0">선택</option>
            <option v-for="(don, index) in dongList" :value="don" :key="index">
              {{
              don
              }}
            </option>
          </select>
          <select v-else id="dong" class="form-control col-md-2" disabled>
            <option value="0">선택</option>
          </select>
        </div>
        <hr />
        <div class="row">
          <div class="col-12 selectform">
            <div
              class="btn btn-primary btn-round"
              v-for="(pick,index) in picks"
              :key="index"
              @click="deleteStack(index)"
            >{{pick}}</div>
          </div>
        </div>
        <div class="row">
          <div class="searchform" style="width:100%;">
            <div class="row" style="margin:8px;">
              <div class="col-12">
                <p>ID :</p>
                <fg-input type="text" placeholder="아이디를 입력하세요" id="keyword1" style="width:85%;" />
              </div>
            </div>
            <div class="input">
              <div class="row" style="margin:8px;">
                <div class="col-12">
                  <p>기술 :</p>
                  <fg-input
                    id="stackWord"
                    type="text"
                    v-model="inputVal"
                    @input="searchQuery()"
                    @keyup.enter="enter()"
                    placeholder="기술 태그 입력해주세요"
                    style="width:85%;"
                  />
                </div>
              </div>
            </div>
            <table class="autoComplete" style="cursor: pointer;">
              <tr v-for="list in lists" :key="list" @click="add(list)">
                <td>{{list}}</td>
              </tr>
            </table>
          </div>
          <div class="col-12">
            <div class="button-7" style="float: none; margin: 0 auto;">
              <div class="eff-7"></div>
              <a @click="searchPool()">프로젝트 검색</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <hr />
    <!-- card layout -->
    <!--
    <ul class="cards">
      <li class="cards__item" v-for="pool in pools" :key="pool.id">
        <div class="card">
          <div class="card__image card__image--fence"></div>
          <div class="card__content">
            <div class="card__title">{{pool.nickname}}</div>
            <p class="card__text">
              <br />
              {{pool.responsibility}}
            </p>
            <router-link
              :to="'/project/' + pool.id"
              tag="button"
              class="btn btn--block card__btn"
            >프로젝트 보기</router-link>
            <router-link
              :to="'/profile/' + pool.id"
              tag="button"
              class="btn btn--block card__btn"
            >프로필 보기</router-link>
            <a
              class="btn btn--block card__btn"
              v-if="pool.git"
              :href="'https://github.com/'+pool.git"
            >Git</a>
            <button
              v-if="!follow.find(item=>item.id === pool.id)"
              class="btn btn-success"
              @click="fol(pool.id)"
            >팔로우하기</button>
          </div>
        </div>
      </li>
    </ul>
    -->
    <notfound v-if="rows === 0" />
    <div v-else>
      <select id="showcnt" @change="changeShowCnt">
        <option value="8" selected>8개씩 보기</option>
        <option value="12">12개씩 보기</option>
        <option value="16">16개씩 보기</option>
      </select>
      <ul class="cards overflow-auto" id="poolpage">
        <li
          class="cards__item"
          v-for="(pool,index) in extendpools.slice(this.perPage*(currentPage-1),perPage*(currentPage))"
          :key="index"
        >
          <div class="card">
            <div
              v-if="pool.responsibility === '디자인'"
              class="card__image card__image--designer"
              style="position:relative"
            >
              <img
                class="avatar border-white"
                :src="urls(pool.id)"
                style="position:absolute;z-index:9;bottom:0;left:0"
              />
            </div>
            <div
              v-if="pool.responsibility === '개발'"
              class="card__image card__image--developer"
              style="position:relative"
            >
              <img
                class="avatar border-white"
                :src="urls(pool.id)"
                style="position:absolute;z-index:1;bottom:0;left:0"
              />
            </div>
            <div
              v-if="pool.responsibility === '기획'"
              class="card__image card__image--head"
              style="position:relative"
            >
              <img
                class="avatar border-white"
                :src="urls(pool.id)"
                style="position:absolute;z-index:1;bottom:0;left:0"
              />
            </div>
            <div class="card__content">
              <div class="card__title">
                <div
                  style="font-weight:800; display:inline-block; margin-right:4px;"
                >{{pool.nickname}}</div>
                <p
                  v-if="pool.isValid ===1"
                  style="display:inline-block; color:rgb(79, 245, 154); font-size:10px; text-align:right; margin-bottom:3px;"
                >
                  <img v-if="pool.isValid ===1" src="@/assets/img/checklist.png" title="인증된 유저입니다" />
                </p>

                <p
                  v-else
                  style="display:inline-block; color:red;font-size:10px;text-align:right; margin-bottom:3px; "
                >
                  <img src="@/assets/img/error.png" title="미인증된 유저입니다" />
                </p>
                <div
                  style="padding-top:0px;margin-top:0px; font-size:10px; margin-bottom:16px;"
                >{{pool.id}}</div>
              </div>

              <p class="card__text" style="font-weight:bold; margin-bottom:4px;">
                <img v-if="pool.responsibility === '디자인'" src="@/assets/img/paint-palette.png" />
                <img v-if="pool.responsibility === '개발'" src="@/assets/img/programming.png" />
                <img v-if="pool.responsibility === '기획'" src="@/assets/img/procedure.png" />
                {{pool.responsibility}}
              </p>
              <div class="inter">
                <div
                  v-for="(interest, idx) in pool.interest"
                  :key="idx"
                  style="margin:2px; display:inline-block"
                >#{{interest.interest}},</div>
                <div></div>
              </div>
              {{pool.intro}}
              <hr />
              <div style="font-size: 12px;">
                <img src="@/assets/img/map-location.png" title="위치는 여기!" />
                {{pool.address2}}
              </div>

              <div>
                <!--  관심사로 사용하기
                   <div v-for="(interest, idx) in pool.interest" :key="idx">
                    {{interest.interest}}
                    
                  </div>
                -->
                <!-- 수행한 프로젝트
                     <div v-for="(pjt, idx) in pool.project" :key="idx">
                    {{pjt.pid}}
                    
                    {{pjt.pjtName}}
                  </div>
                -->
              </div>

              <div class="container-fluid">
                <div class="row">
                  <div class="col-3" style="font-size:22px; text-align:center;">
                    <i
                      class="fa fa-github emo"
                      v-b-tooltip.hover
                      title="Github"
                      v-if="pool.git != null"
                    ></i>
                  </div>
                  <div class="col-3" style="font-size:22px; text-align:center;">
                    <i
                      class="fa fa-user-plus emo"
                      v-b-tooltip.hover
                      title="팔로우"
                      v-if="!follow.find(item=>item.id === pool.id)"
                      @click="fol(pool.id)"
                    ></i>
                    <!-- v-else로 친구라고 넘겨줘도 될듯 -->
                  </div>
                  <div class="col-3" style="font-size:22px; text-align:center;">
                    <i
                      class="fa fa-id-card emo"
                      v-b-tooltip.hover
                      title="프로필"
                      @click="toProfile(pool.id)"
                    ></i>
                  </div>

                  <div class="col-3" style="font-size:22px; text-align:center;">
                    <div>
                      <button
                        v-b-modal="'test-'+pool.id"
                        style="margin:0px; padding:0px; border:0; outline:0; font-size:22px; background-color:white;"
                      >
                        <i class="fa fa-tasks emo" v-b-tooltip.hover title="프로젝트 목록"></i>
                      </button>

                      <b-modal :id="'test-'+pool.id" title="참여한 프로젝트 목록" hide-footer>
                        <div v-if="pool.project != null">
                          <div
                            v-for="(pjt, idx) in pool.project"
                            :key="idx"
                            style="font-size:20px; font-weight:bold;"
                          >
                            <div class="card">
                              <div class="card-header" style="cursor:pointer;">{{pjt.pjtName}}</div>
                              <div class="card-body">
                                <hr />
                                <p>{{pjt.pjtContent}}</p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div v-if="pool.projects == null">아직 참여한 프로젝트가 없습니다. 이번 기회에 함께 해보는건 어떨까요?</div>
                      </b-modal>
                    </div>

                    <!--  <i class="fa fa-tasks emo"></i> -->
                  </div>
                </div>
              </div>
              <div>
                <!--
            <router-link
              :to="'/project/' + pool.id"
              tag="button"
              class="btn btn--block card__btn"
            >프로젝트 보기</router-link>
            <router-link
              :to="'/profile/' + pool.id"
              tag="button"
              class="btn btn--block card__btn"
            >프로필 보기</router-link>
            <a
              class="btn btn--block card__btn"
              v-if="pool.git"
              :href="'https://github.com/'+pool.git"
            >Git</a>
            <button
              v-if="!follow.find(item=>item.id === pool.id)"
              class="btn btn-success"
              @click="fol(pool.id)"
            >팔로우하기</button>
                -->
              </div>
            </div>
            <!--
      <b-btn v-b-toggle="'test-'+pool.id"  style="none;">
    Toggle Collapse
  </b-btn>
  <b-collapse :id="'test-'+pool.id" class="mt-2">
    오우오우오오우
  </b-collapse>
            -->
            <!--
<div>
  <b-button v-b-modal="'test-'+pool.id">Launch demo modal</b-button>

  <b-modal :id="'test-'+pool.id" title="BootstrapVue">
    <p class="my-4">참여한 프로젝트 목록</p>
    <div v-for="(pjt, idx) in pool.project" :key="idx">
                    {{pjt.pid}}
                    
                    {{pjt.pjtName}}
                  </div>
  </b-modal>
</div>
            -->
          </div>
        </li>
      </ul>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="poolpage"
        align="center"
      ></b-pagination>
    </div>
  </div>
</template>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
import Constant from "../../Constant";
import http from "../../http-common.js";
import notfound from "@/components/notfound/notfound.vue";

export default {
  name: "poollist1",
  components: {
    notfound,
  },
  data() {
    return {
      //페이징
      perPage: 8,
      currentPage: 1,

      selectedSido: 0,
      selectedGugun: 0,
      selectedDong: 0,

      lists: [],
      pints: [
        "C",
        "C++",
        "Java",
        "Python",
        "Frontend",
        "Backend",
        "Spring",
        "Jenkins",
        "Django",
        "Android",
        "iOS",
        "Unity",
        "Unreal",
        "react-native",
        "Javascript",
        "node.js",
        "node.express",
        "Angular.js",
        "jQuery",
        "React.js",
        "Vue.js",
        "IoT",
        "Arduino",
        "RasberryPi",
        "Embedded",
        "Qt",
        "MachineVision",
        "BlockChain",
        "MachinLearning",
        "DB",
        "Oracle",
        "MySQL",
        "MSSQL",
        "MariaDB",
        "MongoDB",
        "GraphQL",
      ],
      picks: [],
      inputVal: "",
    };
  },
  computed: {
    rows() {
      return this.extendpools.length;
    },
    pools() {
      console.log("pool 호출");
      return this.$store.state.poolstore.pools;
    },
    extendpools() {
      console.log("extendpools 호출");
      return this.$store.state.poolstore.extendpools;
    },
    sidoList() {
      // console.log("확인" + this.$store.state.stackstore.sidolist);
      return this.$store.state.stackstore.sidolist;
    },
    gugunList() {
      return this.$store.state.stackstore.gugunlist;
    },
    dongList() {
      return this.$store.state.stackstore.donglist;
    },
    follow() {
      return this.$store.state.userstore.followings;
    },
  },
  created() {
    console.log(this.urls("abb"));
    this.$store.dispatch(Constant.GET_POOLLIST);
    this.$store.dispatch(Constant.GET_EXTENDPOOLLIST);
    console.log("디스패치 완료");

    // sido리스트 불러오기
    this.$store.dispatch(Constant.GET_SIDOLIST);
  },
  methods: {
    //페이징
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
    changeSido(selectedSido) {
      // gugun
      // console.log(selectedSido);
      this.selectedGugun = 0;
      this.selectedDong = 0;
      this.$store.dispatch(Constant.GET_GUGUNLIST, { sido: selectedSido });
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
    changeGugun(selectedSido, selectedGugun) {
      // dong
      // console.log(selectedSido + selectedGugun);
      this.selectedDong = 0;

      this.$store.dispatch(Constant.GET_DONGLIST, {
        sido: selectedSido,
        gugun: selectedGugun,
      });
    },
    // changeDong(selectedDong) {
    //   // apt

    // },

    searchPool() {
      // extendpools 반영 안 됨
      let sd = "";
      let gg = "";
      let dn = "";

      if (this.selectedSido == 0) {
        sd = " ";
      } else {
        sd = this.selectedSido;
      }
      if (this.selectedGugun == 0) {
        gg = " ";
      } else {
        gg = this.selectedGugun;
      }
      if (this.selectedDong == 0) {
        dn = " ";
      } else {
        dn = this.selectedDong;
      }
      let addr = sd + "," + gg + "," + dn + ",";
      let stacks = "";
      if (this.picks.length != 0) {
        for (let i = 0; i < this.picks.length; i++) {
          stacks += this.picks[i] + ",";
        }
      } else {
        stacks = null;
      }

      console.log(sd + " " + gg + " " + dn);
      console.log("태그길이:" + this.picks.length);
      console.log("stack is + " + stacks);

      if (
        this.selectedSido == 0 &&
        this.selectedGugun == 0 &&
        this.selectedDong == 0 &&
        this.picks.length == 0 &&
        document.getElementById("keyword1").value == ""
      ) {
        //this.$store.dispatch(Constant.GET_POOLLIST);
        this.$store.dispatch(Constant.GET_EXTENDPOOLLIST);
      } else {
        //통합
        this.$store.dispatch(Constant.SEARCH_POOLIST, {
          addr,
          stacks,
          keyword: document.getElementById("keyword1").value,
        });
      }
    },

    deleteStack(idx) {
      this.picks.splice(idx, 1);
    },

    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
      } else {
        this.lists = [];
      }
    },
    add: function (x) {
      if (this.picks.length == 5) {
        alert("검색 스택은 5개까지만 입력 가능합니다.");
        this.lists = [];
        this.inputVal = "";
      } else {
        if (x !== "") {
          if (!this.picks.find((i) => i === x)) {
            this.picks.push(x);
          }
        }
        this.inputVal = "";
        this.lists = [];
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@font-face {
  font-family: "NanumGothic";
  src: url("/fonts/NanumGothic.eot");
  src: url("/fonts/NanumGothic.eot") format("embedded-opentype"),
    url("/fonts/NanumGothic.woff") format("woff");
}

td,
tr,
th {
  width: 1000px;
  margin: 0 auto;
  text-align: center;
  border: 1px solid black;
}

//카드 레이아웃
//   @gray-darker:               #444444;
// @gray-dark:                 #696969;
// @gray:                      #999999;
// @gray-light:                #cccccc;
// @gray-lighter:              #ececec;
// @gray-lightest:             lighten(@gray-lighter,4%);

*,
*::before,
*::after {
  box-sizing: border-box;
}

html {
  background-color: #f0f0f0;
}

body {
  font-family: "NanumGothic", "serif";
  color: #999999;
  //font-family: "Roboto", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 0;
  padding: 1rem;
}

img {
  height: auto;
  max-width: 100%;
  vertical-align: middle;
}

.btn {
  // background-color: white;
  border: 1px solid #cccccc;
  //border-radius: 1rem;
  // color: #696969;
  // padding: 0.5rem;
  // text-transform: lowercase;
}

.btn--block {
  display: block;
  width: 100%;
}

.cards {
  display: flex;
  flex-flow: row wrap;
  justify-content: stretch;
  list-style: none;
  margin: 0;
  padding: 0;
}

.cards__item {
  display: flex;
  padding: 1rem;
  width: 20%;
  min-width: 250px;
  max-width: 400px;
}

.card {
  background-color: white;
  border-radius: 0.25rem;
  box-shadow: 0 20px 40px -14px rgba(0, 0, 0, 0.25);
  display: flex;
  flex-direction: column;
  overflow: hidden;
  width: 100%;

  &:hover {
    .card__image {
      filter: contrast(100%);
    }
  }
}

.card__content {
  display: flex;
  flex: 1 1 auto;
  flex-direction: column;
  padding: 1rem;
}

.card__image {
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  border-top-left-radius: 0.25rem;
  border-top-right-radius: 0.25rem;
  filter: contrast(70%);
  //filter: saturate(180%);
  overflow: hidden;
  position: relative;
  transition: filter 0.5s cubic-bezier(0.43, 0.41, 0.22, 0.91);
  &::before {
    content: "";
    display: block;
    padding-top: 56.25%; // 16:9 aspect ratio
  }
  @media (min-width: 40rem) {
    &::before {
      padding-top: 66.6%; // 3:2 aspect ratio
    }
  }
}

.card__image--flowers {
  background-image: url(https://unsplash.it/800/600?image=82);
}

.card__image--river {
  background-image: url(https://unsplash.it/800/600?image=11);
}

.card__image--record {
  background-image: url(https://unsplash.it/800/600?image=39);
}

.card__image--fence {
  background-image: url(https://unsplash.it/800/600?image=59);
}
.card__image--designer {
  background-image: url(data:image/jpeg;base64,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);
}
.card__image--head {
  background-image: url(data:image/jpeg;base64,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);
}
.card__image--developer {
  background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT7_nYqttJbwvufS2EGJ7NXrzHCNYY31kcTrw&usqp=CAU);
}

.card__title {
  color: #696969;
  font-size: 1.25rem;
  font-weight: 300;
  letter-spacing: 2px;
  text-transform: uppercase;
}

.card__text {
  flex: 1 1 auto;
  font-size: 0.875rem;
  line-height: 1.5;
  margin-bottom: 1.25rem;
}
.emo {
  cursor: pointer;
}
.inter {
  display: inline-block;
  color: slateblue;
  max-height: 50px;
  overflow-x: hidden;
  overflow-y: auto;
}
.button-7 {
  width: 140px;
  height: 50px;
  border: 2px solid #34495e;
  float: left;
  text-align: center;
  cursor: pointer;
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  margin: 0 0 40px 50px;
}
.button-7 a {
  font-family: arial;
  font-size: 16px;
  color: #34495e;
  text-decoration: none;
  line-height: 50px;
  transition: all 0.5s ease;
  z-index: 2;
  color: white;
  position: relative;
}
.eff-7 {
  width: 140px;
  height: 50px;
  border: 0px solid #34495e;
  position: absolute;
  transition: all 0.5s ease;
  z-index: 1;
  box-sizing: border-box;
}
.button-7:hover .eff-7 {
  border: 70px solid #34495e;
}
.button-7:hover a {
  color: #fff;
}
.searchform {
  position: relative;
  display: flex;
  flex-direction: column;
  // height: 50px;
  .autoComplete {
    position: absolute;
    max-height: 100px;
    overflow: auto;
    background-color: white;
    width: 85%;
    left: 5px;
    bottom: 49px;
    cursor: pointer;
  }
}
</style>
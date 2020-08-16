<template>
  <div>
    <h1>인재풀 샘플</h1>
    <hr />

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
          <div class="selectform col-12" style="float:left;">
            <div v-for="(pick,index) in picks" :key="index" style="float:left; margin:10px;">
              <div style="float:left;">
                <button>{{pick}}</button>
                <button v-on:click="deleteStack(index)">X</button>
              </div>
            </div>
          </div>
        </div>
        <p></p>
        <div class="row">
          <div class="searchform" style="width:100%;">
            <div class="row" style="margin:8px;">
              <div class="col-12">
                ID :
                <input type="text" placeholder="아이디를 입력하세요" id="keyword1" style="width:80%;" />
              </div>
            </div>
            <div class="input">
              <div class="row">
                <div class="col-12">
                  기술 :
                  <input
                    id="stackWord"
                    type="text"
                    v-model="inputVal"
                    @input="searchQuery()"
                    @keyup.enter="enter()"
                    placeholder="기술 태그 입력해주세요"
                    style="width:80%;"
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
          <div class="col-12" style="float:right;">
            <button
              @click="searchPool()"
              style="border: 2px solid rgb(173, 203, 247); border-radius:10px; margin:20px; float:right;"
            >프로젝트 검색</button>
          </div>
        </div>
      </div>
    </div>

    <hr />

    <!-- ///////////////////////////////////////////CARD///////////////////////////////////////////////////////// -->
    <!-- 무한 스크롤 -->
    <div class="container" id="app">
      <div class="list-group-wrapper">
        <transition name="fade">
          <div class="loading" v-show="loading">
            <span class="fa fa-spinner fa-spin"></span> Loading
          </div>
        </transition>
        <!-- <ul class="list-group" id="infinite-list">
          <li class="list-group-item" v-for="item in items" v-text="item"></li>
        </ul>-->
        <!-- card layout -->
        <ul class="cards list-group" id="infinite-list">
          <li class="cards__item list-group-item" v-for="pool in showlist" :key="pool.id">
            <div class="card">
              <div class="card__image card__image--fence"></div>
              <div class="card__content">
                <div class="card__title">{{pool.nickname}}</div>
                <p class="card__text">
                  <br />
                  {{pool.responsibility}}
                </p>
                <!-- <button class="btn btn--block card__btn">Button</button> -->
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
      </div>
    </div>
  </div>
</template>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
import Constant from "../../Constant";

export default {
  name: "poollist1",
  data() {
    return {
      selectedSido: 0,
      selectedGugun: 0,
      selectedDong: 0,

      //보여주는 부분
      //현 스크롤 페이지
      loading: false,
      nextItem: 0,

      lists: [],
      pints: [
        "C",
        "C++",
        "Java",
        "Python",
        "C#",
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
    showlist() {
      console.log(this.pools.length);
      return this.pools.slice(0, 8);
    },
    pools() {
      return this.$store.state.poolstore.pools;
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
    this.nextItem = 1;
    this.$store.dispatch(Constant.GET_POOLLIST);

    // sido리스트 불러오기
    this.$store.dispatch(Constant.GET_SIDOLIST);
  },
  mounted() {
    // Detect when scrolled to bottom.
    const listElm = document.querySelector("#infinite-list");
    listElm.addEventListener("scroll", (e) => {
      if (listElm.scrollTop + listElm.clientHeight >= listElm.scrollHeight) {
        this.loadMore();
      }
    });

    // Initially load some items.
    // this.loadMore();
  },
  methods: {
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
      this.nextItem = 1;
      this.showlist = [];
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
        this.$store.dispatch(Constant.GET_POOLLIST);
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

    //인피니티 스크롤 메소드
    loadMore() {
      let limit = Math.floor(this.pools.length / 8);
      console.log('리밋 : '+limit + " 현재 :" +this.nextItem);
      if(limit != 0 && this.nextItem != limit){

        /** This is only for this demo, you could
         * replace the following with code to hit
         * an endpoint to pull in more data. **/
        this.loading = true;
      setTimeout((e) => {
        let pg = 8 * this.nextItem++;

        if (this.nextItem != limit) {
          for (var i = pg; i < pg + 8; i++) {
            this.showlist.push(this.pools[i]);
          }
        } else {
          for (var i = pg; i < this.pools.length; i++) {
            this.showlist.push(this.pools[i]);
          }
        }

        this.loading = false;
      }, 200);
      /**************************************/
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
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
  color: #999999;
  font-family: "Roboto", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-style: normal;
  font-weight: 400;
  letter-spacing: 0;
  padding: 1rem;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  -moz-font-feature-settings: "liga" on;
}

img {
  height: auto;
  max-width: 100%;
  vertical-align: middle;
}

.btn {
  background-color: white;
  border: 1px solid #cccccc;
  //border-radius: 1rem;
  color: #696969;
  padding: 0.5rem;
  text-transform: lowercase;
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

// 페이징 css
$purple: #5c4084;

.container {
  // padding: 40px 80px 15px 80px;
  background-color: #fff;
  border-radius: 8px;
  // max-width: 800px;
}
.heading {
  text-align: center;
  h1 {
    background: -webkit-linear-gradient(#fff, #999);
    -webkit-text-fill-color: transparent;
    -webkit-background-clip: text;
    text-align: center;
    margin: 0 0 5px 0;
    font-weight: 900;
    font-size: 4rem;
    color: #fff;
  }
  h4 {
    color: lighten(#5c3d86, 30%);
    text-align: center;
    margin: 0 0 35px 0;
    font-weight: 400;
    font-size: 24px;
  }
}

.list-group-wrapper {
  position: relative;
}
.list-group {
  overflow: auto;
  height: 50vh;
  border: 2px solid #dce4ec;
  border-radius: 5px;
}
.list-group-item {
  margin-top: 1px;
  border-left: none;
  border-right: none;
  border-top: none;
  border-bottom: 2px solid #dce4ec;
}
.loading {
  text-align: center;
  position: absolute;
  color: #fff;
  z-index: 9;
  background: $purple;
  padding: 8px 18px;
  border-radius: 5px;
  left: calc(50% - 45px);
  top: calc(50% - 18px);
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>

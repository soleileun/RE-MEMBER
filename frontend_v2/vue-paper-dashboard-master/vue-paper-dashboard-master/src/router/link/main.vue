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
                    <recruitcomponent
                      v-for="(recruit,index) in recruits"
                      :key="index"
                      :recruit="recruit"
                      :pid="recruit.pid"
                    />
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <h3 v-if="this.loginId != '' ">
        <strong>{{userNick}}</strong>님께 추천드리는 프로젝트입니다!
      </h3>
      <div class="dmswjdWKdWkd" v-if="this.loginId != '' ">
        <div class="col-4" name="rpjt" v-for="project in projects" :key="project.pid">
          <project :project="project" />
        </div>

        <hr />
        <div>
          <h3>
            <strong>{{userNick}}</strong>님께 추천드리는 팀원입니다!
          </h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
import project from "../../components/project/project1";
import http from "@/http-common.js";
import recruitcomponent from "../../components/recruit/recruitcomponent.vue";
const storage = window.sessionStorage;
export default {
  name: "mains",
  components: {
    recruitcomponent,
    project,
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
      console.log("유저스토어세ㅓ 꺼내보기..");
      console.log(this.$store.state.userstore.recommendedPJT);
      return this.$store.state.userstore.recommendedPJT;
    },
    loginId() {
      return this.$store.state.userstore.userid;
    },
    // rusers() {
    //   //return this.$store.state.userstore.recommendedUser;
    // },
  },
  methods: {
    toggle() {
      console.log(this.loginId);
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
</style>

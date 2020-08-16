<template>
  <card class="card-user">
    <div slot="image">
      <!-- <img src="@/assets/img/whiteBg.png" alt="..." /> -->
    </div>
    <div>
      <div class="author">
        <img class="avatar border-white" :src="url" />
        <h4 class="title">
          {{userNick}}
          <br />
          <a :href="'https://github.com/'+usergit">
            <small>@{{usergit}}</small>
            <!-- <small>@{{usergit}}</small> -->
          </a>
        </h4>
      </div>
      <div class="filebox">
        <label for="ex_file">프로필변경</label>
        <input type="file" id="ex_file" @change="previewFiles" accept="image/png" />
      </div>
      <p class="description text-center">
        {{bno}} | {{userintro}}
        <br />
        <span style="color:red" v-if="userintro === '한 줄 자기소개를 입력해주세요'">개인정보 수정을 이용해 바꿔주세요</span>
      </p>
    </div>
    <hr />
    <div class="text-center">
      <div class="row">
        <div class="col-lg-3 offset-lg-1">
          <router-link :to="'/buddy/'+id">
            <h5>
              {{Myfollows}}
              <br />
              <small>Follows</small>
            </h5>
          </router-link>
        </div>
        <div class="col-lg-3 offset-mg-1">
          <router-link :to="'/buddy/' + id">
            <h5>
              {{Myfollowers}}
              <br />
              <small>Followers</small>
            </h5>
          </router-link>
        </div>

        <div class="col-lg-4">
          <h5>
            7
            <br />
            <small>Like PJT</small>
          </h5>
        </div>
      </div>
    </div>
  </card>
</template>
<script>
import http from "../../http-common.js";
const storage = window.sessionStorage;
export default {
  props: {
    bno: Number,
  },
  data() {
    return {
      // userId: storage.getItem("userid"),
      Myfollows: this.$store.state.userstore.followings.length,
      Myfollowers: this.$store.state.userstore.followers.length,
      MyPJT: 7,
      id: window.localStorage.getItem("userid"),
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userId"),
      users: storage.getItem("users"),
      usergit: storage.getItem("usergit"),
      userintro: window.localStorage.getItem("userintro"),
      url: this.$store.state.filestore.fileUrl + window.localStorage.getItem("userid") +".png",
    };
  },
  computed: {
    followings: function () {
      return this.$store.state.userstore.followings;
    },
    followers: function () {
      return this.$store.state.userstore.followers;
    },
  },
  mounted() {
    setTimeout(() => {
      this.userNick = storage.getItem("userNick");
      this.userId = storage.getItem("userId");
      this.users = storage.getItem("users");
      this.usergit = window.localStorage.getItem("usergit");
      this.userintro = window.localStorage.getItem("userintro");
    }, 500);
  },
  beforeCreate: function () {
    this.$store.dispatch("getFollow");
    this.$store.dispatch("getFollower");
  },
  methods: {
    previewFiles(event) {
      if (event.target.files[0]) {
        const fileName = window.localStorage.getItem("userid");
        const file2 = new File(
          [event.target.files[0]],
          `${fileName}.${event.target.files[0].name.split(".")[1]}`,
          {
            type: event.target.files[0].type,
          }
        );
        console.log(file2);
        // this.$store.dispatch("upFiledirect", {
        //   file: file2,
        //   bno: this.bno,
        //   reload: true,
        // });
        // let url = this.$store.state.filestore.fileUrl + file2.name;
        // this.url = url;
      }
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
<style>
.filebox {
  display: flex;
  justify-content: center;
  margin-top: -25px;
}
.filebox label {
  display: inline-block;
  padding: 0.5em 0.75em;
  margin-top: 20px;
  margin-bottom: -70px;
  color: #999;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: 0.25em;
}
.filebox input[type="file"] {
  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>

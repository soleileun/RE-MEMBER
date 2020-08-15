<template>
  <div class="row">
    <div class="col-6">
      <card>
        {{id}}님의 팔로우 목록
        <!-- <button @click="check">체크용</button> -->
        <div slot="raw-content" class="table-responsive text-center">
          <table class="table" :class="tableClass">
            <thead>
              <slot name="columns">
                <th>닉네임</th>
                <th>포지션</th>
                <th></th>
              </slot>
            </thead>
            <tbody>
              <tr v-for="(item, index) in Myfollows" :key="index">
                <slot :row="item">
                  <td>
                    <router-link :to="'/profile/' + item.id">{{item.nickname}}</router-link>
                  </td>
                  <td>{{item.responsibility}}</td>
                  <td>
                    <!-- <img src="@/assets/img/close-bl.png" @click="delFollow(item.id)" tag="button" /> -->
                    <i
                      class="fa fa-times"
                      v-b-tooltip.hover
                      title="팔로우 취소"
                      @click="delFollow(item.id)"
                    ></i>
                  </td>
                </slot>
              </tr>
            </tbody>
          </table>
        </div>
      </card>
    </div>
    <div class="col-6">
      <card>
        {{id}}님의 팔로워 목록
        <!-- <button @click="check">체크용</button> -->
        <div slot="raw-content" class="table-responsive text-center">
          <table class="table" :class="tableClass">
            <thead>
              <slot name="columns">
                <th>닉네임</th>
                <th>포지션</th>
                <!-- <th></th> -->
              </slot>
            </thead>
            <tbody>
              <tr v-for="(item, index) in Myfollowers" :key="index">
                <slot :row="item">
                  <td>
                    <router-link :to="'/profile/' + item.id">{{item.nickname}}</router-link>
                  </td>
                  <td>{{item.responsibility}}</td>
                  <!-- <td>
                    <img src="@/assets/img/close-bl.png" />
                  </td>-->
                </slot>
              </tr>
            </tbody>
          </table>
        </div>
      </card>
    </div>
  </div>
</template>
<script>
import http from "../../http-common.js";
const storage = window.sessionStorage;

export default {
  components: {},
  data() {
    return {
      Myfollows: this.$store.state.userstore.followings,
      Myfollowers: this.$store.state.userstore.followers,
      userId: storage.getItem("userid"),
      id: this.$route.params.id,
    };
  },
  computed: {
    followings: function () {
      return this.$store.state.userstore.followings;
    },
    followers: function () {
      return this.$store.state.userstore.followers;
    },
    tableClass() {
      return `table-${this.type}`;
    },
  },
  created() {
    // this.$store.dispatch("getFollow", {
    //   id: this.$route.params.id,
    // });
    // this.$store.dispatch("getFollower", {
    //   id: this.$route.params.id,
    // });
  },
  methods: {
    check: function () {
      console.log(this.Myfollowers);
      console.log(this.Myfollows);
    },
    delFollow(id) {
      console.log(id);
      this.$store.dispatch("delFollow", {
        uid: storage.getItem("userid"),
        target: id,
      });
    },
  },
};
</script>
<style>
.image-wrapper {
  background: white;
}
.img {
  mix-blend-mode: luminosity;
}
.img:hover {
  mix-blend-mode: normal;
}
</style>

<template>
  <card class="card-user">
    <div slot="image">
      <img src="@/assets/img/whiteBg.png" alt="..." />
    </div>
    <div>
      <div class="author">
        <img class="avatar border-white" src="@/assets/img/faces/face-2.jpg" alt="..." />
        <h4 class="title">
          {{userNick}}
          <br />
          <a href="#">
            <small>@Not complete</small>
            <!-- <small>@{{usergit}}</small> -->
          </a>
        </h4>
      </div>
      <p class="description text-center">
        "I like the way you work it
        <br />No diggity
        <br />I wanna bag it up"
      </p>
    </div>
    <hr />
    <div class="text-center">
      <div class="row">
        <div v-for="(info, index) in details" :key="index" :class="getClasses(index)">
          <h5>
            {{info.title}}
            <br />
            <small>{{info.subTitle}}</small>
          </h5>
        </div>
      </div>
    </div>Not Completed
  </card>
</template>
<script>
const storage = window.sessionStorage;
export default {
  data() {
    return {
      details: [
        {
          // this.$store.state.userstore.followings
          title: this.$store.state.userstore.followings.length,
          subTitle: "Following ",
        },
        {
          title: this.$store.state.userstore.followers.length,
          subTitle: "Followers",
        },
        {
          title: "7",
          subTitle: "Like PJT",
        },
      ],
      userNick: storage.getItem("userNick"),
      users: storage.getItem("users"),
      // usergit: storage.getItem("git"),
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
  created() {
    this.$store.dispatch("getFollower");
    // console.log("뭐냐고");
    console.log("팔로잉" + this.$store.state.userstore.followings);
    // console.log("팔로워");
    console.log("팔로워" + this.$store.state.userstore.followers);
  },
  beforeCreate: function () {
    // this.$store.dispatch("getFollow");
    // this.$store.dispatch("getFollower");
  },

  mounted: function () {},

  methods: {
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
</style>

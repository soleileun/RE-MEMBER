<template>
  <div class="othesprofile">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-4 col-xlg-3 col-md-5">
          <card-user></card-user>
          <br />
          <div class="card">
            <div class="card-body">
              <center class="m-t-30">
                <h4 class="card-title m-t-10">관심사</h4>
              </center>
            </div>
          </div>
        </div>
        <div class="col-lg-8 col-xlg-9 col-md-7">
          <div class="card">
            <div class="card-body">
              <profile />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profile from "../../components/user/profile.vue";
import cardUser from "../../components/user/cardUser.vue";
const storage = window.sessionStorage;
// 

export default {
  name: "othesprofile",
  components: {
    profile,
    cardUser,
  },
  mounted: function () {
    this.$store.dispatch("getOthersProfile",{id:this.$route.params.userid});
  },
  data: function () {
    return {
      userNick: storage.getItem("otherNick"),
    };
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
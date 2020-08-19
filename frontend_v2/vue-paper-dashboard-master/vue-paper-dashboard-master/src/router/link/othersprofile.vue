<template>
  <div class="othesprofile">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-4 col-xlg-3 col-md-5">
          <card-user :btitle="btitle" :userid="this.$route.params.userid"></card-user>
        </div>
        <div class="col-lg-8 col-xlg-9 col-md-7">
          <div class="card">
            <div class="card-body">
              <profile :userid="this.$route.params.userid" :foo="btitle" @btitle="val=>btitle = val"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profile from "../../components/user/otherprofile.vue";
import cardUser from "../../components/user/othercardUser.vue";
const storage = window.sessionStorage;
// 

export default {
  name: "othesprofile",
  components: {
    cardUser,
    profile,
  },
  data: function () {
    return {
      userNick: storage.getItem("otherNick"),
      btitle:'',

    };
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
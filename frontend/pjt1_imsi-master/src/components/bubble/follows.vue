<template>
  <div class="follows">
    <div v-if="followings.length===0">팔로우한 사람이 없습니다.</div>
    <div class="dropdown" v-for="target in followings" :key="target.target">
      <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{target.target}}</button>
      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <div class="dropdown-item" @click="mes(target.target)">메세지</div>
        <div class="dropdown-item">프로필 보기</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "follows",
  data: function () {
    return {};
  },
  beforeCreate: function () {
    this.$store.dispatch("getFollow");
  },
  computed: {
    followings: function () {
      return this.$store.state.userstore.followings;
    },
  },
  methods: {
    mes: function (id) {
      this.$store.dispatch("sendMes", { toUser: id });
    },
    fol: function (id) {
      this.$store.dispatch("follow", { target: id });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.follows {
  padding-top: 15px; 
  display: flex;
  flex-direction: column;
  justify-items: flex-start;
  align-content: stretch;
  .dropdown {
    margin: 5px;
    padding: 0px 10px 0px 10px;
    .dropdown-toggle {
      background-color: #aae;
      width: 100%;
      height: 50px;
    }
  }
  &::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  &::-webkit-scrollbar-track {
    background-color: #aae;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
}
</style>

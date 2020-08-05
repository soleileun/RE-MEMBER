<template>
  <div class="signup">
    <signupTerm v-if="states === 1" @nxt="agree" />
    <signupform v-else-if="states === 2" />
  </div>
</template>

<script>
import signupTerm from "../../components/signup/signupterm.vue";
import signupform from "../../components/signup/signupform.vue";

export default {
  name: "signup",
  components: {
    signupform,
    signupTerm,
  },
  data: function () {
    return {
      states: 2,
    };
  },
  methods: {
    agree:function(){
      this.states = 2
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.signup {
  padding: 50px;
}
</style>

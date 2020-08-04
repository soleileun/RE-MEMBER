<template>
  <div class="signup">
    <signupTerm v-if="states === 1" @nxt="agree" />
    <signupform v-else-if="states === 2"  @nxt="signUp" />
    <signupInterest v-else-if="states === 3" />
  </div>
</template>

<script>
import signupTerm from "../../components/signup/signupterm.vue";
import signupform from "../../components/signup/signupform.vue";
import signupInterest from "../../components/signup/signupInterest.vue";

export default {
  name: "signup",
  components: {
    signupform,
    signupTerm,
    signupInterest,
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
    signUp:function(){
      this.states = 3
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.signup {
  padding: 50px;
}
</style>

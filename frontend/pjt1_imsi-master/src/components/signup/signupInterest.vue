<template>
  <div class="signupinterest">
    <h3>관심 분야 및 스택</h3>
    <div>
      <span v-for="pint in pints" :key="pint.pk">
        <input type="checkbox" v-model="picks" :value="pint.title"/>{{pint.title}} |
      </span>
      <br />
      <input type="text" v-model="intVal" @keyup.enter="add"/>
      <button @click="add">직접 입력</button>
    </div>
    {{picks}}
    <button @click="goSignin" class="gosignin" :class="{submitable:submitable}">선택 완료</button>
  </div>
</template>

<script>
export default {
  name: "signupinterest",
  created() {},
  data: function () {
    return {
      pk: 0,
      pints: [
        { 
          pk:0,
          title:"frontend"
        },
        { 
          pk:1,
          title:"backend"
        },
        { 
          pk:2,
          title:"DB"
        },
        { 
          pk:3,
          title:"JAVA"
        },
        { 
          pk:4,
          title:"vue.js"
        }
      ],
      picks: [],
      submitable: false,
      intVal: "",
    };
  },
  watch: {
    picks: function (a) {
      this.submitable = a.length > 0 ? true : false;
    },
  },
  methods: {
    goSignin: function () {},
    add: function () {
      if (this.intVal !== "") {
        this.pints.push({
          pk: ++this.pk,
          title: this.intVal,
        });
        this.picks.push(this.intVal);
      }
      this.intVal = "";
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.gosignin {
  opacity: 0.2;
}
.submitable {
  opacity: 1;
}
</style>

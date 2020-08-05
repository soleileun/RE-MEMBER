<template>
  <div class="signupinterest">
    <h3>관심 분야 및 스택</h3>
    <div>
      <ul>
        예 :
        <li>Language : C / C++ / Java / Python / C#</li>
        <li>web FrontEnd / BackEnd / Spring / Jenkins</li>
        <li>Mobile -> Hybrid / Android / iOS /</li>
        <li>Javascript -> node.js / Angular.JS / React.js / Vue.js / jQuery</li>
        <li>IoT : Aduino / RaspberryPi / Embedded / Qt / MachineVision</li>
        <li>DB : Oracle / MSSQL / MySQL / MariaDB / MongoDB</li>
      </ul>
    </div>
    {{signup}}{{submitable}}
    <div class="selectform">
      <button v-for="pick in picks" :key="pick">{{pick}}</button>
    </div>
    입력
    <div class="searchform">
      <div class="input">
        <input type="text" v-model="inputVal" @input="searchQuery()" @keyup.up="up()" @keyup.down="down()" @keyup.enter="enter()" />
      </div>
      <div class="autoComplete">
        <div v-for="list in lists" :key="list" @click="add(list)">{{list}}</div>
      </div>
    </div>

    <button @click="gosignup" class="gosignup" :class="{submitable:submitable}">회원가입</button>
  </div>
</template>

<script>
export default {
  name: "signupinterest",
  created() {},
  props: {
    signup: Boolean,
  },
  data: function () {
    return {
      idx: 0,
      lists: [],
      pints: [
        "C",
        "C++",
        "Java",
        "Python",
        "C#",
        "Frontend",
        "Backend",
        "Spring",
        "Jenkins",
        "Django",
        "Android",
        "iOS",
        "Unity",
        "Unreal",
        "react-native",
        "Javascript",
        "node.js",
        "node.express",
        "Angular.js",
        "jQuery",
        "React.js",
        "Vue.js",
        "IoT",
        "Arduino",
        "RasberryPi",
        "Embedded",
        "Qt",
        "MachineVision",
        "BlockChain",
        "MachinLearning",
        "DB",
        "Oracle",
        "MySQL",
        "MSSQL",
        "MariaDB",
        "MongoDB",
        "GraphQL",
      ],
      picks: [],
      submitable: false,
      inputVal: "",
    };
  },
  watch: {
    signup:function(){
      this.check()
    },
    picks: function () {
      this.check()
    },
  },
  computed: {},
  methods: {
    check: function(){
      if (this.signup === 0){
        this.submitable = false
      }
      else{
        this.submitable = this.picks.length > 0 ? true : false;
      }
    },
    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
      } else {
        this.lists = [];
      }
    },
    gosignup: function () {
      if (this.submitable) {
        this.$emit('goSignup')
        this.$store.commit('interest',{picks:this.picks})
      }
    },
    add: function (x) {
      if (x !== "") {
        if (!this.picks.find((i) => i === x)) {
          this.picks.push(x);
        }
      }
      this.inputVal = "";
      this.lists = [];
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.signupinterest {
  ul {
    text-align: left;
    li {
      list-style-type: none;
    }
  }
  display: flex;
  flex-direction: column;
  align-items: center;
  .selectform {
    border: 1px solid black;
    min-height: 100px;
    width: 500px;
    margin: 50px;
  }
  .searchform {
    position: relative;
    display: flex;
    flex-direction: column;
    border: 1px black solid;
    width: 500px;
    // height: 50px;
    .autoComplete {
      position: absolute;
      max-height: 100px;
      overflow: auto;
      background-color: white;
      border: 1px black solid;
      width: 500px;
      left: -1px;
      bottom: 49px;
      cursor: pointer;
    }
    .input {
      height: 50px;
      font-size: 1.2rem;
      border: 1px black solid;
      padding: 5px;
      input {
        border: none;
        outline: none;
        &:focus {
          border: none;
        }
      }
    }
  }
  .gosignup {
    opacity: 0.2;
  }
  .submitable {
    opacity: 1;
  }
}
</style>

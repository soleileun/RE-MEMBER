<template>
  <card class="signupinterest card">
    <div>
      <h3>관심 분야 및 스택</h3>
      <form @submit.prevent>
        <div class="row">
          <div class="col-12">
            <ul>
              예
              <li>Language : C / C++ / Java / Python</li>
              <li>web FrontEnd / BackEnd / Spring / Jenkins</li>
              <li>Mobile -> Hybrid / Android / iOS /</li>
              <li>Javascript -> node.js / Angular.JS / React.js / Vue.js / jQuery</li>
              <li>IoT : Aduino / RaspberryPi / Embedded / Qt / MachineVision</li>
              <li>DB : Oracle / MSSQL / MySQL / MariaDB / MongoDB</li>
            </ul>
          </div>
          <div class="col-12 selectform">
            <div class="btn btn-primary btn-round" v-for="pick in picks" :key="pick" @click="del(pick)">{{pick}}</div>
            <br />
          </div>
          <div class="col-12 searchform">
            <div class="input">
              <input type="text" v-model="inputVal" @input="searchQuery()" @keyup.up="upQ()" @keyup.down="downQ()" @keyup.enter="enterQ()" />
            </div>
            <div class="autoComplete">
              <div v-for="(list,index) in lists" :key="list" @click="add(list)" :class="`pk${index}`">{{list}}</div>
            </div>
          </div>
          <div class="col-12">
            <br />
            <button class="gosignup btn btn-success" @click="gosignup" :class="{submitable:submitable&&signup}">회원가입</button>
          </div>
        </div>
      </form>
    </div>
  </card>
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
      cursor: 0,
    };
  },
  watch: {
    signup: function () {
      this.check();
    },
    picks: function () {
      this.check();
    },
  },
  computed: {},
  methods: {
    check: function () {
      if (this.signup === 0) {
        this.submitable = false;
      } else {
        this.submitable = this.picks.length > 0 ? true : false;
      }
    },
    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
        setTimeout(() => {
          if (this.lists.length > 0) {
            this.cursor = 0;
            document.querySelector(`.pk${this.cursor}`).classList.add('act');
          }
        }, 50);
      } else {
        this.lists = [];
      }
    },
    gosignup: function () {
      if (this.submitable && this.signup) {
        this.$emit("goSignup");
        this.$store.commit("interest", { picks: this.picks });
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
    del: function (x) {
      const idx = this.picks.indexOf(x);
      if (idx > -1) this.picks.splice(idx, 1);
    },
    upQ() {
      document.querySelector(`.pk${this.cursor}`).classList.remove('act');
      this.cursor--;
      if (this.cursor === -1) {
        this.cursor = this.lists.length - 1;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add('act');
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document.querySelector('.autoComplete').scrollTo({top:location, behavior:'auto'});
    },
    downQ() {
      document.querySelector(`.pk${this.cursor}`).classList.remove('act');
      this.cursor++;
      if (this.cursor === this.lists.length) {
        this.cursor = 0;
      }
      document.querySelector(`.pk${this.cursor}`).classList.add('act');
      let location = document.querySelector(`.pk${this.cursor}`).offsetTop;
      document.querySelector('.autoComplete').scrollTo({top:location, behavior:'auto'});
    },
    enterQ() {
      this.add(document.querySelector(`.pk${this.cursor}`).innerText)
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
  .searchform {
    position: relative;
    display: flex;
    flex-direction: column;
    // height: 50px;
    .autoComplete {
      border: .5px gray solid;
      position: absolute;
      max-height: 100px;
      overflow: auto;
      background-color: white;
      width: 98%;
      left: 6px;
      bottom: 45px;
      cursor: pointer;
      .act{
        background-color: #aaa;
      }
    }
    .input {
      height: 100%;
      width: 100%;

      font-size: 1.2rem;
      border: 1px black solid;
      padding: 5px;
      input {
        width: 100%;
        height: 100%;
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

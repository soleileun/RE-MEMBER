<template>
  <div class="editinterest">
    <form @submit.prevent>
      <div class="row">
        <div class="col-12">
          <ul>
            예
            <li>Language : C / C++ / Java / Python / C#</li>
            <li>web FrontEnd / BackEnd / Spring / Jenkins</li>
            <li>Mobile -> Hybrid / Android / iOS /</li>
            <li>Javascript -> node.js / Angular.JS / React.js / Vue.js / jQuery</li>
            <li>IoT : Aduino / RaspberryPi / Embedded / Qt / MachineVision</li>
            <li>DB : Oracle / MSSQL / MySQL / MariaDB / MongoDB</li>
          </ul>
        </div>
        <div class="col-12 selectform">
          <button class="btn btn-primary" v-for="pick in picks" :key="pick" @click="del(pick)">{{pick}}</button>
          <br />
        </div>
        <div class="col-12 searchform">
          <div class="input">
            <input type="text" v-model="inputVal" @input="searchQuery()" @keyup.up="up()" @keyup.down="down()" @keyup.enter="enter()" />
          </div>
          <div class="autoComplete">
            <div v-for="list in lists" :key="list" @click="add(list)">{{list}}</div>
          </div>
        </div>
        <div class="col-12">
          <br />
          <button class="gosignup btn btn-success" @click="complete">수정완료</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import http from "../../http-common.js";
const storage = window.sessionStorage;

export default {
  name: "editinterest",
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
      inputVal: "",
      ints: [],
      progress: true,
    };
  },
  mounted: function () {
    const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
    http
      .get("/api/interest/selectById/" + storage.getItem("userid"),config)
      .then((response) => {
        if (response.data.length > 0) {
          this.ints = response.data;
          this.ints.forEach((el) => {
            this.picks.push(el.interest);
          });
        }
      })
      .catch((exp) => alert("관심사를 로드하는데에 실패하였습니다." + exp));
  },
  methods: {
    searchQuery: function () {
      if (this.inputVal.trim() !== "") {
        this.lists = this.pints.filter((el) => {
          return el.toLowerCase().match(this.inputVal.toLowerCase());
        });
      } else {
        this.lists = [];
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
    complete() {
      if (this.progress) {
        this.progress = false;

        let dels = [];
        this.ints.forEach((el) => {
          const idx = this.picks.indexOf(el.interest);
          if (idx > -1) this.picks.splice(idx, 1);
          else dels.push(el.interest);
        });
        const config = {
            headers: {"jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token")}
        }
        dels.forEach((el) => {
          http
            .delete("/api/interest/", {
              data: {
                id: storage.getItem("userid"),
                interest: el,
              },
            },config)
            .then((res) => {
              console.log(res);
            })
            .catch((e) => console.log(e));
        });
        this.picks.forEach((el) => {
          http
            .post("/api/interest/", {
              id: storage.getItem("userid"),
              interest: el,
            },config)
            .then((res) => {
              console.log(res);
            })
            .catch((e) => console.log(e));
        });

        setTimeout(() => {
          this.$router.push("/mypage");
        }, 100);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.editinterest {
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
      position: absolute;
      max-height: 100px;
      overflow: auto;
      background-color: white;
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
}
</style>

<template>
  <div class="col-md-10 ml-auto col-xl-6 mr-auto">
    <!-- 시군구동 검색 -->
    <div class="row">
      <label class="col-md-2" for="sido" style="margin-left:-20px;">
        <strong>
          시도
          <span id="userid" class="text-danger"></span>
        </strong>
      </label>
      <select
        id="sido"
        class="form-control col-md-2"
        @change="changeSido(selectedSido)"
        v-model="selectedSido"
      >
        <option value="0">선택</option>
        <option v-for="(sido, index) in sidoList" :value="sido" :key="index">
          {{
          sido
          }}
        </option>
      </select>

      <label class="col-md-2" for="gugun">
        <strong>
          구군
          <span id="userid" class="text-danger"></span>
        </strong>
      </label>
      <select
        v-if="selectedSido!=0"
        id="gugun"
        class="form-control col-md-2"
        @change="changeGugun(selectedSido, selectedGugun)"
        v-model="selectedGugun"
      >
        <option value="0">선택</option>
        <option v-for="(gu, index) in gugunList" :value="gu" :key="index">
          {{
          gu
          }}
        </option>
      </select>
      <select v-else id="gugun" class="form-control col-md-2" disabled>
        <option value="0">선택</option>
      </select>

      <label class="col-md-2" for="dong">
        <strong>
          읍면동
          <span id="userid" class="text-danger"></span>
        </strong>
      </label>

      <select
        v-if="selectedGugun!=0"
        id="dong"
        class="form-control col-md-2"
        v-model="selectedDong"
      >
        <!-- @change="changeDong(selectedDong)" -->

        <option value="0">선택</option>
        <option v-for="(don, index) in dongList" :value="don" :key="index">
          {{
          don
          }}
        </option>
      </select>
      <select v-else id="dong" class="form-control col-md-2" disabled>
        <option value="0">선택</option>
      </select>
    </div>

    <hr />

    <div class="selectform">
      <div v-for="(pick,index) in picks" :key="index">
        <button>{{pick}}</button>
        <button v-on:click="deleteStack(index)">X</button>
      </div>
    </div>입력
    <div class="searchform">
      <div class="input">
        <input
          id="stackWord"
          type="text"
          v-model="inputVal"
          @input="searchQuery()"
          @keyup.enter="enter()"
          placeholder="기술 태그 입력해주세요"
        />
      </div>
      <table class="autoComplete">
        <tr v-for="list in lists" :key="list" @click="add(list)">
          <td>{{list}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
import Constant from "../../Constant";

export default {
  data() {
    return {
      selectedSido: 0,
      selectedGugun: 0,
      selectedDong: 0,

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
      inputVal: "",
    };
  },
  computed: {
    sidoList() {
      // console.log("확인" + this.$store.state.stackstore.sidolist);
      return this.$store.state.stackstore.sidolist;
    },
    gugunList() {
      return this.$store.state.stackstore.gugunlist;
    },
    dongList() {
      return this.$store.state.stackstore.donglist;
    },
  },

  created() {
    // sido리스트 불러오기
    this.$store.dispatch(Constant.GET_SIDOLIST);
  },
  methods: {
    changeSido(selectedSido) {
      // gugun
      // console.log(selectedSido);
      this.selectedGugun = 0;
      this.selectedDong = 0;
      this.$store.dispatch(Constant.GET_GUGUNLIST, { sido: selectedSido });
    },
    changeGugun(selectedSido, selectedGugun) {
      // dong
      // console.log(selectedSido + selectedGugun);
      this.selectedDong = 0;

      this.$store.dispatch(Constant.GET_DONGLIST, {
        sido: selectedSido,
        gugun: selectedGugun,
      });
    },
    // changeDong(selectedDong) {
    //   // apt

    // },

    searchPool() {
      let sd = "";
      let gg = "";
      let dn = "";

      if(this.selectedSido == 0){
        sd = " ";
      }
      if(this.selectedGugun == 0){
        gg = " ";
      }
      if(this.selectedDong == 0){
        dn = " ";
      }
      //시 구 동 미선택 시 " " 로 보내고 picks 배열은 그대로,
      this.$store.dispatch(Constant.SEARCH_POOL, {sido : sd, gugun : gg, dong : dn, picks  });
     
    },

    deleteStack(idx) {
      this.picks.splice(idx, 1);
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
    add: function (x) {
      if (this.picks.length == 5) {
        alert("검색 스택은 5개까지만 입력 가능합니다.");
        this.lists = [];
        this.inputVal = "";
      } else {
        if (x !== "") {
          if (!this.picks.find((i) => i === x)) {
            this.picks.push(x);
          }
        }
        this.inputVal = "";
        this.lists = [];
      }
    },
  },
};
</script>

<style>
</style>
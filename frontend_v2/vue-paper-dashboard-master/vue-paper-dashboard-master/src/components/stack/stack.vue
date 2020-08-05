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
      
      <select v-if="selectedGugun!=0" id="dong" class="form-control col-md-2" v-model="selectedDong">
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
    <div>
      스택 태그 입력
      <input type="text" placeholder="스택을 입력하세요" id="stackWord" />
      <button v-on:click="registStack">입력</button>

      <div v-for="(stack,index) in stacks" :key="index" :value="stack">
        <span>{{stack}}</span>
        <button v-on:click="deleteStack(index)">X</button>
      </div>
      <hr />
      <button v-on:click="searchPool">검색</button>
    </div>
  </div>
</template>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
import Constant from "../../Constant";

export default {
  data() {
    return {
      stacks: [],
      selectedSido: 0,
      selectedGugun: 0,
      selectedDong: 0,
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
      // this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {
      //   btitle: val,
      //   bstate: "free",
      // });
    },
    registStack() {
      if (this.stacks.length == 5) {
        alert("검색 스택은 5개까지만 입력 가능합니다.");
        $("#stackWord").val(null);
      } else {
        let addValue = $("#stackWord").val();
        this.stacks.push("#" + addValue);
        $("#stackWord").val(null);
      }
    },
    deleteStack(idx) {
      this.stacks.splice(idx, 1);
    },
  },
};
</script>

<style>
</style>
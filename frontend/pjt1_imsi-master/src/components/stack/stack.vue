<template>
  <div class="col-md-10 ml-auto col-xl-6 mr-auto">
    <!-- 실거래정보 검색 -->
    <SDealListSearch />
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
        <option v-for="(si, index) in sidoList" v-bind:value="si.sido_code" :key="index">
          {{
          si.sido_name
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
        id="gugun"
        class="form-control col-md-2"
        @change="changeGugun(selectedGugun)"
        v-model="selectedGugun"
      >
        <option value="0">선택</option>
        <option v-for="(gu, index) in gugunList" v-bind:value="gu.gugun_code" :key="index">
          {{
          gu.gugun_name
          }}
        </option>
      </select>

      <label class="col-md-2" for="dong">
        <strong>
          읍면동
          <span id="userid" class="text-danger"></span>
        </strong>
      </label>
      <select
        id="dong"
        class="form-control col-md-2"
        @change="changeDong(selectedDong)"
        v-model="selectedDong"
      >
        <option value="0">선택</option>
        <option v-for="(don, index) in dongList" v-bind:value="don.dong" :key="index">
          {{
          don.dong
          }}
        </option>
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
// import Constant from "../../Constant";

// import { EventBus } from "@/utils/eventBus";
// import SDealListSearch from "./SDealListSearch";
// let colorArr = ["table-primary", "table-success", "table-danger"];
// import { Button, FormGroupInput } from "@/components";

export default {
  data() {
    return {
      stacks: [],
    };
  },
  methods: {
    searchPool() {
    //   let val  = document.getElementById("searchWord").value;
    //   let std = document.getElementById("standard").value;
    //   //제목 검색
    //   if(std == 't'){
    //     console.log(val);
    //     this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val, bstate : 'free'});
    //   }else{ //작성자 검색
    //     this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val, bstate : 'free'});
    //   }
    },
    registStack() {
      let addValue = $("#stackWord").val();
      this.stacks.push("#" + addValue);
      $("#stackWord").val(null);
    },
    deleteStack(idx) {
      this.stacks.splice(idx,1);
    },
  },
  //   data: function() {
  //     return {
  //       sidoList: [],
  //       gugunList: [],
  //       dongList: [],
  //       selectedSido: 0,
  //       selectedGugun: 0,
  //       selectedDong: 0,
  //       colorArr,
  //     };
  //   },
  //   created() {
  //     // sido
  //     axios
  //       .get(
  //         "http://localhost:9999/happyhouse/v1/sad/sido"
  //       )
  //       .then(({ data }) => {
  //         console.log(data);
  //         this.sidoList = data;
  //       });
  //     EventBus.$on("searchDealListS", this.searchDealListS);
  //   },
  //   methods: {
  //     changeSido(selectedSido) {
  //       // gugun
  //       axios
  //         .get(
  //           "http://localhost:9999/happyhouse/v1/sad/gugun/" +
  //             selectedSido
  //         )
  //         .then(({ data }) => {
  //           console.log(selectedSido);
  //           console.log(data);
  //           this.gugunList = data;
  //         });
  //     },
  //     changeGugun(selectedGugun) {
  //       // dong
  //       axios
  //         .get(
  //           "http://localhost:9999/happyhouse/v1/sad/dong/" +
  //             selectedGugun
  //         )
  //         .then(({ data }) => {
  //           console.log(selectedGugun);
  //           console.log(data);
  //           this.dongList = data;
  //         });
  //     },
  //     changeDong(selectedDong) {
  //       // apt
  //       const sMap = new Map();
  //       sMap.set("dong", selectedDong);
  //       sMap.set("id", this.$store.state.loginUser);
  //       console.log(this.$store.state.loginUser);
  //       axios
  //         .get(
  //           "http://localhost:9999/happyhouse/v1/sad/apt",
  //           {
  //             params: {
  //               dong: selectedDong,
  //               id: this.$store.state.loginUser,
  //             },
  //           }
  //         )
  //         .then(({ data }) => {
  //           console.log(selectedDong);
  //           console.log(data);
  //           this.aptList = data;
  //           //geocode(data);
  //           EventBus.$emit("changeMapS", data);
  //         });
  //     },
  //     searchDealListS(selectedType, word) {
  //       console.log(selectedType);
  //       console.log(word);
  //       if (word !== "") {
  //         axios
  //           .get(
  //             "http://localhost:9999/happyhouse/v1/sad/search/",
  //             {
  //               params: {
  //                 word: word,
  //                 id: this.$store.state.loginUser,
  //               },
  //             }
  //           )
  //           .then(({ data }) => {
  //             this.aptList = data;
  //             console.log(data);
  //           });
  //       }
  // 여긴 원래 생략되있었음
  //  else {
  //   axios.get("http://localhost:9999/happyhouse/v1/apt/list").then(({ data }) => {
  //     this.aptList = data;
  //   });
  // }
  //     },
  //   },
  //   components: {
  //     SDealListSearch,
  //     [Button.name]: Button,
  //     [FormGroupInput.name]: FormGroupInput,
  //   },
};
</script>

<style>
</style>
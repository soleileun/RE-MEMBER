<template>
  <div class="qaboard1">
  <!-- 로그인 여부에 따라 활성 비활성 여부 결정 -->
  <!--
  <table>
    <thead>
      <tr>
        <th>no</th>
        <th>title</th>
        <th>id</th>
        <th>register_date</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="free in frees" :key="free.bno">
        <td>{{free.bno}}</td>
        -->
        <!-- bno 쿼리스트링 달아서 분기 -->
        <!--
        <td>  <router-link :to="'/freeboard/detailfree/' + free.bno">{{free.btitle}}</router-link></td>
        <td>{{free.bwriter}}</td>
        <td>{{free.makeDay}}</td>
      </tr>
    </tbody>
  </table>
  -->
  <!-- 디비에 들어갈 조건들 : {{selected}} + {{searchWord}} <br> -->
  
    <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
               <!--
                <div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">Table Basic</h3>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                            <li class="breadcrumb-item active">Table Basic</li>
                        </ol>
                    </div>
                </div>
                -->
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
        <div class="row">
                    <!-- column -->
          <div class="col-12">
            <div class="card">
              <div class="card-body">
                <h4 class="card-title">질문게시판</h4>
                <!--
                <h6 class="card-subtitle">Add class <code>.table</code></h6>
                -->
                <div class="table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th style="width:2%;">No</th>
                        <th style="width:6%;">종류</th>
                        <th style="width:65%;">제목</th>
                        <th>ID</th>
                        <th>조회수</th>
                        <th>작성일</th>
                      </tr>
                    </thead>
                    <tbody>
                                            
                      <tr v-for="free in frees" :key="free.bno">
                        <td>{{free.bno}}</td>
                        <td>[{{free.bstate}}]</td>
                        <!-- bno 쿼리스트링 달아서 분기 -->
                        <td style="text-align:left;">
                          <router-link :to="'/qaboard/detailqa/' + free.bno">{{free.btitle}}</router-link>
                        </td>
                        <td>{{free.bwriter}}</td>
                        <td>{{free.bview}}</td>
                        <td>{{free.makeDay.slice(0,10)}}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
              <button type="button" class="btn btn-primary" style="width:90px; float:right">
                <router-link to="/qaboard/writeqa" tag="button">글쓰기</router-link>
              </button>
                        
            <br><br>
          </div>            
        </div>
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
            
        <select name="standard" id="standard3" style="height:30px; margin-right:20px; width:80px;">
          <option value="t" selected >제목</option>
          <option value="w">작성자</option>
        </select>
        <input type="text" placeholder="검색어를 입력하세요" id="searchWord3" style="margin-right:20px; width:400px;"/>
          <button v-on:click="searchFree" style="border: 2px solid rgb(173, 203, 247); border-radius:10px;">검색</button>
                  
                  
      </div>


  </div>
</template>

<script>
import Constant from '../../Constant';
// import { mapActions } from 'vuex'

export default {
  name: "qaboard1",
  computed: {
    frees() {
      // console.log('안녕');
      // console.log('확인 : ' + this.$store.state.boardstore.boards);
      return this.$store.state.boardstore.boards; 
    }
  },
  created () {
    this.$store.dispatch(Constant.GET_BOARDLIST,{bstate : 'qa'});
  },

  methods: {
    searchFree(){
      let val  = document.getElementById("searchWord3").value;
      let std = document.getElementById("standard3").value;

      //제목 검색
      if(std == 't'){
        console.log(val);
        this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val, bstate : 'qa'});
      }else{ //작성자 검색
        this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val, bstate : 'qa'});
      }
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
/*
table, td, tr, th {
  width : 1000px;
  margin : 0 auto;
  text-align: center;
  border: 1px solid black;
}
*/



.table-box {
  display: table;
  width: 100%; }

.table.no-border tbody td {
  border: 0px; }

.cell {
  display: table-cell;
  vertical-align: middle; }

.table td, .table th {
  border-color: #f3f1f1; }

.table thead th, .table th {
  font-weight: 500; }

.table-hover tbody tr:hover {
  background: #e9edf2; }

.nowrap {
  white-space: nowrap; }

.lite-padding td {
  padding: 5px; }

.v-middle td, .v-middle th {
  vertical-align: middle; }

/*******************
Table-Layout
******************/
.table thead th, .table th {
  border: 0px; }

.color-table.primary-table thead th {
  background-color: #745af2;
  color: #ffffff; }

.table-striped tbody tr:nth-of-type(odd) {
  background: #e9edf2; }

.color-table.success-table thead th {
  background-color: #06d79c;
  color: #ffffff; }

.color-table.info-table thead th {
  background-color: #398bf7;
  color: #ffffff; }

.color-table.warning-table thead th {
  background-color: #ffb22b;
  color: #ffffff; }

.color-table.danger-table thead th {
  background-color: #ef5350;
  color: #ffffff; }

.color-table.inverse-table thead th {
  background-color: #2f3d4a;
  color: #ffffff; }

.color-table.dark-table thead th {
  background-color: #263238;
  color: #ffffff; }

.color-table.red-table thead th {
  background-color: #fb3a3a;
  color: #ffffff; }

.color-table.purple-table thead th {
  background-color: #7460ee;
  color: #ffffff; }

.color-table.muted-table thead th {
  background-color: #99abb4;
  color: #ffffff; }

.color-bordered-table.primary-bordered-table {
  border: 2px solid #745af2; }
  .color-bordered-table.primary-bordered-table thead th {
    background-color: #745af2;
    color: #ffffff; }

.color-bordered-table.success-bordered-table {
  border: 2px solid #06d79c; }
  .color-bordered-table.success-bordered-table thead th {
    background-color: #06d79c;
    color: #ffffff; }

.color-bordered-table.info-bordered-table {
  border: 2px solid #398bf7; }
  .color-bordered-table.info-bordered-table thead th {
    background-color: #398bf7;
    color: #ffffff; }

.color-bordered-table.warning-bordered-table {
  border: 2px solid #ffb22b; }
  .color-bordered-table.warning-bordered-table thead th {
    background-color: #ffb22b;
    color: #ffffff; }

.color-bordered-table.danger-bordered-table {
  border: 2px solid #ef5350; }
  .color-bordered-table.danger-bordered-table thead th {
    background-color: #ef5350;
    color: #ffffff; }

.color-bordered-table.inverse-bordered-table {
  border: 2px solid #2f3d4a; }
  .color-bordered-table.inverse-bordered-table thead th {
    background-color: #2f3d4a;
    color: #ffffff; }

.color-bordered-table.dark-bordered-table {
  border: 2px solid #263238; }
  .color-bordered-table.dark-bordered-table thead th {
    background-color: #263238;
    color: #ffffff; }

.color-bordered-table.red-bordered-table {
  border: 2px solid #fb3a3a; }
  .color-bordered-table.red-bordered-table thead th {
    background-color: #fb3a3a;
    color: #ffffff; }

.color-bordered-table.purple-bordered-table {
  border: 2px solid #7460ee; }
  .color-bordered-table.purple-bordered-table thead th {
    background-color: #7460ee;
    color: #ffffff; }

.color-bordered-table.muted-bordered-table {
  border: 2px solid #99abb4; }
  .color-bordered-table.muted-bordered-table thead th {
    background-color: #99abb4;
    color: #ffffff; }

.full-color-table.full-primary-table {
  background-color: #f1effd; }
  .full-color-table.full-primary-table thead th {
    background-color: #745af2;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-primary-table tbody td {
    border: 0; }
  .full-color-table.full-primary-table tr:hover {
    background-color: #745af2;
    color: #ffffff; }

.full-color-table.full-success-table {
  background-color: #e8fdeb; }
  .full-color-table.full-success-table thead th {
    background-color: #06d79c;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-success-table tbody td {
    border: 0; }
  .full-color-table.full-success-table tr:hover {
    background-color: #06d79c;
    color: #ffffff; }

.full-color-table.full-info-table {
  background-color: #cfecfe; }
  .full-color-table.full-info-table thead th {
    background-color: #398bf7;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-info-table tbody td {
    border: 0; }
  .full-color-table.full-info-table tr:hover {
    background-color: #398bf7;
    color: #ffffff; }

.full-color-table.full-warning-table {
  background-color: #fff8ec; }
  .full-color-table.full-warning-table thead th {
    background-color: #ffb22b;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-warning-table tbody td {
    border: 0; }
  .full-color-table.full-warning-table tr:hover {
    background-color: #ffb22b;
    color: #ffffff; }

.full-color-table.full-danger-table {
  background-color: #f9e7eb; }
  .full-color-table.full-danger-table thead th {
    background-color: #ef5350;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-danger-table tbody td {
    border: 0; }
  .full-color-table.full-danger-table tr:hover {
    background-color: #ef5350;
    color: #ffffff; }

.full-color-table.full-inverse-table {
  background-color: #f6f6f6; }
  .full-color-table.full-inverse-table thead th {
    background-color: #2f3d4a;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-inverse-table tbody td {
    border: 0; }
  .full-color-table.full-inverse-table tr:hover {
    background-color: #2f3d4a;
    color: #ffffff; }

.full-color-table.full-dark-table {
  background-color: rgba(43, 43, 43, 0.8); }
  .full-color-table.full-dark-table thead th {
    background-color: #263238;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-dark-table tbody td {
    border: 0;
    color: #ffffff; }
  .full-color-table.full-dark-table tr:hover {
    background-color: #263238;
    color: #ffffff; }

.full-color-table.full-red-table {
  background-color: #f9e7eb; }
  .full-color-table.full-red-table thead th {
    background-color: #fb3a3a;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-red-table tbody td {
    border: 0; }
  .full-color-table.full-red-table tr:hover {
    background-color: #fb3a3a;
    color: #ffffff; }

.full-color-table.full-purple-table {
  background-color: #f1effd; }
  .full-color-table.full-purple-table thead th {
    background-color: #7460ee;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-purple-table tbody td {
    border: 0; }
  .full-color-table.full-purple-table tr:hover {
    background-color: #7460ee;
    color: #ffffff; }

.full-color-table.full-muted-table {
  background-color: rgba(152, 166, 173, 0.2); }
  .full-color-table.full-muted-table thead th {
    background-color: #99abb4;
    border: 0;
    color: #ffffff; }
  .full-color-table.full-muted-table tbody td {
    border: 0; }
  .full-color-table.full-muted-table tr:hover {
    background-color: #99abb4;
    color: #ffffff; }

/*******************
Table-Data Table
******************/
.dataTables_wrapper {
  padding-top: 10px; }

.dt-buttons {
  display: inline-block;
  padding-top: 5px;
  margin-bottom: 15px; }
  .dt-buttons .dt-button {
    padding: 5px 15px;
    border-radius: 4px;
    background: #398bf7;
    color: #ffffff;
    margin-right: 3px; }
    .dt-buttons .dt-button:hover {
      background: #2f3d4a; }

.dataTables_info, .dataTables_length {
  display: inline-block; }

.dataTables_length {
  margin-top: 10px; }
  .dataTables_length select {
    border: 0;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#398bf7), to(#398bf7)), -webkit-gradient(linear, left top, left bottom, from(#b1b8bb), to(#b1b8bb));
    background-image: -webkit-linear-gradient(#398bf7, #398bf7), -webkit-linear-gradient(#b1b8bb, #b1b8bb);
    background-image: -o-linear-gradient(#398bf7, #398bf7), -o-linear-gradient(#b1b8bb, #b1b8bb);
    background-image: linear-gradient(#398bf7, #398bf7), linear-gradient(#b1b8bb, #b1b8bb);
    background-size: 0 2px, 100% 1px;
    background-repeat: no-repeat;
    background-position: center bottom, center calc(100% - 1px);
    background-color: transparent;
    -webkit-transition: background 0s ease-out;
    -o-transition: background 0s ease-out;
    transition: background 0s ease-out;
    padding-bottom: 5px; }
    .dataTables_length select:focus {
      outline: none;
      background-image: -webkit-gradient(linear, left top, left bottom, from(#398bf7), to(#398bf7)), -webkit-gradient(linear, left top, left bottom, from(#b1b8bb), to(#b1b8bb));
      background-image: -webkit-linear-gradient(#398bf7, #398bf7), -webkit-linear-gradient(#b1b8bb, #b1b8bb);
      background-image: -o-linear-gradient(#398bf7, #398bf7), -o-linear-gradient(#b1b8bb, #b1b8bb);
      background-image: linear-gradient(#398bf7, #398bf7), linear-gradient(#b1b8bb, #b1b8bb);
      background-size: 100% 2px, 100% 1px;
      -webkit-box-shadow: none;
      box-shadow: none;
      -webkit-transition-duration: 0.3s;
      -o-transition-duration: 0.3s;
      transition-duration: 0.3s; }

.dataTables_filter {
  float: right;
  margin-top: 10px; }
  .dataTables_filter input {
    border: 0;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#398bf7), to(#398bf7)), -webkit-gradient(linear, left top, left bottom, from(#b1b8bb), to(#b1b8bb));
    background-image: -webkit-linear-gradient(#398bf7, #398bf7), -webkit-linear-gradient(#b1b8bb, #b1b8bb);
    background-image: -o-linear-gradient(#398bf7, #398bf7), -o-linear-gradient(#b1b8bb, #b1b8bb);
    background-image: linear-gradient(#398bf7, #398bf7), linear-gradient(#b1b8bb, #b1b8bb);
    background-size: 0 2px, 100% 1px;
    background-repeat: no-repeat;
    background-position: center bottom, center calc(100% - 1px);
    background-color: transparent;
    -webkit-transition: background 0s ease-out;
    -o-transition: background 0s ease-out;
    transition: background 0s ease-out;
    float: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-radius: 0;
    margin-left: 10px; }
    .dataTables_filter input:focus {
      outline: none;
      background-image: -webkit-gradient(linear, left top, left bottom, from(#398bf7), to(#398bf7)), -webkit-gradient(linear, left top, left bottom, from(#b1b8bb), to(#b1b8bb));
      background-image: -webkit-linear-gradient(#398bf7, #398bf7), -webkit-linear-gradient(#b1b8bb, #b1b8bb);
      background-image: -o-linear-gradient(#398bf7, #398bf7), -o-linear-gradient(#b1b8bb, #b1b8bb);
      background-image: linear-gradient(#398bf7, #398bf7), linear-gradient(#b1b8bb, #b1b8bb);
      background-size: 100% 2px, 100% 1px;
      -webkit-box-shadow: none;
      box-shadow: none;
      -webkit-transition-duration: 0.3s;
      -o-transition-duration: 0.3s;
      transition-duration: 0.3s; }

table.dataTable thead .sorting,
table.dataTable thead .sorting_asc,
table.dataTable thead .sorting_desc,
table.dataTable thead .sorting_asc_disabled,
table.dataTable thead .sorting_desc_disabled {
  background: transparent; }

table.dataTable thead .sorting_asc:after {
  content: "\f0de";
  margin-left: 10px;
  font-family: fontawesome;
  cursor: pointer; }

table.dataTable thead .sorting_desc:after {
  content: "\f0dd";
  margin-left: 10px;
  font-family: fontawesome;
  cursor: pointer; }

table.dataTable thead .sorting:after {
  content: "\f0dc";
  margin-left: 10px;
  font-family: fontawesome !important;
  cursor: pointer;
  color: rgba(50, 50, 50, 0.5); }

.dataTables_wrapper .dataTables_paginate {
  float: right;
  text-align: right;
  padding-top: 0.25em; }

.dataTables_wrapper .dataTables_paginate .paginate_button {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  display: inline-block;
  min-width: 1.5em;
  padding: 0.5em 1em;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  *cursor: hand;
  color: #67757c;
  border: 1px solid #ddd; }

.dataTables_wrapper .dataTables_paginate .paginate_button.current,
.dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
  color: #ffffff !important;
  border: 1px solid #398bf7;
  background-color: #398bf7; }

.dataTables_wrapper .dataTables_paginate .paginate_button.disabled,
.dataTables_wrapper .dataTables_paginate .paginate_button.disabled:hover,
.dataTables_wrapper .dataTables_paginate .paginate_button.disabled:active {
  cursor: default;
  color: #67757c;
  border: 1px solid #ddd;
  background: transparent;
  -webkit-box-shadow: none;
  box-shadow: none; }

.dataTables_wrapper .dataTables_paginate .paginate_button:hover {
  color: white;
  border: 1px solid #398bf7;
  background-color: #398bf7; }

.dataTables_wrapper .dataTables_paginate .paginate_button:active {
  outline: none;
  background-color: #67757c; }

.dataTables_wrapper .dataTables_paginate .ellipsis {
  padding: 0 1em; }

/*******************
Table- responsive
******************/
.tablesaw-bar .btn-group label {
  color: #67757c !important; }

/*******************
Table- editable table
******************/
.dt-bootstrap {
  display: block; }

.paging_simple_numbers .pagination .paginate_button {
  padding: 0px;
  background: #ffffff; }
  .paging_simple_numbers .pagination .paginate_button:hover {
    background: #ffffff; }
  .paging_simple_numbers .pagination .paginate_button a {
    padding: 5px 10px;
    border-radius: 4px;
    border: 0px; }
  .paging_simple_numbers .pagination .paginate_button.active a, .paging_simple_numbers .pagination .paginate_button:hover a {
    background: #398bf7;
    color: #ffffff; }


.vtabs {
  display: table; }
  .vtabs .tabs-vertical {
    width: 150px;
    border-bottom: 0px;
    border-right: 1px solid rgba(120, 130, 140, 0.13);
    display: table-cell;
    vertical-align: top; }
    .vtabs .tabs-vertical li .nav-link {
      color: #263238;
      margin-bottom: 10px;
      border: 0px;
      border-radius: 4px 0 0 4px; }
  .vtabs .tab-content {
    display: table-cell;
    padding: 20px;
    vertical-align: top; }

.tabs-vertical li .nav-link.active,
.tabs-vertical li .nav-link:hover,
.tabs-vertical li .nav-link.active:focus {
  background: #398bf7;
  border: 0px;
  color: #ffffff; }

/*Custom vertical tab*/
.customvtab .tabs-vertical li .nav-link.active,
.customvtab .tabs-vertical li .nav-link:hover,
.customvtab .tabs-vertical li .nav-link:focus {
  background: #ffffff;
  border: 0px;
  border-right: 2px solid #398bf7;
  margin-right: -1px;
  color: #398bf7; }

.tabcontent-border {
  border: 1px solid #ddd;
  border-top: 0px; }

.customtab2 li a.nav-link {
  border: 0px;
  margin-right: 3px;
  color: #67757c; }
  .customtab2 li a.nav-link.active {
    background: #398bf7;
    color: #ffffff; }
  .customtab2 li a.nav-link:hover {
    color: #ffffff;
    background: #398bf7; }

</style>

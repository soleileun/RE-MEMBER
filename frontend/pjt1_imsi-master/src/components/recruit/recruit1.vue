<template>
  <div class="recruit1">
    
    <h1>구인 게시판 샘플 1</h1>

    리쿠르트 테이블에 대분류 ex 프론트 백 이런거 넣는거도 좋을 거 같습니다.
  <hr>
  <!-- 로그인 여부에 따라 활성 비활성 여부 결정 -->
  <button id="myBtn" @click="openModal">구인글 등록</button>
  <!-- The Modal -->
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <span class="close">&times;</span>    
      아이디 : store의 state에서 가져오는 세션 아이디<br>
      프로젝트 선택<br>
      <table>
        <tr v-for="project in projects" :key="project.pid">
          <td><input type="radio" name="pjtList" value="HTML">{{project.pjtName}}</td>
        </tr>
      </table>

      제목 : <input type="text" v-model="wrecruit.title" placeholder="제목을 입력하세요" /><br>
      내용<br>
      <textarea name="" id="" cols="30" rows="10" v-model="wrecruit.contents" placeholder="내용을 입력하세요"></textarea><br>
      <button @click="addRecruit">등록</button>                                                           
     
    </div>

  </div>

  <table id="table1">
    <thead>
      <tr>
        <th>rnum</th>
        <th>pid</th>
        <th>title</th>
        <th>makeId</th>
        <th>makeDay</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="recruit in recruits" :key="recruit.rnum">
        <td>{{recruit.rnum}}</td>
        <td>{{recruit.pid}}</td>
        <td>  <router-link :to="'/recruit/recruitdetail/' + recruit.rnum">{{recruit.title}}</router-link></td>
        <td>{{recruit.makeId}}</td>
        <td>{{recruit.makeDay}}</td>
      </tr>
    </tbody>
  </table>

   검색 다양한 기준 생각해볼 것 + 여기도 게시판처럼 CRUD 해야함<br>
    <select name="standard" id="standard" >
      <option value="t" selected>제목</option>
      <option value="w">작성자</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요" id="searchWord"/>
    <button v-on:click="searchRecruit">검색</button>
  


  </div>
</template>

<script>

import Constant from '../../Constant';

export default {
  name: "recruit1",
  computed: {
    recruits() {
      return this.$store.state.recruitstore.recruits; 
    },
    projects() {
      return this.$store.state.projectstore.projects; 
    }
  },
  created () {
    this.$store.dispatch(Constant.GET_RECRUITLIST);
    // 세션 아이디로 교체
    this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER,{userId : 'ssafy' });

  },
  data() {
    return {
      wrecruit : {
        rnum:'',
        pid: '',
        title: '', 
        contents: '',
        endDate: '',
        makeDay: '',
        changeDay: '',
        makeId: '',
        changeId: ''
      }
    }
  },

  methods: {
    searchRecruit(){
      // let val  = document.getElementById("searchWord").value;
      // let std = document.getElementById("standard").value;

      // //제목 검색
      // if(std == 't'){
      //   console.log(val);
      //   this.$store.dispatch(Constant.SEARCH_BOARD_TITLE, {btitle : val, bstate : 'free'});
      // }else{ //작성자 검색
      //   this.$store.dispatch(Constant.SEARCH_BOARD_WRITER, {bwriter : val, bstate : 'free'});
      // }
    },
    openModal(){
      // Get the modal
        var modal = document.getElementById('myModal');

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];                                          
 
        // When the user clicks on the button, open the modal 
        modal.style.display = "block";
 
        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }
 
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

    },
    addRecruit(){
            if(this.wrecruit.contents.trim() != ''){
                this.$store.dispatch(Constant.ADD_RECRUIT,{
                  //rnum:'',
                  pid: this.wrecruit.pid,
                  title: this.wrecruit.title,
                  contents: this.wrecruit.contents,
                  endDate: this.wrecruit.endDate,
                  makeDay: this.wrecruit.makeDay,
                  changeDay: this.wrecruit.changeDay,
                  makeId: this.wrecruit.makeId,
                  changeId: this.wrecruit.changeId
                });
                this.$router.push('/recruit/recruit1');
            }else{
                console.log('공백입력.');
            }    
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
#table1, td, tr, th {
  width : 1000px;
  margin : 0 auto;
  text-align: center;
  border: 1px solid black;
}

/* The Modal (background) */
  .modal {
      display: none; /* Hidden by default */
      position: fixed; /* Stay in place */
      z-index: 1; /* Sit on top */
      left: 0;
      top: 0;
      width: 100%; /* Full width */
      height: 100%; /* Full height */
      overflow: auto; /* Enable scroll if needed */
      background-color: rgb(0,0,0); /* Fallback color */
      background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  }

  /* Modal Content/Box */
  .modal-content {
      background-color: #fefefe;
      margin: 15% auto; /* 15% from the top and centered */
      padding: 20px;
      border: 1px solid #888;
      width: 80%; /* Could be more or less, depending on screen size */                          
  }
  /* The Close Button */
  .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
  }
  .close:hover,
  .close:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
  }
</style>

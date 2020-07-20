<template>
  <div class="freeboard1">
    <h1>인재풀 샘플 1</h1>
  <hr>
  <h2>스택 필터링</h2>
  <!-- name을 db front back으로 묶기도 가능. 회의 후 결정  -->
  <input type="checkbox" name="stack" value="spring"/>Spring
  <input type="checkbox" name="stack" value="vue"/>Vue.js
  <input type="checkbox" name="stack" value="mysql"/>MySQL
  <input type="checkbox" name="stack" value="django"/>django
  <input type="checkbox" name="stack" value="embedded"/>embedded
  <button v-on:click="searchMember">검색</button>
  <!-- 로그인 여부에 따라 인재풀 등록 버튼 활성 비활성 여부 결정 -->
  <br>
  <router-link to="/recruit/registmember" tag="button">인재풀 등록</router-link>
  <table>
    <thead>
      <tr>
        <th>no</th>
        <th>id</th>
        <th>포트폴리오 페이지</th>
        <th>마감기한</th>
        <th>유효여부</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="member in members" :key="member.rnum">
        <td>{{member.rnum}}</td>
        <td>{{member.pid}}</td>
        <td>  
            <!-- <router-link to="/user"> 누르면 해당 멤버 포트폴리오 페이지로 이동-->
            <!-- </router-link> -->
            <button id="myBtn" @click="openModal">포트폴리오 모달</button>
            <!-- The Modal -->
            <div id="myModal" class="modal">
        
              <!-- Modal content -->
              <div class="modal-content">
                <span class="close">&times;</span>                                                               
                <p><portfolio /></p>
                 <button>영입제안</button>
                 <button>메시지 전송</button>
              </div>
        
            </div>
        </td>
        <td>{{member.end_date}}</td>
        <td>on/off는 회원정보 수정에서 꺼놓으면 리스트에 아예 안뜨게</td>
      </tr>
    </tbody>
  </table>
  
  


  </div>
</template>

<script>
import portfolio from "../portfolio/portfolio.vue";

export default {
  name: "searchmember",
  components : {
    portfolio
  },
  data() {
    return {
      stacks : [],
      //후에 디비로 교체
      members:[
        { 
          rnum : 1 ,
          pid : 'ssafy',
          title : '스프링 한분',
          contents : '구합니다',
          end_date : '2020-03-31',
          makeday : '2020-03-31',
          changeday : '2020-07-01',
          makeid : 'makeid1',
          changeid : 'changeid1'
        },
         { 
          rnum : 2 ,
          pid : 'hello',
          title : '뷰 한분',
          contents : '구하는 중입니다',
          end_date : '2020-03-31',
          makeday : '2020-03-31',
          changeday : '2020-07-01',
          makeid : 'makeid1',
          changeid : 'changeid1'
        },
      ]
    }
  },
  methods: {
    searchMember(){
      // this.stacks = document.getElementsByName("stack");
      var check_count = document.getElementsByName("stack").length;
      console.log(check_count);
      for (var i=0; i<check_count; i++) {
          if (document.getElementsByName("stack")[i].checked == true) {
                console.log(document.getElementsByName("stack")[i].value);
          }
      }
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

    }
  },
};


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  table, td, tr, th {
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

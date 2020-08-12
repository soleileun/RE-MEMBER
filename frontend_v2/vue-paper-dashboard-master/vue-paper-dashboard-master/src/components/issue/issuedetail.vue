<template>
  <div class="issuedetail card" title="여기에 프로젝트 이름 / 이슈 번호" style="overflow-x:hidden; overflow-y:hidden;">
    <i class="ti-close" @click="exit"></i>
    <div class="biggest">
        <div class="card-header">
            <div style="font-size:12px;">
                여기 프로젝트 이름 / 이슈번호
            </div>
            <div style=" font-weight:bold; font-size:18px;">
                이슈 타이틀 그리고 priority 그림으로 넣어주자
            </div>
        </div>
        <div class="card-body" style="height:100%;">
            
            <div class="icontent" >
                여기에 이제 이슈 상세 내용이 들어감
                네모나게 이쁘게 만들면 될듯
            </div>
          <div class="container-fluid">
            <div class="row">
                <div class="col-8" >


        <div class="updateform" >
        <b-collapse id="collapse-1" class="mt-2">
     
            수정하는 form을 작성해주세요
       </b-collapse>

        </div>


                  왼쪽
                    <div class="row">
                        <div class="col-5 form">
                            이슈 타입:
                        </div>
                        <div class="col-7 data">
                            issuetype    
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-5 form">
                            상태:
                        </div>
                        <div class="col-7 data">
                            issuestate    
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-5 form">
                            앞선 작업:
                        </div>
                        <div class="col-7 data">
                            prework 
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-5 form">
                            대기중인 작업:
                        </div>
                        <div class="col-7 data">
                            쿼리로 이 이슈를 선행작업으로 가지는 이슈들 보여줌 
                        </div>
                    </div>
                    <br>
                    <hr>
                    <div class="row">
                        <div class="col-5 form" style="text-align:right;">
                            makeid가
                        </div>
                        <div class="col-7 data">
                            makeday에 작성하였음. 
                        </div>
                    </div>

                </div>
                
                <div class="col-4" style="text-align:center;">
                  오른쪽
                  <h3> 담당자 </h3>
                  <hr>
                  response

                  <hr>
                  <b-button pill variant="outline-success" size="sm" class="col-10 btn">내가 담당하기</b-button>
                  <b-button pill variant="outline-warning" size="sm" class="col-10 btn">때려치기</b-button>
                  
                <div class="col-12 btngroup">
                    <b-button-group>
                        <b-button v-b-toggle.collapse-1 variant="outline-info" size="sm" class="btn" @click="updateview">수정</b-button>
                        <b-button variant="outline-danger" size="sm" class="btn">삭제</b-button>

                        </b-button-group>
                </div>
                
                </div>

             
            </div>
            
                    
          </div>

        </div>
        <div style="text-align:right;">
            최근 수정일 : chageday
        </div>
    </div>
  </div>
  
</template>


<script>
const storage = window.sessionStorage;

export default {
  name: "issuedetail",
  data: function () {
    return {
      autologin: false,
      id: "",
      pw: "",
    };
  },
  computed: {
  },
  methods: {
    exit: function () {
      document.querySelector(".issuedetail").classList.remove("active");
    },
    updateview: function () {
      document.querySelector(".updateform").classList.toggle("active");
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.row {
    padding-top: 8px;
    padding-bottom: 8px;
}
.btn {
    margin-top:8px;
    margin-bottom:8px;
    margin-left:3px;
    margin-right:3px;
}
.data {
    font-weight: bold;
}
.icontent {
    background-color:rgb(229, 239, 250);
    border-radius:12px;
    padding:8px;
}
.updateform{
    overflow-y:auto;
}
.updateform.active{
    margin-bottom:20px;
    height:47vh;
}
.issuedetail {
  visibility: hidden;
  height: 0;
  width: 0;
  position: fixed;
  top: 0;
  left: 0;
}
.issuedetail.active {
  padding: 5vh;
  visibility: visible;
  position: fixed;
  z-index: 99999;
  top: 25vh;
  left: 25vw;
  width: 60vw;
  height: 70vh;
  background-color: white;
  border: black 1.5px solid;
  i {
    position: fixed;
    top: 27vh;
    right: 15vw;
    width: 1rem;
    font-size: 1rem;
    &:hover {
      cursor: pointer;
      border-radius: 50px;
      background-color: #aaa;
    }
  }
  [data-tooltip-text]:hover {
    position: relative;
    &:after {
      content: attr(data-tooltip-text);
      position: absolute;
      bottom: 100%;
      height: 50px;
      width: 300px;
      left: 0;
      padding: 10px;
      border: 1px rgb(255, 215, 105) solid;
      background-color: rgba(0, 0, 0, 0.8);
      color: #ffffff;
      font-size: 12px;

      z-index: 9999;
    }
  }
}
@media (max-width: 768px) {
  .issuedetail.active {
    box-sizing: border-box;
    width: 100vw;
    height: 100vh;
    top: 0px;
    left: 0px;
    i {
      top: 2vh;
      right: 30px;
    }
  }
}
.issuedetail.turn {
  animation-name: turn;
  animation-duration: 0.5s;
}
@keyframes turn {
  from {
    transform: rotateY(0deg);
  }
  49% {
    transform: rotateY(90deg);
  }
  50% {
    transform: rotateY(-90deg);
  }
  to {
    transform: rotateY(0deg);
  }
}
</style>

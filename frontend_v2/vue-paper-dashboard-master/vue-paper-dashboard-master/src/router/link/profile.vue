<template>
  <div>
    <!--
  <div class="col-12">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card-user :bno="bno"></card-user>
        </div>
        
        <div class="col-5">
          <div class="card col-12">
            <div class="card-body">
              <center class="m-t-30">
                <h4 class="card-title m-t-10">내 관심사</h4>
                <div
                  class="ints btn btn-success"
                  v-for="inter in inters"
                  :key="inter.interest"
                >{{inter.interest}}</div>
                <hr />
                <router-link class="nav-link" to="/user/editinterest">
                  <button class="btn btn-info">관심사 수정</button>
                </router-link>
              </center>
            </div>
          </div>

          <div class="card col-12" style="max-height:430px; ">
            <div class="card-header">
              <h3 style="text-align:center;">프로젝트 수행 이력</h3>
              <hr>
              </div>
            <div class="card-body" style="overflow:scroll; overflow-x:hidden; margin-bottom:20px;">
              
        <div class = "col-12" style="margin-bottom:13px;" v-for="project in projects" :key="project.pid">
          <div class="row">
                <div class=" col-12 font-weight-bold " style="padding-left:0; max-height:60px; font-size:18px; overflow:hidden;" >{{project.pjtName}}</div>
                <div class="col-12" style="padding-left:0; max-height:50px; overflow:hidden; font-size:12px;"> {{project.pjtContent}}</div>
          </div>
          
        </div>
              </div>
          </div>
        </div>
        <div class="col-7">
<div class="card">
            <div class="card-body">
              <profile :foo="bno" @bno="val=>bno = val" />
            </div>
          </div>
        </div>

        <div class="col-12">
          <div class="card">
            <div class="card-body">
          <div class="row">
              <div class="col-4">
                <router-link class="nav-link" to="/user/editinfo">
                  <button class="profBtn btn btn-info">개인정보 수정</button>
                </router-link>
              </div>
              <div class="col-4">
                <router-link class="nav-link" :to="{path:'/service'}">
                  <button class="profBtn btn btn-info">고객센터</button>
                </router-link>
              </div>

              <div class="col-4">
                <router-link class="nav-link" to="/user/leave">
                  <button class="profBtn btn btn-danger">회원탈퇴</button>
                </router-link>
                </div>
            </div>
          </div>
            </div>
            </div>
    </div>
  </div>
</div>
    -->
    <!--------------------------------------------------------------------->
    <div class="user">
      <div class="container-fluid">
        <div class="row page-titles">
          <div class="col-md-12 align-self-center">
            <h1 class="text-themecolor">PROFILE</h1>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-5 col-xlg-4 col-md-5">
            <card-user :bno="bno"></card-user>
            <div class="card col-12" style="max-height:430px; ">
              <div class="card-header">
                <h3 style="text-align:center;margin-top:0">프로젝트 수행 이력</h3>
                <hr />
              </div>
              <div id="myPjtHistory" class="card-body" style="overflow:scroll; overflow-x:hidden; margin-bottom:20px;min-height:100px">
                <div class="col-12" style="margin-bottom:13px;" v-for="project in projects" :key="project.pid">
                  <div class="row pjt">
                    <div class="col-12 font-weight-bold" style="padding-left:0; max-height:60px; font-size:18px; overflow:hidden;">{{project.pjtName}}</div>
                    <div class="col-12" style="padding-left:0; max-height:50px; overflow:hidden; font-size:12px;">{{project.pjtContent}}</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="card">
              <div class="card-body">
                <center class="m-t-30">
                  <router-link class="nav-link" to="/user/editinfo">
                    <button class="profBtn btn btn-info">개인정보 수정</button>
                  </router-link>

                  <router-link class="nav-link" :to="{path:'/service'}">
                    <button class="profBtn btn btn-info">고객센터</button>
                  </router-link>

                  <router-link class="nav-link" to="/user/leave">
                    <button class="profBtn btn btn-danger">회원탈퇴</button>
                  </router-link>
                </center>
              </div>
            </div>
          </div>
          <div class="col-lg-6 col-xlg-8 col-md-7">
            <div class="card">
              <div class="card-body">
                <center class="m-t-30">
                  <h3 class="card-title m-t-10" style="display:flex;justify-content:space-between;align-items:center">
                    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>내 관심사
                    <router-link class="nav-link" to="/user/editinterest">
                      <button class="btn btn-info">수정</button>
                    </router-link>
                  </h3>
                  <div class="ints btn btn-success" v-for="inter in inters" :key="inter.interest">{{inter.interest}}</div>
                </center>
              </div>
            </div>
            <div class="card col-12">
              <div class="card-body">
                <profile :foo="bno" @bno="val=>bno = val" />
              </div>
            </div>

            <!-- -->
            
            <!--  -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profile from "../../components/user/profile.vue";
import cardUser from "../../components/user/cardUser.vue";
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;

export default {
  name: "user",
  components: {
    profile,
    cardUser,
  },
  mounted: function () {
    const config = {
      headers: {
        "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
      },
    };
    http
      .get("/api/interest/selectById/" + storage.getItem("userid"), config)
      .then((response) => {
        if (response.data.length > 0) {
          this.inters = response.data;
        } else {
          this.inters = [{ interest: "관심사가 아직 없습니다." }];
        }
      })
      .catch((exp) => alert("관심사를 로드하는데에 실패하였습니다." + exp));

    http
      .get("/api/project/searchByUserId/" + storage.getItem("userid"), config)
      .then((response) => {
        console.log("오우오우오오우");
        if (response.data.length > 0) {
          this.projects = response.data;
        } else {
          this.projects = [{ project: "프로젝트 수행 내역이 없습니다." }];
        }
      })
      .catch((exp) => alert("getPmemberList처리에 실패하였습니다." + exp));
  },
  data: function () {
    return {
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),
      inters: [],
      projects: [],
      bno: 0,
    };
  },
  computed: {
    userid() {
      return "/project/myproject/" + storage.getItem("userid");
    },
  },
  methods: {
    
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

#myPjtHistory {
  color: #66a ;
  .pjt {
    margin: 10px -10px 10px -10px;
    padding: 5px 10px 5px 10px;
    border-radius: 10px;
    box-shadow: #2f3542 2px 1px 5px;
  }
  &::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  &::-webkit-scrollbar-track {
    background-color: #eee;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
}
.ints {
  margin: 5px;
  border-radius: 20px;
}
button.profBtn {
  width: 70%;
  border-radius: 18px;
}
</style>

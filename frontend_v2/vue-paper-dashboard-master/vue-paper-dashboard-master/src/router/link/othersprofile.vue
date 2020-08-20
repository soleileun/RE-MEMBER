<template>
  <div class="othesprofile">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-5 col-xlg-4 col-md-5">
          <div class="row">
            <div class="col-12">
          <card-user :btitle="btitle" :userid="this.$route.params.userid"></card-user>
            <div class="card col-12" style="max-height:430px; ">
              <div class="card-header">
                <h3 style="text-align:center;margin-top:0">프로젝트 수행 이력</h3>
                <hr />
              </div>
              <div id="myPjtHistory" class="card-body" style="overflow:scroll; overflow-x:hidden; margin-bottom:20px;min-height:100px">
                <div v-if="projects[0].pid !== '프로젝트 수행 내역이 없습니다.'">
                  <div class="col-12" style="margin-bottom:13px;" v-for="project in projects" :key="project.pid">
                    <div class="row pjt">
                      <div class="col-12 font-weight-bold" style="padding-left:0; max-height:60px; font-size:18px; overflow:hidden;">{{project.pjtName}}</div>
                      <div class="col-12" style="padding-left:0; max-height:50px; overflow:hidden; font-size:12px;">{{project.pjtContent}}</div>
                    </div>
                  </div>
                </div>
                <div v-else>{{projects[0].pid}}</div>
              </div>
            </div>
            </div>
            <div class="col-12">
              
              



              </div>
              </div>
        </div>
        <div class="col-lg-7 col-xlg-8 col-md-7">
          <div class="card">
            <div class="card-body">
              <profile :userid="this.$route.params.userid" :foo="btitle" @btitle="val=>btitle = val"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profile from "../../components/user/otherprofile.vue";
import cardUser from "../../components/user/othercardUser.vue";
import Constant from "@/Constant.js";
import http from "@/http-common.js";
const storage = window.sessionStorage;
// 

export default {
  name: "othesprofile",
  components: {
    cardUser,
    profile,
  },
  mounted: function () {
    const config = {
      headers: {
        "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token"),
      },
    };
    http
      .get("/api/project/searchByUserId/" + this.$route.params.userid, config)
      .then((response) => {
        if (response.data.length > 0) {
          this.projects = response.data;
        } else {
          this.projects = [{ pid: "프로젝트 수행 내역이 없습니다." }];
        }
      })
      .catch((exp) => alert("getPmemberList처리에 실패하였습니다." + exp));
  },
  data: function () {
    return {
      userNick: storage.getItem("otherNick"),
      btitle:'',
      projects: [{pid:''}],

    };
  },
  methods: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.card {
  border: 1px #ddd solid;
}
#myPjtHistory {
  color: #66a;
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
</style>
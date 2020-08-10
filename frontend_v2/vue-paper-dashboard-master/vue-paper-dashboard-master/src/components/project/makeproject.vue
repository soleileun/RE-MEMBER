<template>
  <div id="makepjt" class="container-contact100">
    <div class="wrap-contact100">
      <br />
      <div class="contact100-form validate-form">
        <span class="contact100-form-title">
          <h2>프로젝트 생성페이지</h2>
        </span>
        <br />
        <br />
        <div class="wrap-input100 validate-input" data-validate="Name is required">
          <h5>프로젝트 팀장 : {{userId}}</h5>
          <span class="label-input100">Project Name</span>
          <br />
          <input
            v-model="wproject.pjtName"
            class="input100"
            type="text"
            name="pjtname"
            placeholder="Enter your project name"
          />
          <span class="focus-input100"></span>
        </div>

        <div class="wrap-input100 input100-select">
          <div>
            <span class="label-input100">Member</span>

            <select class="selection-2" name="service" v-model="wproject.pjtMemberCnt">
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
            </select>
          </div>
          <span class="focus-input100"></span>
        </div>
        <span class="label-input100">Content</span>
        <div>
          <textarea
            class="input100"
            v-model="wproject.pjtContent"
            name="contents"
            placeholder="Your Content here..."
          ></textarea>
          <span class="focus-input100"></span>
        </div>

        <div class="container-contact100-form-btn">
          <div class="wrap-contact100-form-btn">
            <div class="contact100-form-bgbtn"></div>
            <button class="contact100-form-btn" @click="addProject">
              <span>
                Submit
                <i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
              </span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";
const storage = window.sessionStorage;
//import project from "../project/project1";
export default {
  name: "makeproject",
  computed: {
    projects() {
      return this.$store.state.projectstore.projects;
    },
  },

  created() {
    // 세션 아이디로 교체
    this.$store.dispatch(Constant.GET_PROJECTLIST_BY_PMEMBER, {
      userId: storage.getItem("userid"), // userId: "ssafy",
    });
  },
  data() {
    return {
      userNick: storage.getItem("userNick"),
      userId: storage.getItem("userid"),

      wproject: {
        pid: "",
        pjtName: "",
        pjtContent: "",
        pjtState: "",
        pjtMemberCnt: "5",
        makeDay: "",
        changeDay: "",
        makeId: "",
        changeId: "",
        location: "",
      },
    };
  },
  methods: {
    addProject() {
      if (
        this.wproject.pjtContent.trim() != "" &&
        this.wproject.pjtName.trim() != "" &&
        this.wproject.pjtMemberCnt != "0"
      ) {
        this.$store.dispatch(Constant.ADD_PROJECT, {
          // pid: "",
          pjtName: this.wproject.pjtName,
          pjtContent: this.wproject.pjtContent,
          pjtState: "ing",
          pjtMemberCnt: this.wproject.pjtMemberCnt,
          makeDay: new Date(),
          //changeDay: this.wproject.title,
          makeId: this.userId,
          //changeId: this.wproject.title,
          //location: this.wproject.location,
        });
        //userid 수정해야됨!
        this.$router.push("/project/myproject/" + this.userId);
        console.log(this.userId);
      } else {
        console.log("공백입력.");
      }
    },
  },
};
</script>

<style></style>

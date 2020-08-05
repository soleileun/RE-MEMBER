<template>
  <div class="findIDPW">
    <div>
      <form action="#">
        <h1>아이디 찾기</h1>
        <input type="radio" v-model="idradio" value="phone" />전화번호로 찾기
        <input type="radio" v-model="idradio" value="email" />이메일로 찾기
        <br />이름 :
        <input type="text" v-model="idname" />
        <br />
        <span v-if="idradio === 'email'">
          이메일주소 :
          <input type="text" v-model="idemail1" />@
          <input type="text" v-model="idemail2" />
        </span>
        <span v-else>
          전화번호 :
          <input type="text" v-model="idphone1" /> -
          <input type="text" v-model="idphone2" /> -
          <input type="text" v-model="idphone3" />
        </span>
        <br />
        <div class="submit" @click="submitID">찾기</div>
      </form>
    </div>
    <hr />
    <div>
      <form action="#">
        <h1>비밀번호 찾기</h1>
        <input type="radio" v-model="pwradio" value="phone" />가입시 입력한 전화번호로 전송
        <input type="radio" v-model="pwradio" value="email" />가입시 입력한 이메일 주소로 전송
        <br />아이디 :
        <input type="text" v-model="pwID" />
        <br />이름 :
        <input type="text" v-model="pwname" />
        <br />
        <div class="submit" @click="submitpw">
          <span v-if="pwradio === 'email'">이메일 주소</span>
          <span v-else>전화번호</span>
          로 변경된 비밀번호를 전송합니다.
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "findIDPW",
  data: function() {
    return {
      idradio: "phone",
      idname: "",
      idphone1: "",
      idphone2: "",
      idphone3: "",
      idemail1: "",
      idemail2: "",
      pwradio: "phone",
      pwID: "",
      pwname: ""
    };
  },
  methods: {
    submitID: function() {
      let form = new FormData();
      form.append("name", this.idname);
      if (this.idradio === "phone") {
        form.append("phone", this.idphone1 + this.idphone2 + this.idphone3);
      } else {
        form.append("email", this.idemail1 + "@" + this.idemail2);
      }

      axios({
        method: "post",
        url: "아이디 찾기 url",
        data: form,
        responseType: "json"
      })
        .then(response => {
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    submitpw: function() {
      let form = new FormData();
      form.append("id", this.pwID);
      form.append("name", this.pwname);
      if (this.pwradio === 'phone'){
        form.append("method",'phone');
      }
      else {
        form.append("method",'email');
      }
      axios({
        method: "post",
        url: "비번 찾기 url",
        data: form,
        responseType: "json"
      })
        .then(response => {
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.submit{
  margin: 15px;
  margin-left: 30vw;
  margin-right: 30vw;
  background-color: #eee;
  border: 1px black solid;
}
.submit:hover{
  cursor: pointer;
  
}
</style>

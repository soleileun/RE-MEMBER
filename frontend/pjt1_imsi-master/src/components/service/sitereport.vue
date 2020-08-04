<template>
  <div class="sitereport">
    <select name="reporttype" v-model="reporttype">
      <option value="report">신고</option>
      <option value="qa">문의</option>
      <option value="propose">제안</option>
    </select>
    <br />
    <textarea name="reportform" id="reportform" cols="30" rows="10" v-model="reportVal"></textarea>
    <br />
    <button @click="submit">제출</button>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "sitereport",
  data: function() {
    return {
      reporttype: "report",
      reportVal: ""
    };
  },
  methods: {
    submit: function() {
      // 운영자에게 쪽지 보내기로 구현
      let form = new FormData();
      form.append("to", "admin");
      form.append("title", this.reporttype);
      form.append("content", this.reportVal);
      axios({
        method: "post",
        url: "쪽지 url",
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
.sitereport {
  border: 1px solid gray;
  padding: 15px;
}
</style>

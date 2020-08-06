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
      if (this.mesval !== "") {
        this.$store.dispatch("sendMes", {
          other: "admin",
          content: `[${this.reporttype}]` + this.reportVal,
        });
        this.reportVal = '';
        
      }
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

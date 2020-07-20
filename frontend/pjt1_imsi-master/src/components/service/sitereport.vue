<template>
  <div class="sitereport">
    <select name="reporttype" v-model="reporttype">
      <option value="report">신고</option>
      <option value="qa">문의</option>
      <option value="propose">제안</option>
    </select>
    <br>
    <textarea name="reportform" id="reportform" cols="30" rows="10" v-model="reportVal"></textarea><br>
    {{reporttype}}/{{reportVal}}
  </div>
</template>
<script>
export default {
  name: "sitereport",
  data: function() {
    return {
      reporttype:'report',
      reportVal:''
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.sitereport {
  border: 1px solid gray;
  width: 70vw;
  margin-left: 15vw;
  padding: 15px;
}
</style>

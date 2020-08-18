<template>
  <div class="sitereport">
    <select class="custom-select" name="reporttype" v-model="reporttype">
      <option value="report">신고</option>
      <option value="qa">문의</option>
      <option value="propose">제안</option>
    </select>
    <br />
    <textarea name="reportform" id="reportform" cols="30" rows="10" v-model="reportVal"></textarea>
    <br />
    <div align="right">
      <button @click="submit" class="btn btn-dark">제출 :)</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import serviceSubmitNT from "@/pages/Notifications/serviceSubmitNT";
export default {
  name: "sitereport",
  data: function () {
    return {
      type: ["", "info", "success", "warning", "danger"],
      reporttype: "report",
      reportVal: "",
    };
  },
  methods: {
    submit: function () {
      if (this.mesval !== "") {
        this.$store.dispatch("sendMes", {
          other: "admin",
          content: `[${this.reporttype}]` + this.reportVal,
        });
        this.reportVal = "";
      }
      {
        this.notifyVue("top", "center");
      }
    },
    notifyVue(verticalAlign, horizontalAlign) {
      console.log("실행됨");
      const color = Math.floor(Math.random() * 4 + 1);
      //1 info 2 success 3 warn 4 danger
      this.$notify({
        component: serviceSubmitNT,
        icon: "ti-gift",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color],
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Open+Sans&family=Roboto&display=swap");
.sitereport {
  font-family: "Nanum Gothic", sans-serif;
  border: 1px solid gray;
  padding: 15px;
  background-color: white;
}
form {
  text-align: center;
  width: 50%;
  margin: 0 auto;
}

textarea {
  font-family: "Nanum Gothic", sans-serif;
  display: block;
  width: 100%;
  border: 0;
  padding: 10px 5px;
  background: white no-repeat;
  background-image: linear-gradient(to bottom, #292424, #292424),
    linear-gradient(to bottom, silver, silver);
  background-size: 0 2px, 100% 1px;
  background-position: 50% 100%, 50% 100%;

  /* animation solely on background-size */
  transition: background-size 0.3s cubic-bezier(0.64, 0.09, 0.08, 1);
}
textarea:focus {
  /* sizes for the 2 images (focus state) */
  background-size: 100% 2px, 100% 1px;
  outline: none;
}

.custom-select {
  position: relative;
  width: 50%;
  font-family: "Nanum Gothic", sans-serif;
}

.custom-select select {
  display: none; /*hide original SELECT element:*/
}

/*style the arrow inside the select element:*/
.select-selected:after {
  position: absolute;
  content: "";
  top: 14px;
  right: 10px;
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-color: #fff transparent transparent transparent;
}

/*point the arrow upwards when the select box is open (active):*/
.select-selected.select-arrow-active:after {
  border-color: transparent transparent #fff transparent;
  top: 7px;
}

/*style the items (options), including the selected item:*/
.select-items div,
.select-selected {
  color: #ffffff;
  padding: 8px 16px;
  border: 1px solid transparent;
  border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
  cursor: pointer;
  user-select: none;
}

/*style items (options):*/
.select-items {
  position: absolute;
  background-color: #292424;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 99;
}

/*hide the items when the select box is closed:*/
.select-hide {
  display: none;
}

.select-items div:hover,
.same-as-selected {
  background-color: rgba(0, 0, 0, 0.1);
}
</style>

<template>
  <div class="mesToAll">
    전체 공지
    <br />
    <textarea v-model="mesval" cols="50" rows="3"></textarea>
    <br />
    <button @click="send">보내기</button>
  </div>
</template>
<script>
import http from "@/http-common.js";
export default {
  name: "mesToAll",
  computed: {
    id: function () {
      return this.$store.state.userstore.mesdetailid;
    },
  },
  data: function () {
    return {
      mesval: "",
      users: [],
    };
  },
  methods: {
    send: function () {
      http
        .get("/api/userinfo/")
        .then((response) => {
          this.users = response.data;
          if (this.users) {
            this.users.forEach((element) => {
              this.$store.dispatch("sendMes", {
                other: element.id,
                content: this.mesval,
              });
            });
          }
        })
        .catch((e) => {
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.terms {
  border: 1px solid gray;
  width: 70vw;
  margin-left: 15vw;
  padding: 15px;
}
</style>

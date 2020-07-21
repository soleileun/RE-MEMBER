<template>
  <div class="sending">
    <textarea v-model="mesval" cols="50" rows="3"></textarea>
    <button @click="send">보내기</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "sending",
  data: function() {
    return {
      mesval: ""
    };
  },
  props: {
    id: String
  },
  methods: {
    send: function() {
      let form = new FormData();
      form.append("to_user", this.id);
      form.append("from_user", this.$store.state.user.id);
      form.append("content", this.mesval);
      axios({
        method: "post",
        url: "쪽지보내기 url",
        data: form,
        responseType: "json"
      })
        .then(response => {
          this.msgs = response;
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
.sending {
  background-color: red;
}
</style>

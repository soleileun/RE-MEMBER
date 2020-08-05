<template>
  <div class="sending">
    <textarea v-model="mesval" cols="50" rows="3"></textarea>
    <button @click="send">보내기</button>
  </div>
</template>

<script>
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

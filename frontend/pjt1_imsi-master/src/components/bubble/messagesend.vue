<template>
  <section class="messagesend">
    <h6>{{id}}에게 전송</h6>
    <textarea v-model="mesval" cols="50" rows="3"></textarea>
    <button @click="send">보내기</button>
  </section>
</template>

<script>
export default {
  name: "messagesend",
  components: {
  },
  data: function() {
    return {
      mesval:''
    };
  },
  props: {
    id: String
  },
  methods:{
    send: function(){

    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messagesend{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  background-color: yellow;
}

</style>

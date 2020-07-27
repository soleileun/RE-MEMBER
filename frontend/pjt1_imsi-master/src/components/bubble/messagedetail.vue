<template>
  <div class="messages">
    <section class="detaillist">
      쪽지 디테일.. id가 {{id}} 인 사람
      <li v-for="mes in msgs" :key="mes.mnum">{{mes.mnum}} : {{mes.content}}</li>
    </section>
    <sending :id="id"/>
  </div>
</template>

<script>
import sending from "./sending.vue";
export default {
  name: "messages",
  components: {
    sending
  },
  data: function() {
    return {
      msgs: []
    };
  },
  props: {
    id: String
  },
  mounted: function() {
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messages{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: yellow;
}

</style>

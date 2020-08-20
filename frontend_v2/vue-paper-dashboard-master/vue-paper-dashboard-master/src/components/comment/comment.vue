<template>
  <div>
    {{comment.cwriter}} {{comment.ccontent}} {{comment.makeDay}}
    <button
      @click="deleteComment(comment)"
    >삭제</button>
  </div>
</template>

<script>
import Constant from "../../Constant";
const storage = window.sessionStorage;
export default {
  data() {
    return {
      userNick: storage.getItem("userNick"),
    };
  },
  props: {
    comment: {
      type: Object,
      required: true,
    },
  },

  methods: {
    deleteComment(comment) {
      console.log("왜???");
      if (this.userNick === comment.cwriter) {
        this.$store.dispatch(Constant.REMOVE_COMMENT, {
          cno: comment.cno,
          bno: comment.bno,
        });
      } else {
        aler("삭제할 수 없습니다");
      }
    },
  },
};
</script>

<style>
</style>
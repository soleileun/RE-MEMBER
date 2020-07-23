<template>
  <div>
    {{comment.cwriter}} {{comment.ccontent}} {{comment.makeDay}} 
    <button @click="deleteComment(comment)">삭제</button>
  </div>
</template>

<script>
import Constant from '../../Constant';
export default {
  props:{
    comment : {
      type : Object,
      required : true
    }
  },

  methods:{
    deleteComment(comment){
      this.$store.dispatch(Constant.REMOVE_COMMENT,{cno : comment.cno, bno : comment.bno });
      
    }
  }
}
</script>

<style>

</style>
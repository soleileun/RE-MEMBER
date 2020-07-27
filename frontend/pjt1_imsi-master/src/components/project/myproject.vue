<template>
  <div class="project1">
    <h1>프로젝트 관리 페이지</h1>
    <hr>
    
    <project v-for="pmember in pmembers" 
            :key="pmember.pid"
            :pmember="pmember"
            />

            <!-- @delete-project="deleteComment" -->
    
  </div>
</template>

<script>
import Constant from '../../Constant';

import project from '../project/project1';

export default {
  name: "myproject",
  components:{
    project
  },
  computed: {
    pmembers() {
      console.log(this.$store.state.projectstore.pmembers);
      return this.$store.state.projectstore.pmembers; 
    }
  },
  created () {
    this.getPmemberList(this.$route.params.userId);
  },

  methods: {
    getPmemberList(userId) {
      console.log('과정1');
      this.$store.dispatch(Constant.GET_PMEMBERLIST,{userId });
    },

    
  },
  // data() {
  //   return {
  //     project : {
  //       pid : 'ssafy',
  //       pjtname : '블록체인 프로젝트',
  //       pjtstartdate : '2020-02-11',
  //       pjtcontent : '블록 체인을 이용',
  //       pjtstate : '완료',
  //       pjtmember_cnt : 3,
  //       makeday : '2020-03-31',
  //       changeday : '2020-07-01',
  //       makeid : 'makeid1',
  //       changeid : 'changeid1'
  //     }
  //   }
  // },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>

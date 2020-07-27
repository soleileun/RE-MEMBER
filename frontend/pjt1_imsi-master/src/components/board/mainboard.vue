<template>
  <div id="mb">
    <h1>메인 보드 샘플</h1>
    <hr>

    <div class="tab_wrap">
      <div class="tab_menu_container">
        <button class="tab_menu_btn " type="button" v-on:click="urlFree">자유게시판</button>
        <button class="tab_menu_btn" type="button" v-on:click="urlNotice">공지사항</button>
        <button class="tab_menu_btn" type="button" v-on:click="urlQa">질문게시판</button>
      </div> <!-- tab_menu_container e -->
      <hr>
  
        
            <router-view></router-view>
        
      
    </div> <!-- tab_wrap e -->


  </div>
</template>

<!-- JQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  
<script>
// import freeboard from "../freeboard/freeboard1.vue";
// import notice from "../notice/notice1.vue";
// import qaboard from "../qaboard/qaboard1.vue";
export default {
    // components: {
    //     freeboard,
    //     notice,
    //     qaboard,
    //  },
    mounted(){
        //탭 메뉴 js

        $('.tab_menu_btn').on('click',function(){
            //버튼 색 제거,추가
            $('.tab_menu_btn').removeClass('on');
            $(this).addClass('on')
            
            //컨텐츠 제거 후 인덱스에 맞는 컨텐츠 노출
            var idx = $('.tab_menu_btn').index(this);
            
            $('.tab_box').hide();
            $('.tab_box').eq(idx).show();
        });
    },
    methods:{
      urlFree(){
        this.$router.push('/mainboard/freeboard');

      },
      urlNotice(){
        this.$router.push('/mainboard/notice');

      },
      urlQa(){
        this.$router.push('/mainboard/qaboard');
      },
    }
}

</script>

<style>
#mb button {
  background:none;
  border:0;
  outline:0;
  cursor:pointer;
}
.tab_menu_container {
  display:flex;
}
.tab_menu_btn {
  width:200px;
  height:40px;
  transition:0.3s all;
}
.tab_menu_btn.on {
  border-bottom:2px solid #df0000;
  font-weight:700;
  color:#df0000;
}
.tab_menu_btn:hover {
  color:#df0000;
}
.tab_box {
  display:none;
  padding:20px;
}
.tab_box.on {
  display:block;
}

</style>

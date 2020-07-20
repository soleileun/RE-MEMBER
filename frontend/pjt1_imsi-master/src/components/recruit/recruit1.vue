<template>
  <div class="recruit1" id="rec">
    <h1>모집 메인 샘플 1</h1>
    <hr>

    <div class="tab_wrap">
  <div class="tab_menu_container">
    <button class="tab_menu_btn on" type="button">팀원 찾기</button>
    <button class="tab_menu_btn" type="button">구인중인 프로젝트</button>
  </div> <!-- tab_menu_container e -->
  <hr>

  <div class="tab_box_container">
    <div class="tab_box on">
        <searchmember />
    </div>
    <div class="tab_box">
        <searchteam />
    </div>

  </div> <!-- tab_box_container e -->
</div> <!-- tab_wrap e -->

  </div>
</template>

<!-- JQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
 
<script>
import searchmember from "../recruit/searchmember.vue";
import searchteam from "../recruit/searchteam.vue";

export default {
  name: "recruit1",
  components: {
        searchmember,
        searchteam
     },
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
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
#rec button {
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

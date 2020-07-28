<template>

<!-- 테스트용
<div class="about"> 
  <div class="container"> 
    <div class="left"> 
      <div ref="left" id="drag-elements"> 
        <div>Element 1</div> 
        <div>Element 2</div> 
        <div>Element 3</div> 
      </div> 
      <div ref="right" id="drop-target"> 
      </div> 
      <div class="left" ref="middle" id="drop-target"> 
      </div> 
    </div> 
    <div class="right"> 
      <div id="display" ref="display1">Display</div> 
    </div> 
  </div> 
-->
  <div class="hello">
    <div style="background:#E6E6E6; height:1000px;padding:20px;">
      <div style="height: 200px; background:white;   border-radius: 10px;width:100%; float:left;">
        간단한 이슈 대시보드
        <br>
        프로젝트 db 연동 완료<br>
        {{project.pid}}
        {{project.makeId}}
        {{project.pjtName}}
        {{project.pjtContent}}
      </div>

      <section>
        <div style="background:white;margin-top:40px;margin-bottom:40px; height:300px; width:47%; float:left; border-radius: 10px;padding-top:10px;padding-left:10px;padding-right:10px;">
          전체 이슈 처리 현황
          그래프를 넣어주자.
        </div>

        <div style="background:white; margin-top:40px;margin-bottom:40px; height:300px;  width:47%; float:right; border-radius: 10px;padding-top:10px;padding-left:10px;padding-right:10px;overflow-y : scroll">
          진행중인 이슈
          나한테 할당된 이슈나, 이런거 보여줌<br>
          아니면 총 이슈에 대한 정보를 스크롤 바 형식으로 보여주면 좋을것같음.
          
          <section style="padding:5px; margin:5px; border:1px solid #D8D8D8; background:white; border-radius:25px; "> 
            이런식으로 들어감
          </section>
          <section style="padding:5px; margin:5px; border:1px solid #D8D8D8; background:white; border-radius:25px; s"> 
            요렇게
          </section>
        </div>


      </section>

      <div style="height: 300px; background:white;  border-radius: 10px;width:100%; float:left;">
        간단한 이슈 대시보드 <br>

        <div class="left" id="before" ref="before" style="background:lightgrey; width:29%;height:80%;border-radius: 20px; margin:10px; padding:10px; float:left;">
          생성된 이슈
        </div>

        <div class="left" id="during" ref="during" style="background:lightgrey; width:29%;height:80%;border-radius: 20px; margin:10px;padding:10px; float:left;">
          진행중인 이슈 <br>
          <div class="test" id="첫번째" style="padding:5px; margin:5px; background:white; border-radius:25px;"> 
            이런식으로 들어감
          </div>
          <div class="test" id = "두번째" style="padding:5px; margin:5px; background:white; border-radius:25px;"> 
            요렇게
          </div>
        </div>
        <div ref="end" id="end" class="left" style="background:lightgrey; width:29%;height:80%;border-radius: 20px; margin:10px;padding:10px; float:right;">
          완료된 이슈
        </div>

      </div>
      
    </div>

  </div>
  

  
<!--</div>-->
</template>


<script>
import 'expose-loader?$!expose-loader?jQuery!jquery'
// export default {
//   name: 'HelloWorld',
//   props: {
//     msg: String
//   }
// }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
-->

<script >
import dragula from 'dragula'
import Constant from '../../Constant';

export default {
  created(){
      this.$store.dispatch(Constant.GET_PROJECT,{pid : this.$route.params.pid});
  },
  computed:{
    project(){
      return this.$store.state.projectstore.project;
    }
  },
   mounted(){ 
    const { //right, display1, left, 
    middle,before,during,end } = this.$refs;
    dragula([ 
      //left, right, 
      middle,before,during,end //어디서 어디로 옮기는지 
    ],{
      revertOnSpill: true //클릭한 요소 컨테이너 밖으로 보내도 순서유지. 
    }

    ).on('drop', el => { 
      //console.log("drop");
      
      //console.log("source : " + source);
      //console.log("el : " + el.className);
      console.log("id : " + el.id);
      //console.log("부모 id : " + this.$parent.name);
      console.log("부모 id : " + document.querySelector('#' + el.id).parentNode.id);
      console.log(el.id + "의 상태를 " + document.querySelector('#' + el.id).parentNode.id + "로 바꾸는 쿼리 실행");
      //부모 id에 따라 상태를 대기 진행중 완료됨으로 업데이트 하는 쿼리 실행하면 됨


      //console.log(this.$parent);
      //var a = $('첫번째').closet('div');
      //console.log(a);
      //console.log($("첫번째").closet('div'));
      /*
      if(right.children.length > 0) { 
        display1.innerHTML = right.innerHTML 
      }else{ 
        display1.innerHTML = "Display" 
      } 
      */
    }) 
  } 
} 
</script>
<style scoped lang="scss">
.container { 
  max-width: 960px; 
  margin: 0 auto; 
} 
.left { 
  float: left; 
  position: relative; 
  width: 50%; 
  height: 100%; 
} 
.right { 
  float: left; 
  position: relative; 
  width: 40%; 
  margin-left: 5%; 
  height: 100%; 
} 
#display { 
  background: #2d2d2d; 
  border: 10px solid #000000; 
  border-radius: 5px; 
  font-size: 2em; 
  color: white; 
  height: 100px; 
  min-width:200px; 
  text-align: center; 
  padding: 1em; 
  display:table-cell; 
  vertical-align:middle; 
}
#drag-elements { 
  display: block; 
  background-color: #dfdfdf; 
  border-radius: 5px; 
  min-height: 50px; 
  margin: 0 auto; 
  padding: 2em; 
} 
#drag-elements > div { 
  text-align: center; 
  float: left; 
  padding: 1em; 
  margin: 0 1em 1em 0; 
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.3); 
  border-radius: 100px; 
  border: 2px solid #ececec; 
  background: #F7F7F7; 
  transition: all .5s ease; 
} 
#drag-elements > div:active { 
  -webkit-animation: wiggle 0.3s 0s infinite ease-in; 
  animation: wiggle 0.3s 0s infinite ease-in; 
  opacity: .6; 
  border: 2px solid #000; 
} 
#drag-elements > div:hover { 
  border: 2px solid gray; 
  background-color: #e5e5e5;
 } 
 #drop-target { 
   border: 2px dashed #D9D9D9; 
   border-radius: 5px; 
   min-height: 50px; 
   margin: 0 auto; 
   margin-top: 10px; 
   padding: 2em; 
   display: block; 
   text-align: center;
} 
#drop-target > div { 
  text-align: center; 
  float: left; 
  padding: 1em; 
  margin: 0 1em 1em 0; 
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.3); 
  border-radius: 100px; 
  border: 2px solid #ececec; 
  background: #F7F7F7; 
  transition: all .5s ease; 
} 
#drop-target > div:active { 
  -webkit-animation: wiggle 0.3s 0s infinite ease-in; 
  animation: wiggle 0.3s 0s infinite ease-in; 
  opacity: .6; 
  border: 2px solid #000; 
} 
@-webkit-keyframes wiggle { 
  0% { 
    -webkit-transform: rotate(0deg);
  } 
  25% { 
    -webkit-transform: rotate(2deg); 
  } 
  75% { 
    -webkit-transform: rotate(-2deg);
  } 
  100% { 
    -webkit-transform: rotate(0deg); 
  } 
} 
@keyframes wiggle { 
  0% { 
    transform: rotate(-2deg); 
  }
   25% { 
     transform: rotate(2deg); 
  } 
  75% { 
    transform: rotate(-2deg); 
  } 
  100% { 
    transform: rotate(0deg); 
  } 
} 
.gu-mirror {
  position: fixed !important; 
  margin: 0 !important; 
  z-index: 9999 !important; 
  padding: 1em; 
} 
.gu-hide { 
  display: none !important; 
}
.gu-unselectable { 
  -webkit-user-select: none !important; 
  -moz-user-select: none !important; 
  -ms-user-select: none !important; 
  user-select: none !important; 
  } 
.gu-transit { 
  opacity: 0.5; 
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)"; 
  filter: alpha(opacity=50); 
} 
.gu-mirror { 
  opacity: 0.5; 
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)"; 
  filter: alpha(opacity=50); 
} 
</style>

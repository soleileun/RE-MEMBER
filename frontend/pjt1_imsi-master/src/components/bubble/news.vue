<template>
  <div class="news">
    <section class="newss">
      <div v-for="neww in news" :key="neww.mnum">
        <span>{{neww.time}} {{neww.content}}</span>
        <i class="far fa-trash-alt del" @click="read(neww.mnum)"></i>
      </div>
      <div v-if="news.length===0">알림이 없습니다.</div>
    </section>
    <button class="btn" @click="readAll">모두 삭제</button>
  </div>
</template>

<script>
export default {
  name: "news",
  computed: {
    news: function () {
      return this.$store.state.userstore.news;
    },
  },
  mounted: function () {
    let com = document.querySelector(".newss");
    com.scrollTop = com.scrollHeight;
  },
  methods: {
    read: function (mnum) {
      console.log(mnum);
      // this.$store.dispatch('newsRead',{mnum:mnum})
    },
    readAll: function () {
      this.news.forEach((element) => {
        console.log(element.mnum);
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.news {
  display: flex;
  flex-direction: column;
  justify-self: start;
  height: 100%;
  .newss {
    flex-grow: 1;
    box-shadow: #aaa 0px 1px 15px;
    div:nth-child(n) {
      display: flex;
      justify-content: space-between;
      margin: 10px;
      padding: 10px;
      border-radius: 5px;
      box-shadow: 1px 1px 3px black;
      &:hover {
        border: 1px solid black;
      }
      span {
        cursor: text;
      }
      .del {
        cursor: pointer;
        &:hover {
          font-size: 1.1rem;
          font-weight: 900;
        }
      }
    }
  }
  .btn {
    height: 60px;
    &:hover {
      font-weight: 900;
    }
  }
}
</style>

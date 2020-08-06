<template>
  <div class="news">
    <section class="newss">
      <div v-for="neww in news" :key="neww.mnum" :class="{unread:!neww.read}">
        <span @mouseenter="read(neww.mnum,neww.read)">
          {{newsTime(neww.time)}}
          <br />
          {{neww.content}}
        </span>
      </div>
      <div v-if="news.length===0">알림이 없습니다.</div>
    </section>
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
    setTimeout(() => {
      com.scrollTop = com.scrollHeight;
    }, 50);
  },
  data: function () {
    return {};
  },
  methods: {
    newsTime(str) {
      if (str) {
        const year = str.slice(0, 4),
          month = str.slice(5, 7),
          day = str.slice(8, 10),
          si = str.slice(11, 13),
          boon = str.slice(14, 16),
          cho = str.slice(17, 19);
        return `${year}/${month}/${day} ${si}시 ${boon}분 ${cho}초`;
      } else {
        return "";
      }
    },
    read: function (mnum, read) {
      if (!read) {
        this.$store.dispatch("mesRead", { mnum: mnum });
        this.news.find((item) => {
          if (item.mnum === mnum) {
            item.read = true;
            return true;
          }
        });
      }
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
    overflow: auto;
    &::-webkit-scrollbar {
      width: 10px;
    }
    &::-webkit-scrollbar-thumb {
      background-color: #2f3542;
      border-radius: 10px;
      background-clip: padding-box;
      border: 2px solid transparent;
    }
    &::-webkit-scrollbar-track {
      background-color: #aae;
      border-radius: 10px;
      box-shadow: inset 0px 0px 5px white;
    }
    div:nth-child(n) {
      display: flex;
      justify-content: space-between;
      margin: 10px;
      padding: 10px;
      border-radius: 5px;
      box-shadow: 0px 1px 1px black;
      &.unread {
        border: red 1px solid;
      }
      &:hover {
        border: 1px solid black;
      }
      span {
        cursor: text;
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

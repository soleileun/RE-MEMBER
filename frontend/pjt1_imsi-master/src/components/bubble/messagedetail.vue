<template>
  <div class="messagedetail">
    <section class="detaillist">
      <div class="eachMes" v-for="mes in msgs" :key="mes.mnum" :class="{read:mes.read}">
        <div v-if="mes.fromUser === myid" class="me">
          <i class="fas fa-envelope-open-text">나</i>
          <div class="mesContent">
            {{mes.content}}
            <h6>
              <i class="fas fa-ellipsis-h"></i>
              <span>{{mestime(mes.time)}}</span>
            </h6>
          </div>
        </div>
        <div v-else class="you">
          <i class="fas fa-envelope-open-text">{{mes.fromUser}}</i>
          <div class="mesContent">
            {{mes.content}}
            <h6>
              <span>{{mestime(mes.time)}}</span>
              <i class="fas fa-ellipsis-h"></i>
            </h6>
          </div>
        </div>
      </div>
    </section>
    <messagesend />
  </div>
</template>

<script>
import messagesend from "./messagesend.vue";
const storage = window.sessionStorage;

export default {
  name: "messagedetail",
  components: {
    messagesend,
  },
  data: function () {
    return {
      myid: storage.getItem("userid"),
    };
  },
  computed: {
    msgs: function () {
      let tmp = this.$store.state.userstore.mesDetail;
      if (tmp) {
        tmp.sort(function (a, b) {
          return a.mnum - b.mnum;
        });
      }
      return tmp;
    },
    id: function () {
      return this.$store.state.userstore.mesdetailid;
    },
  },
  mounted: function () {
    this.$store.dispatch("detailMes", { other: this.id });
    let com = document.querySelector(".detaillist");
    setTimeout(() => {
      com.scrollTop = 9999999999;
    }, 50);
  },
  methods: {
    mestime: function (x) {
      if (x) {
        return x.slice(0, 10);
      }
    },
    delmes: function (x) {
      this.$store.dispatch("delMes", { mnum: x });
    },
  },
  beforeDestroy: function () {
    this.$store.commit("viewMes", { id: "", view: false });
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.messagedetail {
  display: flex;
  height: 100%;
  flex-direction: column;
  justify-content: space-around;
  .detaillist {
    overflow-y: auto;
    flex-grow: 1;
    .eachMes {
      margin: 3px;
      padding: 3px;
      h6 {
        display: flex;
        justify-content: space-between;
        // padding: 5px 10px px 5px;
        margin: 0;
      }
      .you {
        text-align: start;
        .mesContent {
          padding: 10px;
          border: 1px black solid;
          background-color: beige;
          &.read {
            background-color: skyblue;
          }
        }
      }
      .me {
        text-align: end;
        .mesContent {
          padding: 10px;
          border: 1px black solid;
          background-color: aquamarine;
        }
      }
    }
  }
}
</style>

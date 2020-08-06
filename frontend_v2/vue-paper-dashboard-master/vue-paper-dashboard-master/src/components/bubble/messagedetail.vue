<template>
  <div class="messagedetail">
    <section class="detaillist">
      <div class="eachMes" v-for="mes in msgs" :key="mes.mnum" :class="{read:mes.read}">
        <div v-if="mes.fromUser === myid" class="me">
          <i class="ti-email">나</i>
          <div class="mesContent">
            {{mes.content}}
            <h6>
              <div class="dropdown">
                <i class="ti-more-alt" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>

              </div>
              <span>{{mestime(mes.time)}}</span>
            </h6>
          </div>
        </div>
        <div v-else class="you">
          <span>
            <i class="ti-email">{{mes.fromUser}}</i>
            <strong v-if="!mes.read">읽지 않음</strong>
          </span>
          <div class="mesContent" @mouseenter="read(mes.mnum,mes.read);mes.read = true">
            {{mes.content}}
            <h6>
              <span>{{mestime(mes.time)}}</span>
              <div class="dropdown">
                <i class="ti-more-alt" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
                
              </div>
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
      mnums:[],
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
    read: function (mnum, read) {
      if (!read && this.mnums.indexOf(mnum)===-1) {
        this.mnums.push(mnum)
        this.$store.dispatch("mesRead", { mnum: mnum });
       
      }
    },
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
    this.$store.dispatch("update", {});
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
    box-shadow: #2f3542 0px 1px 3px;
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
    .eachMes {
      margin: 3px;
      padding: 3px;
      margin-bottom: 5px;
      h6 {
        display: flex;
        justify-content: space-between;
        padding-top: 10px;
        margin: 0px;
        margin-bottom: -5px;
        i {
          font-size: 1.1rem;
          cursor: pointer;
          &:hover {
            font-size: 1.25rem;
            background: #aaa;
            border-radius: 50px;
          }
        }
      }
      .you {
        text-align: start;
        .mesContent {
          padding: 10px;
          border: 1px black solid;
          border-radius: 10px;
          background-color: beige;
          &.read {
            background-color: skyblue;
          }
          &:hover {
            box-shadow: #aaa 2px 2px 2px;
          }
        }
      }
      .me {
        text-align: end;
        .mesContent {
          padding: 10px;
          border: 1px black solid;
          border-radius: 10px;
          background-color: aquamarine;
          &:hover {
            box-shadow: #aaa 1px 1px 5px;
          }
        }
      }
    }
  }
}
</style>

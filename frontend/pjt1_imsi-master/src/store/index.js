import Vue from 'vue';
import Vuex from 'vuex';
// import Constant from '../Constant.js';
// import http from '../http-common.js';

Vue.use(Vuex);

// 작성한 모듈을 가져옵니다.
import boardstore from '@/store/modules/boardstore.js';
import userstore from '@/store/modules/userstore.js';
import commentstore from '@/store/modules/commentstore.js';
import projectstore from '@/store/modules/projectstore.js';
import poolstore from '@/store/modules/poolstore.js';
import recruitstore from '@/store/modules/recruitstore.js';
import chatstore from '@/store/modules/chatstore.js';
import stackstore from '@/store/modules/stackstore.js';

const store = new Vuex.Store({
  modules: {
    // 키: 값 형태로 저장됩니다.
    // 이런형태로 저장!
    boardstore,
    userstore,
    commentstore,
    projectstore,
    poolstore,
    recruitstore,
    chatstore,
    stackstore
  }
});

export default store;
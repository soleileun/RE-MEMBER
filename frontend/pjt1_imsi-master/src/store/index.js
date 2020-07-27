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

const store = new Vuex.Store({
  modules: {
    // 키: 값 형태로 저장됩니다.
    // 이런형태로 저장!
    boardstore,
    userstore,
    commentstore,
    projectstore
  }
});

export default store;
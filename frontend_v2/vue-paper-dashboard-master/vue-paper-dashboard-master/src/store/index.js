import Vue from 'vue';
import Vuex from 'vuex';
// import Constant from '../Constant.js';
// import http from '../http-common.js';
import createPersistedState from 'vuex-persistedstate';

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
import issuestore from '@/store/modules/issuestore.js';
import mapstore from '@/store/modules/mapstore.js';
import filestore from '@/store/modules/filestore.js';
import pmemberstore from '@/store/modules/pmemberstore.js';

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
    stackstore,
    issuestore,
    mapstore,
    filestore,
    pmemberstore
  },
  plugins: [
    createPersistedState({
      paths: ['mapstore', 'issuestore'],
    })
  ]
});

export default store;

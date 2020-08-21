import Vuex from 'vuex';
import Vue from 'vue';

import Constant from '../../Constant.js';
import http from '../../http-common.js';
Vue.use(Vuex);

const mapstore = {
    state: {
        users: []
    },

    actions: {
        //게시판

        //bstate에 맞는 리스트 가져오기
        [Constant.GET_ADDRESS_LIST]: (store, payload) => {
            const config = {
                headers: { "jwt-auth-token": window.sessionStorage.getItem("jwt-auth-token") }
            }
            http.get('/api/userinfo/getAddressList/' + payload.pid, config)
                .then(response => {
                    store.commit(Constant.GET_ADDRESS_LIST, { users: response.data })
                })
                // .catch(exp => alert('getBoardList처리에 실패하였습니다!!' + exp));
        },


    },

    mutations: {
        [Constant.GET_ADDRESS_LIST]: (state, payload) => {
            // console.log('mutation' + payload.boards);
            state.users = payload.users;
        }
    },

    modules: {}
};

export default mapstore;
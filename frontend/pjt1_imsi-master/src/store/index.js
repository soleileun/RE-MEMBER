import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      id: '',
      nick: '',
    },
    followings: [
      {
        id: 'abc',
        nick: '김팀원',
        lastdate: '2000-01-01',
        state: 1
      },
      {
        id: 'bbb',
        nick: '유명인',
        lastdate: '2000-01-01',
        state: 0
      }
    ],
    messageList: [
      {
        from: 'bbb',
        nick: '유명인',
        lastdate: '2000-01-01',
        state: 0,
        message: [
          {
            content: '안녕',
            time: '',
            is_read: ''
          },
          {
            content: '11',
            time: '',
            is_read: ''
          }
        ]
      }
    ],
    news: [

    ],
    logined: true,
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})

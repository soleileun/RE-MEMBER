import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from '@/store/index.js'

import BootstartpVue from 'bootstrap-vue'
Vue.config.productionTip = false
Vue.use(BootstartpVue)

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')

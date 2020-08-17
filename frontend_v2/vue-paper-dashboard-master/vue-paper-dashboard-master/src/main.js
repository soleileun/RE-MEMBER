/*!

 =========================================================
 * Vue Paper Dashboard - v2.0.0
 =========================================================

 * Product Page: http://www.creative-tim.com/product/paper-dashboard
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from "vue";
import VueDraggable from 'vue-draggable'
import App from "./App";
import router from "./router/index";
import store from '@/store/index.js'
import BootstrapVue from 'bootstrap-vue'
import GAuth from 'vue-google-oauth2'

import PaperDashboard from "./plugins/paperDashboard";
import "vue-notifyjs/themes/default.css";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(PaperDashboard);
//부트스트랩-뷰
Vue.use(BootstrapVue);

// dragula
Vue.use(VueDraggable);
Vue.use(GAuth, {
  clientId: '1037334804608-n8oulm1omlcln4r870bbckk31n09nifi.apps.googleusercontent.com',
  scope: 'profile email https://www.googleapis.com/auth/plus.login'
})





/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

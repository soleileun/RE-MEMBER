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

import PaperDashboard from "./plugins/paperDashboard";
import "vue-notifyjs/themes/default.css";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(PaperDashboard);
//부트스트랩-뷰
Vue.use(BootstrapVue);

// dragula
Vue.use(VueDraggable);

/* eslint-disable no-new */
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");
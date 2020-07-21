import Vue from 'vue'
import VueRouter from 'vue-router'
import mainpage from './link/mainpage.vue'
import freeboard from './link/freeboard.vue'
//유동균 추가
import mainboard from '../components/board/mainboard.vue'

import writefree from '../components/freeboard/writefree.vue'
import detailfree from '../components/freeboard/detailfree.vue'

import notice from './link/notice.vue'
//유동균 추가
import writenotice from '../components/notice/writenotice.vue'
import detailnotice from '../components/notice/detailnotice.vue'

import project from './link/project.vue'
//유동균 추가
import project1 from '../components/project/project1.vue'

import qaboard from './link/qaboard.vue'
//유동균 추가
import writeqa from '../components/qaboard/writeqa.vue'
import detailqa from '../components/qaboard/detailqa.vue'

import recruit from './link/recruit.vue'
//유동균 추가
import registmember from '../components/recruit/registmember.vue'
import registteam from '../components/recruit/registteam.vue'

import service from './link/service.vue'
import signup from './link/signup.vue'
import user from './link/user.vue'
import usermessenger from '../components/user/usermessenger.vue'
import findIDPW from "../components/user/findIDPW.vue";
import leave from "../components/user/leave.vue";
import editinfo from "../components/user/editinfo.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'mainpage',
    component: mainpage
  },
  {
    path: '/freeboard',
    name: 'freeboard',
    component: freeboard
  },
  //유동균 추가
  {
    path: '/mainboard',
    name: 'mainboard',
    component: mainboard
  },
  {
    path: '/freeboard/writefree',
    name: 'writefree',
    component: writefree
  },
  {
    path: '/freeboard/detailfree',
    name: 'detailfree',
    component: detailfree
  },

  {
    path: '/notice',
    name: 'notice',
    component: notice
  },
  //유동균 추가
  {
    path: '/notice/writenotice',
    name: 'writenotice',
    component: writenotice
  },
  {
    path: '/notice/detailnotice',
    name: 'detailnotice',
    component: detailnotice
  },

  {
    path: '/project',
    name: 'project',
    component: project
  },
  //유동균 추가
  {
    path: '/project/project1',
    name: 'project1',
    component: project1
  },

  {
    path: '/qaboard',
    name: 'qaboard',
    component: qaboard
  },
  //유동균 추가
  {
    path: '/qaboard/writeqa',
    name: 'writeqa',
    component: writeqa
  },
  {
    path: '/qaboard/detailqa',
    name: 'detailqa',
    component: detailqa
  },

  {
    path: '/recruit',
    name: 'recruit',
    component: recruit
  },
  //유동균 추가
  {
    path: '/recruit/registmember',
    name: 'registmember',
    component: registmember
  },
  {
    path: '/recruit/registteam',
    name: 'registteam',
    component: registteam
  },


  {
    path: '/service',
    name: 'service',
    component: service
  },
  {
    path: '/signup',
    name: 'signup',
    component: signup
  },
  {
    path: '/user',
    name: 'user',
    component: user
  },
  {
    path: '/user/messenger',
    name: 'usermessenger',
    component: usermessenger
  },
  {
    path: '/user/findid',
    name: 'findIDPW',
    component: findIDPW
  },
  {
    path: '/user/editinfo',
    name: 'editinfo',
    component: editinfo
  },
  {
    path: '/user/leave',
    name: 'leave',
    component: leave
  }
]

// eslint-disable-next-line no-new
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

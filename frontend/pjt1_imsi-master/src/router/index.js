import Vue from 'vue'
import VueRouter from 'vue-router'
import mainpage from './link/mainpage.vue'

import freeboard from './link/freeboard.vue'
import writefree from '../components/freeboard/writefree.vue'
import detailfree from '../components/freeboard/detailfree.vue'
import modifyfree from '../components/freeboard/modifyfree.vue'

import mainboard from '../components/board/mainboard.vue'

import notice from './link/notice.vue'
import writenotice from '../components/notice/writenotice.vue'
import detailnotice from '../components/notice/detailnotice.vue'

import project from './link/project.vue'
import project1 from '../components/project/project1.vue'

import qaboard from './link/qaboard.vue'
import writeqa from '../components/qaboard/writeqa.vue'
import detailqa from '../components/qaboard/detailqa.vue'

import recruit from './link/recruit.vue'
import registmember from '../components/recruit/registmember.vue'
import registteam from '../components/recruit/registteam.vue'

import service from './link/service.vue'
import signup from './link/signup.vue'

import user from './link/user.vue'
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
        component: freeboard,
    },
    {
        path: '/freeboard/writefree',
        name: 'writefree',
        component: writefree
    },
    {
        path: '/freeboard/detailfree/:bno',
        name: 'detailfree',
        component: detailfree
    },
    {
        path: '/freeboard/modifyfree/:bno',
        name: 'modifyfree',
        component: modifyfree
    },
    {
        path: '/mainboard',
        name: 'mainboard',
        component: mainboard
    },
    {
        path: '/notice',
        name: 'notice',
        component: notice,
    },
    
    {
        path: '/writenotice',
        name: 'writenotice',
        component: writenotice
    },
    {
        path: '/writenotice/detailnotice',
        name: 'detailnotice',
        component: detailnotice
    },
    {
        path: '/project',
        name: 'project',
        component: project,
    },
    {
        path: '/project/project1',
        name: 'project1',
        component: project1
    },
    {
        path: '/qaboard',
        name: 'qaboard',
        component: qaboard,
    },
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
        component: recruit,
    },
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
        name:'user',
        component: user,
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
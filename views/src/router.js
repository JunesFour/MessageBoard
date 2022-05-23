import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    // test
    {
        path: '/test',
        component: () => import('./components/test/test.vue')
    },

    // message
    {
        path: '/pageHeader',
        component: () => import('./components/public/pageHeader.vue')
    },
    {
        path: '/messageList',
        component: () => import('./components/public/messageList.vue')
    },
    {
        path: '/index',
        component: () => import('./components/public/index.vue'),
    },
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
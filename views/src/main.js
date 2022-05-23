import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'


Vue.config.productionTip = false;

// 全局设置 axios 发送请求带上cookie
// axios.defaults.withCredentials = true;
const instance = axios.create({
    // withCredentials: true,
    baseURL: "/api",
})
// instance.withCredentials = true;
Vue.prototype.$axios = instance;

Vue.use(ElementUI);
Vue.use(router);

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')

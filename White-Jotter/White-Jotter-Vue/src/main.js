import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//增加钩子函数 判断拦截
import store from './store'

// 设置反向代理，前端请求默认发送到 http://localhost:9090/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:9090/api'

//前端开启cookie
axios.defaults.withCredentials = true

// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

//使用router.beforeEach 和 元数据 过滤每一个路由
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.user) {
        axios.get('/authentication').then(resp => {
          if (resp.data) {
            next()
          } else {
            next({
              path: 'login',
              query: {redirect: to.fullPath}
            })
          }
        })
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)

// router.beforeEach((to, from, next) => {
//   if (to.meta.requireAuth) {
//     if (store.state.user.username) {
//       next()
//     } else {
//       next({
//         path: 'login',
//         query: {redirect: to.fullPath}
//       })
//     }
//   } else {
//     next()
//   }
// })

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: {App},
  template: '<App/>'
})


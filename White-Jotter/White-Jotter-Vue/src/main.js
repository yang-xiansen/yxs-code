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
    if (store.state.user && to.path.startsWith('/admin')) {
      initAdminMenu(router, store)
    }
    if (store.state.user && to.path.startsWith('/login')) {
      next({
        name: 'Dashboard'
      })
    }
    // 如果前端没有登录信息则直接拦截，如果有则判断后端是否正常登录（防止构造参数绕过）

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

//根据用户初始化菜单
const initAdminMenu = (router, store) => {
  // 防止重复触发加载菜单操作
  if (store.state.adminMenus.length > 0) {
    return
  }
  axios.get('/menu').then(resp => {
    if (resp && resp.status === 200) {
      var fmtRoutes = formatRoutes(resp.data.result)
      router.addRoutes(fmtRoutes)
      store.commit('initAdminMenu', fmtRoutes)
    }
  })
}

//展示菜单
const formatRoutes = (routes) => {
  let fmtRoutes = []
  routes.forEach(route => {
    if (route.children) {
      route.children = formatRoutes(route.children)
    }

    let fmtRoute = {
      path: route.path,
      component: resolve => {
        require(['./components/admin/' + route.component + '.vue'], resolve)
      },
      name: route.name,
      nameZh: route.nameZh,
      iconCls: route.iconCls,
      meta: {
        requireAuth: true
      },
      children: route.children
    }
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes
}


/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: {App},
  template: '<App/>'
})


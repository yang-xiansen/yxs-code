import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
import appIndex from '@/components/home/appIndex'
import login from '@/components/login'
import home from '@/components/home'
import libraryIndex from '@/components/library/libraryIndex'

Vue.use(Router)

export default new Router({
  //路由启用history模式 不使用hash（#）
  mode: 'history',
  routes: [
    // 下面都是固定的写法
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/',
      name: 'index',
      redirect: '/index',
      component: appIndex,
      meta: {
        requireAuth: true
      }
    },

    {
      path: '/home',
      name: 'home',
      component: home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'appIndex',
          component: appIndex,
          //增加元数据 判断是非需要拦截
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'libraryIndex',
          component: libraryIndex,
          //增加元数据 判断是非需要拦截
          meta: {
            requireAuth: true
          }
        },
      ]
    },
  ]
})


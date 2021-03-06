import Vue from 'vue'
import Vuex from 'vuex'

//使用vuex
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    },
    adminMenus: []
  },
  mutations: {
    initAdminMenu (state, menus) {
      state.adminMenus = menus
    },
    //登陆获取用户信息
    login(state, user) {
      state.user = user
      //todo 保存到本地 一直存在 清除缓存也没用 待改进
      window.localStorage.setItem('user', JSON.stringify(user))
    },
    //退出
    logout (state) {
      state.user = []
      window.localStorage.removeItem('user')
      state.adminMenus = []
    }

  }
})

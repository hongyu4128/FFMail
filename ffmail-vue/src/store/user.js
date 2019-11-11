import {
  login,
  register
} from '../api/common/user'


const state = {
  user: null,
  isLogin: false
}

const actions = {
  async login ({commit}, {userName, password}) {
    // 登录方法
    commit('', true)
    return new Promise((resolve, reject) => {
      login({
        userName,
        password
      }).then(res => {
        const ret = res.data
        if (ret.succeed) {
          commit('SET_LOGIN_TOKEN', ret.data)
          resolve(ret)
        } else {
          reject(new Error(ret.data))
        }
      }).catch(err => {
        reject(err)
      })
    })
  },
  async register ({commit}, {userName, telephone, password}) {
    // 注册方法
    commit('', true)
    return new Promise((resolve, reject) => {
      register({
        userName,
        telephone,
        password
      }).then(res => {
        const ret = res.data
        if (ret.succeed) {
          commit('SET_LOGIN_TOKEN', ret.data)
          resolve(ret)
        } else {
          reject(new Error(ret.data))
        }
      }).catch(err => {
        reject(err)
      })
    })
  },
  async getLoginUser ({commit}) {
    // 模拟请求用户信息
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        var user = sessionStorage.getItem('user')
        // console.log(JSON.parse(user))
        commit('SET_LOGIN_USER', JSON.parse(user))
        resolve({bool: true, user})
      }, 1000)
    })
  },
  async logout ({commit}) {
    // 模拟退出
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        sessionStorage.removeItem('user')
        sessionStorage.removeItem('token')
        commit('SET_LOGIN_USER', null)
        resolve({bool: true})
      }, 2000)
    })
  }
}

const mutations = {
  SET_DOING_LOGIN (state, isLogin) {
    state.isLogin = isLogin
  },
  SET_LOGIN_USER (state, user) {
    state.user = user
  },
  SET_LOGIN_TOKEN (state, token) {
    if (token) {
      sessionStorage.setItem('token', token)
    } else {
      sessionStorage.removeItem('token')
    }
  }
}

export default {
  state,
  actions,
  mutations
}

import axios from '../../libs/api.request'

export const login = ({userName, password}) => {
  let user = {
    userName: userName,
    password: password
  }
  return axios.request({
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    url: 'basic/user/login',
    params: user
  })
}

export const register = ({userName, telephone, password}) => {
  let user = {
    userName: userName,
    telephone: telephone,
    password: password
  }
  return axios.request({
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    data: user,
    url: 'basic/user/register'
  })
}

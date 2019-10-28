import axios from '../../libs/api.request'

export const login = ({userName, password}) => {
  let user = {
    userName: userName,
    password: password
  }
  return axios.request({
    method: 'get',
    headers: {
      'Content-Type': 'application/json'
    },
    url: 'basic/user/login',
    params: user
  })
}

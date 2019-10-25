import axios from '../../libs/api.request'

export const login = ({userName, password}) => {
  let params = {
    userName: userName,
    password: password
  }
  axios.request({
    method: 'get',
    url: '/basic/login',
    data: params
  })
}

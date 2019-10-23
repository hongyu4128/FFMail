import axios from '../../libs/api.request'

export const login = ({userName, password}) => {
  let params = {
    userName: userName,
    password: password
  }
  axios.request({
    method: 'post',
    url: '/common/login',
    data: params
  })
}

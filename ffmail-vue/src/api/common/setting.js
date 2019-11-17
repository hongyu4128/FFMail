import axios from '../../libs/api.request'

export const saveUserEmail = ({userId, recvServiceType, emailAddress, emailPwd, recvService}) => {
  let userEmail = {
    userId: userId,
    recvServiceType: recvServiceType,
    emailAddress: emailAddress,
    emailPwd: emailPwd,
    recvService: recvService
  }
  return axios.request({
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    url: 'setting/setting/saveUserEmail',
    data: userEmail
  })
}

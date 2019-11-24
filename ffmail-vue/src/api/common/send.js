import axios from '../../libs/api.request'

export const sendEmail = ({emailFrom, emailPwd, emailRecipient, emailSubject, sendDate, emailContent}) => {
  let emailInfo = {
    emailFrom: emailFrom,
    emailPwd: emailPwd,
    emailRecipient: emailRecipient,
    emailSubject: emailSubject,
    sendDate: sendDate,
    emailContent: emailContent
  }
  return axios.request({
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    url: 'send/send/sendEmailInfo',
    data: emailInfo
  })
}

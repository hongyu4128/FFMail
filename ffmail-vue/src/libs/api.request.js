// const HttpRequest = require('@/libs/axios')
import HttpRequest from '@/libs/axios'
const baseUrl = process.env.APP_BASE_URL

const axios = new HttpRequest(baseUrl)
export default axios

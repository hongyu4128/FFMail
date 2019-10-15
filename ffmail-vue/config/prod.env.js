'use strict'
var version = require('./version')
module.exports = {
  NODE_ENV: '"production"',
  APP_BASE_URL: 'http://localhost:3001/remoteServer',
  APP_INFO: JSON.stringify(version)
}

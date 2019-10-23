'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  APP_BASE_URL: '"http://localhost:3001/remoteServer"'
})

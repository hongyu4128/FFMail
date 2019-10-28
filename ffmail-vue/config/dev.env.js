'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  APP_BASE_URL: '"http://localhost:81/remoteServer"'
  //APP_BASE_URL: '"http://localhost:9089/"'
})

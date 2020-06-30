import request from './utils/request.js'

//app.js
App({
  globalData: {
    userInfo: null
  },
  myRequest(){
    return new request();
  }
})
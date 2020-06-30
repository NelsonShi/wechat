//index.js
//获取应用实例
const app = getApp()
const http = app.myRequest()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    deliverInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function () {
    var that = this
    wx.authorize({
      scope: 'scope.userInfo',
      success() {
        //已获取授权，则检查session 
        wx.checkSession({
          success(res) {
            //上传服务，更新openId 对应的token 时效 并且校验签名
            wx.getUserInfo({
              success: (authorizedUser) => {
                http.postRequest('/user/updateSession', {
                  'rowData': authorizedUser.rawData,
                  'signature': authorizedUser.signature,
                  'encryptedData': authorizedUser.encryptedData,
                  'iv': authorizedUser.iv
                }).then((res) => {
                  if (res.data.resultStatus === 1) {
                    var token = res.data.data.token;
                    console.log("获取到Token", token)
                    wx.setStorage({
                      key: "token",
                      data: token
                    })
                    app.globalData.userInfo = authorizedUser.userInfo
                    that.setData({
                      userInfo: authorizedUser.userInfo,
                      hasUserInfo: true,
                      deliverInfo: res.data.data.deliveryInfo
                    })
                  }
                }).catch((error) => {
                  console.log('getUserInfo sucesss exception', error)
                })
              },
            })
          },
          fail(res) {
            wx.getUserInfo({
              success(userInfo) {
                wechatLogin(userInfo)
              }
            })
          }
        })
      },
      fail(res) {
        console.log('小程序无权限获取用户信息', res)
      }
    })
  },
  getUserInfo: function (e) {
    if (e.detail.userInfo) {
      this.wechatLogin(e.detail)
    } else {
      console.log('用户拒绝授权')
    }
  },
  wechatLogin: function (detial) {
    wx.login({
      success: (res) => {
        http.postRequest('/user/login', {
          'code': res.code,
          'rowData': detial.rawData,
        }).then((res) => {
          if (res.data.resultStatus === 1) {
            var token = res.data.data.token;
            wx.setStorage({
              key: "token",
              data: token
            })
            app.globalData.userInfo = detial.userInfo
            this.setData({
              userInfo: detial.userInfo,
              hasUserInfo: true,
              deliverInfo: res.data.data.deliveryInfo
            })
          }
        }).catch((res) => {
          console.log("post 请求报错", res)
        })
      }
    })
  },

  locateAddress: function () {
    var that = this;
    wx.getLocation({
      type: 'gcj02', //返回可以用于wx.openLocation的经纬度
      success(res) {
        const latitude = res.latitude
        const longitude = res.longitude
        wx.chooseLocation({
          latitude: latitude,
          longitude: longitude,
          success(chooseRes) {
            console.log(chooseRes)
            var del = that.data.deliverInfo;
            del.address = chooseRes.address + chooseRes.name;
            that.setData({
              deliverInfo: del
            })
          },
          fail(chooseFail) {
            console.log(chooseFail)
          }
        })
      },
      fail(fail) {
        wx.showToast({
          title: 'getLocation fail',
          icon: 'none',
          duration: 10000
        })
      }
    })
  },
  submitDeliveryInfo: function (e) {
    var newValue=e.detail.value;
    var that = this
    wx.getStorage({
      key: 'token',
      success(res) {
        if (res.data) {
          var deliveryInfo = {'openId':that.data.deliverInfo.openId,'userName':newValue.userName,'phoneNumber':newValue.phoneNumber,'address':newValue.address,'nickName':that.data.deliverInfo.nickName};
          http.postRequest('/user/updateDelivery', {
            'token': res.data,
            'data': deliveryInfo,
          }).then((response)=>{
            if(response.data.resultStatus===1){
               var newInfo=response.data.data;
               that.setData({
                deliverInfo: newInfo
              })
            }else{
              //服务器更新失败，请提示用户
            }
          }).catch((error) => {});
        } else {
            wx.showToast({
              title: '请登录',
            })
        }
      }
    })
  },
  navigateToOrder:function(e){
    console.log(e.currentTarget.id)
    var selectedId=e.currentTarget.id
    wx.navigateTo({
      url: '/pages/order/index',
      success: (res)=> {
        console.log(res.eventChannel)
        // 通过eventChannel向被打开页面传送数据
        res.eventChannel.emit('acceptDataFromOpenerPage', { id: selectedId })
      }
    })
  }
})
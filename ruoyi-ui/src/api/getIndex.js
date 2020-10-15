import request from '@/utils/request'

// 获取用户详细信息
export function getOnlineUser() {
    return request({
      url: '/system/user/get_online_user',
      method: 'get'
    })
  }

  export function getRank() {
    return request({
      url: '/bajiaostar/ranking/integral_list',
      method: 'get'
    })
  }

  
  export function getTask() {
    return request({
      url: '/bajiaostar/order/schedule_list',
      method: 'get'
    })
  }

  export function bdToken() {
    return request({
      url: '/system/user/get_baidu_token',
      method: 'get'
    })
  }
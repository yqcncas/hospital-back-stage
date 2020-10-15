import request from '@/utils/request'

// 查询通知列表
export function listNotice(query) {
  return request({
    url: '/bajiaostar/notice/list',
    method: 'get',
    params: query
  })
}
// 推送
export function pushNotice(id) {
  return request({
    url: '/bajiaostar/notice/push',
    method: 'get',
    params: id
  })
}

// 查询通知详细
export function getNotice(id) {
  return request({
    url: '/bajiaostar/notice/' + id,
    method: 'get'
  })
}

// 新增通知
export function addNotice(data) {
  return request({
    url: '/bajiaostar/notice',
    method: 'post',
    data: data
  })
}

// 修改通知
export function updateNotice(data) {
  return request({
    url: '/bajiaostar/notice',
    method: 'put',
    data: data
  })
}

// 删除通知
export function delNotice(id) {
  return request({
    url: '/bajiaostar/notice/' + id,
    method: 'delete'
  })
}

// 导出通知
export function exportNotice(query) {
  return request({
    url: '/bajiaostar/notice/export',
    method: 'get',
    params: query
  })
}
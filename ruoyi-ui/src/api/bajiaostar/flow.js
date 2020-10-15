import request from '@/utils/request'

// 查询用户流水列表
export function listFlow(query) {
  return request({
    url: '/bajiaostar/flow/list',
    method: 'get',
    params: query
  })
}

// 查询用户流水详细
export function getFlow(id) {
  return request({
    url: '/bajiaostar/flow/' + id,
    method: 'get'
  })
}

// 新增用户流水
export function addFlow(data) {
  return request({
    url: '/bajiaostar/flow',
    method: 'post',
    data: data
  })
}

// 修改用户流水
export function updateFlow(data) {
  return request({
    url: '/bajiaostar/flow',
    method: 'put',
    data: data
  })
}

// 删除用户流水
export function delFlow(id) {
  return request({
    url: '/bajiaostar/flow/' + id,
    method: 'delete'
  })
}

// 导出用户流水
export function exportFlow(query) {
  return request({
    url: '/bajiaostar/flow/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询用户排名列表
export function listRanking(query) {
  return request({
    url: '/bajiaostar/ranking/order_list',
    method: 'get',
    params: query
  })
}

export function jflistRanking(query) {
  return request({
    url: '/bajiaostar/ranking/integral_list',
    method: 'get',
    params: query
  })
}

// 查询用户排名详细
export function getRanking(id) {
  return request({
    url: '/bajiaostar/ranking/' + id,
    method: 'get'
  })
}

// 新增用户排名
export function addRanking(data) {
  return request({
    url: '/bajiaostar/ranking',
    method: 'post',
    data: data
  })
}

// 修改用户排名
export function updateRanking(data) {
  return request({
    url: '/bajiaostar/ranking',
    method: 'put',
    data: data
  })
}

// 删除用户排名
export function delRanking(id) {
  return request({
    url: '/bajiaostar/ranking/' + id,
    method: 'delete'
  })
}

// 导出用户排名
export function exportRanking(query) {
  return request({
    url: '/bajiaostar/ranking/export',
    method: 'get',
    params: query
  })
}
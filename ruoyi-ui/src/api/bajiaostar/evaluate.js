import request from '@/utils/request'

// 查询任务评价列表
export function listEvaluate(query) {
  return request({
    url: '/bajiaostar/evaluate/list',
    method: 'get',
    params: query
  })
}

// 查询任务评价详细
export function getEvaluate(id) {
  return request({
    url: '/bajiaostar/evaluate/' + id,
    method: 'get'
  })
}

// 新增任务评价
export function addEvaluate(data) {
  return request({
    url: '/bajiaostar/evaluate',
    method: 'post',
    data: data
  })
}

// 修改任务评价
export function updateEvaluate(data) {
  return request({
    url: '/bajiaostar/evaluate',
    method: 'put',
    data: data
  })
}

// 删除任务评价
export function delEvaluate(id) {
  return request({
    url: '/bajiaostar/evaluate/' + id,
    method: 'delete'
  })
}

// 导出任务评价
export function exportEvaluate(query) {
  return request({
    url: '/bajiaostar/evaluate/export',
    method: 'get',
    params: query
  })
}
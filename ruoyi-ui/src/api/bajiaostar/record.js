import request from '@/utils/request'

// 查询推送用户通知记录列表
export function listRecord(query) {
  return request({
    url: '/bajiaostar/record/list',
    method: 'get',
    params: query
  })
}

// 查询推送用户通知记录详细
export function getRecord(id) {
  return request({
    url: '/bajiaostar/record/' + id,
    method: 'get'
  })
}

// 新增推送用户通知记录
export function addRecord(data) {
  return request({
    url: '/bajiaostar/record',
    method: 'post',
    data: data
  })
}

// 修改推送用户通知记录
export function updateRecord(data) {
  return request({
    url: '/bajiaostar/record',
    method: 'put',
    data: data
  })
}

// 删除推送用户通知记录
export function delRecord(id) {
  return request({
    url: '/bajiaostar/record/' + id,
    method: 'delete'
  })
}

// 导出推送用户通知记录
export function exportRecord(query) {
  return request({
    url: '/bajiaostar/record/export',
    method: 'get',
    params: query
  })
}
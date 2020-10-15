import request from '@/utils/request'

// 查询任务类型列表
export function listType(query) {
  return request({
    url: '/bajiaostar/type/list',
    method: 'get',
    params: query
  })
}export function enumlistType(query) {
  return request({
    url: '/bajiaostar/type/list_enum',
    method: 'get',
    params: query
  })
}
// 查询父节点id
export function getfatherId() {
  return request({
    url: '/bajiaostar/type/pid_list',
    method: 'get'
  })
}

// 查询任务类型详细
export function getType(id) {
  return request({
    url: '/bajiaostar/type/' + id,
    method: 'get'
  })
}

// 新增任务类型
export function addType(data) {
  return request({
    url: '/bajiaostar/type',
    method: 'post',
    data: data
  })
}

// 修改任务类型
export function updateType(data) {
  return request({
    url: '/bajiaostar/type',
    method: 'put',
    data: data
  })
}

// 删除任务类型
export function delType(id) {
  return request({
    url: '/bajiaostar/type/' + id,
    method: 'delete'
  })
}

// 导出任务类型
export function exportType(query) {
  return request({
    url: '/bajiaostar/type/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询任务管理列表
export function listTask(query) {
  return request({
    url: '/bajiaostar/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务管理详细
export function getTask(id) {
  return request({
    url: '/bajiaostar/task/' + id,
    method: 'get'
  })
}

// 新增任务管理
export function addTask(data) {
  return request({
    url: '/bajiaostar/task',
    method: 'post',
    data: data
  })
}

// 新增分配
export function mergeAdd(query) {
  return request({
    url: '/bajiaostar/order/batch_merge_add',
    method: 'post',
    params: query
  })
}

export function userList(query) {
  return request({
    url: '/system/user/get_list_by_type',
    method: 'get',
    params: query
  })
}

export function recreate(query) {
  return request({
    url: '/bajiaostar/order/recreate_order',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
     },
    method: 'put',
    params: query
  })
}

export function addUserList(query) {
  return request({
    url: '/bajiaostar/order/batch_add',
    method: 'post',
    params: query
  })
}

// 修改任务管理
export function updateTask(data) {
  return request({
    url: '/bajiaostar/task',
    method: 'put',
    data: data
  })
}

// 删除任务管理
export function delTask(id) {
  return request({
    url: '/bajiaostar/task/' + id,
    method: 'delete'
  })
}

// 导出任务管理
export function exportTask(query) {
  return request({
    url: '/bajiaostar/task/export',
    method: 'get',
    params: query
  })
}
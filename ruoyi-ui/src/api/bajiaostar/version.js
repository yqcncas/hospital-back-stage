import request from '@/utils/request'

// 查询app版本管理列表
export function listVersion(query) {
  return request({
    url: '/bajiaostar/version/list',
    method: 'get',
    params: query
  })
}

// 查询app版本管理详细
export function getVersion(id) {
  return request({
    url: '/bajiaostar/version/' + id,
    method: 'get'
  })
}

export function getQnToken() {
  return request({
    url: '/system/user/get_qiniu_token',
    method: 'get'
  })
}


// 新增app版本管理
export function addVersion(data) {
  return request({
    url: '/bajiaostar/version',
    method: 'post',
    data: data
  })
}

// 修改app版本管理
export function updateVersion(data) {
  return request({
    url: '/bajiaostar/version',
    method: 'put',
    data: data
  })
}

// 删除app版本管理
export function delVersion(id) {
  return request({
    url: '/bajiaostar/version/' + id,
    method: 'delete'
  })
}

// 导出app版本管理
export function exportVersion(query) {
  return request({
    url: '/bajiaostar/version/export',
    method: 'get',
    params: query
  })
}
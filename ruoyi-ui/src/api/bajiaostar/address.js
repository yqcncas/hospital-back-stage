import request from '@/utils/request'

// 查询任务地址列表
export function listAddress(query) {
  return request({
    url: '/bajiaostar/address/list_enum',
    method: 'get',
    params: query
  })
}

export function newlistAddress(query) {
  return request({
    url: '/bajiaostar/address/list',
    method: 'get',
    params: query
  })
}

export function getQrcode() {
  return request({
    url: '/bajiaostar/address/export_qrcode',
    method: 'get',
    responseType: "blob"
  })
}

export function getOnlyQrcode(id) {
  return request({
    url: '/bajiaostar/address/export_qrcode?id=' + id,
    method: 'get',
    responseType: "blob"
  })
}

// 查询任务地址详细
export function getAddress(id) {
  return request({
    url: '/bajiaostar/address/' + id,
    method: 'get'
  })
}

// 新增任务地址
export function addAddress(data) {
  return request({
    url: '/bajiaostar/address',
    method: 'post',
    data: data
  })
}

// 修改任务地址
export function updateAddress(data) {
  return request({
    url: '/bajiaostar/address',
    method: 'put',
    data: data
  })
}

// 删除任务地址
export function delAddress(id) {
  return request({
    url: '/bajiaostar/address/' + id,
    method: 'delete'
  })
}

// 导出任务地址
export function exportAddress(query) {
  return request({
    url: '/bajiaostar/address/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/bajiaostar/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(id) {
  return request({
    url: '/bajiaostar/order/' + id,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/bajiaostar/order',
    method: 'post',
    data: data
  })
}
// 评价
export function evaluate(data) {
  return request({
    url: '/bajiaostar/evaluate',
    method: 'post',
    data: data
  })
}
// 评价详情
export function evaluateDetail(id) {
  return request({
    url: '/bajiaostar/evaluate/orderId/' + id,
    method: 'get'
  })
}


// 修改订单
export function updateOrder(data) {
  return request({
    url: '/bajiaostar/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(id) {
  return request({
    url: '/bajiaostar/order/' + id,
    method: 'delete'
  })
}

// 导出订单
export function exportOrder(query) {
  return request({
    url: '/bajiaostar/order/export',
    method: 'get',
    params: query
  })
}
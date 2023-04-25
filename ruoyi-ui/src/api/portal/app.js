import request from '@/utils/request'

// 查询门户应用列表
export function listApp(query) {
  return request({
    url: '/portal/app/list',
    method: 'get',
    params: query
  })
}

// 查询门户应用详细
export function getApp(appId) {
  return request({
    url: '/portal/app/' + appId,
    method: 'get'
  })
}

// 新增门户应用
export function addApp(data) {
  return request({
    url: '/portal/app',
    method: 'post',
    data: data
  })
}

// 修改门户应用
export function updateApp(data) {
  return request({
    url: '/portal/app',
    method: 'put',
    data: data
  })
}

// 删除门户应用
export function delApp(appId) {
  return request({
    url: '/portal/app/' + appId,
    method: 'delete'
  })
}

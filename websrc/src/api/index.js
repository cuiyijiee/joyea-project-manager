import axios from 'axios'

// 创建axios实例
const service = axios.create({
  //baseURL: process.env.BASE_API, // api 的 base_url
  //baseURL: process.env.NODE_ENV === 'production' ? '' : '',
  timeout: 60 * 60 * 1000 // 请求超时时间
});

service.interceptors.request.use(config => {
  config.headers['X-TOKEN'] = "test";
  return config;
}, error => {
  return Promise.reject(error);
});


export function listProject(searchKey, pageNum, pageSize) {
  return service.post("api/project/list", {
    pageSize: pageSize, pageNum: pageNum, projectName: searchKey
  }).then(resp => resp.data);
}

export function findProjectSchedule(fid) {
  return service.post("api/project/findSchedule", {
    fid: fid
  }).then(resp => resp.data);
}

export function listProduct(xmId, searchKey, pageNum, pageSize) {
  return service.post("api/product/list", {
    xmId: xmId, productName: searchKey, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function findProductSchedule(orderId) {
  return service.post("api/product/findSchedule", {
    orderId: orderId
  }).then(resp => resp.data);
}

export function listProcess(orderId, searchKey, pageNum, pageSize) {
  return service.post("api/process/list", {
    orderId: orderId, processName: searchKey, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function listCheckItem(taskId, searchKey, pageNum, pageSize) {
  return service.post("api/checkItem/list", {
    taskId: taskId, checkStandard: searchKey, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function listCheckItemResult(cfCheckEntryId, pageNum, pageSize) {
  return service.post("api/checkItemResult/list", {
    cfCheckEntryId: cfCheckEntryId, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

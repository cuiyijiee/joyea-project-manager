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
    pageSize: pageSize,
    pageNum: pageNum,
    projectName: searchKey
  }).then(resp => resp.data);
}

export function findSchedule(fnumber) {
  return service.post("api/project/findSchedule", {
    fnumber: fnumber
  }).then(resp => resp.data);
}

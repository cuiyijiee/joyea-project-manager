import axios from 'axios'
import store from "../store";
import constants from "../constants";
import router from "../router";

// 创建axios实例
const service = axios.create({
  //baseURL: process.env.BASE_API, // api 的 base_url
  //baseURL: process.env.NODE_ENV === 'production' ? '' : '',
  timeout: 60 * 60 * 1000 // 请求超时时间
});

service.interceptors.request.use(config => {
  if (store.state.token) {
    config.headers['X-TOKEN'] = store.state.token;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

service.interceptors.response.use(config => {
  if (config.data) {
    switch (config.data.code) {
      case constants.USER_NOT_AUTH_CODE:
        try {
          this.$store.commit('delToken');
        } catch (e) {
          console.log("request exist error: " + e);
        }
        router.replace({
          path: '/login', query: {redirect: router.currentRoute.fullPath}//登录成功后跳入浏览的当前页面
        })
    }
  }
  return config;
}, error => {
  return Promise.reject(error);
})

export function getTicket() {
  return service.post("api/nextplus/ticket", {}).then(resp => resp.data);
}

export function getProfile(authCode) {
  return service.post("api/nextplus/profile", {
    authCode: authCode
  }).then(resp => resp.data);
}

export function getEcologyAuthUrl() {
  return service.post("api/ecology/authUrl", {}).then(resp => resp.data);
}

export function getEcologyProfile(ticket) {
  return service.post("api/ecology/profile?ticket=" + ticket, {}).then(resp => resp.data);
}

export function listProject(searchKey, pageNum, pageSize) {
  return service.post("api/project/list", {
    pageSize: pageSize, pageNum: pageNum, projectName: searchKey
  }).then(resp => resp.data);
}

export function listProjectCollect(searchKey, pageNum, pageSize) {
  return service.post("api/project/listCollect", {
    pageSize: pageSize, pageNum: pageNum, projectName: searchKey
  }).then(resp => resp.data);
}

export function addProjectCollect(fid) {
  return service.post("api/project/addCollect", {
    fid: fid
  }).then(resp => resp.data);
}

export function findProjectSchedule(fid) {
  return service.post("api/project/findSchedule", {
    fid: fid
  }).then(resp => resp.data);
}

export function listProduct(fid, searchKey, status, pageNum, pageSize) {
  return service.post("api/product/list", {
    fid: fid, productName: searchKey, status: status, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function listProductCount(fid) {
  return service.post("api/product/count", {
    fid: fid
  }).then(resp => resp.data);
}

export function findProductSchedule(orderId) {
  return service.post("api/product/findSchedule", {
    orderId: orderId
  }).then(resp => resp.data);
}

export function listProcess(orderId, searchKey, status, pageNum, pageSize) {
  return service.post("api/process/list", {
    orderId: orderId, processName: searchKey, status: status, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function listProcessCount(orderId) {
  return service.post("api/process/count", {
    orderId: orderId
  }).then(resp => resp.data);
}

export function listCheckItem(taskId, searchKey, cfCheckType, finished, pageNum, pageSize) {
  return service.post("api/checkItem/list", {
    taskId: taskId,
    checkStandard: searchKey,
    cfCheckType: cfCheckType,
    finished: finished,
    pageNum: pageNum,
    pageSize: pageSize
  }).then(resp => resp.data);
}

export function findCheckItem(fid) {
  return service.post("api/checkItem/find", {
    fid: fid
  }).then(resp => resp.data);
}

export function findCheckItemCount(taskId) {
  return service.post("api/checkItem/count", {
    taskId: taskId
  }).then(resp => resp.data);
}

export function listCheckItemResult(cfCheckEntryId, pageNum, pageSize) {
  return service.post("api/checkItemResult/list", {
    cfCheckEntryId: cfCheckEntryId, pageNum: pageNum, pageSize: pageSize
  }).then(resp => resp.data);
}

export function insertCheckItemResult(data) {
  return service.post("api/checkItemResult/insert", data).then(resp => resp.data);
}

export function previewFile(neid) {
  return service.get("apiv2/preview?neid=" + neid)
    .then(resp => {
      return resp.data;
    })
}

export function getLatestSearchHistory(searchType) {
  return service.post("api/searchHistory/latest", {
    searchType: searchType
  }).then(resp => resp.data);
}

/******************filez start******************/

export function getUploadFileRegion(filename) {
  return service.get("api/filez/upload/region/" + filename, {}).then(resp => resp.data);
}

/******************filez end******************/

/******************edoc2 start******************/
export function edoc2Upload(file) {
  let formData = new FormData();
  formData.append('file', file.file)

  return axios.post('api/edoc2/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
      'X-TOKEN': store.state.token
    },
    onUploadProgress: progressEvent => {
      let completed = (progressEvent.loaded / progressEvent.total * 100 | 0) + "%";
    }
  }).then(res => {
    return res.data;
  })
}
/******************edoc2 end******************/

import {getUploadFileRegion} from "./index";

import axios from "axios";

export function upload(file) {
  return getUploadFileRegion(file.file.name).then(regionResp => {
    console.log("region resp: " + JSON.stringify(regionResp.obj));

    let uploadUrl = regionResp.obj.region + "/v2/files/databox/Eas点检记录附件/" + file.file.name +
      "?X-LENOVO-SESS-ID=" + regionResp.obj.session + "&path_type=ent&bytes=" + file.size + "&filename=" + file.file.name + "$overwrite=true";

    let formData = new FormData();
    formData.append('file', file.file)

    return axios.post(uploadUrl, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      onUploadProgress: progressEvent => {
        let completed = (progressEvent.loaded / progressEvent.total * 100 | 0) + "%";
      }
    }).then(res => {
      return res.data;
    })
  })
}


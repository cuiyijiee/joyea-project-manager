import {previewFile} from "../api"
import {Dialog} from 'vant';
import {genImageListView} from "./ImageViewUtil";
import loadGif from "../assets/loading.gif"

import store from "../store";

export function genSrcOriginSrc(neid) {
  return "/apiv2/imagePreview?neid=" + neid + "&thumbtail=false"
}

let videoLoading = false;

export function handleGoToPreview(context, clickItem, itemList) {
  let fileName = clickItem.fileName
  let url = genSrcOriginSrc(clickItem.lenovoId)
  if (clickItem.mimeType.startsWith("video")) {
    videoLoading = true;
    const {promise, abort} = getVideoPreviewUrl(clickItem.lenovoId, 30)
    promise.then(resp => {
      if (videoLoading) {
        //callNextPlusPreview(fileName, resp);
        store.state.videoDialogTitle = fileName;
        store.state.videoDialogUrl = resp;
        store.state.videoDialogShow = true;
      }
      Dialog.close();
    });
    Dialog.alert({
      title: "转码中，成功后将自动播放，请耐心等待。。。",
      message: "<img src='" + loadGif + "' style='width: 50px;height: 50px'></img>",
      allowHtml: true,
      showConfirmButton: true,
      confirmButtonText: '取消'
    }).then(() => {
      abort();
    })
    //callNextPlusPreview(fileName, url)
  } else if (clickItem.mimeType.startsWith("doc") && !clickItem.mimeType.endsWith("pdf")) {
    if (clickItem.mimeType.endsWith("pdf")) {
    } else {
      window.open(url)
    }
  } else if (clickItem.mimeType.startsWith("image")) {
    genImageListView(context, itemList, clickItem);
  } else if (clickItem.mimeType.startsWith("word")) {
    window.location.href = 'esen://word?wordId=' + clickItem.lenovoId;
  }
}

export function getVideoPreviewUrl(neid, times) {
  console.log("start check preview:" + neid)
  let _res, _rej;
  const promise = new Promise(function (resolve, reject) {
    _res = resolve;
    _rej = reject;

    function attempt() {
      previewFile(neid).then(function (previewUrl) {
        console.log(previewUrl)
        if (previewUrl.code) {
          if (0 === times) {
            reject(err)
          } else {
            times--;
            setTimeout(attempt, 1000)
          }
        } else {
          resolve(previewUrl)
        }
      }).catch(function (err) {
        console.log("第" + times + "次尝试获取视频预览地址")
        if (0 === times) {
          reject(err)
        } else {
          times--;
          setTimeout(attempt, 1000)
        }
      })
    }

    attempt()
  });

  return {
    promise, abort: (opt = {}) => {
      times = 0;
      _rej({
        name: "abort", message: "the promise is aborted", aborted: true,
      })
    }
  }
}

export function getQueryParam(key) {
  if (!key) {
    return false;
  }

  var value = '';
  var paramStr = window.location.search ? window.location.search.substr(1) : '';

  if (paramStr) {
    paramStr.split('&').forEach(function (param) {
      var arr = param.split('=');
      if (arr[0] == key) {
        value = arr[1];
      }
    });
  }
  return value;
}

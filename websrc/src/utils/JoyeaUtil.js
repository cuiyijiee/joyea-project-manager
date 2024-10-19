import {Dialog} from 'vant';
import {genImageListView} from "./ImageViewUtil";
import loadGif from "../assets/loading.gif"

import store from "../store";


export function handleGoToPreview(context, title, previewUrl) {
  if(!previewUrl) {
    Dialog.alert({
      title: "附件正在处理中请稍后查看！", message: "", allowHtml: true, showConfirmButton: true, confirmButtonText: '知道了'
    }).then(() => {
    })
    return;
  }
  if(previewUrl) {
    previewUrl = previewUrl.replaceAll("GetConversionFile","GetOriginFile")
  }
  let fileType = getFileType(title);
  if (fileType === 'video') {
    store.state.videoDialogTitle = title;
    store.state.videoDialogUrl = previewUrl;
    store.state.videoDialogShow = true;
  } else if (fileType === 'doc') {
    window.open(previewUrl)
  } else if (fileType === 'image') {
    genImageListView(context, title, previewUrl);
  } else {
    Dialog.alert({
      title: "该文件类型暂不支持预览！",
      message: "<img src='" + loadGif + "' style='width: 50px;height: 50px'></img>",
      allowHtml: true,
      showConfirmButton: true,
      confirmButtonText: '知道了'
    }).then(() => {
    })
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


/* 根据后缀判断文件类型 */
export function getFileType(fileName) {
  let suffix = ''; // 后缀获取
  let result = ''; // 获取类型结果
  if (fileName) {
    const fileArr = fileName.split('.'); // 根据.分割数组
    suffix = fileArr[fileArr.length - 1]; // 取最后一个
  }
  if (!suffix) return false; // fileName无后缀返回false
  suffix = suffix.toLocaleLowerCase(); // 将后缀所有字母改为小写方便操作
  // 匹配图片
  const imgList = ['png', 'jpg', 'jpeg', 'bmp', 'gif']; // 图片格式
  result = imgList.find(item => item === suffix);
  if (result) return 'image';
  // 匹配文档
  const excelList = ['xls', 'xlsx', 'txt', 'doc', 'docx', 'pdf', 'ppt', 'pptx',];
  result = excelList.find(item => item === suffix);
  if (result) return 'doc';
  // 匹配视频
  const videoList = ['mp4', 'm2v', 'mkv', 'rmvb', 'wmv', 'avi', 'flv', 'mov', 'm4v'];
  result = videoList.find(item => item === suffix);
  if (result) return 'video';
  // 其他文件类型
  return 'other';
}

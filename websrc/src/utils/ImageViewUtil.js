import store from "../store";

export function genImageListView(context, title, previewUrl) {
  let list = [{
    title, previewUrl
  }];
  store.state.imagePreviewStartIndex = 0;
  store.state.imagePreviewImages = list;
  store.state.imagePreviewShow = true;
}

export function genAttachmentImageListView(context, itemList, clickItem) {
  let index = 0;
  let tmp = 0;
  let list = itemList.filter(attach => {
    return attach.attacheType && (attach.attacheType.indexOf('图像') > -1 || attach.attacheType.indexOf('png') > -1 || attach.attacheType.indexOf('jpg') > -1);
  }).map(item => {
    if (item.attachFid === clickItem.attachFid) {
      index = tmp;
    }
    tmp++;
    return {
      previewUrl: '/api/checkItemAttachment/download?attachId=' + window.btoa(item.attachFid),
      fileName: item.attachName
    };
  });
  if (list.length === 0) {
    context.$notify({
      message: "当前清单没有可以预览的图片！"
    });
  } else {
    store.state.imagePreviewShow = true;
    store.state.imagePreviewStartIndex = index ? index : 0;
    store.state.imagePreviewImages = list;
  }
}

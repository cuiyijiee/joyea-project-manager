import {genSrcOriginSrc} from "./JoyeaUtil";
import store from "../store";

export function genImageListView(context, itemList, clickItem) {
  let index = 0;
  let tmp = 0;
  let list = itemList.filter(item => {
    return item.mimeType && item.mimeType.startsWith('image')
  }).map(item => {
    if (item.lenovoId === clickItem.lenovoId) {
      index = tmp;
    }
    tmp++;
    return {
      url: genSrcOriginSrc(item.lenovoId), fileName: item.fileName
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

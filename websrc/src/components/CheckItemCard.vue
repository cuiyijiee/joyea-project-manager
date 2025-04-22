<template>
  <div class="check-item-card">
    <div class="container">
      <van-row>
        <van-col span="24"><span class="desc">序号：</span>{{ item.seq }}</van-col>
        <van-col span="24"><span class="desc">检验类型：</span>{{ item.typeName || '' }}</van-col>
        <van-col span="24"><span class="desc">
          <van-icon v-if="item.keyItem === 1" name="star" color="red" size="20"/>
          检验标准：</span>
          <!-- 使用 v-html 渲染替换后的文本 -->
          <span v-html="formatCheckStandard(item.checkStandard)"></span>
        </van-col>
      </van-row>
      <van-row>
        <van-col span="24"><span class="desc">检验方法：
        </span>
          <van-row>
            <van-col span="20">
              <div :class="isOpen ? 'new_detail' : 'default'">
                {{ item.checkMethod || '无' }}
              </div>
            </van-col>
            <van-col span="4">
              <van-button @click.stop="isOpen=!isOpen"
                          type="info" size="mini" plain>{{ word }}
                <!--            <i :class="isOpen?'arrow-up':'arrow-down'"></i>-->
              </van-button>
            </van-col>
          </van-row>
        </van-col>
      </van-row>
      <van-grid :border="false" :column-num="3">
        <van-grid-item v-for="attach in item.attachmentList" :key="attach.fid">
          <div v-if="attach.attacheType.indexOf('图像') > -1 || attach.attacheType.indexOf('png') > -1 || attach.attacheType.indexOf('jpg') > -1" width="100" height="100"
               @click.stop="handleClickAttachment(attach,item.attachmentList)">
            <van-image
              :src="'/api/checkItemAttachment/download?attachId=' + base64(attach.attachFid)"/>
          </div>
          <van-image v-else :src="defaultImg"/>
        </van-grid-item>
      </van-grid>
      <van-row>
        <van-col span="24" v-if="item.cfCheckResult !== null">
          <van-image v-if="item.cfCheckResult === '1'" :src="qualifiedImg" width="100" height="100"/>
          <van-image v-if="item.cfCheckResult === '2'" :src="unQualifiedImg" width="100" height="100"/>
          <van-image v-if="item.cfCheckResult === '3'" :src="unKnownImg" width="100" height="100"/>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
import {genAttachmentImageListView} from "../utils/ImageViewUtil";

export default {
  name: "CheckItemCard",
  props: {
    item: {
      type: Object,
    },
    index: {
      type: Number,
    }
  },
  data() {
    return {
      checkMethodVisible: false,
      defaultImg: require("@/assets/unknown.png"),
      qualifiedImg: require("@/assets/qualified.png"),
      unQualifiedImg: require("@/assets/unqualified.png"),
      unKnownImg: require("@/assets/unknown.png"),
      isOpen: true,
    }
  },
  computed: {
    // 内容
    word() {
      if (this.isOpen === false) {
        return '展开↓'
      } else if (this.isOpen === true) {
        return '收起↑'
      } else if (this.is_show === '') {
        return null
      }
    },
  },
  methods: {
    handleClickAttachment(attachment, attachmentList) {
      genAttachmentImageListView(this, attachmentList, attachment)
    },
    base64(origin){
     return  window.btoa(origin);
    },
    handleClickCheckMethod(item) {
      this.$dialog.alert({
        title: '检验方法',
        message: item.checkMethod || '',
      })
    },
    // 新增方法，用于替换换行符
    formatCheckStandard(text) {
      return text ? text.replace(/\n/g, '<br>') : '';
    }
  },
  mounted() {
  }
}
</script>

<style scoped>
div.check-item-card {
  margin: 10px;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

div.container {
  padding: 10px;
}

.van-progress {
  margin: 10px 0;
}

span.desc {
  color: dimgray;
}

.van-col {
  margin: 5px 0;
}

.new_detail {
  padding: 5px 0px 5px 0;
  font-size: 14px;
}

.default {
  padding: 5px 0px 5px 0;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  position: relative;
}

.popper-btn {
  position: absolute;
  right: 15px;
  bottom: 21px;
}

</style>

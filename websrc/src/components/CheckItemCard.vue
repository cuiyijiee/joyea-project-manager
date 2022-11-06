<template>
  <div class="check-item-card">
    <div class="container">
      <van-row>
        <van-col span="24"><span class="desc">序号：</span>{{ index + 1 }}</van-col>
        <van-col span="24"><span class="desc">检验类型：</span>{{ item.typeName || '' }}</van-col>
        <van-col span="24"><span class="desc">
          <van-icon v-if="item.keyItem === 1" name="star" color="red" size="20"/>
          检验标准：</span>{{ item.checkStandard || '' }}
        </van-col>
      </van-row>
      <van-row>
        <van-col span="24"><span class="desc">检验方法：</span>
          <span class="van-multi-ellipsis--l2" @click.stop="handleClickCheckMethod(item)">
            {{ item.checkMethod || '' }}
          </span>
        </van-col>
        <van-col span="24">
          <van-grid :border="false" :column-num="3">
            <van-grid-item v-for="attach in item.attachmentList" :key="attach.fid">
              <van-image v-if="attach.attacheType.indexOf('图像') > -1" width="100" height="100"
                         :src="'/api/checkItemAttachment/download?attachId=' + attach.fid"/>
              <van-image v-else :src="defaultImg"/>
            </van-grid-item>
          </van-grid>
        </van-col>
        <van-col span="24">
          <van-image v-if="item.qualified" :src="qualifiedImg" width="100" height="100"/>
          <van-image v-else :src="unQualifiedImg" width="100" height="100"/>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
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
    }
  },
  methods: {
    handleClickCheckMethod(item) {
      this.$dialog.alert({
        title: '检验方法',
        message: item.checkMethod || '',
      })
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
</style>

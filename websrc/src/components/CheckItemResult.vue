<template>
  <div>
    <van-nav-bar left-arrow left-text="返回" title="点检项操作"
                 @click-left="() => {this.$router.push({path:'/checkItem',query:{projectId:projectId,orderId:orderId,taskId:taskId}})}"/>
    <van-divider>共 {{ searchResultCount }} 条点检记录</van-divider>
    <van-button style="width: 100%" type="info" @click="onClickAddResult">新增点检记录</van-button>
    <van-list
      ref="checkItemResultList" v-model="searchLoading" :finished="!searchHasMore"
      finished-text="没有更多了" @load="onLoad">
      <div>
        <van-collapse v-model="activeNames">
          <van-collapse-item :name="item.fid" v-for="item in checkItemResultList" :key="item.fid"
                             @click="handleClickCheckItemResult(item.fid)">
            <template #title>
              <van-row>
                <van-col span="12">{{ item.cfCheckDate || '' }}</van-col>
                <van-col span="8">{{ item.cfCheckPersonName || '' }}</van-col>
                <van-col span="4">
                  <div
                    :class="{'green-color':((item.cfCheckResult || '2') === '1') , 'red-color':((item.cfCheckResult || '2') === '2')}">
                    {{ (item.cfCheckResult || '2') === '1' ? '合格' : '不合格' }}
                  </div>
                </van-col>
              </van-row>
            </template>
            <van-row>
              <van-col span="24"><span class="desc">检验人：</span>{{ item.cfCheckPersonName || '' }}</van-col>
              <van-col span="24"><span class="desc">检验时间：</span>{{ item.cfCheckDate || '' }}</van-col>
              <van-col span="24"><span class="desc">检验结果：</span>
                <span
                  :class="{'green-color':((item.cfCheckResult || '2') === '1') , 'red-color':((item.cfCheckResult || '2') === '2')}">
                  {{ (item.cfCheckResult || '2') === '1' ? '合格' : '不合格' }}
                </span>
              </van-col>
              <van-col span="24"><span class="desc">检验依据：</span>{{ item.cfCheckRecords || '' }}</van-col>
              <van-col span="24">
                <span class="desc">图片视频：</span>
                <van-grid :border="false" :column-num="3">
                  <van-grid-item
                    v-for="attach in item.attachmentList.filter(item => {return item.fileType === '1' || item.fileType === '2'})"
                    :key="attach.fid">
                    <div @click="handleClickAttachment(attach)">
                      <van-image v-if="attach.mimeType.startsWith('image')" width="100" height="100"
                                 :src="'/apiv2/imagePreview?neid=' + attach.lenovoId"/>
                      <van-image v-else-if="attach.mimeType.startsWith('video')" width="100" height="100"
                                 :src="defaultVideoImg"/>
                      <van-image v-else width="100" height="100" :src="defaultImg"/>
                    </div>
                  </van-grid-item>
                </van-grid>
              </van-col>
              <van-col span="24">
                <span class="desc">检验附件：</span>
                <van-grid :border="false" :column-num="3">
                  <van-grid-item v-for="attach in item.attachmentList.filter(item => {return item.fileType === '3'})"
                                 :key="attach.fid">
                    <div @click="handleClickAttachment(attach)">
                      <van-image v-if="attach.mimeType.startsWith('image')" width="100" height="100"
                                 :src="'/apiv2/imagePreview?neid=' + attach.lenovoId"/>
                      <van-image v-else-if="attach.mimeType.startsWith('video')" width="100" height="100"
                                 :src="defaultVideoImg"/>
                      <van-image v-else width="100" height="100" :src="defaultImg"/>
                    </div>
                  </van-grid-item>
                </van-grid>
              </van-col>
            </van-row>
          </van-collapse-item>
        </van-collapse>
      </div>
    </van-list>
    <van-popup v-model="addResultVisible" position="bottom" style="padding-bottom: 18px" closeable>
      <van-nav-bar title="新增点检记录"/>
      <van-form @submit="onSubmitNewCheckResult">
        <van-field name="radio" label="检验结果" required>
          <template #input>
            <van-radio-group v-model="checkResultRadio" direction="horizontal">
              <van-radio name="1">合格</van-radio>
              <van-radio name="2">不合格</van-radio>
              <van-radio name="3">不涉及</van-radio>
            </van-radio-group>
          </template>
        </van-field>
        <van-field v-model="checkStandard" placeholder="请填写检验依据" label="检验依据" :required="currentCheckItem.needText"/>
        <van-field name="uploader" label="图片附件" :required="currentCheckItem.needPicture">
          <template #input>
            <van-uploader v-model="pictureFilePreviewList" :after-read="(file) => afterRead(file,'picture')"
                          accept="image/*"/>
          </template>
        </van-field>
        <van-field name="uploader" label="视频附件" :required="currentCheckItem.needVideo">
          <template #input>
            <van-uploader v-model="videoFilePreviewList" :after-read="(file) => afterRead(file,'video')"
                          accept="video/*"/>
          </template>
        </van-field>
        <van-field name="uploader" label="通用附件" :required="currentCheckItem.needAttachment">
          <template #input>
            <van-uploader v-model="commonFilePreviewList" :after-read="(file) => afterRead(file,'attachment')"
                          accept="all"/>
          </template>
        </van-field>
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
      </van-form>
    </van-popup>
  </div>
</template>

<script>
import {listCheckItemResult, findCheckItem, insertCheckItemResult} from "../api";
import {upload} from "../api/fileUpload";

export default {
  name: "CheckItemResult",
  data() {
    return {

      checkResultRadio: "",
      checkStandard: "",
      pictureFilePreviewList: [],
      videoFilePreviewList: [],
      commonFilePreviewList: [],

      projectId: "",
      orderId: "",
      taskId: "",
      checkItemId: "",

      currentCheckItem: {},

      typeActive: "自检",
      searchKey: "",
      current: 0,
      pageSize: 5,
      checkItemResultList: [],
      searchResultCount: 0,
      searchLoading: false,
      searchHasMore: true,
      activeNames: [],
      addResultVisible: false,

      defaultVideoImg: require("@/assets/video.png"),
      defaultImg: require("@/assets/unknown.png"),
    }
  },
  methods: {
    onLoad() {
      console.log("on load")
      this.listCheckItemResult();
    },
    listCheckItemResult() {
      listCheckItemResult(this.checkItemId, this.current + 1, this.pageSize).then(data => {
        this.checkItemResultList = this.checkItemResultList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;
        this.current = data.pageNum;
      }).finally(() => {
        this.searchLoading = false;
      })
    },
    handleClickCheckItemResult() {

    },
    onSubmitNewCheckResult() {
      if (!this.checkResultRadio) {
        this.$notify({type: 'warning', message: '请填写检验结果！'});
        return;
      }
      if (this.currentCheckItem.needText && !this.checkStandard) {
        this.$notify({type: 'warning', message: '请填写检验依据！'});
        return;
      }
      if (this.currentCheckItem.needPicture && this.pictureFilePreviewList.length <= 0) {
        this.$notify({type: 'warning', message: '请上传图片附件！'});
        return;
      }
      if (this.currentCheckItem.needVideo && this.videoFilePreviewList.length <= 0) {
        this.$notify({type: 'warning', message: '请上传视频附件！'});
        return;
      }
      if (this.currentCheckItem.needAttachment && this.commonFilePreviewList.length <= 0) {
        this.$notify({type: 'warning', message: '请上传通用附件！'});
        return;
      }
      let newCheckResultForm = {

        cfCheckEntryId: this.checkItemId,
        cfCheckResult: this.checkResultRadio,
        cfCheckRecords: this.checkStandard,

        attachmentList: this.pictureFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "1"};
        }).concat(this.videoFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "2"};
        })).concat(this.commonFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "3"};
        }))
      }

      insertCheckItemResult(newCheckResultForm).then(resp => {
        console.log("resp: " + JSON.stringify(resp))
        if (resp.code === '0') {
          this.$notify({type: 'success', message: '新增成功！'});
          this.current = 0;
          this.checkItemResultList = [];

          console.log("do check")
          this.$refs.checkItemResultList.check();
        }
      }).finally(() => {
        this.addResultVisible = false;
      })
      console.log(JSON.stringify(newCheckResultForm));
    },
    afterRead(file, fileType) {
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file).then(uploadResult => {

        file.status = 'done';
        file.message = '上传成功';

        file.neid = uploadResult.neid;
        file.mimeType = uploadResult.mime_type;
      }).finally(() => {
        console.log("upload success");
      })
    },
    onClickAddResult() {
      this.addResultVisible = !this.addResultVisible;
    },
    handleClickAttachment(attachment) {
      console.log("attachment: " + JSON.stringify(attachment))

      let previewUrl = location.protocol + location.host + "/apiv2/imagePreview?neid=" + attachment.lenovoId;
      console.log("preview url: " + previewUrl)
      callNextPlusPreview(attachment.fileName, previewUrl);
    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.orderId = this.$route.query.orderId;
    this.taskId = this.$route.query.taskId;
    this.checkItemId = this.$route.query.checkItemId;
    findCheckItem(this.checkItemId).then(resp => {
      if (resp.code === '0') {
        this.currentCheckItem = resp.obj;
      }
    })
  }
}
</script>

<style scoped>

.red-color {
  color: red;
}

.green-color {
  color: green;
}

</style>

<template>
  <div>
    <van-sticky>
      <van-nav-bar title="点检项操作"/>
      <div style="background-color: #ffffff;">
        <span
          @click="() => {this.$router.back()}"
          style="color: #1989fa;display: flex;align-items: center;width: 80px">
          <van-icon name="arrow-left" size="25px"/>
          <span style="font-size: 16px;color: #1989fa">返回</span>
        </span>
        <van-divider style="margin: 0;">共 {{ searchResultCount }} 条点检记录</van-divider>
      </div>
      <van-button style="width: 100%" type="info" @click="onClickAddResult">新增点检记录</van-button>
    </van-sticky>
    <van-list
      ref="checkItemResultList" v-model="searchLoading" :finished="!searchHasMore"
      finished-text="没有更多了" @load="onLoad">
      <div>
        <van-collapse v-model="activeNames">
          <van-collapse-item :name="item.fid" v-for="item in checkItemResultList" :key="item.fid"
                             @click="handleClickCheckItemResult(item.fid)">
            <template #title>
              <van-row>
                <van-col span="14">
                  <span style="border: medium solid black; padding: 0 2px">{{ getCheckType(item.cfCheckType) }}</span>
                  {{ item.cfCheckDate || '' }}
                </van-col>
                <van-col span="6">{{ item.cfCheckPersonName || '' }}</van-col>
                <van-col span="4">
                  <div class="green-color" v-if="item.cfCheckResult==='1'">合格</div>
                  <div class="red-color" v-if="item.cfCheckResult==='2'">不合格</div>
                  <div class="blue-color" v-if="item.cfCheckResult==='3'">不涉及</div>
                </van-col>
              </van-row>
            </template>
            <van-row>
              <van-col span="24"><span class="desc">检验人：</span>{{ item.cfCheckPersonName || '' }}</van-col>
              <van-col span="24"><span class="desc">检验时间：</span>{{ item.cfCheckDate || '' }}</van-col>
              <van-col span="24"><span class="desc">检验结果：</span>
                <span class="green-color" v-if="item.cfCheckResult==='1'">合格</span>
                <span class="red-color" v-if="item.cfCheckResult==='2'">不合格</span>
                <span class="blue-color" v-if="item.cfCheckResult==='3'">不涉及</span>
              </van-col>
              <van-col span="24"><span class="desc">检验依据：</span>{{ item.cfCheckRecords || '' }}</van-col>
              <van-col span="24">
                <span class="desc">图片视频：</span>
                <van-grid :border="false" :column-num="3">
                  <van-grid-item
                    v-for="attach in item.attachmentList.filter(item => {return item.fileType === '1' || item.fileType === '2'})"
                    :key="attach.fid">
                    <div @click="handleClickAttachment(attach)">
                      <van-image width="100" height="100" :src="'/Thumbnails/default/' + attach.lenovoId + '.jpg'"/>
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
                      <van-image width="100" height="100" :src="'/Thumbnails/default/' + attach.lenovoId + '.jpg'"/>
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
        <van-field v-model="checkStandard" placeholder="请填写检验依据" label="检验依据"
                   :required="currentCheckItem.needText"/>
        <van-field name="uploader" label="图片附件" :required="currentCheckItem.needPicture">
          <template #input>
            <van-uploader v-model="pictureFilePreviewList" :after-read="(file) => afterRead(file,'picture')"
                          accept="image/*" multiple/>
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
import {edoc2Upload, edoc2PreviewData} from "../api/";
import {handleGoToPreview} from "../utils/JoyeaUtil";

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
      toCheckItemType: -1,

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
    }
  },
  methods: {
    onLoad() {
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
      //检查文件是否上传完毕
      let notUploadSuccessFiles = this.pictureFilePreviewList
        .concat(this.videoFilePreviewList)
        .concat(this.commonFilePreviewList)
        .filter(item => {
          return item.status !== "done";
        })
      if (notUploadSuccessFiles.length > 0) {
        this.$notify({type: 'warning', message: '请等待全部文件全部上传完毕后提交！'});
        return;
      }
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
        cfCheckType: this.toCheckItemType,

        attachmentList: this.pictureFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "1"};
        }).concat(this.videoFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "2"};
        })).concat(this.commonFilePreviewList.map(file => {
          return {lenovoId: file.neid, fileName: file.file.name, mimeType: file.mimeType, fileType: "3"};
        }))
      }

      insertCheckItemResult(newCheckResultForm).then(resp => {
        if (resp.code === '0') {
          //重置新增表单
          this.resetAddForm();
          this.$notify({type: 'success', message: '新增成功！', duration: 400});

          this.checkItemResultList = [];

          this.current = 0;
          this.listCheckItemResult();
        }
      }).finally(() => {
        this.addResultVisible = false;
      })
      console.log(JSON.stringify(newCheckResultForm));
    },

    resetAddForm() {
      this.checkResultRadio = "";
      this.checkStandard = "";
      this.pictureFilePreviewList = [];
      this.videoFilePreviewList = [];
      this.commonFilePreviewList = [];
    },

    afterRead(file, fileType) {
      file.status = 'uploading';
      file.message = '上传中...';
      edoc2Upload(file).then(uploadResult => {

        file.status = 'done';
        file.message = '上传成功';

        file.neid = uploadResult.obj.fileId;
        file.mimeType = "edoc2";
      }).finally(() => {
        console.log("upload success");
      })
    },
    onClickAddResult() {
      this.addResultVisible = !this.addResultVisible;
    },
    handleClickAttachment(attachment) {
      let context = this;
      edoc2PreviewData(attachment.lenovoId).then(resp => {
        handleGoToPreview(context, resp.obj.fileName, resp.obj.fileUrl);
      })
    },
    getCheckType(cfCheckType) {
      let checkType = "自";
      switch (cfCheckType) {
        case "1":
          checkType = "自"
          break;
        case "2":
          checkType = "互"
          break;
        case "3":
          checkType = "三"
          break;
      }
      return checkType;
    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.orderId = this.$route.query.orderId;
    this.taskId = this.$route.query.taskId;
    this.checkItemId = this.$route.query.checkItemId;
    this.toCheckItemType = this.$route.query.toCheckItemType;
    findCheckItem(this.checkItemId).then(resp => {
      if (resp.code === '0') {
        this.currentCheckItem = resp.obj;
      }
    })
  },
  beforeRouteLeave(to, from, next) {
    console.log(to);
    if (to.path === '/checkItemResult') {
      to.meta.keepAlive = false;
    } else {
      to.meta.keepAlive = true;
    }
    next();
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

.blue-color {
  color: dodgerblue;
}

</style>

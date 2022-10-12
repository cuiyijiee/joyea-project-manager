<template>
  <div>
    <van-nav-bar left-arrow left-text="返回" title="点检项操作"
                 @click-left="() => {this.$router.push({path:'/checkItem',query:{projectId:projectId,orderId:orderId,taskId:taskId}})}"/>
    <van-divider>共 {{ searchResultCount }} 条点检记录</van-divider>
    <van-button style="width: 100%" type="info">新增点检记录</van-button>
    <van-list
      ref="checkItemResultList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <div v-for="item in checkItemResultList" :key="item.fid"
           @click="handleClickCheckItemResult(item.fid)">
        <van-collapse v-model="activeNames">
          <van-collapse-item name="1">
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
              <van-col span="24"><span class="desc">图片视频：</span>{{ item.processName || '' }}</van-col>
              <van-col span="24"><span class="desc">检验附件：</span>{{ item.processName || '' }}</van-col>
              <van-uploader :after-read="afterRead" />
            </van-row>
          </van-collapse-item>
        </van-collapse>
      </div>
    </van-list>
  </div>
</template>

<script>
import {listCheckItemResult} from "../api";

export default {
  name: "CheckItemResult",
  data() {
    return {
      projectId: "",
      orderId: "",
      taskId: "",
      checkItemId: "",
      typeActive: "自检",
      searchKey: "",
      current: 0,
      pageSize: 5,
      checkItemResultList: [],
      searchResultCount: 0,
      searchLoading: false,
      searchHasMore: true,
      activeNames: []
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
    afterRead(){

    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.orderId = this.$route.query.orderId;
    this.taskId = this.$route.query.taskId;
    this.checkItemId = this.$route.query.checkItemId;
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

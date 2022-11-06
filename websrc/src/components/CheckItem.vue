<template>
  <div>
    <van-nav-bar title="点检项信息"
                 @click-left="() => {this.$router.push({path:'/process',query:{projectId:projectId,orderId:orderId}})}">
      <template #left>
        <van-icon name="arrow-left" size="25px"/>
        <span style="font-size: 16px;color: #1989fa">返回</span>
      </template>
    </van-nav-bar>
    <van-tabs v-model="cfCheckType" color="#1989fa" @change="handleTagChange">
      <van-tab :title="'自检(' + (parseInt(count[0]) + parseInt(count[1]))+')'"></van-tab>
      <van-tab :title="'互检(' + count[1]+')'"></van-tab>
      <van-tab :title="'第三方(' + (parseInt(count[0]) + parseInt(count[1])) +')'"></van-tab>
    </van-tabs>
    <div style="margin: 10px 5px 0 5px;">
      <van-field name="switch" label="展示所有点检项">
        <template #input>
          <van-switch v-model="showAll" size="20" @change="handleShowAll"/>
        </template>
      </van-field>
    </div>
    <van-search v-model="searchKey" placeholder="搜索点检项关键字" @search="onSearch"/>
    <van-divider>共 {{ searchResultCount }} 个结果</van-divider>
    <van-list
      ref="checkItemList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <CheckItemCard v-for="(item,index) in checkItemList" :key="item.fid" :item="item" :index="index"
                     @click.native="handleClickCheckItem(item.fid)">
      </CheckItemCard>
    </van-list>
  </div>
</template>

<script>

import {listCheckItem, findCheckItemCount} from "../api";
import CheckItemCard from "./CheckItemCard";

export default {
  name: "CheckItem",
  components: {
    CheckItemCard
  },
  data() {
    return {
      projectId: "",
      orderId: "",
      taskId: "",
      cfCheckType: 0,
      searchKey: "",
      showAll: false,
      current: 0,
      pageSize: 5,
      checkItemList: [],
      searchResultCount: 0,
      searchLoading: false,
      searchHasMore: true,
      count: [0, 0]
    }
  },
  methods: {
    handleShowAll(event) {
      this.onSearch();
    },
    handleTagChange(name, title) {
      this.onSearch();
    },
    onSearch() {
      this.current = 0;
      this.searchHasMore = true;
      this.checkItemList = [];
      this.$refs.checkItemList.check();
    },
    onLoad() {
      this.listCheckItem();
    },
    listCheckItem() {
      listCheckItem(this.taskId, this.searchKey, this.cfCheckType + 1, this.showAll,
        this.current + 1, this.pageSize).then(data => {
        this.checkItemList = this.checkItemList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;
        this.current = data.pageNum;
      }).finally(() => {
        this.searchLoading = false;
      })
    },
    handleClickCheckItem(fid) {
      this.$router.push({
        path: "/checkItemResult",
        query: {
          orderId: this.orderId,
          projectId: this.projectId,
          taskId: this.taskId,
          checkItemId: fid,
          toCheckItemType: this.cfCheckType + 1
        }
      })
    },
    listCount() {
      findCheckItemCount(this.taskId).then(resp => {
        if (resp.code === '0') {
          this.count = resp.obj.split("_");
        }
      })
    }
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.orderId = this.$route.query.orderId;
    this.taskId = this.$route.query.taskId;
    this.listCount();
  }
}
</script>

<style scoped>

</style>

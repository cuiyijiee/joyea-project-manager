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
    <div style="margin: 10px 5px 0 15px;align-content: center">
      <van-radio-group v-model="showAll" direction="horizontal" @change="handleShowAll">
        <van-radio name="0">未完成</van-radio>
        <van-radio name="1">展示所有点检项</van-radio>
      </van-radio-group>
    </div>
    <!--    <van-search v-model="searchKey" placeholder="搜索点检项关键字" @search="onSearch"/>-->
    <my-search-input placeholder="搜索点检项关键字"
                     cache-key="CACHE_CHECKITEM_SEARCH_HISTORY"
                     @onSearch="onSearch"/>
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
import MySearchInput from "./MySearchInput";

export default {
  name: "CheckItem",
  components: {
    CheckItemCard, MySearchInput
  },
  data() {
    return {
      projectId: "",
      orderId: "",
      taskId: "",
      cfCheckType: 0,
      searchKey: "",
      showAll: '0',
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
    onSearch(searchKey) {
      this.searchKey = searchKey;
      this.current = 0;
      this.searchHasMore = true;
      this.checkItemList = [];
      this.$refs.checkItemList.check();
    },
    onLoad() {
      this.listCheckItem();
    },
    listCheckItem() {
      listCheckItem(this.taskId, this.searchKey, this.cfCheckType + 1, this.showAll === '1',
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
      localStorage.setItem("CACHE_CHECKITEM_SEARCHKEY", this.searchKey)
      localStorage.setItem("CACHE_CHECKITEM_SHOWALL", this.showAll)
      localStorage.setItem("CACHE_CHECKITEM_CHECKTYPE", this.cfCheckType)

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

    let searchKey = localStorage.getItem("CACHE_CHECKITEM_SEARCHKEY")
    if (searchKey !== undefined) {
      this.searchKey = searchKey;
    }
    let showAll = localStorage.getItem("CACHE_CHECKITEM_SHOWALL")
    if (showAll !== undefined) {
      this.showAll = showAll;
    }
    let checkType = localStorage.getItem("CACHE_CHECKITEM_CHECKTYPE")
    if (checkType !== undefined) {
      this.cfCheckType = parseInt(checkType);
    }
    this.listCount();
  }
}
</script>

<style scoped>

</style>

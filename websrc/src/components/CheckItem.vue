<template>
  <div>
    <van-sticky>
      <van-nav-bar title="点检项信息"/>
      <van-tabs v-model="cfCheckType" color="#1989fa" @change="handleTagChange">
        <van-tab :title="'自检(' + (parseInt(count[0]) + parseInt(count[1]))+')'"></van-tab>
        <van-tab :title="'互检(' + count[1]+')'"></van-tab>
        <van-tab :title="'第三方(' + (parseInt(count[0]) + parseInt(count[1])) +')'"></van-tab>
        <van-tab :title="'第三方专检(' + count[2] +')'"></van-tab>
      </van-tabs>
      <div style="padding: 10px 5px 0 15px;align-content: center;background-color: #ffffff">
        <van-radio-group v-model="showAll" direction="horizontal" @change="handleShowAll">
          <van-radio name="1">未完成</van-radio>
          <van-radio name="2">展示所有点检项</van-radio>
        </van-radio-group>
      </div>
      <my-search-input placeholder="搜索点检项关键字"
                       cache-key="CACHE_CHECKITEM_SEARCH_HISTORY"
                       @onSearch="onSearch"/>
      <div style="background-color: #ffffff;">
        <span @click="() => {this.$router.back()}"
              style="color: #1989fa;display: flex;align-items: center;width: 80px">
          <van-icon name="arrow-left" size="25px"/>
          <span style="font-size: 16px;color: #1989fa">返回</span>
        </span>
        <van-divider style="margin: 0;">共 {{ searchResultCount }} 个结果</van-divider>
      </div>
    </van-sticky>
    <van-list
      ref="checkItemList"
      v-model="searchLoading"
      :finished="!searchHasMore"
      finished-text="没有更多了"
      @load="onLoad">
      <CheckItemCard
        :id="'checkItem_' + item.fid"
        v-for="(item,index) in checkItemList" :key="item.fid" :item="item" :index="index"
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
      showAll: "1",
      current: 0,
      pageSize: 5,
      checkItemList: [],
      searchResultCount: 0,
      searchLoading: false,
      searchHasMore: true,
      count: [0, 0, 0],
      isLoadCached: false
    }
  },
  methods: {
    handleShowAll(event) {
      this.onSearch("");
    },
    handleTagChange(name, title) {
      this.onSearch("");
    },
    onSearch(searchKey) {
      this.searchKey = searchKey;
      this.current = 0;
      this.searchHasMore = true;
      this.checkItemList = [];
      this.$refs.checkItemList.check();
    },
    onLoad() {
      let cachedTaskId = localStorage.getItem("checkItem.taskId");
      if (!this.isLoadCached && cachedTaskId === this.taskId) {
        this.current = localStorage.getItem("checkItem.current");
        this.searchResultCount = localStorage.getItem("checkItem.total");
        this.checkItemList = JSON.parse(localStorage.getItem("checkItem.list"));
        let toLocalIndex = localStorage.getItem("checkItem.index");
        this.$nextTick(() => {
          document.getElementById("checkItem_" + toLocalIndex).scrollIntoView();
          this.isLoadCached = true;
        });
        this.searchLoading = false;
      } else {
        this.listCheckItem();
      }
    },
    listCheckItem() {
      listCheckItem(this.taskId,
        (this.searchKey),
        this.cfCheckType + 1,
        this.showAll === '2',
        this.current + 1, this.pageSize).then(data => {
        this.checkItemList = this.checkItemList.concat(data.list);
        this.searchResultCount = data.total;
        this.searchHasMore = data.hasMore;
        this.current = data.pageNum;
      }).finally(() => {

        localStorage.setItem("CACHE_CHECKITEM_SEARCHKEY", this.searchKey)
        localStorage.setItem("CACHE_CHECKITEM_SHOWALL", this.showAll)
        localStorage.setItem("CACHE_CHECKITEM_CHECKTYPE", this.cfCheckType)

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

    let searchKey = localStorage.getItem("CACHE_CHECKITEM_SEARCHKEY")
    if (searchKey !== undefined && searchKey !== null && searchKey !== 'undefined' && searchKey !== 'null') {
      this.searchKey = searchKey;
    } else {
      this.searchKey = "";
    }
    let showAll = localStorage.getItem("CACHE_CHECKITEM_SHOWALL")
    if (showAll !== undefined && showAll !== null && showAll !== 'undefined' && showAll !== 'null') {
      this.showAll = showAll;
    } else {
      this.showAll = '1';
    }
    let checkType = localStorage.getItem("CACHE_CHECKITEM_CHECKTYPE")
    if (checkType !== undefined && checkType !== null && checkType !== 'undefined' && checkType !== 'null') {
      this.cfCheckType = parseInt(checkType);
    } else {
      this.cfCheckType = 0;
    }
    this.listCount();
  },
  beforeRouteLeave(to, from, next) {
    if (to.path === '/checkItemResult') {
      localStorage.setItem("checkItem.current", this.current);
      localStorage.setItem("checkItem.total", this.searchResultCount);
      localStorage.setItem("checkItem.list", JSON.stringify(this.checkItemList));
      localStorage.setItem("checkItem.index", to.query.checkItemId);
      localStorage.setItem("checkItem.taskId", this.taskId);
    }
    next();
  }
}
</script>

<style scoped>

</style>
